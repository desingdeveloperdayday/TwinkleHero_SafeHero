package com.ddd.twinkle.safehero.emergency.calling

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.WindowManager
import com.ddd.twinkle.safehero.R
import kotlinx.android.synthetic.main.custom_view.*

class CustomDialog(context: Context) : Dialog(context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window?.attributes= WindowManager.LayoutParams().also {
            it.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND
            it.dimAmount = 0.8f
        }

        setContentView(R.layout.custom_view)
        close.setOnClickListener {
            this.dismiss()
        }
    }
}
