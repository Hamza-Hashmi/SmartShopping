package com.example.smartshoppingapp.Adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.smartshoppingapp.Adapter.ShopsAdapter.ShopsViewHolder
import com.example.smartshoppingapp.databinding.CustomShopsLayoutBinding
import com.example.smartshoppingapp.model.ShoplistData
import com.squareup.picasso.Picasso

class ShopsAdapter(
    val context: Context,
    val shoplist: ArrayList<ShoplistData>,
    var callBack: (pos: Int) -> Unit
) :
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

        holder.binding.tvShopName.text = shoplist[position].shop_name
        holder.binding.tvLocation.text = shoplist[position].address

        Log.e("TAG", "onBindViewHolder: ${shoplist[position].image}" )
        //Picasso.get().load(shoplist[position].image).into(holder.binding.imgShop)
        Glide.with(holder.binding.imgShop.context).load(shoplist[position].image).into(holder.binding.imgShop);
        holder.itemView.setOnClickListener {
            callBack(shoplist[position].id)
        }
    }

    override fun getItemCount(): Int {
        return shoplist.size
    }

    class ShopsViewHolder(val binding: CustomShopsLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }
}