package com.example.dstu_4_2

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.dstu_4_2.databinding.ActivityEditParticipantBinding
import com.example.dstu_4_2.db.DatabaseHelper
import com.example.dstu_4_2.models.Participant
import com.example.dstu_4_2.models.Sport

class EditParticipantActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditParticipantBinding
    private lateinit var databaseHelper: DatabaseHelper
    private lateinit var sports: List<Sport>
    private var participantId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditParticipantBinding.inflate(layoutInflater)
        setContentView(binding.root)

        databaseHelper = DatabaseHelper(this)
        sports = databaseHelper.getAllSports()

        // Получаем данные из Intent
        participantId = intent.getIntExtra("PARTICIPANT_ID", 0)
        val participantName = intent.getStringExtra("PARTICIPANT_NAME")
        val participantAge = intent.getIntExtra("PARTICIPANT_AGE", 0)
        val participantSport = intent.getStringExtra("PARTICIPANT_SPORT")

        // Устанавливаем данные в поля
        binding.participantNameEditText.setText(participantName)
        binding.participantAgeEditText.setText(participantAge.toString())
        setupSportSpinner(participantSport)

        binding.saveButton.setOnClickListener {
            val updatedName = binding.participantNameEditText.text.toString()
            val updatedAge = binding.participantAgeEditText.text.toString().toIntOrNull()
            val selectedSportPosition = binding.participantSportSpinner.selectedItemPosition

            if (updatedName.isNotEmpty() && updatedAge != null && selectedSportPosition != -1) {
                val selectedSport = sports[selectedSportPosition]
                updateParticipantInDatabase(updatedName, updatedAge, selectedSport.name)
                Toast.makeText(this, "Участник обновлен", Toast.LENGTH_SHORT).show()
                val intent = Intent().apply {
                    action = "com.example.dstu_4_2.PARTICIPANT_UPDATED"
                }
                sendBroadcast(intent)
                finish()
            } else {
                Toast.makeText(this, "Заполните все поля", Toast.LENGTH_SHORT).show()
            }
        }

        binding.cancelButton.setOnClickListener {
            finish()
        }
    }

    private fun setupSportSpinner(selectedSportName: String?) {
        val sportNames = sports.map { it.name }
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, sportNames)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.participantSportSpinner.adapter = adapter

        // Устанавливаем выбранный вид спорта
        val selectedPosition = sportNames.indexOf(selectedSportName)
        if (selectedPosition != -1) {
            binding.participantSportSpinner.setSelection(selectedPosition)
        }
    }

    private fun updateParticipantInDatabase(updatedName: String, updatedAge: Int, updatedSport: String) {
        val db = databaseHelper.writableDatabase
        val values = android.content.ContentValues().apply {
            put(DatabaseHelper.COLUMN_NAME, updatedName)
            put(DatabaseHelper.COLUMN_AGE, updatedAge)
            put(DatabaseHelper.COLUMN_SPORT, updatedSport)
        }
        db.update(DatabaseHelper.TABLE_PARTICIPANTS, values, "${DatabaseHelper.COLUMN_ID}=?", arrayOf(participantId.toString()))
    }
}
