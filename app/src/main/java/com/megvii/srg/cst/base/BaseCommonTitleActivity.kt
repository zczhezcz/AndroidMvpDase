package com.process.factory.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import com.leinyo.android.appbar.AppBar
import com.megvii.srg.cst.R
import kotlinx.android.synthetic.main.activity_base.*
import kotlinx.android.synthetic.main.include_title_layout.*

/**
 * 不带Present的CommonTitleActivity
 */
abstract class BaseCommonTitleActivity : AppCompatActivity() {
    protected lateinit var mToolbar: AppBar
    private lateinit var mContentLayout: FrameLayout
    /**
     * 默认左侧按钮点击事件
     */
    protected var mBackOnClickListener: View.OnClickListener = View.OnClickListener { finish() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        delegate.setContentView(R.layout.activity_base)

        initWindows()

        if (getLayoutId() != 0) {
            setContentView(getLayoutId())
        }

        initData()
    }

    protected abstract fun getLayoutId(): Int

    protected open fun initWindows() {

        mContentLayout = this.content
        mToolbar = this.toolbar
        mToolbar.setTitleConfig(getTitleViewConfig())

        setSupportActionBar(mToolbar)
        //不显示Toolbar title
        if (supportActionBar != null) {
            supportActionBar?.setDisplayShowTitleEnabled(false)
        }
    }

    override fun setContentView(layoutResID: Int) {
        if (layoutResID != 0) {
            mContentLayout.removeAllViews()
            layoutInflater.inflate(layoutResID, mContentLayout, true)
        }
    }

    override fun setContentView(view: View) {
        mContentLayout.removeAllViews()
        mContentLayout.addView(view)
    }

    override fun setContentView(view: View, params: ViewGroup.LayoutParams) {
        mContentLayout.addView(view, params)
    }

    protected open fun initData() {

    }

    open fun setTitleConfig(name:String){
        mToolbar.toolbarTitle = name

    }

    abstract fun getTitleViewConfig(): AppBar.TitleConfig


    open fun buildDefaultConfig(titleId: Int): AppBar.TitleConfig {
        return buildDefaultConfig(getString(titleId))
    }

    open fun buildDefaultConfig(title: String): AppBar.TitleConfig {
        val config = AppBar.TitleConfig(title)
        config.leftViewListener = mBackOnClickListener
        return config
    }

}