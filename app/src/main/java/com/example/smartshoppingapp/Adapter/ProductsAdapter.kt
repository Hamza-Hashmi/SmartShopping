package com.example.smartshoppingapp.Adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.smartshoppingapp.databinding.CustomItemLayoutBinding
import com.example.smartshoppingapp.model.Data
import com.squareup.picasso.Picasso

class ProductsAdapter(val shoplist: ArrayList<Data>, var callBack: (data: Data) -> Unit):RecyclerView.Adapter<ProductsAdapter.MyViewHolder>() {

    inner class MyViewHolder(val binding:CustomItemLayoutBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(CustomItemLayoutBinding.inflate(LayoutInflater.from(parent.context)))
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        var productDetail = shoplist[position]
        holder.binding.tvItemName.text = productDetail.product_name
        holder.binding.orignalpricetv.text =
            "Orignal Price :£ ${productDetail.original_price.toString()}"
        holder.binding.pricetv.text =
            "Discount Price :£ ${productDetail.discount_price.toString()}"
        Picasso.get().load(productDetail.image).into(holder.binding.imgPlaceholder)
        holder.itemView.setOnClickListener {
            callBack(productDetail)
        }
    }

    override fun getItemCount(): Int {
       return shoplist.size
    }
}