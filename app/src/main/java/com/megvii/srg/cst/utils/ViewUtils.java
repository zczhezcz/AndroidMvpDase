package com.megvii.srg.cst.utils;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.megvii.srg.cst.widget.LoadingDialog;


import java.lang.reflect.Field;

/**
 * Created by hly on 16/7/29.
 * email hugh_hly@sina.cn
 */
public class ViewUtils {

    private static LoadingDialog sMLoadingDialogFragment;

    private static boolean isDialogShowing;

    /**
     * 显示加载提示框
     *
     * @param message 提示信息
     * @return
     */

    public static synchronized LoadingDialog showLoadingDialog(FragmentActivity activity, String message) {
        if (!isDialogShowing) {
            sMLoadingDialogFragment = LoadingDialog.createDialogFragment(message);
            // mKKDialogFragment.show(activity.getSupportFra/*gmentManager(), "Loading");
            sMLoadingDialogFragment.setCancelable(false);
            FragmentTransaction ft = activity.getSupportFragmentManager().beginTransaction();
            ft.add(sMLoadingDialogFragment, "Loading");
            ft.commitAllowingStateLoss();
        } else {
            sMLoadingDialogFragment.setMessage(message);
        }
        isDialogShowing = true;
        return sMLoadingDialogFragment;
    }

    public static synchronized LoadingDialog showLoadingDialog(FragmentActivity activity) {
        return showLoadingDialog(activity, "正在请求，请稍后...");
    }

    /**
     * 关闭加载提示框
     */
    public static synchronized void closeLoadingDialog() {
        if (sMLoadingDialogFragment != null) {
            sMLoadingDialogFragment.dismissAllowingStateLoss();
            if (sMLoadingDialogFragment.isDetached()) {
                sMLoadingDialogFragment.onDetach();
            }
            sMLoadingDialogFragment = null;
        }
        isDialogShowing = false;
    }

    /**
     * 判断是否显示中
     *
     * @return
     */
    public static synchronized boolean isShowing() {
        return sMLoadingDialogFragment != null && sMLoadingDialogFragment.getDialog() != null && sMLoadingDialogFragment.getDialog().isShowing();
    }

    public static void setIndicator(TabLayout tabs, int leftDip, int rightDip) {
        Class<?> tabLayout = tabs.getClass();
        Field tabStrip = null;
        try {
            tabStrip = tabLayout.getDeclaredField("mTabStrip");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        tabStrip.setAccessible(true);
        LinearLayout llTab = null;
        try {
            llTab = (LinearLayout) tabStrip.get(tabs);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        int left = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, leftDip, Resources.getSystem().getDisplayMetrics());
        int right = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, rightDip, Resources.getSystem().getDisplayMetrics());

        for (int i = 0; i < llTab.getChildCount(); i++) {
            View child = llTab.getChildAt(i);
            child.setPadding(0, 0, 0, 0);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1);
            params.leftMargin = left;
            params.rightMargin = right;
            child.setLayoutParams(params);
            child.invalidate();
        }
    }

    /**
     * 获取Activity的根View
     *
     * @param activity
     * @return
     */
    public static View getRootView(Activity activity) {
        return ((ViewGroup) activity.findViewById(android.R.id.content)).getChildAt(0);
    }


    public static void showAlertDialog(Context context, String message,
                                       DialogInterface.OnClickListener positiveListener,
                                       DialogInterface.OnClickListener negativeListener) {
        showAlertDialogSelf(context, "", message, positiveListener, negativeListener,
                "确定", "取消",null,null);
    }

    public static void showAlertDialogSelf(Context context, String title, String message,
                                           DialogInterface.OnClickListener positiveListener,
                                           DialogInterface.OnClickListener negativeListener,
                                           String positiveText, String negativeText) {

        AlertDialog dialog = getDialogBuilder(context,title,message,positiveListener,negativeListener,
                positiveText,negativeText,null,null).create();
        dialog.setCancelable(true); // 对话框以外 及 返回按键 不起作用
        dialog.show();
        dialog.getButton(DialogInterface.BUTTON_NEGATIVE).setTextColor(Color.parseColor("#FF6D00"));
    }

    public static void showAlertDialogSelf(Context context, String title, String message,
                                           DialogInterface.OnClickListener positiveListener,
                                           DialogInterface.OnClickListener negativeListener,
                                           String positiveText, String negativeText,String neutralText,
                                           DialogInterface.OnClickListener neutralListener) {

        AlertDialog dialog = getDialogBuilder(context,title,message,positiveListener,negativeListener,
                positiveText,negativeText,neutralText,neutralListener).create();
        dialog.setCancelable(true);
        dialog.show();
        dialog.getButton(DialogInterface.BUTTON_NEGATIVE).setTextColor(Color.parseColor("#FF6D00"));
    }

    private static AlertDialog.Builder getDialogBuilder(Context context, String title, String message,
                                                        DialogInterface.OnClickListener positiveListener,
                                                        DialogInterface.OnClickListener negativeListener,
                                                        String positiveText,
                                                        String negativeText,
                                                        String neutralText,
                                                        DialogInterface.OnClickListener neutralListener){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setCancelable(true);
        if (!TextUtils.isEmpty(title)) {
            builder.setTitle(title);
        }

        if (!TextUtils.isEmpty(message)) {
            builder.setMessage(message);
        }

        if (positiveListener != null) {
            builder.setPositiveButton(positiveText, positiveListener);
        }

        if (negativeListener != null) {
            builder.setNegativeButton(negativeText, negativeListener);
        }

        // 第三个按钮
        if(neutralListener != null) {
            builder.setNeutralButton(neutralText, neutralListener);
        }
        return  builder;
    }
}
