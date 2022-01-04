package com.example.smartshoppingapp.model

data class CartListResponse(
    val products: List<Product>,
    val status: String
)