package com.ddd.twinkle.safehero.emergency.calling

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ddd.twinkle.safehero.R

fun Context.newIntentCallingActivity() : Intent{
    val intent = Intent(this,CallingActivity::class.java)
    return intent
}

class CallingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calling)
    }
}
