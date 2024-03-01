package com.example.adminsweeto

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.adminsweeto.databinding.ActivityCreateUserBinding

class CreateUserActivity : AppCompatActivity() {
    private val binding : ActivityCreateUserBinding by lazy {
        ActivityCreateUserBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.backButton.setOnClickListener {
            finish()
        }
    }
}