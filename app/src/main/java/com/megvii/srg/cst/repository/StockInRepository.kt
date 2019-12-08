package com.megvii.srg.cst.repository

import com.leinyo.httpclient.exception.HttpRequestEnum
import com.leinyo.httpclient.retrofit.HttpClient
import com.leinyo.httpclient.retrofit.HttpRequest
import com.leinyo.httpclient.retrofit.NetworkResponse
import com.megvii.srg.cst.core.net.httpConstants.StockInHttpConstants
import com.megvii.srg.cst.core.net.urlHandler.StockInURL
import java.util.*

class StockInRepository {
    /**
     * 提交预检单
     */
    fun commitProCheckOrder(tag: String, param: Map<String, String>,
                            networkResponse: NetworkResponse) {

        val httpRequest = HttpRequest.HttpRequestBuilder(
                StockInURL.COMMIT_PRO_CHECK_ORDER, StockInHttpConstants.COMMIT_PRO_CHECK_ORDER, tag)
                .params(param).requestWay(HttpRequestEnum.GET).create()

        HttpClient.getInstance().asyncNetWork<Any>(httpRequest, networkResponse)
    }

    /**
     *
     * 查询预检单
     */
    fun queryProCheckOrderList(tag: String, skuId: String, purchaseId: String, networkResponse: NetworkResponse) {
        var params = HashMap<String, String>()
        params.put("skuId", skuId)
        params.put("purchaseId", purchaseId)

        val httpRequest = HttpRequest.HttpRequestBuilder(
                StockInURL.QUERY_PRO_CHECK_ORDER_LIST, StockInHttpConstants.QUERY_PRO_CHECK_ORDER_LIST, tag)
                .params(params).requestWay(HttpRequestEnum.GET).create()

        HttpClient.getInstance().asyncNetWork<Any>(httpRequest, networkResponse)
    }

    /**
     *
     * 查询未完成的预检单
     */
    fun queryProCheckOrderUndoList(tag: String, skuId: String, purchaseId: String?, networkResponse: NetworkResponse) {
        var params = HashMap<String, String>()
        params.put("skuId", skuId)
        purchaseId?.let { params.put("purchseId", purchaseId) }

        val httpRequest = HttpRequest.HttpRequestBuilder(
                StockInURL.QUERY_PRO_CHECK_ORDER_UNDO_LIST, StockInHttpConstants.QUERY_PRO_CHECK_ORDER_UNDO_LIST, tag)
                .params(params).requestWay(HttpRequestEnum.GET).create()

        HttpClient.getInstance().asyncNetWork<Any>(httpRequest, networkResponse)
    }

    /**
     * 查询"生产入库"单列表(含成品，残品，退料)
     */
    fun queryProduceOrderList(produceId: String, actId: String, tag: String, networkResponse: NetworkResponse) {
        var param = HashMap<String, String>()
        param.put("workOrderId", produceId)
        param.put("actId", actId)
        val httpRequest = HttpRequest.HttpRequestBuilder(
                StockInURL.QUERY_PRODUCE_ORDER_LIST, StockInHttpConstants.QUERY_PRODUCE_ORDER_LIST, tag)
                .params(param).requestWay(HttpRequestEnum.GET).create()

        HttpClient.getInstance().asyncNetWork<Any>(httpRequest, networkResponse)
    }
}

