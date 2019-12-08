package com.megvii.srg.cst.ui.menu

import com.leinyo.android.appbar.AppBar
import com.megvii.srg.cst.R
import com.megvii.srg.cst.ui.menu.baseMenu.MenuItem
import com.megvii.srg.cst.ui.activity.StockOutMaterialWorkActivity
import com.megvii.srg.cst.ui.menu.baseMenu.BaseMenuActivity



class MenuStockOutActivity: BaseMenuActivity(){
    override fun getTitleViewConfig(): AppBar.TitleConfig {
        return buildDefaultConfig(getString(R.string.menu_stock_out))
    }


    override fun registerMenu() {
        var item = MenuItem(
            R.drawable.ic_stock_out_material,getString(R.string.menu_stock_out_material),
                "2.1", StockOutMaterialWorkActivity::class.java)
        mMenuList!!.add(item)
    }

}