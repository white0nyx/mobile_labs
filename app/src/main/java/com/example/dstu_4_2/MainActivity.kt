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

        // Обработка нажатия на кнопку
        binding.button.setOnClickListener {
            handleButtonClick()
        }

        // Обработка нажатия на чекбокс
        binding.checkBox.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                Toast.makeText(this, "Чекбокс выбран", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Чекбокс снят", Toast.LENGTH_SHORT).show()
            }
        }

        // Обработка нажатия на переключатель
        binding.switchButton.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                Toast.makeText(this, "Переключатель включен", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Переключатель выключен", Toast.LENGTH_SHORT).show()
            }
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

    private fun handleButtonClick() {
        Toast.makeText(this, "Button clicked!", Toast.LENGTH_SHORT).show()
    }
}
