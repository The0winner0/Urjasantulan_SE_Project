package com.example.urjasantulan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class SignUpActivity : AppCompatActivity() {

    private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        firebaseAuth = FirebaseAuth.getInstance()

        val logionButton: Button = findViewById(R.id.signupButton)
        val usernameEditText: EditText = findViewById(R.id.usernameEditText)
        val passwordEditText: EditText = findViewById(R.id.passwordEditText)
        val cpasswordEditText: EditText = findViewById(R.id.cpasswordEditText)
        val exsistingUser: TextView = findViewById(R.id.ifAlreadyHaveAccount)

        exsistingUser.setOnClickListener() {
            val intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)
        }


        logionButton.setOnClickListener {
            val username = usernameEditText.text.toString();
            val password = passwordEditText.text.toString();
            val cpassword = cpasswordEditText.text.toString();

            if (username.isNotEmpty() && password.isNotEmpty() && cpassword.isNotEmpty()) {
                if (password == cpassword) {

                    firebaseAuth.createUserWithEmailAndPassword(username, password).addOnCompleteListener {
                        if (it.isSuccessful) {
                            val intent = Intent(this, SignInActivity::class.java)
                            startActivity(intent)
                        } else {
                            Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()
                        }
                    }
                } else {
                    Toast.makeText(this, "Password is not matching", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Empty Fields Are not Allowed !!", Toast.LENGTH_SHORT).show()

            }
        }
    }
}