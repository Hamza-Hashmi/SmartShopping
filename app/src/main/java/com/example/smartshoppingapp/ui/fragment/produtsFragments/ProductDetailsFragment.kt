package com.example.smartshoppingapp.ui.fragment.produtsFragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.smartshoppingapp.R
import com.example.smartshoppingapp.databinding.FragmentProductDetailsBinding
import com.example.smartshoppingapp.productDetail
import com.squareup.picasso.Picasso


class ProductDetailsFragment : Fragment() {

    var _binding:FragmentProductDetailsBinding? = null
    val binding get() = _binding!!
    var count = 1
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        _binding = FragmentProductDetailsBinding.inflate(layoutInflater)

        Log.e("TAG", "onCreateView product detail:  $productDetail" )


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var totalamount = productDetail.discount_price

        binding.apply {
            this.productName.text = productDetail.product_name
            this.tvInstock.text = productDetail.instock
            this.orignalpricetv.text = "Orignal Price : ${productDetail.original_price}"
            this.discountPriceTv.text = "Discount Price : ${productDetail.discount_price}"
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

                if (count <= 1){
                    this.countTv.text = "1"
                    this.totalAmountTv.text = "${productDetail.discount_price}"
                    return@setOnClickListener
                }else{
                    totalamount-= productDetail.discount_price

                    this.countTv.text = "$count"
                    this.totalAmountTv.text = "$totalamount"

                }
            }

        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}