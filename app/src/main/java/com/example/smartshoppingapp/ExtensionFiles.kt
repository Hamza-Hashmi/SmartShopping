package com.example.smartshoppingapp

fun isEmailValid(email: String): Boolean = android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
