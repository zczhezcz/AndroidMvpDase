package com.megvii.srg.cst.base;

import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.leinyo.android.appbar.AppBar;
import com.megvii.srg.cst.R;

public abstract class BaseTitleActivity<P extends BasePresenter> extends BaseActivity<P> {

    protected AppBar mToolbar;
    FrameLayout mContentLayout;

    @Override
    protected void initWindows() {
        super.initWindows();
        getDelegate().setContentView(R.layout.activity_base);
        mContentLayout = findViewById(R.id.content);
        mToolbar = findViewById(R.id.toolbar);
    }

    @Override
    public void setContentView(int layoutResID) {
        if (layoutResID != 0) {
            mContentLayout.removeAllViews();
            getLayoutInflater().inflate(layoutResID, mContentLayout, true);
        }
    }

    @Override
    public void setContentView(View view) {
        mContentLayout.removeAllViews();
        mContentLayout.addView(view);
    }

    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        mContentLayout.addView(view, params);
    }

    @Override
    protected void initData() {
        super.initData();
        if (mToolbar == null) {
            mToolbar = findViewById(R.id.toolbar);
        }
        setSupportActionBar(mToolbar);
        mToolbar.setTitleConfig(getTitleViewConfig());

        //不显示Toolbar title
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
    }

    public abstract AppBar.TitleConfig getTitleViewConfig();

    /**
     * 1> 设置左边返回按钮的整件
     * 2> 设置标题文本
     *
     * @param title
     * @return
     */
    public AppBar.TitleConfig buildDefaultConfig(String title) {
        AppBar.TitleConfig config = new AppBar.TitleConfig(title);
        config.leftViewListener = mBackOnClickListener;
        return config;
    }

    /**
     * 默认左侧按钮点击事件
     */
    public View.OnClickListener mBackOnClickListener = v -> finish();
}
