package com.ddd.twinkle.safehero.emergency.calling

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.media.MediaRecorder
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.ddd.twinkle.safehero.R
import timber.log.Timber
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

fun Context.newIntentCallingActivity() : Intent{
    val intent = Intent(this,CallingActivity::class.java)
    return intent
}

class CallingActivity : AppCompatActivity() {

    lateinit var audioRecorder: MediaRecorder
    lateinit var outFile : File

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calling)
        requestPermissionsForRecordAudio()
    }

    override fun onStart() {
        super.onStart()
        setupRecorder()
    }
    private fun requestPermissionsForRecordAudio() {
        if(ActivityCompat.checkSelfPermission(this,Manifest.permission.RECORD_AUDIO)!=PackageManager.PERMISSION_GRANTED)
            ActivityCompat.requestPermissions(this, arrayOf( Manifest.permission.RECORD_AUDIO),10)
        else {
            setupRecorder()
            setupOutputFile()
            startRecording()
        }
    }

    private fun startRecording() {
        try {
            audioRecorder.prepare()
            audioRecorder.start()
            Timber.d("voidce Recorder + ${outFile.absolutePath}")
        }catch (e : IOException){
            Timber.e(e)
        }
    }

    private fun setupOutputFile(){
        outFile = getOutputFile()
        outFile.parentFile.mkdir()
        audioRecorder.setOutputFile(outFile.absolutePath)
    }

    private fun getOutputFile() : File {
        val dataFormat  = SimpleDateFormat("yyyyMMdd_HHMMSSss", Locale.KOREA)
        return File(Environment.getExternalStorageDirectory().absolutePath.toString()
        +"Voice Recorder/RECORDING_"
        +dataFormat.format(Date())
        +".m4a")
    }

    private fun setupRecorder() {
        audioRecorder = MediaRecorder().apply {
            setAudioSource(MediaRecorder.AudioSource.MIC)
            setOutputFormat(MediaRecorder.OutputFormat.MPEG_4)
            if(Build.VERSION.SDK_INT >=Build.VERSION_CODES.JELLY_BEAN){
                this.setAudioEncoder(MediaRecorder.AudioEncoder.HE_AAC)
                this.setAudioEncodingBitRate(48000)
            }else{
                this.setAudioEncoder(MediaRecorder.AudioEncoder.AAC)
                this.setAudioEncodingBitRate(64000)
            }
            setAudioSamplingRate(16000)
        }


    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
       when(requestCode){
           10->{
               if (grantResults[0]==PackageManager.PERMISSION_GRANTED)
                   setupRecorder()
               else
                   Toast.makeText(this,"녹음 기능이 필요합니다.",Toast.LENGTH_LONG).show()
           }
       }
    }
}
