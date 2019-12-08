package com.megvii.srg.cst.app

class SysConfig{
    companion object {
        //自动刷新的间隔(毫秒)
        const val Period = 1000L * 20 //20秒
        //首次刷新的延迟
        const val Delay = 1000L * 15  //15秒
    }
}