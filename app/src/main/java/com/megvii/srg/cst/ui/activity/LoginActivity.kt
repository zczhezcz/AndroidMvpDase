package com.megvii.srg.cst.ui.activity

import com.megvii.srg.cst.R
import com.megvii.srg.cst.contract.LoginContract
import com.megvii.srg.cst.login.ResponseLoginEntity
import com.megvii.srg.cst.presenter.LoginPresenter
import com.megvii.srg.cst.utils.ToastUtils
import com.process.factory.base.BaseActivityKt
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseActivityKt<LoginPresenter>(), LoginContract.IView{
    override fun initData() {
        super.initData()
        this.bt_login.setOnClickListener {
            mPresenter!!.login()
        }
    }


    override fun getLayoutId(): Int {
        return R.layout.activity_login
    }

    override fun loginIView(entity: ResponseLoginEntity) {
        if(entity.success){
            ToastUtils.showToast(entity.errorMsg)
        }
    }

}
