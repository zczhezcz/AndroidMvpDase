package com.megvii.srg.cst.service

import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder
import android.support.v4.app.NotificationCompat
import android.util.Log
import com.megvii.srg.cst.R
import com.megvii.srg.cst.contract.AlertContract
import com.megvii.srg.cst.presenter.ALertPresent
import com.megvii.srg.cst.ui.activity.LoginActivity
import com.megvii.srg.cst.utils.TimerUnit
import com.process.factory.base.BaseResponse
import java.util.*


class AlertService : Service(), AlertContract.IView {
    private val TAG = AlertService::class.java.getSimpleName()

    private val FOREGROUND_ID = 1000
    private var timer: Timer? = Timer()
    protected final var mtag = this.javaClass.simpleName;

    private var mPresent = ALertPresent(this)

    override fun onCreate() {
        Log.i(TAG, "WhiteService->onCreate")
        super.onCreate()
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        Log.i(TAG, "WhiteService->onStartCommand")

        val builder = NotificationCompat.Builder(this)
        builder.setSmallIcon(R.drawable.ic_stock_out)
        builder.setContentTitle("Foreground")
        builder.setContentText("I am a foreground service")
        builder.setContentInfo("Content Info")
        builder.setWhen(System.currentTimeMillis())
        val activityIntent = Intent(this, LoginActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(this, 1, activityIntent, PendingIntent.FLAG_UPDATE_CURRENT)
        builder.setContentIntent(pendingIntent)
        val notification = builder.build()
        startForeground(FOREGROUND_ID, notification)

        initTimer()
        mPresent.setTag(mtag)
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onBind(intent: Intent): IBinder? {
        // TODO: Return the communication channel to the service.
        throw UnsupportedOperationException("Not yet implemented")
    }

    override fun onDestroy() {
        Log.i(TAG, "WhiteService->onDestroy")
        timer?.cancel()
        timer = null
        super.onDestroy()
    }

    private fun initTimer() {
        timer = TimerUnit.createTimerInstance(null, object : TimerUnit.TaskCall {
            override fun task() {
                mPresent.haveStockOutTask()
            }
        }, false, 1000L, 10 * 1000L)
    }

    override fun haveStockOutTaskIView(result: BaseResponse) {
        if (result.success) {
            var mMediaPlayer: MediaPlayer? = null
            mMediaPlayer = MediaPlayer.create(this, R.raw.alert)
            mMediaPlayer!!.start()
        }
    }
}