package com.csh.favoritefood

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.util.Log
import com.csh.favoritefood.dao.DBHelper

private const val TAG = "ReviewBoundService"
class ReviewBoundService : Service() {

    private lateinit var dbHelper: DBHelper

    override fun onBind(intent: Intent): IBinder {
        return myBinder
    }

    val myBinder: IBinder = LocalBinder()
    inner class LocalBinder: Binder(){
        fun getService(): ReviewBoundService{
            return this@ReviewBoundService
        }
    }

    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "onCreate ing...")
        dbHelper = DBHelper(this)
        dbHelper.writableDatabase
        Log.d(TAG, "onCreate dao...")
    }
}