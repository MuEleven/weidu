package com.bw.movie.base;

/**
 * 1、声明泛型V
 * 2、绑定和解绑 view
 * 3、在无参构造器中初始化model
 */
public abstract class BasePresenter<V> {
    protected V view;

    //绑定
    public void attach(V view) {
        this.view = view;
    }

    //解绑
    public void detach() {
        view = null;
    }

    //无参构造器
    public BasePresenter() {
        initModel();
    }
    

    //让子类初始化model
    protected abstract void initModel();
}
