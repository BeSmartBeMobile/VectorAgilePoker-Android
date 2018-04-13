package com.vectormobile.agilepoker.model;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kurt on 03/05/17.
 */
public class CardModel implements Parcelable {

    public static final int IMAGE_CARD_TYPE = 0;
    public static final int TEXT_CARD_TYPE = 10;

    private int cardType;
    private String cardValue;
    private String cardIcon;

    private String backgroundColour;

    private List<Integer> bigBackgroundCardImages;

    public CardModel(int cardType, String backgroundColor) {
        this.cardType = cardType;
        this.backgroundColour = backgroundColor;
        bigBackgroundCardImages = new ArrayList<>();
    }

    public int getCardType() {
        return cardType;
    }

    public String getCardValue() {
        return cardValue;
    }

    public void setCardValue(String cardValue) {
        this.cardValue = cardValue;
    }

    public int getCardIcon(Context context) {
        return context.getResources().getIdentifier(cardIcon, "drawable", context.getPackageName());
    }

    public void setCardIcon(String cardIcon) {
        this.cardIcon = cardIcon;
    }

    public String getBackgroundColour() {
        return backgroundColour;
    }


    public List<Integer> getBigBackgroundCardImages() {
        return bigBackgroundCardImages;
    }

    public void setBigBackgroundCardImages(List<Integer> bigBackgroundCardImages) {
        this.bigBackgroundCardImages = bigBackgroundCardImages;
    }

    protected CardModel(Parcel in) {
        cardType = in.readInt();
        cardValue = in.readString();
        cardIcon = in.readString();
        backgroundColour = in.readString();

        if (in.readByte() == 0x01) {
            bigBackgroundCardImages = new ArrayList<Integer>();
            in.readList(bigBackgroundCardImages, Integer.class.getClassLoader());
        } else {
            bigBackgroundCardImages = null;
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(cardType);
        dest.writeString(cardValue);
        dest.writeString(cardIcon);
        dest.writeString(backgroundColour);
        if (bigBackgroundCardImages == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(bigBackgroundCardImages);
        }
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<CardModel> CREATOR = new Parcelable.Creator<CardModel>() {
        @Override
        public CardModel createFromParcel(Parcel in) {
            return new CardModel(in);
        }

        @Override
        public CardModel[] newArray(int size) {
            return new CardModel[size];
        }
    };
}
