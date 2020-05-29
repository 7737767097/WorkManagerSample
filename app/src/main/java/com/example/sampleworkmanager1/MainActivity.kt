package com.example.sampleworkmanager1

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val task = OneTimeWorkRequest.Builder(BackgroundPhotoUploader::class.java).build()
        val workManager = WorkManager.getInstance()
        workManager.enqueue(task)
        workManager.getWorkInfoByIdLiveData(task.id).observe(this, Observer {
            Log.d(TAG, "Observer -> " + it.state.name)
        })
    }
}
