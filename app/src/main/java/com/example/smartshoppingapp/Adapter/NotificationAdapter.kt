package com.example.smartshoppingapp.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.smartshoppingapp.databinding.CustomNotificationLayoutBinding
import com.example.smartshoppingapp.model.DataX

class NotificationAdapter( val cartList: ArrayList<DataX>) :
    RecyclerView.Adapter<NotificationAdapter.CartViewHolder>() {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ):CartViewHolder {
        return CartViewHolder(
            CustomNotificationLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }


    override fun getItemCount(): Int {
        return cartList.size
    }

    class CartViewHolder(val binding: CustomNotificationLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        holder.binding.apply {
            val data  = cartList[position]
            this.tvDescription.text = data.data
        }
    }

}