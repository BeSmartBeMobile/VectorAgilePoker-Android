package com.vectormobile.agilepoker.config.di.module;

import android.content.Context;
import com.vectormobile.agilepoker.AppController;
import com.vectormobile.agilepoker.config.di.ForApplication;
import com.vectormobile.agilepoker.utils.glide.GlideWrapper;
import dagger.Module;
import dagger.Provides;

/**
 * Created by daniel on 3/3/17.
 */

@Module
public class AppModule {

    private AppController application;
    private GlideWrapper glideWrapper;

    public AppModule(AppController application) {
        this.application = application;
        glideWrapper = new GlideWrapper(application);
    }

    @Provides
    @ForApplication
    AppController provideAppController() {
        return application;
    }

    @Provides
    @ForApplication
    Context provideContext() {
        return application;
    }

    @Provides
    GlideWrapper provideGlideWrapper() {return glideWrapper;}
}
