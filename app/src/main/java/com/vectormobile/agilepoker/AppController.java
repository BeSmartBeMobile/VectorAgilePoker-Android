package com.vectormobile.agilepoker;

import android.app.Activity;
import android.app.Application;
import com.crashlytics.android.Crashlytics;
import com.vectormobile.agilepoker.config.di.component.AppComponent;
import io.fabric.sdk.android.Fabric;

/**
 * Created by kurt on 28/04/17.
 */
public class AppController extends Application {

    private AppComponent appComponent;

    private Activity currentActivity;

    public AppController() {
        //Fabric.with(this, new Crashlytics());
        appComponent = createComponent();
        currentActivity = null;
    }

    private AppComponent createComponent() {
        appComponent = AppComponent.Initializer.init(this);
        appComponent.inject(this);
        return appComponent;
    }

    public AppComponent getComponent() {
        if (appComponent == null) {
            createComponent();
        }
        return appComponent;
    }

    public Activity getCurrentActivity() {
        return currentActivity;
    }

    public void setCurrentActivity(Activity currentActivity) {
        this.currentActivity = currentActivity;
    }
}
