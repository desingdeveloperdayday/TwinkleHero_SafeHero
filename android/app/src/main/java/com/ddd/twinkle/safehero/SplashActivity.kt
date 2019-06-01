package com.ddd.twinkle.safehero

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
            goToMainActivity()
    }

    private fun goToMainActivity() {

        //todo("회원가입 필요한 회원인지 아닌지 분기 처리 필요함")


        /*startActivity(newIntentMainActivity())
        finish()*/
    }
}
