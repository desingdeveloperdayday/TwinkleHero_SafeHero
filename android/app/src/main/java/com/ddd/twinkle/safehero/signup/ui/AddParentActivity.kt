package com.ddd.twinkle.safehero.signup.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.ddd.twinkle.safehero.R
import kotlinx.android.synthetic.main.activity_add_parent.*
import kotlinx.android.synthetic.main.app_bar.*

fun Context.newIntentAddParentActivityu() : Intent{
    val intent = Intent(this,AddParentActivity::class.java)
    return intent
}

class AddParentActivity : AppCompatActivity() {

    private var idx : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_parent)
        setupToolbar()
        setupButton()
    }

    private fun setupButton() {
        button_add_parent.setOnClickListener {
            checkParent()
        }
    }

    private fun checkParent(){
        val arrayList= arrayListOf(R.id.parent_2,R.id.parent_3)
        if(idx < arrayList.size) {
            val view = findViewById<View>(arrayList[idx])
            view.visibility = View.VISIBLE
            idx++
        }
        else
            Toast.makeText(applicationContext,"보호자 최대 설정은 3명입니다",Toast.LENGTH_SHORT).show()
    }

    private fun setupToolbar() {
        toolbar_text.text="보호자 설정"
    }

}
