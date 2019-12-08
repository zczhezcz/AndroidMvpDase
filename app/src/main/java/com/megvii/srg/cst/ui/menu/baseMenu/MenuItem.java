package com.megvii.srg.cst.ui.menu.baseMenu;

import java.util.Map;


public class MenuItem {
    private int _iconId;
    private String _title;
    private String _tag;
    private Class<?> _activity;
    private Map<String,?> parameter;

    public MenuItem(int iconId, String title, String tag, Class<?> activity) {
        _iconId = iconId;
        _title = title;
        _tag = tag;
        _activity = activity;
    }

    public MenuItem(String _title, String _tag, Class<?> _activity) {
        this._title = _title;
        this._tag = _tag;
        this._activity = _activity;
    }

    public MenuItem(int iconId, String title, String tag, Class<?> activity, Map<String,?> parameter) {
        _iconId = iconId;
        _title = title;
        _tag = tag;
        _activity = activity;
        this.parameter = parameter;
    }

    public int getIconId() {
        return _iconId;
    }

    public void setIconId(int iconId) {
        this._iconId = iconId;
    }

    public String getTitle() {
        return _title;
    }

    public void setTitle(String title) {
        this._title = title;
    }

    public String getTag() {
        return _tag;
    }

    public void setTag(String tag) {
        this._tag = tag;
    }

    public Class getActivity() {
        return _activity;
    }

    public void setActivity(Class<?> _activity) {
        this._activity = _activity;
    }

    public Map<String, ?> getParameter() {
        return parameter;
    }

    public void setParameter(Map<String, ?> parameter) {
        this.parameter = parameter;
    }
}
