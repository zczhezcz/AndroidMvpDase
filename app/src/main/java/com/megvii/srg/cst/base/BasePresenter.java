package com.megvii.srg.cst.base;


import com.leinyo.httpclient.retrofit.HttpClient;


public abstract class BasePresenter implements IBasePresenter {
    protected String mTag;

    @Override
    public void setTag(String tag) {
        mTag = tag;
    }

    @Override
    public void destroy() {
        HttpClient.getInstance().cancelTagCall(mTag);
    }
}
