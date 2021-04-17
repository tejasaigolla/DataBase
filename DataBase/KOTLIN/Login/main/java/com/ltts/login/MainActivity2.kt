package com.ltts.login

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main2.*

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        button.setOnClickListener {
            Toast.makeText(this,"Home Screen", Toast.LENGTH_LONG).show()
            var myIntent= Intent(this,MainActivity::class.java)
            startActivity(myIntent)
        }
        button.setOnClickListener {
            var myIntent= Intent(this,MainActivity::class.java)
            startActivity(myIntent)
            Toast.makeText(this,"next", Toast.LENGTH_LONG).show()
        }
    }
}