package com.example.smartshoppingapp

var shopId = -1

fun isEmailValid(email: String): Boolean = android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
