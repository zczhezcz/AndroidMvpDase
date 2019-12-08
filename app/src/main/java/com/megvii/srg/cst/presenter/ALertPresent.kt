package com.megvii.srg.cst.presenter

import android.content.Context
import com.alibaba.fastjson.JSON
import com.leinyo.httpclient.retrofit.NetworkStringResponse
import com.megvii.srg.cst.base.BasePresenter
import com.megvii.srg.cst.contract.AlertContract
import com.megvii.srg.cst.core.net.httpConstants.HttpConstants
import com.megvii.srg.cst.repository.Repository
import com.megvii.srg.cst.utils.ToastUtils
import com.megvii.srg.cst.utils.ViewUtils
import com.process.factory.base.BaseResponse

class ALertPresent(private var mView: AlertContract.IView?) : BasePresenter(), AlertContract.IPresenter,
    NetworkStringResponse {


    private var repository = Repository()


    override fun haveStockOutTask() {
        repository.haveStockOutTask(mTag, this)
    }

    override fun onDataError(requestCode: Int, responseCode: Int, message: String?) {
        ViewUtils.closeLoadingDialog()
        ToastUtils.showToast(message)
    }

    override fun onDataReady(response: String?, requestCode: Int) {
        ViewUtils.closeLoadingDialog()

        try {
            when (requestCode) {
                HttpConstants.OUTBOUND_TASK_HAVE -> {
                    val result = JSON.parseObject(response, BaseResponse::class.java)
                    mView!!.haveStockOutTaskIView(result)
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