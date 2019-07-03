package com.ddd.twinkle.safehero.calling

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.media.MediaRecorder
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.ddd.twinkle.safehero.R
import kotlinx.android.synthetic.main.activity_calling.*
import timber.log.Timber
import java.text.SimpleDateFormat
import java.util.*

fun Context.newIntentCallingActivity() : Intent{
    val intent = Intent(this,CallingActivity::class.java)
    return intent
}

const val REQUEST_RECORD_AUDIO_PERMISSIONS = 200

class CallingActivity : AppCompatActivity() {

    private var audioRecorder: MediaRecorder? = null

    private var outputFileName : String? =""
    private var dateFormat = SimpleDateFormat("YYYY-MM-dd-HH-mm-ss")

    private var permissionToRecordAccepted = false
    private var permissions = arrayOf(Manifest.permission.RECORD_AUDIO)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calling)
        outputFileName = "${externalCacheDir.absolutePath}/${dateFormat.format(Date(System.currentTimeMillis()))}.mp3"
        ActivityCompat.requestPermissions(this,permissions, REQUEST_RECORD_AUDIO_PERMISSIONS)
    }

    override fun onStart() {
        super.onStart()
        setupButton()
    }

    private fun setupButton() {
        button_receive.setOnClickListener {

           /* //기존 녹음 기능
            startRecording()*/
            startActivity(newIntentRecordActivity())
        }
    }


    private fun startRecording() {
        audioRecorder = MediaRecorder().apply {
            setAudioSource(MediaRecorder.AudioSource.MIC)
            setOutputFormat(MediaRecorder.OutputFormat.MPEG_4)
            setOutputFile(outputFileName)
            setAudioEncoder(MediaRecorder.AudioEncoder.AAC)
            try {
                prepare()
            }catch (e : Exception){
                Timber.e(e)
            }
            start()
            Timber.d("start")
        }

    }


    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        permissionToRecordAccepted = if(requestCode == REQUEST_RECORD_AUDIO_PERMISSIONS){
            grantResults[0] ==PackageManager.PERMISSION_GRANTED
        }else{
            false
        }
        if(!permissionToRecordAccepted) finish()
    }
}
