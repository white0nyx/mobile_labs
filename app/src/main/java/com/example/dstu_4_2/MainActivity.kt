package com.example.dstu_4_2

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.dstu_4_2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.participantsButton.setOnClickListener {
            val intent = Intent(this, ParticipantsActivity::class.java)
            startActivity(intent)
        }

        binding.sportsButton.setOnClickListener {
            val intent = Intent(this, SportsActivity::class.java)
            startActivity(intent)
        }
    }
}