package com.vectormobile.agilepoker.config.di.module;

import com.vectormobile.agilepoker.config.di.ActivityScope;
import com.vectormobile.agilepoker.ui.deck.DeckActivity;
import dagger.Module;
import dagger.Provides;

/**
 * Created by daniel on 4/3/17.
 */

@Module
@ActivityScope
public class DeckModule extends ActivityModule<DeckActivity> {

    private DeckActivity activity;

    public DeckModule(DeckActivity activity) {
        super(activity);
        this.activity = activity;
    }

    @Provides
    DeckActivity provideActivity() {
        return activity;
    }
}
