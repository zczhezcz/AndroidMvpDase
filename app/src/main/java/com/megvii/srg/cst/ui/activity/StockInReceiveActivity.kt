package com.megvii.srg.cst.ui.activity

import com.leinyo.android.appbar.AppBar
import com.megvii.srg.cst.R
import com.megvii.srg.cst.contract.StockInReceiveContract
import com.megvii.srg.cst.presenter.StockInReceivePresent
import com.process.factory.base.BaseTitleActivityKt

class StockInReceiveActivity: BaseTitleActivityKt<StockInReceivePresent>(),
    StockInReceiveContract.IView {
    override fun getTitleViewConfig(): AppBar.TitleConfig {
        return buildDefaultConfig(getString(R.string.stock_in_receive_title))
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_stock_in_receive
    }

}