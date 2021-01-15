package com.example.week6assignment1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.EditText
import com.google.android.material.button.MaterialButton

class MainActivity : AppCompatActivity() {
    private lateinit var etUsername: EditText
    private lateinit var etPassword: EditText
    private lateinit var btnLogin: MaterialButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        retrieveValues()
        btnLogin.setOnClickListener{
            if(validation()){
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
        }

    }

    override fun onStart() {
        super.onStart()
        reset()
    }

    private fun reset() {
        etUsername.setText("")
        etPassword.setText("")
    }

    private fun validation(): Boolean {
        when {
            TextUtils.isEmpty(etUsername.text) -> {
                etUsername.requestFocus()
                etUsername.error = "Please enter a username"
                return false
            }
            etUsername.text.toString() != "softwarica" -> {
                etUsername.requestFocus()
                etUsername.error = "Username did not match"
                return false
            }
            TextUtils.isEmpty(etPassword.text) -> {
                etPassword.requestFocus()
                etPassword.error = "Please enter a password"
                return false
            }
            etPassword.text.toString() != "coventry" -> {
                etPassword.requestFocus()
                etPassword.error = "Password did not match"
                return false
            }
            else -> return true
        }
    }

    private fun retrieveValues()
    {
        etUsername = findViewById(R.id.etUsername)
        etPassword = findViewById(R.id.etPassword)



        btnLogin = findViewById(R.id.btnLogin)
    }

}