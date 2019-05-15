package com.smu.a05_15

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnMainGoTest.setOnClickListener{
            val intent = Intent(this,TestActivity::class.java)
            startActivity(intent)
        }
    }



}
