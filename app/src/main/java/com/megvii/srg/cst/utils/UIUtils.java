package com.megvii.srg.cst.utils;

import android.content.Context;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

/**
 * Created by xing.ru on 2017/12/28.
 */

public class UIUtils {

    /**
     * 判断软键盘是否开启
     *
     * @param context
     * @return
     */
    public static boolean isShowSoftKeyBoard(Context context) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        boolean isOpen = imm.isActive();//isOpen若返回true，则表示输入法打开
        return isOpen;
    }

    /**
     * 隐藏软键盘
     * @param context
     * @param editText
     */
    public static void hideSoftKeyBoard(Context context, EditText editText) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        //隐藏软键盘
        imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
    }

    /**
     * 显示软键盘
     */
    public static void showSoftKeyBoard(Context context){
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
    }
}
