package com.ddd.twinkle.safehero.signup.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.ddd.twinkle.safehero.R
import com.ddd.twinkle.safehero.utills.toReadableTime
import com.jakewharton.rxbinding3.widget.textChangeEvents
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
    private var timer : CountDownTimer? =null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        setuptoolbar()
        setupButton()
        checkButtonValid()
    }

    private fun checkButtonValid() {
      val nameCheck =  edit_name.textChangeEvents()
           .map { it.text.isNotEmpty() }
      val phoneCheck = edit_phone.textChangeEvents()
          .map { it.text.isNotEmpty() }
      val authNumCheck = edit_auth_num.textChangeEvents()
          .map { it.text.isNotEmpty() }
    }


    private fun setuptoolbar() {
        toolbar_text.text="본인 인증"
    }

    private fun setupButton() {
        send_auth_num.setOnClickListener {
            //todo("인증 번호 발송하는 는 api 필요.")
            startTimer()
        }

        button_nextStep.setBackgroundColor(ContextCompat.getColor(applicationContext,R.color.alert_color))
            .run {
                button_nextStep.isEnabled =true
            }

        button_nextStep.setOnClickListener {
            startActivity(newIntentAddParentActivityu())
        }
    }

    private fun startTimer() {
       timer = object : CountDownTimer(180000,1000){
            override fun onFinish() {
                text_timer.text="0:00"
            }
            override fun onTick(millisUntilFinished: Long) {
                text_timer.text = toReadableTime(millisUntilFinished)
            }
       }.start()
    }


}
