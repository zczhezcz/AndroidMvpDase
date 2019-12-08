package com.megvii.srg.cst.ui.menu

import com.leinyo.android.appbar.AppBar
import com.megvii.srg.cst.R
import com.megvii.srg.cst.ui.activity.StockInReceiveActivity
import com.megvii.srg.cst.ui.menu.baseMenu.MenuItem

import com.megvii.srg.cst.ui.menu.baseMenu.BaseMenuActivity


class MenuStockInActivity: BaseMenuActivity(){

    override fun getTitleViewConfig(): AppBar.TitleConfig {
        return buildDefaultConfig(getString(R.string.menu_stock_in))
    }

    override fun registerMenu() {
        var item : MenuItem

        item = MenuItem(
            R.drawable.ic_stock_in_pro_check, getString(R.string.menu_stock_in_receive), "1.1", StockInReceiveActivity::class.java)

        mMenuList!!.add(item)

    }

}
