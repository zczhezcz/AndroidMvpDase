package com.megvii.srg.cst.core.net.urlHandler

class StockOutURL{
    companion object {
        const val QUERY_STOCK_OUT_MATERIAL_WORK = "pdaMatAct/wareHouseKeeperQueryWorkOrders"
        const val QUERY_STOCK_OUT_MATERIAL = "pdaMatAct/wareHouseKeeperQueryMatAct"
        const val COMMIT_STOCK_OUT_MATERIAL = "pdaMatAct/finishMatAct"
        const val COMMIT_STOCK_OUT_OTHER = ""
    }
}