package com.vectormobile.agilepoker.ui.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import timber.log.Timber;

/**
 * Created by daniel on 3/3/17.
 */

public abstract class Presenter<View> {

    @Nullable
    private View view;

    protected void onCreate(@Nullable Bundle savedState) {
    }

    protected void onAttachView(View view) {
    }

    protected void onDetachView() {
    }

    @Nullable
    public View getView() {
        return view;
    }

    public void create(Bundle bundle) {
        onCreate(bundle);
        Timber.d("%s::create", getClass().getSimpleName());
    }

    public void attachView(View view) {
        this.view = view;
        onAttachView(view);
        Timber.d("%s::attachView", getClass().getSimpleName());
    }

    public void detachView() {
        onDetachView();
        Timber.d("%s::detachView", getClass().getSimpleName());
        this.view = null;
    }
}
