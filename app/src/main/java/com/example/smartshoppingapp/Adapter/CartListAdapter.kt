package com.example.smartshoppingapp.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.smartshoppingapp.databinding.CustomCartLayoutBinding
import com.example.smartshoppingapp.model.Product
import com.squareup.picasso.Picasso

class CartListAdapter(val context: Context, val cartList: ArrayList<Product>) :
    RecyclerView.Adapter<CartListAdapter.CartViewHolder>() {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CartListAdapter.CartViewHolder {
        return CartViewHolder(
            CustomCartLayoutBinding.inflate(LayoutInflater.from(context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: CartListAdapter.CartViewHolder, position: Int) {
        val imgPath = cartList[position].product.image

        holder.binding.tvProductName.text = cartList[position].product.product_name
        holder.binding.tvitemPrice.text = "£ " + cartList[position].total

        Picasso.get().load(imgPath).into(holder.binding.imgProduct)

    }

    override fun getItemCount(): Int {
        return cartList.size
    }

    class CartViewHolder(val binding: CustomCartLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }

}