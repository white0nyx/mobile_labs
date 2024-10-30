package com.example.dstu_4_2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Создание объектов классов и их заполнение
        val tv = Electronics(101, "Телевизор Samsung", 49999.99, 24)
        val shirt = Clothing(202, "Рубашка мужская", 1999.99, "L", "Хлопок")
        val apple = Food(303, "Яблоко", 29.99, "2024-01-01")
        val chair = Furniture(404, "Стул деревянный", 3499.99, "Дерево", "45x45x90 см")

        // Отображение данных объектов
        printProduct(tv)
        printProduct(shirt)
        printProduct(apple)
        printProduct(chair)
    }

    // Метод отображения данных объекта Product
    private fun printProduct(product: Product) {
        println("Product ID: ${product.getProductId()}")
        println("Product Name: ${product.getName()}")
        println("Product Price: ${product.getPrice()}")
        when (product) {
            is Electronics -> println("Warranty Period: ${product.getWarrantyPeriod()} months")
            is Clothing -> {
                println("Size: ${product.getSize()}")
                println("Material: ${product.getMaterial()}")
            }
            is Food -> println("Expiration Date: ${product.getExpirationDate()}")
            is Furniture -> {
                println("Material: ${product.getMaterial()}")
                println("Dimensions: ${product.getDimensions()}")
            }
        }
        println("--------------------------------------------------")
    }
}
