package com.ddd.twinkle.safehero.signup.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
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
            if (idx == arrayList.size){
                button_add_parent.visibility=View.GONE
            }
        }
    }

    private fun setupToolbar() {
        toolbar_text.text="보호자 설정"
    }

}
