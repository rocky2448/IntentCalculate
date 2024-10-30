package com.example.intentcalculator

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private lateinit var resultTV: TextView
    private lateinit var callCalculateBTN: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        resultTV = findViewById(R.id.resultTV)
        callCalculateBTN = findViewById(R.id.callCalculateBTN)

        callCalculateBTN.setOnClickListener{view ->
            val intent = Intent(this, ActivityCalculate::class.java)
            lunchSomeActivity.launch(intent)
        }

        val result = intent.getStringExtra("result")
        if (result == null) resultTV.text = "Результат"
        else resultTV.text = "$result"

    }
    private val lunchSomeActivity = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            val data = result.data
            val resultOnCalculate = data!!.getStringExtra("resultOnCalculate")
            resultTV.text = resultOnCalculate
        }
    }
}