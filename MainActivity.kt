package com.example.calculator

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.appcompat.app.AppCompatDelegate
import android.widget.Switch
import org.mozilla.javascript.Context
import org.mozilla.javascript.Scriptable

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        val themeSwitch = findViewById<Switch>(R.id.switch_mode)

        themeSwitch.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                // Dark mode ON
                AppCompatDelegate.setDefaultNightMode(
                    AppCompatDelegate.MODE_NIGHT_YES
                )
            } else {
                // Dark mode OFF
                AppCompatDelegate.setDefaultNightMode(
                    AppCompatDelegate.MODE_NIGHT_NO
                )
            }
        }



        // getting views and buttons from xml file..

        val display_result = findViewById<TextView>(R.id.display_result)
        val result = findViewById<EditText>(R.id.take_operation)

        // operation buttons
        val buttonPlus = findViewById<Button>(R.id.btnPlus)
        val buttonMinus = findViewById<Button>(R.id.btnMinus)
        val buttonDiv = findViewById<Button>(R.id.btnDiv)
        val buttonPercentage = findViewById<Button>(R.id.btnPercent)
        val buttonAllClear = findViewById<Button>(R.id.btnAC)
        val buttonClearOne = findViewById<Button>(R.id.btnClear)
        val buttonMulti = findViewById<Button>(R.id.btnMulti)
        val buttonEqual = findViewById<Button>(R.id.btnEqual)
        val buttonDot = findViewById<Button>(R.id.btnDot)

        // number buttons
        val button1 = findViewById<Button>(R.id.btn1)
        val button2 = findViewById<Button>(R.id.btn2)
        val button3 = findViewById<Button>(R.id.btn3)
        val button4 = findViewById<Button>(R.id.btn4)
        val button5 = findViewById<Button>(R.id.btn5)
        val button6 = findViewById<Button>(R.id.btn6)
        val button7 = findViewById<Button>(R.id.btn7)
        val button8 = findViewById<Button>(R.id.btn8)
        val button9 = findViewById<Button>(R.id.btn9)
        val button0 = findViewById<Button>(R.id.btn0)

        //declaring variables to store values..

        var first_number = ""
        var isNewInput = false
        var operator = ""

        // actions of number buttons....

        button1.setOnClickListener {



            if (isNewInput) {
                result.setText("")
                isNewInput = false
            }
            val input = result.text.toString() + "1"
            result.setText(input)
        }

        button2.setOnClickListener {

            if (isNewInput) {
                result.setText("")
                isNewInput = false
            }
            val input = result.text.toString() + "2"
            result.setText(input)
        }

        button3.setOnClickListener {

            if (isNewInput) {
                result.setText("")
                isNewInput = false
            }
            val input = result.text.toString() + "3"
            result.setText(input)
        }
        button4.setOnClickListener {

            if (isNewInput) {
                result.setText("")
                isNewInput = false
            }
            val input = result.text.toString() + "4"
            result.setText(input)
        }
        button5.setOnClickListener{

            if (isNewInput) {
                result.setText("")
                isNewInput = false
            }
            val input = result.text.toString() + "5"
            result.setText(input)
        }
        button6.setOnClickListener {

            if (isNewInput) {
                result.setText("")
                isNewInput = false
            }
            val input = result.text.toString() + "6"
            result.setText(input)
        }

        button7.setOnClickListener {

            if (isNewInput) {
                result.setText("")
                isNewInput = false
            }
            val input = result.text.toString() + "7"
            result.setText(input)
        }
        button8.setOnClickListener {

            if (isNewInput) {
                result.setText("")
                isNewInput = false
            }
            val input = result.text.toString() + "8"
            result.setText(input)
        }
        button9.setOnClickListener {

            if (isNewInput) {
                result.setText("")
                isNewInput = false
            }
            val input = result.text.toString() + "9"
            result.setText(input)
        }
        button0.setOnClickListener {

            if (isNewInput) {
                result.setText("")
                isNewInput = false
            }
            val input = result.text.toString() + "0"
            result.setText(input)
        }

        // actions of operator buttons

        buttonPlus.setOnClickListener {
            if (result.text.toString().isEmpty())
                return@setOnClickListener


            first_number = result.text.toString()
            operator = "+"
            isNewInput = false

            display_result.setText("")

                result.setText(result.text.toString() + "+")



        }





        buttonMinus.setOnClickListener {
            if (result.text.toString().isEmpty())
                return@setOnClickListener


            first_number = result.text.toString()
            operator = "-"
            isNewInput = false

            result.setText(result.text.toString() + "-")
            display_result.setText("")

        }



        buttonMulti.setOnClickListener {
            if (result.text.toString().isEmpty())
                return@setOnClickListener



            first_number = result.text.toString()
            operator = "*"
            isNewInput = false

            result.setText(result.text.toString() + "*")
            display_result.setText("")
        }


        buttonDiv.setOnClickListener {
            if (result.text.toString().isEmpty())
                return@setOnClickListener



            first_number = result.text.toString()
            operator = "/"
            isNewInput = false

            result.setText(result.text.toString() + "/")
            display_result.setText("")
        }



        buttonPercentage.setOnClickListener {
            if (result.text.toString().isEmpty())
                return@setOnClickListener



            first_number = result.text.toString()
            operator = "%"
            isNewInput = false
            result.setText(result.text.toString() + "%")
            display_result.setText("")
        }


       //action of equal button....


        buttonEqual.setOnClickListener {

            val expression = result.text.toString()
            if (expression.isEmpty()) return@setOnClickListener

            try {
                val context = Context.enter()
                context.optimizationLevel = -1

                val scope: Scriptable = context.initStandardObjects()

                val finalResult = context.evaluateString(scope,expression,"javascript",1,null)

                display_result.text = finalResult.toString()
                result.setText(finalResult.toString())

                operator = ""
                first_number = finalResult.toString()
                isNewInput = true

                Context.exit()

            } catch (e: Exception) {
                display_result.text = "ERROR"
                Context.exit()
            }
        }





// action of dot operator
        buttonDot.setOnClickListener {

            if (result.text.toString().contains("."))
                return@setOnClickListener


            if (result.text.toString().isEmpty()) {
                result.setText("0.")
            } else {
                result.append(".")
            }
        }
// actions of clear buttons
        buttonClearOne.setOnClickListener {
            val currentText = result.text.toString()
            if (currentText.isNotEmpty()) {
                result.setText(currentText.dropLast(1))
            }
        }
        buttonAllClear.setOnClickListener {
            result.setText("")
            operator = ""
            first_number = ""
            display_result.setText("")
        }
    }
}






