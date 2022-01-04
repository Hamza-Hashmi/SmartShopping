package com.example.smartshoppingapp.ui.fragment.dashboardFragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.smartshoppingapp.Adapter.CartListAdapter
import com.example.smartshoppingapp.AppUtils.SharedHelper
import com.example.smartshoppingapp.databinding.FragmentCartBinding
import com.example.smartshoppingapp.model.Product
import com.example.smartshoppingapp.ui.activites.DashBoardActivity
import com.example.smartshoppingapp.viewModels.RegistrationViewModel

class CartFragment : Fragment() {
    lateinit var cartListAdapter: CartListAdapter
    lateinit var viewModel: RegistrationViewModel
    var _binding: FragmentCartBinding? = null

    val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    @SuppressLint("SetTextI18n", "NotifyDataSetChanged")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCartBinding.inflate(layoutInflater)


        val userId = SharedHelper.getKey(requireActivity(), "userID")
        viewModel = (activity as DashBoardActivity).viewModel
        if (userId != null) {
            viewModel.getCartListData(userId.toInt())

            viewModel._cartList.observe(viewLifecycleOwner, {

                Log.e("TAG", "onCreateView:cart list ${userId.toString()}")
                cartListAdapter = CartListAdapter(
                    requireContext(),
                    it.body()?.products as ArrayList<Product>
                )
                val range = cartListAdapter.cartList.size
                var total = 0
                for (i: Int in 0 until range) {
                    val count = cartListAdapter.cartList[i].total.toInt()
                    total += count
                }

                binding.totalAmountTv.text = "Place Order Total:\u00a3 $total"
                binding.recyclerviewItems.adapter = cartListAdapter
            })

            binding.btnPlaceOrder.setOnClickListener {
                binding.progbar.visibility = View.VISIBLE
                viewModel.placeOrder(userId.toInt())
                viewModel._order.observe(viewLifecycleOwner, {
                    val response = it.body()?.status
                    Log.e("TAG", "onCreateView: ${it.body()?.status}")
                    if (response.equals("success")) {
                        binding.progbar.visibility = View.GONE
                        if (cartListAdapter.cartList.size > 0) {
                            cartListAdapter.cartList.clear()
                            cartListAdapter.notifyDataSetChanged()
                            binding.totalAmountTv.text = "Place Order"

                        }
                        /* val ft = childFragmentManager.beginTransaction()
                         if (Build.VERSION.SDK_INT >= 26) {
                             ft.setReorderingAllowed(false)
                         }
                         ft.detach(this).attach(this).commit()*/

                        Toast.makeText(
                            requireContext(),
                            "Your order has been placed",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        binding.progbar.visibility = View.GONE
                        Toast.makeText(requireContext(), "Error!", Toast.LENGTH_SHORT).show()
                    }
                })
            }
        }


        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}