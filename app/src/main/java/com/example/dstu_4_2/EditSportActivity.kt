package com.example.dstu_4_2

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.dstu_4_2.databinding.ActivityEditSportBinding
import com.example.dstu_4_2.db.DatabaseHelper

class EditSportActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditSportBinding
    private lateinit var databaseHelper: DatabaseHelper
    private var sportId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditSportBinding.inflate(layoutInflater)
        setContentView(binding.root)

        databaseHelper = DatabaseHelper(this)

        // Получаем данные из Intent
        sportId = intent.getIntExtra("SPORT_ID", 0)
        val sportName = intent.getStringExtra("SPORT_NAME")
        binding.sportNameEditText.setText(sportName)

        binding.saveButton.setOnClickListener {
            val updatedSportName = binding.sportNameEditText.text.toString()
            if (updatedSportName.isNotEmpty()) {
                updateSportInDatabase(updatedSportName)
                Toast.makeText(this, "Вид спорта обновлен", Toast.LENGTH_SHORT).show()
                val intent = Intent().apply {
                    action = "com.example.dstu_4_2.SPORT_UPDATED"
                }
                sendBroadcast(intent)
                finish()
            } else {
                Toast.makeText(this, "Введите название вида спорта", Toast.LENGTH_SHORT).show()
            }
        }

        binding.cancelButton.setOnClickListener {
            finish()
        }
    }

    private fun updateSportInDatabase(updatedName: String) {
        val db = databaseHelper.writableDatabase
        val values = android.content.ContentValues().apply {
            put(DatabaseHelper.COLUMN_NAME, updatedName)
        }
        db.update(DatabaseHelper.TABLE_SPORTS, values, "${DatabaseHelper.COLUMN_ID}=?", arrayOf(sportId.toString()))
    }
}