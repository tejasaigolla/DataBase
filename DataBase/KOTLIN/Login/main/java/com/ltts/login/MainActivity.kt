package com.ltts.login
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        register.setOnClickListener {

            var un=register.text.toString()
            Toast.makeText(this,"Clicked",Toast.LENGTH_LONG).show()
            register.setText(un)


            var myIntent=Intent(this,MainActivity2::class.java)
            myIntent.putExtra("key",un)
            startActivity(myIntent)
        }
    }
}