package com.process.factory.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import butterknife.ButterKnife
import butterknife.Unbinder
import com.megvii.srg.cst.base.IBasePresenter
import java.lang.reflect.Constructor
import java.lang.reflect.ParameterizedType

abstract class BaseActivityKt<T : IBasePresenter> : AppCompatActivity() {
    val TAG: String = this.javaClass.simpleName

    var mPresenter: T? = null

    private var mUnbinder: Unbinder? = null

    companion object {
        var contractPackage = "com.megvii.srg.cst"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initWindows()

        if (getLayoutId() != 0) {
            setContentView(getLayoutId())
            mUnbinder = ButterKnife.bind(this)
        }

        mPresenter = initPresenter()

        mPresenter?.setTag(TAG)

        initData()
    }

    abstract fun getLayoutId(): Int


    protected open fun initWindows() {

    }

    protected open fun initData() {

    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter?.destroy()
        mUnbinder?.unbind()
    }


    private fun initPresenter(): T? {
        val viewTypes = javaClass.genericInterfaces
        var viewType: Class<*>? = null

        for (type in viewTypes) {
            var packageName = (type as Class<*>).`package`?.name!!
            if (packageName.length >= contractPackage.length) {
                if (packageName.substring(0, contractPackage.length) == contractPackage) {
                    viewType = type
                }
            }
        }
        if (viewType != null) {
            val parameterTypes = arrayOf(viewType)

            val types: Class<T> = (javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0] as Class<T>

            if (types.canonicalName == "com.zhangcz.baseproject.base.IBasePresenter") {
                return null
            }
            val constructor: Constructor<T> = types.getConstructor(*parameterTypes)

            return constructor.newInstance(this)
        }
        return null
    }
}