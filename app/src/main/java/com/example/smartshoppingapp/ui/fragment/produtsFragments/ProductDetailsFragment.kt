package com.example.smartshoppingapp.ui.fragment.produtsFragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.smartshoppingapp.AppUtils.SharedHelper
import com.example.smartshoppingapp.dataRepository.RemoteDataRepo
import com.example.smartshoppingapp.databinding.FragmentProductDetailsBinding
import com.example.smartshoppingapp.model.CartModel
import com.example.smartshoppingapp.productDetail
import com.example.smartshoppingapp.viewModels.RegistrationViewModel
import com.example.smartshoppingapp.viewModels.ViewModelProviderFactory
import com.squareup.picasso.Picasso


class ProductDetailsFragment : Fragment() {

    var _binding: FragmentProductDetailsBinding? = null
    val binding get() = _binding!!
    var count = 1
    lateinit var viewModel: RegistrationViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        _binding = FragmentProductDetailsBinding.inflate(layoutInflater)

        Log.e("TAG", "onCreateView product detail:  $productDetail")

        val repo = RemoteDataRepo(requireContext())
        val viewModelProviderFactory = ViewModelProviderFactory(repo)
        viewModel = ViewModelProvider(
            requireActivity(),
            viewModelProviderFactory
        ).get(RegistrationViewModel::class.java)


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var totalamount = productDetail.discount_price

        binding.apply {
            this.productName.text = productDetail.product_name
            this.tvInstock.text = productDetail.instock
            this.orignalpricetv.text = "Orignal Price :£ ${productDetail.original_price}"
            this.discountPriceTv.text = "Discount Price :£ ${productDetail.discount_price}"
            this.tvProductdescription.text = productDetail.description
            Picasso.get().load(productDetail.image).into(this.imgProduct)

            this.btnIncrement.setOnClickListener {
                count++

                totalamount+= productDetail.discount_price*count

                this.countTv.text = "$count"
                this.totalAmountTv.text = "$totalamount"
            }

            this.btnDecrement.setOnClickListener {
                count--

                if (count <= 1) {
                    this.countTv.text = "1"
                    this.totalAmountTv.text = "${productDetail.discount_price}"
                    return@setOnClickListener
                } else {
                    totalamount -= productDetail.discount_price

                    this.countTv.text = "$count"
                    this.totalAmountTv.text = "$totalamount"

                }
            }

            this.btnAddtoCart.setOnClickListener {

                val userID = SharedHelper.getKey(requireContext(), "userID")
                val cartModel = CartModel(
                    productDetail.id.toString(),
                    userID.toString(),
                    productDetail.user_id.toString(),
                    totalamount.toString()
                )
                viewModel.addToCart(cartModel)
                Toast.makeText(requireContext(), "Added to cart", Toast.LENGTH_SHORT).show()
            }

        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}