package com.megvii.srg.cst.repository

import com.leinyo.httpclient.exception.HttpRequestEnum
import com.leinyo.httpclient.retrofit.HttpClient
import com.leinyo.httpclient.retrofit.HttpRequest
import com.leinyo.httpclient.retrofit.NetworkResponse
import com.megvii.srg.cst.core.net.httpConstants.LoginConstants
import com.megvii.srg.cst.core.net.urlHandler.LoginURL
import java.util.HashMap


class LoginRepository {

    fun login(tag: String, networkResponse: NetworkResponse) {
        val params = HashMap<String, String>()
        params.put("stationId", "111")

        val httpRequest = HttpRequest.HttpRequestBuilder(LoginURL.LOGIN,
            LoginConstants.LOGIN, tag).requestWay(HttpRequestEnum.GET).params(params).create()

        HttpClient.getInstance().asyncNetWork<Any>(httpRequest, networkResponse)
    }
}