package com.example.hasdeneme

import Person
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val btnKaydet = findViewById<Button>(R.id.button3)

        btnKaydet.setOnClickListener {
            val intent = Intent(this, form_menu::class.java)
            startActivity(intent)
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val personList = listOf(
            Person("Misafir Odası Temizlik Formu", "Form 101", R.drawable.ic_person, misafirodasitemizlikkontrol::class.java),
         //   Person("Ayşe Kaya", "Oda 205", R.drawable.ic_person, Form2Activity::class.java),
          //  Person("Mehmet Demir", "Oda 310", R.drawable.ic_person, Form3Activity::class.java)
        )



    }
}

