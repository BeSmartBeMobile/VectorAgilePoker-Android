package com.vectormobile.agilepoker.config.di.component;

import com.vectormobile.agilepoker.config.di.ActivityScope;
import com.vectormobile.agilepoker.config.di.module.DeckModule;
import com.vectormobile.agilepoker.ui.deck.DeckActivity;
import dagger.Component;

/**
 * Created by daniel on 3/3/17.
 */

@ActivityScope
@Component(dependencies = AppComponent.class, modules = DeckModule.class)
public interface DeckComponent {

    void inject(DeckActivity activity);
}
