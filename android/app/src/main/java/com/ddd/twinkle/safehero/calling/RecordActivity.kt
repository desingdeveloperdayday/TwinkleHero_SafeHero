package com.ddd.twinkle.safehero.calling

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.media.MediaRecorder
import android.os.Bundle
import android.os.SystemClock
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.ddd.twinkle.safehero.R
import kotlinx.android.synthetic.main.activity_record.*
import timber.log.Timber
import java.text.SimpleDateFormat
import java.util.*

fun Context.newIntentRecordActivity() : Intent{
    val intent= Intent(this,RecordActivity::class.java)
    return intent
}

class RecordActivity : AppCompatActivity() {

    private var audioRecorder : MediaRecorder? = null
    private var dateFormat = SimpleDateFormat("YYYY-MM-dd-HH-mm-ss")
    private var outputFileName : String? =""

    private var permissionToRecordAccepted = false
    private var permissions = arrayOf(Manifest.permission.RECORD_AUDIO)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_record)
        outputFileName = "${externalCacheDir.absolutePath}/${dateFormat.format(Date(System.currentTimeMillis()))}.mp3"
        ActivityCompat.requestPermissions(this,permissions, REQUEST_RECORD_AUDIO_PERMISSIONS)
    }

    override fun onStart() {
        super.onStart()
        setupButton()
        startRecording()
    }

    private fun setupButton() {
        button_reject.setOnClickListener {
            stopRecording()
        }
    }

    private fun stopRecording() {
        audioRecorder?.apply {
            stop()
            release()
        }
        audioRecorder=null
        timer.stop()
        finishActivity(Activity.RESULT_OK)
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

        timer.apply {
            base= SystemClock.elapsedRealtime()
            start()
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        permissionToRecordAccepted = if(requestCode == REQUEST_RECORD_AUDIO_PERMISSIONS){
            grantResults[0] == PackageManager.PERMISSION_GRANTED
        }else{
            false
        }
        if(!permissionToRecordAccepted) finish()
    }

}
