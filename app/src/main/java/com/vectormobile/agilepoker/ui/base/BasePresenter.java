package com.vectormobile.agilepoker.ui.base;

/**
 * Created by daniel on 3/3/17.
 */

public class BasePresenter<V extends PresenterView> extends Presenter<V> {

    protected V view;

    public BasePresenter(V view) {
        this.view = view;
    }
}
