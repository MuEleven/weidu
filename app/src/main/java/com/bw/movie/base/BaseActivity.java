package com.bw.movie.base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity {
    //受保护的类型 P
    protected P mPresenter;
    private Unbinder unbinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //提供布局id
        setContentView(layoutId());
        //提供p
        mPresenter = providePresenter();
        //绑定
        if (mPresenter != null) {
            mPresenter.attach(this);
        }
        //初始化view
        unbinder = ButterKnife.bind(this);

        //特殊view的监听
        initView();
        //初始化数据
        initData();
    }

    protected abstract void initData();

    protected abstract void initView();

    protected abstract P providePresenter();

    protected abstract int layoutId();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //解绑
        if (mPresenter != null) {
            mPresenter.detach();
        }
        //butterkni 解绑，不要求
        if (unbinder != null) {
            unbinder.unbind();
        }
    }
}
