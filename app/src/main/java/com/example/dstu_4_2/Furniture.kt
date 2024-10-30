package com.example.dstu_4_2

class Furniture(
    productId: Int,
    name: String,
    basePrice: Double,
    private val material: String, // Материал мебели (например, дерево, металл)
    private val dimensions: String // Размеры мебели (например, "200x100x50 см")
) : Product(productId, name, basePrice) {

    fun getMaterial(): String = material
    fun getDimensions(): String = dimensions
}
