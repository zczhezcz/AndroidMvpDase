package com.megvii.srg.cst.presenter

import android.content.Context
import com.leinyo.httpclient.retrofit.NetworkStringResponse
import com.megvii.srg.cst.base.BasePresenter
import com.megvii.srg.cst.contract.StockInReceiveContract
import com.megvii.srg.cst.core.net.httpConstants.StockInHttpConstants
import com.megvii.srg.cst.repository.StockInRepository
import com.megvii.srg.cst.utils.ToastUtils
import com.megvii.srg.cst.utils.ViewUtils

class StockInReceivePresent(private var mView: StockInReceiveContract.IView)
    : BasePresenter(), StockInReceiveContract.IPresenter, NetworkStringResponse {

    private val mRepository: StockInRepository = StockInRepository()

    override fun onDataError(requestCode: Int, responseCode: Int, message: String?) {
        ToastUtils.showToast(message)
        ViewUtils.closeLoadingDialog()
    }

    override fun onDataReady(response: String?, requestCode: Int) {
        ViewUtils.closeLoadingDialog()
        try {
            when (requestCode) {
                StockInHttpConstants.QUERY_PRO_CHECK_ORDER_UNDO_LIST -> {

                }
                StockInHttpConstants.COMMIT_RECEIVE_ORDER -> {

                }
            }
        }catch (e:Exception){
            ToastUtils.showToast(response)
        }

    }

    private fun getContext(): Context {
        return mView as Context
    }
}