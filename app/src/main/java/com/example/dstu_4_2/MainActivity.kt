package com.example.dstu_4_2

import android.os.Bundle
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import android.widget.ImageView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val sunImageView: ImageView = findViewById(R.id.sunImageView)
        val sunAnimation = AnimationUtils.loadAnimation(this, R.anim.sunrise)
        sunImageView.startAnimation(sunAnimation)
    }
}