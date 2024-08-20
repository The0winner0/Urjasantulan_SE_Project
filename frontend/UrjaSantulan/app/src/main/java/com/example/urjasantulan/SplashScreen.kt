package com.example.urjasantulan

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.VideoView

class SplashScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        val videoView: VideoView = findViewById(R.id.splashVideoView)

        val videoPath = "android.resource://" + packageName + "/" + R.raw.splash_video
        val uri = Uri.parse(videoPath)
        videoView.setVideoURI(uri)

        videoView.setOnCompletionListener {
            navigateToMainActivity()
        }

        videoView.start()

        // Backup plan to proceed after 4 seconds in case the video doesn't trigger completion
        Handler().postDelayed({
            if (!videoView.isPlaying) {
                navigateToMainActivity()
            }
        }, 4000) // 4 seconds
    }

    private fun navigateToMainActivity() {
        val intent = Intent(this, OnboardingActivity::class.java)
        startActivity(intent)
        finish() // Close the splash activity
    }
}