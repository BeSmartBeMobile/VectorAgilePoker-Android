package com.vectormobile.agilepoker.config.di.module;

import com.vectormobile.agilepoker.config.di.ActivityScope;
import com.vectormobile.agilepoker.ui.base.BaseActivity;
import dagger.Module;
import dagger.Provides;

/**
 * Created by daniel on 3/3/17.
 */

@Module
@ActivityScope
public abstract class ActivityModule<A extends BaseActivity> {

    protected A activity;

    public ActivityModule(A activity) {
        this.activity = activity;
    }

    @Provides
    @ActivityScope
    A getActivity() {
        return activity;
    }
}
