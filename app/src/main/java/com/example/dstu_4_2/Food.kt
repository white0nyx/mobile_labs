package com.example.dstu_4_2

class Food(
    productId: Int,
    name: String,
    basePrice: Double,
    private val expirationDate: String // Срок годности (например, "2024-12-31")
) : Product(productId, name, basePrice) {

    fun getExpirationDate(): String = expirationDate
}
