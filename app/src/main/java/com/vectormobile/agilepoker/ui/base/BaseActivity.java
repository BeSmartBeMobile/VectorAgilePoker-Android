package com.vectormobile.agilepoker.ui.base;

import android.graphics.Point;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.MenuItem;
import android.view.View;

import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * Created by daniel on 3/3/17.
 */

public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity implements PresenterView {

    private Unbinder unbinder;

    @CallSuper
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResourceId());
        injectModule();
        unbinder = ButterKnife.bind(this);
        getPresenter().create(savedInstanceState);
    }

    protected abstract void injectModule();

    protected abstract P getPresenter();

    protected abstract int getLayoutResourceId();

    public abstract <C> C getComponent();

    @CallSuper
    @Override
    public void onDestroy() {
        unbinder.unbind();
        super.onDestroy();
    }

    @CallSuper
    @Override
    protected void onResume() {
        super.onResume();
        getPresenter().attachView(this);
    }

    @CallSuper
    @Override
    protected void onPause() {
        super.onPause();
        getPresenter().detachView();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showError(String error) {
        View rootView = getWindow().getDecorView().findViewById(android.R.id.content);
        Snackbar.make(rootView, error, Snackbar.LENGTH_SHORT).show();
    }
}
