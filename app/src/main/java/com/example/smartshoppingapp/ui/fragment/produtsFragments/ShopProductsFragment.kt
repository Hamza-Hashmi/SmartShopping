package com.example.smartshoppingapp.ui.fragment.produtsFragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.smartshoppingapp.Adapter.ProductsAdapter
import com.example.smartshoppingapp.MainActivity
import com.example.smartshoppingapp.R
import com.example.smartshoppingapp.databinding.FragmentShopProductsBinding
import com.example.smartshoppingapp.model.Data
import com.example.smartshoppingapp.productDetail
import com.example.smartshoppingapp.shopId
import com.example.smartshoppingapp.ui.activites.DashBoardActivity
import com.example.smartshoppingapp.viewModels.RegistrationViewModel

class ShopProductsFragment : Fragment() {

    var _binding:FragmentShopProductsBinding? = null
    val binding get() = _binding!!
    lateinit var viewModel:RegistrationViewModel
    lateinit var adapter : ProductsAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentShopProductsBinding.inflate(layoutInflater)

        viewModel = (activity as DashBoardActivity).viewModel

        binding.progressbar.visibility = View.VISIBLE


            viewModel.getShopProductList(shopId)

            Log.e("TAG", "onCreateView shopId: $shopId")

        viewModel._shopProductList.observe(viewLifecycleOwner, Observer {
            Log.e("TAG", "onCreateView observer: ${it.body()?.data}" )
            binding.progressbar.visibility  = View.GONE
            adapter = ProductsAdapter(it.body()?.data as ArrayList<Data>,{
                Log.e("TAG", "onCreateView: ${it}" )
                productDetail = it

                findNavController().navigate(R.id.action_shopProductsFragment_to_productDetailsFragment)

            })

            binding.productsRv.adapter = adapter
            adapter.notifyDataSetChanged()
        })

        return binding.root
    }


}