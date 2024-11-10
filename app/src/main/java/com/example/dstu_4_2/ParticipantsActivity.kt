package com.example.dstu_4_2

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dstu_4_2.databinding.ActivityParticipantsBinding
import com.example.dstu_4_2.db.DatabaseHelper
import com.example.dstu_4_2.models.Participant
import com.example.dstu_4_2.adapters.ParticipantAdapter

class ParticipantsActivity : AppCompatActivity() {

    private fun deleteParticipant(participant: Participant) {
        val builder = android.app.AlertDialog.Builder(this)
        builder.setTitle("Удаление участника")
        builder.setMessage("Вы уверены, что хотите удалить этого участника?")
        builder.setPositiveButton("Да") { _, _ ->
            databaseHelper.deleteParticipant(participant.id)
            loadParticipants()
        }
        builder.setNegativeButton("Нет") { dialog, _ ->
            dialog.dismiss()
        }
        builder.show()
    }

    private fun editParticipant(participant: Participant) {
        val intent = Intent(this, EditParticipantActivity::class.java)
        intent.putExtra("PARTICIPANT_ID", participant.id)
        intent.putExtra("PARTICIPANT_NAME", participant.name)
        intent.putExtra("PARTICIPANT_AGE", participant.age)
        intent.putExtra("PARTICIPANT_SPORT_ID", participant.sportId)
        startActivity(intent)
    }

    private val participantReceiver = object : android.content.BroadcastReceiver() {
        override fun onReceive(context: android.content.Context?, intent: android.content.Intent?) {
            loadParticipants()
        }
    }

    private lateinit var binding: ActivityParticipantsBinding
    private lateinit var databaseHelper: DatabaseHelper
    private lateinit var participantAdapter: ParticipantAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityParticipantsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        registerReceiver(participantReceiver, android.content.IntentFilter().apply {
            addAction("com.example.dstu_4_2.PARTICIPANT_ADDED")
            addAction("com.example.dstu_4_2.PARTICIPANT_UPDATED")
        })

        databaseHelper = DatabaseHelper(this)

        setupRecyclerView()
        loadParticipants()

        binding.backButton.setOnClickListener {
            finish()
        }

        binding.addParticipantButton.setOnClickListener {
            val intent = Intent(this, AddParticipantActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setupRecyclerView() {
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        participantAdapter = ParticipantAdapter(mutableListOf(), { participant ->
            deleteParticipant(participant)
        }, { participant ->
            editParticipant(participant)
        })
        binding.recyclerView.adapter = participantAdapter
    }

    private fun loadParticipants() {
        val participants: List<Participant> = databaseHelper.getAllParticipants()
        participantAdapter.updateData(participants)
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(participantReceiver)
    }
}
