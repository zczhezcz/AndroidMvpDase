package com.megvii.srg.cst.core.net;


import android.content.Context;
import com.leinyo.httpclient.okhttp.provider.impl.DefaultOkHttpProvider;
import okhttp3.Interceptor;
import okhttp3.Request;

import java.util.List;

public class MyOkHttpConfigProvider extends DefaultOkHttpProvider {
    @Override
    public List<Interceptor> createInterceptor(Context context) {
        List<Interceptor> list = super.createInterceptor(context);
        list.add(chain -> {
            Request originalRequest = chain.request();
            Request.Builder newBuilder = originalRequest.newBuilder();
            newBuilder.addHeader("appId", "factoryPda");
            newBuilder.addHeader("X-Requested-With", "XMLHttpRequest");
            newBuilder.addHeader("X-app-With", "android");
            return chain.proceed(newBuilder.build());
        });
        return list;
    }
}

