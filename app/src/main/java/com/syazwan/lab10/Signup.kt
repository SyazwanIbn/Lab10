package com.syazwan.lab10

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import com.syazwan.lab10.databinding.ActivitySignupBinding

class Signup : AppCompatActivity() {

    private lateinit var binding: ActivitySignupBinding

    private lateinit var auth: FirebaseAuth;

    private lateinit var db: FirebaseFirestore;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivitySignupBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        //Dapatkan connection firebase Authentication dari app
        auth = FirebaseAuth.getInstance()

        binding.registerBtn.setOnClickListener {
            createUser(
                binding.emailSignupId.text.toString(),
                binding.passwordSignupId.text.toString()
            )
        }
        db = Firebase.firestore
    }


        fun createUser(email:String, password:String){
            //createuser mengunakan email dan password
            //bila dah sudah saya akan dapat task
            auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener {
                    task ->

                if (task.isSuccessful){
                    newCustomer()
                    val intent = Intent(this,Thankyou::class.java)
                    startActivity(intent)
                }
                else{
                    Snackbar.make(
                        binding.root,"Enter a valid username and password",
                        Snackbar.LENGTH_LONG).show()

                }
            }
        }

    }
