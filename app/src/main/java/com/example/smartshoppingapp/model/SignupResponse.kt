package com.example.smartshoppingapp.model

import com.google.gson.annotations.SerializedName

data class SignupResponse(
    @SerializedName("message")
    val msg: String,
    @SerializedName("status")
    val status: String
)