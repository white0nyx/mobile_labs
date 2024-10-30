package com.example.dstu_4_2

open class Product(
    private val productId: Int,
    private var name: String,
    protected var basePrice: Double // Изменено имя поля с `price` на `basePrice`
) {
    // Геттеры для полей
    fun getProductId(): Int = productId
    fun getName(): String = name
    fun getPrice(): Double = basePrice

    // Сеттер только для имени
    fun setName(newName: String) {
        name = newName
    }

    // Метод для изменения цены
    fun changePrice(newPrice: Double) {
        if (newPrice > 0) {
            basePrice = newPrice
        }
    }
}
