package com.example.dstu_4_2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.example.dstu_4_2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Добавляем обработчик для кнопки "Рассчитать стоимость"
        binding.buttonCalculate.setOnClickListener {
            calculateFare()
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

                binding.textViewResult.text = "Стоимость: $totalCost руб."
            } else {
                Toast.makeText(this, "Введите корректные значения!", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(this, "Заполните все поля!", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> {
                Toast.makeText(this, "Settings clicked", Toast.LENGTH_SHORT).show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
