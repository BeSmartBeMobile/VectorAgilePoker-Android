package com.vectormobile.agilepoker.config.di.module;

import com.vectormobile.agilepoker.config.di.ActivityScope;
import com.vectormobile.agilepoker.ui.card.CardActivity;
import com.vectormobile.agilepoker.ui.deck.DeckActivity;
import dagger.Module;
import dagger.Provides;

/**
 * Created by daniel on 4/3/17.
 */

@Module
@ActivityScope
public class CardModule extends ActivityModule<CardActivity> {

    private CardActivity activity;

    public CardModule(CardActivity activity) {
        super(activity);
        this.activity = activity;
    }

    @Provides
    CardActivity provideActivity() {
        return activity;
    }
}
