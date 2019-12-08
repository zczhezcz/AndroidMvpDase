package com.megvii.srg.cst.core.net.httpConstants

class  StockInHttpConstants{
    /**
     * 入库HttpConstants:1000~2000
     */
    companion object {
        /**
         * 提交预检单
         */
        const val COMMIT_PRO_CHECK_ORDER = 1001
        const val QUERY_PRO_CHECK_ORDER_LIST = 1002
        const val QUERY_PRODUCE_WORK_LIST =1003
        const val QUERY_PRODUCE_ORDER_LIST = 1004
        const val COMMIT_RECEIVE_ORDER = 1005
        const val QUERY_PRO_CHECK_ORDER_UNDO_LIST = 1006
        const val COMMIT_PRODUCE_ORDER = 1007
        const val QUERY_GOODS_BACK_LIST  =1008
        const val COMMIT_GOODS_BACK_ORDER = 1009
    }
}