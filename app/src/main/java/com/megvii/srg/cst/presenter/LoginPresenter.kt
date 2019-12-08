package com.megvii.srg.cst.presenter

import com.alibaba.fastjson.JSON
import com.leinyo.httpclient.retrofit.NetworkStringResponse
import com.megvii.srg.cst.base.BasePresenter
import com.megvii.srg.cst.contract.LoginContract
import com.megvii.srg.cst.core.net.httpConstants.LoginConstants
import com.megvii.srg.cst.login.ResponseLoginEntity
import com.megvii.srg.cst.repository.LoginRepository
import com.megvii.srg.cst.utils.ToastUtils
import com.megvii.srg.cst.utils.ViewUtils

open class LoginPresenter(private var mView: LoginContract.IView)
    : BasePresenter(), LoginContract.IPresenter, NetworkStringResponse {
    private var loginRepository = LoginRepository()

    override fun login() {
        loginRepository.login(mTag, this)
    }

    override fun onDataError(requestCode: Int, responseCode: Int, message: String?) {
        ViewUtils.closeLoadingDialog()
        ToastUtils.showToast(message)
    }

    override fun onDataReady(response: String?, requestCode: Int) {
        try {
            when (requestCode) {
                LoginConstants.LOGIN -> {
                    val entity = JSON.parseObject(response, ResponseLoginEntity::class.java)
                    mView.loginIView(entity)
                }
            }
        } catch (e: Exception) {
            ViewUtils.closeLoadingDialog()
            ToastUtils.showToast(response)
        }
    }
}