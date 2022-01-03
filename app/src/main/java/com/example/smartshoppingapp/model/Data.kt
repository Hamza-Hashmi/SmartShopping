package com.example.smartshoppingapp.model

data class Data(
    val created_at: String,
    val description: String,
    val discount_price: Int,
    val id: Int,
    val image: String,
    val instock: String,
    val original_price: Int,
    val product_name: String,
    val status: String,
    val updated_at: String,
    val user_id: Int
)