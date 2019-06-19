package com.ddd.twinkle.safehero.emergency.calling

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.media.MediaRecorder
import android.os.Bundle
import android.os.Environment
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
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
        setUserPermission()
        setupOutputFile()
    }

    private fun setUserPermission() {
        if(ActivityCompat.checkSelfPermission(this,android.Manifest.permission.RECORD_AUDIO)!=PackageManager.PERMISSION_GRANTED)
            ActivityCompat.requestPermissions(this, arrayOf( Manifest.permission.RECORD_AUDIO),10)
        else
           setupRecorder()
        
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
