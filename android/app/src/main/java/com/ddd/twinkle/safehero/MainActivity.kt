package com.ddd.twinkle.safehero

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

fun Context.newIntentMainActivity() : Intent{
    val intent =Intent(this,MainActivity::class.java)
    return intent
}

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
