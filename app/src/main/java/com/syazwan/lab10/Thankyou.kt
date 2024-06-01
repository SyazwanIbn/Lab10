package com.syazwan.lab10

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.syazwan.lab10.databinding.ActivityMainBinding
import com.syazwan.lab10.databinding.ActivityThankyouBinding

class Thankyou : AppCompatActivity() {

    private lateinit var binding:ActivityThankyouBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityThankyouBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.portalBtn.setOnClickListener {
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
        }

    }
}