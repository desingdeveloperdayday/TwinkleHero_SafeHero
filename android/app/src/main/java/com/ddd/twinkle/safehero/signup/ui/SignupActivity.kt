package com.ddd.twinkle.safehero.signup.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import androidx.appcompat.app.AppCompatActivity
import com.ddd.twinkle.safehero.R
import com.ddd.twinkle.safehero.utills.toReadableTime
import kotlinx.android.synthetic.main.activity_signup.*
import kotlinx.android.synthetic.main.app_bar.*
import timber.log.Timber
import java.util.concurrent.TimeUnit

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
        Timber.d("22")
    }

    private fun setuptoolbar() {
        toolbar_text.text="본인 인증"
    }

    private fun setupButton() {
        send_auth_num.setOnClickListener {
            //todo("인증 번호 발송하는 는 api 필요.")
            startTimer()

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
