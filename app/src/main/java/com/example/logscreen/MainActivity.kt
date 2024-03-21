package com.example.logscreen

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var sp =getSharedPreferences("PC",Context.MODE_PRIVATE)
        if (sp.getString("TY","-9")!="-9"){
            startActivity(Intent(this, MainActivity2::class.java))
        }
        else {
            var signuptext: TextView = findViewById(R.id.signuptext)
            signuptext.setOnClickListener {
                var intent = Intent(this, signupactiviti::class.java)
                startActivity(intent)
            }
            var email:TextView =findViewById(R.id.email)
            var password:TextView = findViewById(R.id.password)
            var button: ConstraintLayout = findViewById(R.id.button)
            var db = Firebase.firestore
            var df =false
            button.setOnClickListener {
                db.collection("user")
                    .get()
                    .addOnSuccessListener { result ->
                        for (document in result) {
                            if (document.getString("email")==email.text.toString()){
                                if (document.getString("password")==password.text.toString()){

                                    var name = document.getString("name")
                                    if (name != null) {
                                        sp.edit().putString("Name", name).commit()
                                    }
                                    df = true
                                    startActivity(Intent(this,MainActivity2::class.java))
                                }
                                else if (document.getString("password")==password.text){
                                    password.text=""
                                }
                            }
                        }
                    }
                    .addOnFailureListener { exception ->
                        Toast.makeText(this,"Неполучилось попробуйте позже!",Toast.LENGTH_LONG).show()
                    }
                var h =Handler()
                h.postDelayed({
                    if (df == false){
                        Toast.makeText(this,"Неправильно набраны поля",Toast.LENGTH_LONG).show()
                    }
                },1600)
                }
            }
        }
    }
