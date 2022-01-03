package com.example.smartshoppingapp.model

data class LoginResponse(
    val msg: String,
    val status: String,
    val token: String,
    val user_id: Int
)