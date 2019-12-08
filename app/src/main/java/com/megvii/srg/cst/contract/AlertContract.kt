package com.megvii.srg.cst.contract

import com.process.factory.base.BaseResponse

class AlertContract{
    interface IPresenter{
        fun haveStockOutTask()
    }

    interface IView {
        fun haveStockOutTaskIView(result: BaseResponse)
    }
}