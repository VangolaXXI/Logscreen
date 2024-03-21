package com.example.logscreen

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        var sp = getSharedPreferences("PC",Context.MODE_PRIVATE)
        sp.edit().putString("TY","9").commit()
        var emailname:TextView = findViewById(R.id.emailname)
        emailname.text=sp.getString("Name","Не загрузилось (")
        var logout:Button = findViewById(R.id.logout)
        logout.setOnClickListener{
            sp.edit().putString("TY","-9").commit()
            Toast.makeText(this,"Вы вышли из аккаунта",Toast.LENGTH_LONG).show()
            startActivity(Intent(this,MainActivity::class.java))
        }



    }

    override fun onBackPressed() {
    }
}