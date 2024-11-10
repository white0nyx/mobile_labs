package com.example.dstu_4_2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dstu_4_2.databinding.ActivityParticipantsBinding
import com.example.dstu_4_2.db.DatabaseHelper
import com.example.dstu_4_2.models.Participant
import com.example.dstu_4_2.adapters.ParticipantAdapter

class ParticipantsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityParticipantsBinding
    private lateinit var databaseHelper: DatabaseHelper
    private lateinit var participantAdapter: ParticipantAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityParticipantsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        databaseHelper = DatabaseHelper(this)

        setupRecyclerView()
        loadParticipants()
    }

    private fun setupRecyclerView() {
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        participantAdapter = ParticipantAdapter(mutableListOf())
        binding.recyclerView.adapter = participantAdapter
    }

    private fun loadParticipants() {
        val participants: List<Participant> = databaseHelper.getAllParticipants()
        participantAdapter.updateData(participants)
    }
}