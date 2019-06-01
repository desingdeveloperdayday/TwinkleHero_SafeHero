package com.ddd.twinkle.safehero.signup.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ddd.twinkle.safehero.R
import kotlinx.android.synthetic.main.activity_signup.*
import kotlinx.android.synthetic.main.app_bar.*

fun Context.newIntentSignupActivity() : Intent {
    val intent = Intent(this,SignupActivity::class.java)
    return intent
}
class SignupActivity : AppCompatActivity() {

    private var naem : String? = ""
    private var phone_num : String? = ""
    private var confirm_num: String? =""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        setuptoolbar()
        setupButton()
    }

    private fun setuptoolbar() {
        toolbar_text.text="본인 인증"
    }

    private fun setupButton() {
        send_auth_num.setOnClickListener {
            //todo("인증 번호 발소오디는 api 필요.")
        }
        button_nextStep.setOnClickListener {
            startActivity(newIntentAddParentActivityu())
        }

    }




}
