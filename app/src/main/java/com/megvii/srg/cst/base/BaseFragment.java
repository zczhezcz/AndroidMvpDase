package com.megvii.srg.cst.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import com.megvii.srg.cst.R;


import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;


public abstract class BaseFragment<P extends BasePresenter> extends Fragment {

    protected final String TAG = this.getClass().getSimpleName();

    protected View mRootView;

    protected boolean mHasInit;

    protected P mPresenter;

    Unbinder unbinder;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mRootView == null) {
            mRootView = inflater.inflate(getLayoutId(), container, false);
        }
        return mRootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (!mHasInit) {
            mPresenter = initPresenter();
            findViews();
            initData();
        }
        mHasInit = true;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mRootView != null) {
            ViewGroup parent = ((ViewGroup) mRootView.getParent());
            if (parent != null) {
                parent.removeView(mRootView);
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (unbinder != null) {
            unbinder.unbind();
        }
        if (mPresenter != null) {
            mPresenter.destroy();
        }
    }

    protected View findViewById(int id) {
        return mRootView.findViewById(id);
    }

    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
        if (getActivity() != null) {
            getActivity().overridePendingTransition(R.anim.alpha_in, R.anim.alpha_out);
        }
    }

    protected abstract int getLayoutId();

    /**
     * 查找控件
     */
    protected void findViews() {
        if (mPresenter != null) {
            mPresenter.setTag(TAG);
        }
        unbinder = ButterKnife.bind(this, mRootView);
    }

    /**
     * 初始化数据
     */
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
                if (((Class)type).getPackage().getName().contains("com.xstore.wms.contract")) {
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
            } catch (NoSuchMethodException | java.lang.InstantiationException | IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
