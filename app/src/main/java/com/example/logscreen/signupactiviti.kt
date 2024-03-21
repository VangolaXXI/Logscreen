package com.example.logscreen

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

class signupactiviti : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signupactiviti)
        var sp = getSharedPreferences("PC", Context.MODE_PRIVATE)
        var editor = sp.edit()
        var email:TextView =findViewById(R.id.email)
        var password:TextView = findViewById(R.id.password)
        var name:TextView = findViewById(R.id.name)
        var button:ConstraintLayout = findViewById(R.id.button)
        button.setOnClickListener {
            if (email.text.isEmpty() || !email.text.contains("@")) {
                Toast.makeText(this, "Проверьте поле email", Toast.LENGTH_LONG).show()
            } else if (password.text.isEmpty() || password.text.length < 6) {
                Toast.makeText(this, "Пороль должен быть больше 5 символов", Toast.LENGTH_LONG)
                    .show()
            }else if (name.text.isEmpty()|| name.text.length < 4){
                Toast.makeText(this,"Имя должно содержать как минимум 4 символа", Toast.LENGTH_LONG).show()
            } else {
                val db = Firebase.firestore
                val user = hashMapOf(
                    "email" to email.text.toString(),
                    "password" to password.text.toString(),
                    "name" to  name.text.toString()

                )

                db.collection("user")
                    .add(user)
                    .addOnSuccessListener { documentReference ->
                        editor.putString("Name", name.text.toString())
                        editor.apply()
                        startActivity(Intent(this,MainActivity2::class.java))
                    }
                    .addOnFailureListener { e ->
                        Toast.makeText(this,"Неполучилось попробуйте позже!",Toast.LENGTH_LONG).show()
                    }
            }
        }
        }
    }
