package com.example.dstu_4_2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.example.dstu_4_2.databinding.ActivityMainBinding
import java.io.File
import java.io.FileOutputStream
import java.io.FileInputStream

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val fileName = "fare_history.txt"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Добавляем обработчик для кнопки "Рассчитать стоимость"
        binding.buttonCalculate.setOnClickListener {
            calculateFare()
        }

        // Добавляем обработчик для кнопки "История заказов"
        binding.buttonShowHistory.setOnClickListener {
            showHistory()
        }
    }

    // Функция расчета стоимости проезда
    private fun calculateFare() {
        val distanceText = binding.editTextDistance.text.toString()
        val timeText = binding.editTextTime.text.toString()

        if (distanceText.isNotEmpty() && timeText.isNotEmpty()) {
            val distance = distanceText.toDoubleOrNull()
            val time = timeText.toDoubleOrNull()

            if (distance != null && time != null) {
                val costBase = 50.0
                val costPerKm = 10.0
                val costPerMinute = 2.0

                val totalCost = costBase + (distance * costPerKm) + (time * costPerMinute)
                val resultText = "Стоимость: $totalCost руб."

                binding.textViewResult.text = resultText
                saveToHistory(resultText)
            } else {
                Toast.makeText(this, "Введите корректные значения!", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(this, "Заполните все поля!", Toast.LENGTH_SHORT).show()
        }
    }

    // Сохранение результата в файл
    private fun saveToHistory(result: String) {
        try {
            val fileOutputStream: FileOutputStream = openFileOutput(fileName, MODE_APPEND)
            fileOutputStream.write((result + "\n").toByteArray())
            fileOutputStream.close()
        } catch (e: Exception) {
            e.printStackTrace()
            Toast.makeText(this, "Ошибка сохранения в файл!", Toast.LENGTH_SHORT).show()
        }
    }

    // Показать историю расчетов
    private fun showHistory() {
        try {
            val fileInputStream: FileInputStream = openFileInput(fileName)
            val history = fileInputStream.bufferedReader().use { it.readText() }
            fileInputStream.close()
            binding.textViewHistory.text = history
        } catch (e: Exception) {
            e.printStackTrace()
            Toast.makeText(this, "Ошибка чтения из файла!", Toast.LENGTH_SHORT).show()
        }
    }
}