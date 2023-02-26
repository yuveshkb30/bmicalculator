package com.yuvesh.bmicalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {
    lateinit var weight:EditText
    lateinit var height:EditText
    lateinit var buttoncalculate: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        weight=findViewById(R.id.wtnumber)
        height=findViewById(R.id.wtnumber1)

        buttoncalculate=findViewById(R.id.btnCalculate)
        buttoncalculate.setOnClickListener {
            val w = weight.text.toString()
            val h = height.text.toString()
            if(validateInput(w,h))
            {
            val bmi = (w.toFloat()) / ((h.toFloat() / 100) * (h.toFloat() / 100))
            val bmi2digit = String.format("%.2f", bmi).toFloat()
            displayResult(bmi)
        }
        }
    }

    private fun validateInput(weight:String?,height:String?):Boolean{
         return when{
              weight.isNullOrBlank()->{
                  Toast.makeText(this@MainActivity,"Weight is empty",Toast.LENGTH_LONG).show()
                  return false
              }
             height.isNullOrBlank()->{
                 Toast.makeText(this@MainActivity,"Height is empty",Toast.LENGTH_LONG).show()
                 return false
             }
             else->{
                 return true
             }
         }
    }

    private fun displayResult(bmi:Float)
    {
        val amt=findViewById<TextView>(R.id.txtamt)
        val statement=findViewById<TextView>(R.id.txtStatement)
        val info=findViewById<TextView>(R.id.txtinfo)

        amt.text=bmi.toString()
        info.text="(Normal range is 18.5-24.9)"

        var resultText=""
        var color=0

        when{
            bmi<18.50 ->{
                resultText="Underweight"
                color=R.color.underweight
            }
            bmi in 18.50..24.99->{
                resultText="Healthy"
                color=R.color.normal
            }
            bmi in 25.00..29.99->{
                resultText="Overweight"
                color=R.color.overweight
            }
            bmi > 29.99->{
                   resultText="Obese"
                   color=R.color.OBESE
            }
        }

         statement.setTextColor(ContextCompat.getColor(this@MainActivity,color))
         statement.text=resultText
    }
}