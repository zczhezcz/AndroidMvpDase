package com.megvii.srg.cst.entity

import com.process.factory.base.BaseResponse


class ResponseLockSscc : BaseResponse() {

    var data:List<LockSscc> ?=null

    class LockSscc {
        /**
         * sku code
         */
        var skuCode: String? = null

        /**
         * sku 名称
         */
        var skuName: String? = null


        var isCheck  = false

        constructor(skuCode:String,skuName:String,isCheck:Boolean){
            this.skuCode = skuCode
            this.skuName = skuName
            this.isCheck = isCheck
        }
    }
}