package com.megvii.srg.cst.ui

import com.leinyo.android.appbar.AppBar
import com.megvii.srg.cst.R
import com.megvii.srg.cst.ui.menu.MenuStockInActivity
import com.megvii.srg.cst.ui.menu.MenuStockOutActivity
import com.megvii.srg.cst.ui.menu.baseMenu.BaseMenuActivity
import com.megvii.srg.cst.ui.menu.baseMenu.MenuItem


class MainActivityKt: BaseMenuActivity(){

    override fun getTitleViewConfig(): AppBar.TitleConfig {
        val titleConfig = AppBar.TitleConfig()
        titleConfig.title = getString(R.string.menu_main)

        return titleConfig

    }

    override fun registerMenu() {
        var item = MenuItem(R.drawable.ic_stock_in, getString(R.string.menu_stock_in), "1", MenuStockInActivity::class.java)
        mMenuList!!.add(item)

        item = MenuItem(R.drawable.ic_stock_out, getString(R.string.menu_stock_out), "2", MenuStockOutActivity::class.java)
        mMenuList!!.add(item)
    }


}
