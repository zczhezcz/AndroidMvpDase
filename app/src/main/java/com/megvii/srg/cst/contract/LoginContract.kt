package com.megvii.srg.cst.contract

import com.megvii.srg.cst.entity.ResponseLoginEntity


class LoginContract{
    interface IPresenter{
        fun login()

    }

    interface IView {
        fun loginIView(entity: ResponseLoginEntity)
    }
}