package com.vectormobile.agilepoker.config.di.component;

import android.content.Context;
import com.vectormobile.agilepoker.AppController;
import com.vectormobile.agilepoker.config.di.ForApplication;
import com.vectormobile.agilepoker.config.di.module.AppModule;
import com.vectormobile.agilepoker.utils.glide.GlideWrapper;
import dagger.Component;

/**
 * Created by daniel on 3/3/17.
 */

@ForApplication
@Component(modules = {AppModule.class})
public interface AppComponent {

    void inject(AppController app);

    @ForApplication
    AppController provideAppController();

    @ForApplication
    Context provideContext();

    GlideWrapper provideGlideWrapper();

    final class Initializer {
        public static AppComponent init(AppController appController) {
            return DaggerAppComponent.builder()
                    .appModule(new AppModule(appController))
                    .build();
        }
    }
}
