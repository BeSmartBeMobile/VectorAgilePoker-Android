package com.vectormobile.agilepoker.ui.card;

import com.vectormobile.agilepoker.config.di.ActivityScope;
import com.vectormobile.agilepoker.ui.base.BasePresenter;

import javax.inject.Inject;

/**
 * Created by kurt on 03/05/17.
 */
@ActivityScope
public class CardPresenter extends BasePresenter<CardView> {

    private boolean flipCard = false;

    @Inject
    public CardPresenter(CardActivity activity) {
        super(activity);
        this.view = activity;
    }

    public boolean isCardFlipped() {
        return flipCard;
    }

    public void setFlipCard(boolean flipCard) {
        this.flipCard = flipCard;
    }

    public void showCard() {
        if (!flipCard) {
            flipCard();
        } else {
            view.closeView();
        }
    }

    public void flipCard() {
        view.flipCard();
    }
}
