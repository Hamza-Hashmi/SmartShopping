package com.example.smartshoppingapp.model

data class ShopListResponse(
    public val `data`: List<ShoplistData>,
    public val status: String
)