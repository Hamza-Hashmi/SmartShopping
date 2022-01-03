package com.example.smartshoppingapp.ui.fragment.dashboardFragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.smartshoppingapp.Adapter.ShopsAdapter
import com.example.smartshoppingapp.R
import com.example.smartshoppingapp.databinding.FragmentHomeBinding
import com.example.smartshoppingapp.model.ShoplistData
import com.example.smartshoppingapp.shopId
import com.example.smartshoppingapp.ui.activites.DashBoardActivity
import com.example.smartshoppingapp.viewModels.RegistrationViewModel


class HomeFragment : Fragment() {

    var _binding: FragmentHomeBinding? = null
    val binding get() = _binding!!
    lateinit var shopsAdapter: ShopsAdapter
    lateinit var viewModel: RegistrationViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(layoutInflater)
        var layoutManager = GridLayoutManager(requireContext(), 2)

        viewModel = (activity as DashBoardActivity).viewModel

        binding.progressbar.visibility = View.VISIBLE
        viewModel.getShopList()
        binding.recyclerviewItems.layoutManager = layoutManager

        viewModel._shopList.observe(viewLifecycleOwner, {
            shopsAdapter =
                ShopsAdapter(requireContext(), it.body()?.data as ArrayList<ShoplistData>) {
                    Log.e("TAG", "onCreateView: ${it}")
                    shopId = it
                    findNavController().navigate(R.id.action_homeFragment_to_shopProductsFragment)
                }

            binding.progressbar.visibility = View.GONE
            binding.recyclerviewItems.adapter = shopsAdapter
        })

        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}