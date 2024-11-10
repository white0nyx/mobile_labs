package com.example.dstu_4_2

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dstu_4_2.databinding.ActivitySportsBinding
import com.example.dstu_4_2.db.DatabaseHelper
import com.example.dstu_4_2.models.Sport
import com.example.dstu_4_2.adapters.SportAdapter

class SportsActivity : AppCompatActivity() {

    private fun deleteSport(sport: Sport) {
        val builder = android.app.AlertDialog.Builder(this)
        builder.setTitle("Удаление вида спорта")
        builder.setMessage("Вы уверены, что хотите удалить этот вид спорта?")
        builder.setPositiveButton("Да") { _, _ ->
            databaseHelper.deleteSport(sport.id)
            loadSports()
        }
        builder.setNegativeButton("Нет") { dialog, _ ->
            dialog.dismiss()
        }
        builder.show()
    }

    private fun editSport(sport: Sport) {
        val intent = Intent(this, EditSportActivity::class.java)
        intent.putExtra("SPORT_ID", sport.id)
        intent.putExtra("SPORT_NAME", sport.name)
        startActivity(intent)
    }

    private val sportReceiver = object : android.content.BroadcastReceiver() {
        override fun onReceive(context: android.content.Context?, intent: android.content.Intent?) {
            loadSports()
        }
    }

    private lateinit var binding: ActivitySportsBinding
    private lateinit var databaseHelper: DatabaseHelper
    private lateinit var sportAdapter: SportAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySportsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        registerReceiver(sportReceiver, android.content.IntentFilter().apply {
            addAction("com.example.dstu_4_2.SPORT_ADDED")
            addAction("com.example.dstu_4_2.SPORT_UPDATED")
        })

        databaseHelper = DatabaseHelper(this)

        setupRecyclerView()
        loadSports()

        binding.backButton.setOnClickListener {
            finish()
        }

        binding.addSportButton.setOnClickListener {
            val intent = Intent(this, AddSportActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setupRecyclerView() {
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        sportAdapter = SportAdapter(mutableListOf(), { sport ->
            deleteSport(sport)
        }, { sport ->
            editSport(sport)
        })
        binding.recyclerView.adapter = sportAdapter
    }

    private fun loadSports() {
        val sports: List<Sport> = databaseHelper.getAllSports()
        sportAdapter.updateData(sports)
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(sportReceiver)
    }
}
