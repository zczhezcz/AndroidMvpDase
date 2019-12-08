package com.megvii.srg.cst.core.net.urlHandler
class StockInURL{
    companion object {
        /**
         * 提交预检单
         */
        const val COMMIT_PRO_CHECK_ORDER:String = "beforecheck/save"
        /**
         * 查询预检单列表
         */
        const val QUERY_PRO_CHECK_ORDER_LIST:String = "beforecheck/findSkuRateAndBeforeCheck"
        const val QUERY_PRO_CHECK_ORDER_UNDO_LIST:String = "beforecheck/find"
        /**
         * 查询生产入库列表
         */
        const val QUERY_PRODUCE_ORDER_LIST :String = "pdaWarehouseIn/findWareAndMatActAndProfit"
        /**
         * 查询入库加工单
         */
        const val QUERY_PRODUCE_WORK_LIST:String = "pdaWarehouseIn/wareHouseKeeperQueryWorkOrders"
        /**
         * 提交成品入库
         */
        const val COMMIT_PRODUCE_ORDER_MADE:String = "pdaWarehouseIn/finishWarehouseInRecode"
        /**
         * 提交残品入库
         */
        const val COMMIT_PRODUCE_ORDER_DEFECTIVE:String = "pdaWarehouseIn/finishMatProfit"
        /**
         * 提交退料入库
         */
        const val COMMIT_PRODUCE_ORDER_MATERIAL:String = "pdaWarehouseIn/finishMatact"
        /**
         * 收货入库
         */
        const val COMMIT_RECEIVE_ORDER: String = "putInBills/save"
        /**
         *  退货入库
         */
        const val QUERY_GOODS_BACK_LIST: String = "order/rtv/item/rtv_list"
        const val COMMIT_GOODS_BACK_ORDER: String = "order/rtv/do_storage"


    }
}