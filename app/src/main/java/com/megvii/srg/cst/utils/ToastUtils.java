package com.megvii.srg.cst.utils;

import android.content.Context;
import android.content.DialogInterface;
import android.text.TextUtils;
import android.view.Gravity;
import android.widget.Toast;

public class ToastUtils {
    private static Context mContext;
    private static String mOldMsg;
    protected static Toast mToast = null;
    private static long mOneTime = 0;
    private static long mTwoTime = 0;
    private static final int LONG_ERROR_MESSAGE = 20;

    /**
     * 初始化Toast
     *
     * @param ctx 必须使用Application，否则会造成内存泄漏
     */
    public static void init(Context ctx) {
        mContext = ctx;
    }


    public static void showToast(int resId) {
        showToast(mContext.getString(resId));
    }

    public static void showToast(String s) {
        if (TextUtils.isEmpty(s)) {
            //s = " ";
            return;
        }
        if (mToast == null) {
            mToast = Toast.makeText(mContext, s, Toast.LENGTH_SHORT);
            mToast.setGravity(Gravity.CENTER, 0, 0);
            mToast.show();
            mOneTime = System.currentTimeMillis();
        } else {
            mTwoTime = System.currentTimeMillis();
            if (s.equals(mOldMsg)) {
                if (mTwoTime - mOneTime > Toast.LENGTH_SHORT) {
                    mToast.setGravity(Gravity.CENTER, 0, 0);
                    mToast.show();
                }
            } else {
                mOldMsg = s;
                mToast.setText(s);
                mToast.setGravity(Gravity.CENTER, 0, 0);
                mToast.show();
            }
        }
        mOneTime = mTwoTime;
    }

    public static void showDiffLength(String msg, String title, Context context){
        if(TextUtils.isEmpty(msg)){
            showToast("系统错误，消息为空");
        }
        if(msg.length() <= LONG_ERROR_MESSAGE) {
            //字数长度小于20，使用吐司
            showToast(msg);
        }else{
            //字数长度大于20，使用"确定"信息
            ViewUtils.showAlertDialogSelf(context ,title, msg,
                    (dialogInterface, i) -> {
                    }, null,"确定","取消");
        }
    }

    public static void showDiffLength(String msg, String title, Context context,
                                      DialogInterface.OnClickListener positiveListener){
        if(msg.length() <= LONG_ERROR_MESSAGE) {
            //字数长度小于20，使用吐司
            showToast(msg);
        }else{
            //字数长度大于20，使用"确定"信息
            ViewUtils.showAlertDialogSelf(context ,title, msg,
                    positiveListener, null,"确定","取消");
        }
    }
}
