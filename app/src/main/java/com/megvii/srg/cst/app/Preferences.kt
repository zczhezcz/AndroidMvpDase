package com.megvii.srg.cst.app


class Preferences {

    /**
     * 登陆设置
     */
    companion object Key {

        val preferenceName = "fragment_account_login"

        val password = "$preferenceName.user.password"

        val user = "$preferenceName.user"

        val resources = "$preferenceName.user.resources"


        //盘点缓存单号

        var proStockCheckId = "$preferenceName.proStockCheckId"
        var proMaterialCheck ="$proStockCheckId.material"
        var proDisableCheck ="$proStockCheckId.disable"
        var proProductCheck ="$proStockCheckId.product"
        var proConsumerCheck ="$proStockCheckId.consumer"
    }
}
