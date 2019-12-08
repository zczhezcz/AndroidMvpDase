package com.megvii.srg.cst.ui.menu.baseMenu

import android.support.v7.widget.GridLayoutManager
import com.megvii.srg.cst.R
import com.process.factory.base.BaseCommonTitleActivity
import kotlinx.android.synthetic.main.activity_menu.*
import java.util.*

abstract class BaseMenuActivity: BaseCommonTitleActivity() {

    var mMenuList: ArrayList<MenuItem>? = ArrayList()
    private var mMenuItemAdapter: MenuItemAdapter? = null

    private fun initRecycleView(){
        rv_menu.layoutManager = GridLayoutManager(this, 2)

        mMenuItemAdapter = MenuItemAdapter(this, mMenuList)
        //rv_menu.addItemDecoration(GirdMenuDecoration())
        rv_menu.adapter = mMenuItemAdapter
    }

    override fun initData() {
        super.initData()
        registerMenu()
        initRecycleView()
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_menu
    }

    protected abstract fun registerMenu()

}