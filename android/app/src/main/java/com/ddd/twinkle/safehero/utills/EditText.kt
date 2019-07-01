package com.ddd.twinkle.safehero.utills

import android.text.InputFilter
import android.widget.EditText

fun EditText.setInputFilter(filter: InputFilter) {
    clearInputFilter()
    filters = filters.plus(filter)
}

fun EditText.clearInputFilter(){
    filters = arrayOf<InputFilter>()
}