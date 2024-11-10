package com.example.dstu_4_2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dstu_4_2.databinding.ActivitySportsBinding
import com.example.dstu_4_2.db.DatabaseHelper
import com.example.dstu_4_2.models.Sport
import com.example.dstu_4_2.adapters.SportAdapter

class SportsActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySportsBinding
    private lateinit var databaseHelper: DatabaseHelper
    private lateinit var sportAdapter: SportAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySportsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        databaseHelper = DatabaseHelper(this)

        setupRecyclerView()
        loadSports()
    }

    private fun setupRecyclerView() {
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        sportAdapter = SportAdapter(mutableListOf())
        binding.recyclerView.adapter = sportAdapter
    }

    private fun loadSports() {
        val sports: List<Sport> = databaseHelper.getAllSports()
        sportAdapter.updateData(sports)
    }
}