package com.example.dstu_4_2

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.dstu_4_2.databinding.ActivityAddSportBinding
import com.example.dstu_4_2.db.DatabaseHelper
import com.example.dstu_4_2.models.Sport

class AddSportActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddSportBinding
    private lateinit var databaseHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddSportBinding.inflate(layoutInflater)
        setContentView(binding.root)

        databaseHelper = DatabaseHelper(this)

        binding.saveButton.setOnClickListener {
            val sportName = binding.sportNameEditText.text.toString()
            if (sportName.isNotEmpty()) {
                val sport = Sport(0, sportName)
                addSportToDatabase(sport)
                Toast.makeText(this, "Вид спорта добавлен", Toast.LENGTH_SHORT).show()
                val intent = Intent().apply {
                    action = "com.example.dstu_4_2.SPORT_ADDED"
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

    private fun addSportToDatabase(sport: Sport) {
        val db = databaseHelper.writableDatabase
        val values = android.content.ContentValues().apply {
            put(DatabaseHelper.COLUMN_NAME, sport.name)
        }
        db.insert(DatabaseHelper.TABLE_SPORTS, null, values)
    }
}