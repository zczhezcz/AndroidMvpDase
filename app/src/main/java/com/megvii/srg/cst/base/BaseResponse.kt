package com.process.factory.base

open class BaseResponse{
    /**
     * 成功与否标识
     */
    var success: Boolean = false

    /**
     * 错误码
     */
    var code: Int? = null
    /**
     * 错误信息
     */
    var errorMsg: String? = null

}