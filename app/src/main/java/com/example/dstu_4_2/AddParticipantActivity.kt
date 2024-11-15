package com.example.dstu_4_2

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.dstu_4_2.databinding.ActivityAddParticipantBinding
import com.example.dstu_4_2.db.DatabaseHelper
import com.example.dstu_4_2.models.Participant
import com.example.dstu_4_2.models.Sport

class AddParticipantActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddParticipantBinding
    private lateinit var databaseHelper: DatabaseHelper
    private lateinit var sports: List<Sport>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddParticipantBinding.inflate(layoutInflater)
        setContentView(binding.root)

        databaseHelper = DatabaseHelper(this)
        sports = databaseHelper.getAllSports()

        setupSportSpinner()

        binding.saveButton.setOnClickListener {
            val participantName = binding.participantNameEditText.text.toString()
            val participantAge = binding.participantAgeEditText.text.toString().toIntOrNull()
            val selectedSportPosition = binding.participantSportSpinner.selectedItemPosition

            if (participantName.isNotEmpty() && participantAge != null && selectedSportPosition != -1) {
                val selectedSport = sports[selectedSportPosition]
                val participant = Participant(0, participantName, participantAge, selectedSport.id)
                addParticipantToDatabase(participant)
                Toast.makeText(this, "Участник добавлен", Toast.LENGTH_SHORT).show()
                val intent = Intent().apply {
                    action = "com.example.dstu_4_2.PARTICIPANT_ADDED"
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

    private fun setupSportSpinner() {
        val sportNames = sports.map { it.name }
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, sportNames)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.participantSportSpinner.adapter = adapter
    }

    private fun addParticipantToDatabase(participant: Participant) {
        val db = databaseHelper.writableDatabase
        val values = android.content.ContentValues().apply {
            put(DatabaseHelper.COLUMN_NAME, participant.name)
            put(DatabaseHelper.COLUMN_AGE, participant.age)
            put(DatabaseHelper.COLUMN_SPORT_ID, participant.sportId)
        }
        db.insert(DatabaseHelper.TABLE_PARTICIPANTS, null, values)
    }

}
