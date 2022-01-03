package com.example.smartshoppingapp

import com.example.smartshoppingapp.model.Data

var shopId = -1
lateinit var productDetail:Data

fun isEmailValid(email: String): Boolean = android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
