package com.vectormobile.agilepoker.ui.deck;

import com.vectormobile.agilepoker.config.di.ActivityScope;
import com.vectormobile.agilepoker.ui.base.BasePresenter;

import javax.inject.Inject;

/**
 * Created by dgarciar on 13/04/2016.
 */
@ActivityScope
public class DeckPresenter extends BasePresenter<DeckView> {

    @Inject
    public DeckPresenter(DeckActivity activity) {
        super(activity);
        this.view = activity;
    }
}
