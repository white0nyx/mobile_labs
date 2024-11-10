package com.example.dstu_4_2

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.dstu_4_2.databinding.ActivityAddParticipantBinding
import com.example.dstu_4_2.db.DatabaseHelper
import com.example.dstu_4_2.models.Participant

class AddParticipantActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddParticipantBinding
    private lateinit var databaseHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddParticipantBinding.inflate(layoutInflater)
        setContentView(binding.root)

        databaseHelper = DatabaseHelper(this)

        binding.saveButton.setOnClickListener {
            val participantName = binding.participantNameEditText.text.toString()
            val participantAge = binding.participantAgeEditText.text.toString().toIntOrNull()
            val participantSport = binding.participantSportEditText.text.toString()

            if (participantName.isNotEmpty() && participantAge != null && participantSport.isNotEmpty()) {
                val participant = Participant(0, participantName, participantAge, participantSport)
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

    private fun addParticipantToDatabase(participant: Participant) {
        val db = databaseHelper.writableDatabase
        val values = android.content.ContentValues().apply {
            put(DatabaseHelper.COLUMN_NAME, participant.name)
            put(DatabaseHelper.COLUMN_AGE, participant.age)
            put(DatabaseHelper.COLUMN_SPORT, participant.sport)
        }
        db.insert(DatabaseHelper.TABLE_PARTICIPANTS, null, values)
    }
}
