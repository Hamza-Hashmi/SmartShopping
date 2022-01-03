package com.example.smartshoppingapp.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.smartshoppingapp.Adapter.ShopsAdapter.ShopsViewHolder
import com.example.smartshoppingapp.databinding.CustomShopsLayoutBinding
import com.example.smartshoppingapp.model.ShoplistData
import com.squareup.picasso.Picasso

class ShopsAdapter(val context: Context, val shoplist: ArrayList<ShoplistData>) :
    RecyclerView.Adapter<ShopsViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopsViewHolder {
        return ShopsViewHolder(
            CustomShopsLayoutBinding.inflate(
                LayoutInflater.from(context),
                parent,
                false
            )
        )
    }


    override fun onBindViewHolder(holder: ShopsViewHolder, position: Int) {
        val pos = shoplist[position]

        holder.binding.tvShopName?.text = shoplist[position].shop_name
        holder.binding.tvLocation?.text = shoplist[position].address

        Picasso.get().load(pos.image).into(holder.binding.imgShop)

        Toast.makeText(context, shoplist[position].image, Toast.LENGTH_SHORT).show()

    }

    override fun getItemCount(): Int {
        return shoplist.size
    }

    class ShopsViewHolder(val binding: CustomShopsLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }
}