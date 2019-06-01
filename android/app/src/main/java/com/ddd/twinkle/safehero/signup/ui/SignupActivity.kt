package com.ddd.twinkle.safehero.signup.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import androidx.appcompat.app.AppCompatActivity
import com.ddd.twinkle.safehero.R
import kotlinx.android.synthetic.main.app_bar.*

fun Context.newIntentSignupActivity() : Intent {
    val intent = Intent(this,SignupActivity::class.java)
    return intent
}
class SignupActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        setuptoolbar()
    }

    private fun setuptoolbar() {
        toolbar_text.text="본인 인증"
    }


}
