package com.example.dstu_4_2

class Clothing(
    productId: Int,
    name: String,
    basePrice: Double,
    private val size: String, // Размер одежды (например, S, M, L)
    private val material: String // Материал (например, хлопок, полиэстер)
) : Product(productId, name, basePrice) {

    fun getSize(): String = size
    fun getMaterial(): String = material
}
