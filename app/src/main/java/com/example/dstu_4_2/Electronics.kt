package com.example.dstu_4_2

class Electronics(
    productId: Int,
    name: String,
    basePrice: Double,
    private val warrantyPeriod: Int // Срок гарантии в месяцах
) : Product(productId, name, basePrice) {

    fun getWarrantyPeriod(): Int = warrantyPeriod
}
