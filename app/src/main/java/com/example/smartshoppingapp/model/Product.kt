package com.example.smartshoppingapp.model

data class Product(
    val created_at: String,
    val id: Int,
    val product: ProductX,
    val product_id: Int,
    val shop_id: Int,
    val total: String,
    val updated_at: String,
    val user_id: Int
)