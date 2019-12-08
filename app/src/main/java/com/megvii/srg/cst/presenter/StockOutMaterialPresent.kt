package com.megvii.srg.cst.presenter

import android.content.Context
import com.alibaba.fastjson.JSON
import com.leinyo.httpclient.retrofit.NetworkStringResponse
import com.megvii.srg.cst.base.BasePresenter
import com.megvii.srg.cst.contract.StockOutMaterialContract
import com.megvii.srg.cst.core.net.httpConstants.StockOutHttpConstants
import com.megvii.srg.cst.repository.StockOutRepository
import com.megvii.srg.cst.utils.ToastUtils
import com.megvii.srg.cst.utils.ViewUtils
import com.process.factory.base.BaseResponse

class StockOutMaterialPresent(private var mView: StockOutMaterialContract.IView)
    : BasePresenter(), StockOutMaterialContract.IPresenter, NetworkStringResponse {

    private val repository = StockOutRepository()



    override fun onDataError(requestCode: Int, responseCode: Int, message: String?) {
        ViewUtils.closeLoadingDialog()
        ToastUtils.showToast(message)
    }

    override fun onDataReady(response: String?, requestCode: Int) {
        try {
            when (requestCode) {
                StockOutHttpConstants.QUERY_STOCK_OUT_MATERIAL_WORK -> {

                }

                StockOutHttpConstants.QUERY_STOCK_OUT_MATERIAL -> {

                }

                StockOutHttpConstants.COMMIT_STOCK_OUT_MATERIAL -> {

                }
            }
        } catch (e: Exception) {
            ToastUtils.showToast(response)
        }
    }

    private fun getContext(): Context {
        return mView as Context
    }
}