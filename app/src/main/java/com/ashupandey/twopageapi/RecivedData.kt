package com.ashupandey.twopageapi

data class RecivedData(
    val `data`: List<Data>,
    val page: Int,
    val per_page: Int,
    val support: SupportX,
    val total: Int,
    val total_pages: Int
)