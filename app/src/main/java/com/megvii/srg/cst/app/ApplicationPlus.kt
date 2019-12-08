package com.megvii.srg.cst.app

import android.support.multidex.MultiDexApplication


import com.leinyo.httpclient.retrofit.HttpClient
import com.leinyo.httpclient.retrofit.HttpClientConfig
import com.megvii.srg.cst.core.AppConfig
import com.megvii.srg.cst.core.net.MyOkHttpConfigProvider
import com.megvii.srg.cst.utils.PreferencesUtils

import com.megvii.srg.cst.utils.ToastUtils

class ApplicationPlus : MultiDexApplication() {

    companion object {
        var instance: ApplicationPlus? = null
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        ToastUtils.init(this)
        PreferencesUtils.init(this)
        val httpClientConfig = HttpClientConfig.
                HttpClientBuilder(this, AppConfig.APP_ROOT_URL).OkHttpConfigProvider(MyOkHttpConfigProvider())
        HttpClient.getInstance().init(httpClientConfig.builder())


    }
}