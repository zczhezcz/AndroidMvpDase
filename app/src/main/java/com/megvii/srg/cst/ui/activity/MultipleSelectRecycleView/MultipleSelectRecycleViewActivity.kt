package com.megvii.srg.cst.ui.activity.MultipleSelectRecycleView

import android.support.v7.widget.LinearLayoutManager
import com.leinyo.android.appbar.AppBar
import com.megvii.srg.cst.R
import com.megvii.srg.cst.contract.StockOutMaterialContract
import com.megvii.srg.cst.entity.ResponseLockSscc
import com.megvii.srg.cst.presenter.StockOutMaterialPresent
import com.process.factory.base.BaseTitleActivityKt
import kotlinx.android.synthetic.main.activity_stock_out_material_work.*
import java.util.*
import kotlin.collections.ArrayList

class MultipleSelectRecycleViewActivity : BaseTitleActivityKt<StockOutMaterialPresent>()
, StockOutMaterialContract.IView{

    lateinit var mAdapter: MultipleSelectAdapter
    override fun getLayoutId(): Int {
        return R.layout.activity_stock_out_material_work
    }

    override fun getTitleViewConfig(): AppBar.TitleConfig {
        return buildDefaultConfig(getString(R.string.stock_out_material_list_title))
    }

    override fun initData() {
        val itemList = ArrayList<ResponseLockSscc.LockSscc>()

        itemList.add(ResponseLockSscc.LockSscc("001","巧克力",false))
        itemList.add(ResponseLockSscc.LockSscc("002","牛奶",false))
        itemList.add(ResponseLockSscc.LockSscc("003","棒棒糖",false))

        mAdapter = MultipleSelectAdapter(
            this,
            ctv_check_all,
            itemList
        )
        rv_outbound_detail.layoutManager = LinearLayoutManager(this)
        rv_outbound_detail.adapter = mAdapter

    }
}