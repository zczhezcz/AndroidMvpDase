package com.megvii.srg.cst.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity {

    protected final String TAG = this.getClass().getSimpleName();

    protected P mPresenter;

    private Unbinder mUnbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initWindows();

        mPresenter = initPresenter();

        if (mPresenter != null) {
            mPresenter.setTag(TAG);
        }

        if (getLayoutId() != 0) {
            setContentView(getLayoutId());
            mUnbinder = ButterKnife.bind(this);
        }

        initData();
    }

    protected abstract int getLayoutId();

    protected void initWindows() {

    }

    protected void initData() {

    }

    private P initPresenter() {
        Class activityClass = getClass();
        if (activityClass.getGenericSuperclass() instanceof ParameterizedType) {
            Class<P> types = (Class<P>) ((ParameterizedType) activityClass.getGenericSuperclass()).getActualTypeArguments()[0];
            if (types.isInterface()) {
                return null;
            }

            Type[] viewTypes = activityClass.getGenericInterfaces();

            Class viewType = null;

            for (Type type : viewTypes) {
                if (((Class)type).getPackage().getName().contains("com.process.factory.contract")) {
                    viewType = (Class) type;
                }
            }

            try {
                if (viewType != null) {
                    Class[] parameterTypes = new Class[]{viewType};
                    Constructor<P> constructor = types.getConstructor(parameterTypes);
                    return constructor.newInstance(this);
                } else {
                    return types.newInstance();
                }
            } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.destroy();
        }
        if (mUnbinder != null) {
            mUnbinder.unbind();
        }
    }
}
