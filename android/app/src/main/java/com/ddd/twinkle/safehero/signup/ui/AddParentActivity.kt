package com.ddd.twinkle.safehero.signup.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ddd.twinkle.safehero.R

fun Context.newIntentAddParentActivityu() : Intent{
    val intent = Intent(this,AddParentActivity::class.java)
    return intent
}

class AddParentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_parent)

    }

}
