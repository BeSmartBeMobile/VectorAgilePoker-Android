package com.vectormobile.agilepoker.ui.deck.adapter;

import com.vectormobile.agilepoker.R;
import com.vectormobile.agilepoker.model.CardModel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by kurt on 03/05/17.
 */
public class DeckBuilder {

    public static final int TYPE_STANDARD = 1;

    private int deckType;
    private List<Integer> cardTypes = new ArrayList<>();
    private List<String> cardValues = new ArrayList<>();
    private List<String> cardShapes = new ArrayList<>();


    public DeckBuilder(int deckType) {
        this.deckType = deckType;
    }

    public List<CardModel> buildDeck() {
        List<CardModel> cards = new ArrayList<>();
        populateArrays();

        int size = cardTypes.size();
        for (int index = 0; index < size; index++) {
            CardModel item = new CardModel(cardTypes.get(index), cardShapes.get(index));

            if (item.getCardType() == CardModel.TEXT_CARD_TYPE) {
                item.setCardValue(cardValues.get(index));
            } else if (item.getCardType() == CardModel.IMAGE_CARD_TYPE) {
                item.setCardIcon(cardValues.get(index));
            }
            item.setBigBackgroundCardImages(populateStandardBigCardBackgroundImagesArray(item));
            cards.add(item);
        }
        return cards;
    }

    private void populateArrays() {
        if (deckType == TYPE_STANDARD) {
            cardTypes = populateStandardTypesArray();
            cardValues = populateStandardValuesArray();
            cardShapes = populateStandardShapeArray();

        }
    }

    private List<Integer> populateStandardTypesArray() {
        List<Integer> types = new ArrayList<>();
        types.add(CardModel.TEXT_CARD_TYPE);
        types.add(CardModel.TEXT_CARD_TYPE);
        types.add(CardModel.TEXT_CARD_TYPE);
        types.add(CardModel.TEXT_CARD_TYPE);
        types.add(CardModel.TEXT_CARD_TYPE);
        types.add(CardModel.TEXT_CARD_TYPE);
        types.add(CardModel.TEXT_CARD_TYPE);
        types.add(CardModel.TEXT_CARD_TYPE);
        types.add(CardModel.TEXT_CARD_TYPE);
        types.add(CardModel.TEXT_CARD_TYPE);
        types.add(CardModel.TEXT_CARD_TYPE);
        types.add(CardModel.TEXT_CARD_TYPE);
        types.add(CardModel.IMAGE_CARD_TYPE);
        types.add(CardModel.TEXT_CARD_TYPE);
        types.add(CardModel.TEXT_CARD_TYPE);
        return types;
    }

    private List<String> populateStandardValuesArray() {
        List<String> values = new ArrayList<>();
        values.add("0");
        values.add("1/2");
        values.add("1");
        values.add("2");
        values.add("3");
        values.add("5");
        values.add("8");
        values.add("13");
        values.add("20");
        values.add("40");
        values.add("90");
        values.add("100");
        values.add("R.drawable.ic_small_coffee_card");
        values.add("∞");
        values.add("?");
        return values;
    }

    private List<String> populateStandardShapeArray() {
        List<String> resourceIds = new ArrayList<>();
        resourceIds.add("#c7c7cd");     //Silver
        resourceIds.add("#faddb0");     //Light tan
        resourceIds.add("#fae2d6");     //Light tan two
        resourceIds.add("#d5e8d3");     //Light grey
        resourceIds.add("#b4ebfc");     //Pale sky blue
        resourceIds.add("#f5f7d5");     //Light khaki
        resourceIds.add("#a8bbd7");     //Cloudy blue
        resourceIds.add("#ffd9d9");     //Very light pink
        resourceIds.add("#c7f3e4");     //Light sage
        resourceIds.add("#ffdcf0");     //Light pink
        resourceIds.add("#ffdcf0");     //Light pink
        resourceIds.add("#efe67f");     //Sandy
        resourceIds.add("#ddd9ef");     //Light blue
        resourceIds.add("#85d8ea");     //Tiffany blue
        resourceIds.add("#acd5d4");     //Cloudy blue two
        resourceIds.add("#d1e5ff");     //Light sky blue
        /*
        resourceIds.add(R.drawable.deck_item_background_silver);
        resourceIds.add(R.drawable.deck_item_background_light_tan);
        resourceIds.add(R.drawable.deck_item_background_light_tan_two);
        resourceIds.add(R.drawable.deck_item_background_light_grey);
        resourceIds.add(R.drawable.deck_item_background_pale_sky_blue);
        resourceIds.add(R.drawable.deck_item_background_light_khaki);
        resourceIds.add(R.drawable.deck_item_background_cloudy_blue);
        resourceIds.add(R.drawable.deck_item_background_very_light_pink);
        resourceIds.add(R.drawable.deck_item_background_light_sage);
        resourceIds.add(R.drawable.deck_item_background_light_pink);
        resourceIds.add(R.drawable.deck_item_background_sandy);
        resourceIds.add(R.drawable.deck_item_background_light_blue_grey);
        resourceIds.add(R.drawable.deck_item_background_tiffany_blue);
        resourceIds.add(R.drawable.deck_item_background_cloudy_blue_two);
        resourceIds.add(R.drawable.deck_item_background_light_sky_blue);
        */
        return resourceIds;
    }


    private List<Integer> populateStandardBigCardBackgroundImagesArray(CardModel item) {
        List<Integer> bigCards = new ArrayList<>();
        switch (item.getCardType()) {
            case CardModel.IMAGE_CARD_TYPE:
                bigCards.addAll(populateCoffeeCardBackgroundImages());
                break;

            case CardModel.TEXT_CARD_TYPE:
                bigCards.addAll(populateTextCardsBackgroundImages(item.getCardValue()));
                break;
        }
        return bigCards;
    }

    private Collection<? extends Integer> populateCoffeeCardBackgroundImages() {
        List<Integer> bigCards = new ArrayList<>();
        bigCards.add(R.drawable.big_coffee_0);
        bigCards.add(R.drawable.big_coffee_1);
        bigCards.add(R.drawable.big_coffee_2);
        return bigCards;
    }

    private Collection<? extends Integer> populateTextCardsBackgroundImages(String cardValue) {
        List<Integer> bigCards = new ArrayList<>();
        if ("0".equalsIgnoreCase(cardValue)) {
            bigCards.add(R.drawable.big_0_0);
            bigCards.add(R.drawable.big_0_1);
            bigCards.add(R.drawable.big_0_2);
        } else if ("1/2".equalsIgnoreCase(cardValue)) {
            bigCards.add(R.drawable.big_05_0);
            bigCards.add(R.drawable.big_05_1);
            bigCards.add(R.drawable.big_05_2);
        } else if ("1".equalsIgnoreCase(cardValue)) {
            bigCards.add(R.drawable.big_1_0);
            bigCards.add(R.drawable.big_1_1);
            bigCards.add(R.drawable.big_1_2);
        } else if ("2".equalsIgnoreCase(cardValue)) {
            bigCards.add(R.drawable.big_2_0);
            bigCards.add(R.drawable.big_2_1);
            bigCards.add(R.drawable.big_2_2);
        } else if ("3".equalsIgnoreCase(cardValue)) {
            bigCards.add(R.drawable.big_3_0);
            bigCards.add(R.drawable.big_3_1);
            bigCards.add(R.drawable.big_3_2);
        } else if ("5".equalsIgnoreCase(cardValue)) {
            bigCards.add(R.drawable.big_5_0);
            bigCards.add(R.drawable.big_5_1);
            bigCards.add(R.drawable.big_5_2);
        } else if ("8".equalsIgnoreCase(cardValue)) {
            bigCards.add(R.drawable.big_8_0);
            bigCards.add(R.drawable.big_8_1);
            bigCards.add(R.drawable.big_8_2);
        } else if ("13".equalsIgnoreCase(cardValue)) {
            bigCards.add(R.drawable.big_13_0);
            bigCards.add(R.drawable.big_13_1);
            bigCards.add(R.drawable.big_13_2);
        } else if ("20".equalsIgnoreCase(cardValue)) {
            bigCards.add(R.drawable.big_20_0);
            bigCards.add(R.drawable.big_20_1);
            bigCards.add(R.drawable.big_20_2);
        } else if ("40".equalsIgnoreCase(cardValue)) {
            bigCards.add(R.drawable.big_40_0);
            bigCards.add(R.drawable.big_40_1);
            bigCards.add(R.drawable.big_40_2);
        } else if ("90".equalsIgnoreCase(cardValue)) {
            bigCards.add(R.drawable.big_90_0);
            bigCards.add(R.drawable.big_90_1);
            bigCards.add(R.drawable.big_90_2);
        } else if ("100".equalsIgnoreCase(cardValue)) {
            bigCards.add(R.drawable.big_100_0);
            bigCards.add(R.drawable.big_100_1);
            bigCards.add(R.drawable.big_100_2);
        } else if ("∞".equalsIgnoreCase(cardValue)) {
            bigCards.add(R.drawable.big_infinite_0);
            bigCards.add(R.drawable.big_infinite_1);
            bigCards.add(R.drawable.big_infinite_2);
        } else if ("?".equalsIgnoreCase(cardValue)) {
            bigCards.add(R.drawable.big_doubt_0);
            bigCards.add(R.drawable.big_doubt_1);
            bigCards.add(R.drawable.big_doubt_2);
        }
        return bigCards;
    }
}
