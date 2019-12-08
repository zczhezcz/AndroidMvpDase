package com.megvii.srg.cst.utils;

import android.content.Context;
import android.content.SharedPreferences;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class PreferencesUtils {
    public static final String FILE_NAME = "process-factory-pda";

    private static SharedPreferences sSharedPreferences;

    public static void clear() {

    }

    public static void init(Context context) {
        sSharedPreferences = context.getSharedPreferences(FILE_NAME,
                Context.MODE_PRIVATE);
    }

    public static void put(String key, Object object) {
        SharedPreferences.Editor editor = sSharedPreferences.edit();
        if (object instanceof String) {
            editor.putString(key, (String) object);
        } else if (object instanceof Integer) {
            editor.putInt(key, (Integer) object);
        } else if (object instanceof Boolean) {
            editor.putBoolean(key, (Boolean) object);
        } else if (object instanceof Float) {
            editor.putFloat(key, (Float) object);
        } else if (object instanceof Long) {
            editor.putLong(key, (Long) object);
        } else {
            editor.putString(key, object.toString());
        }
        SharedPreferencesCompat.apply(editor);
    }

    public static void putStoreId(Long token) {
        put("store_id", token);
    }

    /**
     * 登入登录标记， 登录一天后失效
     *
     * @return
     */
    public static Long getStoreId() {
        return sSharedPreferences.getLong("store_id", 0);
    }




    public static void putToken(String token) {
        put("token", token);
    }

    /**
     * 登入登录标记， 登录一天后失效
     *
     * @return
     */
    public static String getToken() {
        return sSharedPreferences.getString("token", "");
    }

    /**
     * 登入的账户PIN，（配送员）
     *
     * @return
     */
    public static String getCarrierPin() {
        return sSharedPreferences.getString("carrierPin", "");
    }

    public static Object get(String key, Object defaultObject) {
        if (defaultObject instanceof String) {
            return sSharedPreferences.getString(key, (String) defaultObject);
        } else if (defaultObject instanceof Integer) {
            return sSharedPreferences.getInt(key, (Integer) defaultObject);
        } else if (defaultObject instanceof Boolean) {
            return sSharedPreferences.getBoolean(key, (Boolean) defaultObject);
        } else if (defaultObject instanceof Float) {
            return sSharedPreferences.getFloat(key, (Float) defaultObject);
        } else if (defaultObject instanceof Long) {
            return sSharedPreferences.getLong(key, (Long) defaultObject);
        }
        return null;
    }

    /**
     * 移除某个key值已经对应的值
     *
     * @param key
     */
    public static void remove(String key) {
        SharedPreferences.Editor editor = sSharedPreferences.edit();
        editor.remove(key);
        SharedPreferencesCompat.apply(editor);
    }

    /**
     * 清除所有数据
     */
    public static void clearAll() {
        SharedPreferences.Editor editor = sSharedPreferences.edit();
        editor.clear();
        SharedPreferencesCompat.apply(editor);
    }

    /**
     * 查询某个key是否已经存在
     *
     * @param key
     * @return
     */
    public static boolean contains(String key) {
        return sSharedPreferences.contains(key);
    }

    /**
     * 创建一个解决SharedPreferencesCompat.apply方法的一个兼容类
     */
    private static class SharedPreferencesCompat {
        private static final Method sApplyMethod = findApplyMethod();

        /**
         * 反射查找apply的方法
         *
         * @return
         */
        @SuppressWarnings({"unchecked", "rawtypes"})
        private static Method findApplyMethod() {
            try {
                Class clz = SharedPreferences.Editor.class;
                return clz.getMethod("apply");
            } catch (NoSuchMethodException e) {
            }

            return null;
        }

        /**
         * 如果找到则使用apply执行，否则使用commit
         *
         * @param editor
         */
        public static void apply(SharedPreferences.Editor editor) {
            try {
                if (sApplyMethod != null) {
                    sApplyMethod.invoke(editor);
                    return;
                }
            } catch (IllegalArgumentException e) {
            } catch (IllegalAccessException e) {
            } catch (InvocationTargetException e) {
            }
            editor.commit();
        }
    }
}  
