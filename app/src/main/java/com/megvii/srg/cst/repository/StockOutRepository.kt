package com.megvii.srg.cst.repository

import com.leinyo.httpclient.exception.HttpRequestEnum
import com.leinyo.httpclient.retrofit.HttpClient
import com.leinyo.httpclient.retrofit.HttpRequest
import com.leinyo.httpclient.retrofit.NetworkResponse
import com.megvii.srg.cst.core.net.httpConstants.StockOutHttpConstants
import com.megvii.srg.cst.core.net.urlHandler.StockOutURL
import java.util.*

class StockOutRepository{
    /**
     * 查询加工单
     */
    fun queryStockOutMaterialWork(tag: String, networkResponse: NetworkResponse) {
        val httpRequest = HttpRequest.HttpRequestBuilder(
                StockOutURL.QUERY_STOCK_OUT_MATERIAL_WORK, StockOutHttpConstants.QUERY_STOCK_OUT_MATERIAL_WORK, tag).requestWay(HttpRequestEnum.GET).create()

        HttpClient.getInstance().asyncNetWork<Any>(httpRequest, networkResponse)
    }
    /**
     * 查询出库领料
     */
    fun queryStockOutMaterial(workOrderId:String,tag: String, networkResponse: NetworkResponse) {
        val params = HashMap<String, String>()
        params["workOrderId"] = workOrderId

        val httpRequest = HttpRequest.HttpRequestBuilder(
                StockOutURL.QUERY_STOCK_OUT_MATERIAL, StockOutHttpConstants.QUERY_STOCK_OUT_MATERIAL, tag)
                .params(params).requestWay(HttpRequestEnum.GET).create()

        HttpClient.getInstance().asyncNetWork<Any>(httpRequest, networkResponse)
    }


}