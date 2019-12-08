package com.megvii.srg.cst.ui.activity

import com.leinyo.android.appbar.AppBar
import com.megvii.srg.cst.R
import com.megvii.srg.cst.contract.StockOutMaterialContract
import com.megvii.srg.cst.presenter.StockOutMaterialPresent
import com.process.factory.base.BaseTitleActivityKt

class StockOutMaterialWorkActivity : BaseTitleActivityKt<StockOutMaterialPresent>()
, StockOutMaterialContract.IView{

    override fun getLayoutId(): Int {
        return R.layout.activity_stock_out_material_work
    }

    override fun getTitleViewConfig(): AppBar.TitleConfig {
        return buildDefaultConfig(getString(R.string.stock_out_material_list_title))
    }
}