package com.example.urjasantulan

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.VideoView
import com.google.firebase.auth.FirebaseAuth

class SplashScreen : AppCompatActivity() {

    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        val videoView: VideoView = findViewById(R.id.splashVideoView)

        val videoPath = "android.resource://" + packageName + "/" + R.raw.splash_video
        val uri = Uri.parse(videoPath)
        videoView.setVideoURI(uri)


        videoView.start()

        firebaseAuth = FirebaseAuth.getInstance()
        val user = firebaseAuth.currentUser

        videoView.setOnCompletionListener {
            if(user != null){
                Log.d("SplashScreen", "User is already logged in")
                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
                finish()
            }
            else {
                Log.d("SplashScreen", "User is not logged in")
                navigateToMainActivity()
            }
        }
    }

    private fun navigateToMainActivity() {
        val intent = Intent(this, OnboardingActivity::class.java)
        startActivity(intent)
        finish() // Close the splash activity
    }
}