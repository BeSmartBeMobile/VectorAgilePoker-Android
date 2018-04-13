package com.vectormobile.agilepoker.ui.deck.adapter;

import android.view.View;
import com.vectormobile.agilepoker.model.CardModel;

/**
 * Created by dgarciar on 14/04/2016.
 */
public interface CardClickListener {

    /**
     * Called when a card is picked
     * @param holder The View for the picked card
     * @param card The card that was picked
     */
    void onCardPicked(View holder, CardModel card, String transitionName);
}

