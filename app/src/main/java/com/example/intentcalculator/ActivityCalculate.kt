package com.example.intentcalculator


import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.lang.Exception

class ActivityCalculate : AppCompatActivity(), View.OnClickListener {

    private lateinit var firstOperandTE: EditText
    private lateinit var secondOperandTE: EditText

    private lateinit var summButtonBTN: Button
    private lateinit var diffButtonBTN: Button
    private lateinit var multButtonBTN: Button
    private lateinit var divButtonBTN: Button

    private lateinit var resultCalculateTV: TextView

    private lateinit var buttonLoadtoMainBTN: Button



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_calculate)

        firstOperandTE = findViewById(R.id.firstOperandET)
        secondOperandTE = findViewById(R.id.secondOperandET)
        summButtonBTN = findViewById(R.id.buttonSummBTN)
        diffButtonBTN = findViewById(R.id.buttonDiffBTN)
        multButtonBTN = findViewById(R.id.buttonMultBTN)
        divButtonBTN = findViewById(R.id.buttonDivBTN)
        buttonLoadtoMainBTN = findViewById(R.id.buttonLoadtoMainBTN)
        resultCalculateTV = findViewById(R.id.resultCalculateTV)

        summButtonBTN.setOnClickListener(this)
        diffButtonBTN.setOnClickListener(this)
        multButtonBTN.setOnClickListener(this)
        divButtonBTN.setOnClickListener(this)
        buttonLoadtoMainBTN.setOnClickListener(this)

        buttonLoadtoMainBTN.setOnClickListener{view ->
            if (resultCalculateTV.text.isEmpty()) return@setOnClickListener
            val resultOnCalculate = resultCalculateTV.text.toString()
            val intent = Intent()
            intent.putExtra("resultOnCalculate", resultOnCalculate)
            setResult(RESULT_OK, intent)
            finish()
        }

    }

    override fun onClick(p0: View) {
        var check = true

        if (firstOperandTE.text.isEmpty() || secondOperandTE.text.isEmpty()) {
            return
        }

        val first = firstOperandTE.text.toString().toDouble()
        val second = secondOperandTE.text.toString().toDouble()

        val result = when(p0.id) {
            R.id.buttonSummBTN -> Operation(first, second).sum()
            R.id.buttonDiffBTN -> Operation(first, second).diff()
            R.id.buttonMultBTN -> Operation(first, second).mult()
            R.id.buttonDivBTN -> Operation(first, second).div()
            else -> 0.0
        }
        if (!check) resultCalculateTV.text = "Результат" else resultCalculateTV.text = result.toString()
    }

}