package com.megvii.srg.cst.contract

import com.megvii.srg.cst.login.ResponseLoginEntity


class LoginContract{
    interface IPresenter{
        fun login()

    }

    interface IView {
        fun loginIView(entity: ResponseLoginEntity)
    }
}