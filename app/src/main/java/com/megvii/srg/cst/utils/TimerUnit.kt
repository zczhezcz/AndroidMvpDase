package com.megvii.srg.cst.utils

import android.support.v4.app.FragmentActivity
import com.megvii.srg.cst.app.SysConfig
import java.util.*

class TimerUnit {
    companion object {
        fun createTimerInstance(activity: FragmentActivity, call: TaskCall, showLoad: Boolean): Timer {
            return createTimerInstance(activity, call, showLoad, SysConfig.Delay, SysConfig.Period)
        }

        fun createTimerInstance(
            activity: FragmentActivity?, call: TaskCall, showLoad: Boolean, delay: Long,
            period: Long
        ): Timer {
            val timer = Timer()
            val task = object : TimerTask() {
                override fun run() {
                    if (showLoad && activity != null) {
                        activity.runOnUiThread { ViewUtils.showLoadingDialog(activity) }
                    }
                    call.task()
                }
            }
            timer.schedule(task, delay, period)
            return timer
        }

    }

    interface TaskCall {
        fun task()
    }
}