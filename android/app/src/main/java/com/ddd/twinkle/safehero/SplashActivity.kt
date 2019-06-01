package com.ddd.twinkle.safehero

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ddd.twinkle.safehero.signup.ui.newIntentSignupActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
            goToMainActivity()
    }

    private fun goToMainActivity() {

        //todo("회원가입 필요한 회원인지 아닌지 분기 처리 필요함")
        startActivity(newIntentSignupActivity())


        /*startActivity(newIntentMainActivity())
        finish()*/
    }
}
