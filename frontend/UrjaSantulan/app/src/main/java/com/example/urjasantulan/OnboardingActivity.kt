package com.example.urjasantulan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class OnboardingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding)

        val navigateButton: Button = findViewById(R.id.SuButton)

        navigateButton.setOnClickListener {
            print("HClicked")
//             Create an Intent to start the new Activity
            val intent = Intent(this, SignInActivity::class.java)

            // Start the new Activity
            startActivity(intent)
        }
    }
}