package com.ddd.twinkle.safehero.emergency.calling

import android.content.Context
import android.content.Intent
import android.media.MediaRecorder
import android.os.Bundle
import android.os.Environment
import androidx.appcompat.app.AppCompatActivity
import com.ddd.twinkle.safehero.R
import timber.log.Timber

fun Context.newIntentCallingActivity() : Intent{
    val intent = Intent(this,CallingActivity::class.java)
    return intent
}

class CallingActivity : AppCompatActivity() {

    lateinit var audioRecorder: MediaRecorder
    lateinit var mPath : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calling)

        setupButton()
        setupRecorder()
        setupOutputFile()
    }

    private fun setupButton() {

    }

    private fun setupRecorder(){
        audioRecorder= MediaRecorder().apply {
            setAudioSource(MediaRecorder.AudioSource.MIC)
            setOutputFormat(MediaRecorder.OutputFormat.AAC_ADTS)
            setAudioEncoder(MediaRecorder.AudioEncoder.AAC)
            setOutputFile(setupOutputFile())
        }
        try {
            audioRecorder.prepare()
        }catch (e : Exception){
            Timber.e("${e.printStackTrace()}")
        }
    }

    private fun setupOutputFile() : String{
        mPath = Environment.getExternalStorageDirectory().absolutePath +"/record.aac"
        Timber.d("mPath is $mPath")
        return mPath
    }
}
