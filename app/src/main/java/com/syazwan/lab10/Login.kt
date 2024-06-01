package com.syazwan.lab10

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.syazwan.lab10.databinding.ActivityLoginBinding

class Login : AppCompatActivity() {

     private lateinit var binding: ActivityLoginBinding

     private lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        //Dapatkan connection ke database
        auth = FirebaseAuth.getInstance()

        binding.loginBtn.setOnClickListener {
            //Verification yang value username da password dimasukkan user ada
            if(binding.emailLoginId.text.trim().toString().isNotEmpty() ||
                binding.passwordLoginId.text.trim().toString().isNotEmpty()) {
                loginUser(binding.emailLoginId.toString(),
                    binding.passwordLoginId.text.toString())
            }
            else{
                Snackbar.make(binding.root,
                    "Please check your username and password",
                    Snackbar.LENGTH_LONG).show()
            }

        }

    }

    fun loginUser(email:String, password:String){
        auth.signInWithEmailAndPassword(email,password).addOnCompleteListener{
            task ->
            if(task.isSuccessful){
                val intent = Intent(this, Service::class.java)
                startActivity(intent)
            }
            else{
                Snackbar.make(binding.root,
                    "Please check your username and password",
                    Snackbar.LENGTH_LONG).show()
            }
        }
            .addOnFailureListener { err->
                Snackbar.make(binding.root,
                    err.message!!,
                    Snackbar.LENGTH_LONG).show()
            }
    }
}