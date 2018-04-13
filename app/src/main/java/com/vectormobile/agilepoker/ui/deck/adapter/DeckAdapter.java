package com.vectormobile.agilepoker.ui.deck.adapter;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.Display;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

import com.vectormobile.agilepoker.R;
import com.vectormobile.agilepoker.model.CardModel;
import com.vectormobile.agilepoker.utils.glide.GlideWrapper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kurt on 03/05/17.
 */
public class DeckAdapter extends RecyclerView.Adapter<DeckAdapter.ViewHolder> {

    private List<CardModel> cardList;
    private GlideWrapper glideWrapper;
    private int width;
    private int height;
    private int deckMargin;

    private CardClickListener listener;

    public DeckAdapter(CardClickListener listener, GlideWrapper wrapper, int width, int height) {
        this.listener = listener;
        glideWrapper = wrapper;
        cardList = new ArrayList<>();

        deckMargin = height / (6 * 15);
        height = (height - 12 * deckMargin) / 5;
        width = (width - 8 * deckMargin) / 3;

        this.width = width;
        this.height = height;
    }

    public void addItems(List<CardModel> cards) {
        cardList.addAll(cards);
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_deck_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        CardModel item = cardList.get(position);

        holder.cardLayout.getLayoutParams().height = height;
        holder.cardLayout.getLayoutParams().width = width;

        ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) holder.cardLayout.getLayoutParams();
        params.leftMargin = deckMargin;
        params.topMargin = deckMargin;
        params.rightMargin = deckMargin;
        params.bottomMargin = deckMargin;

        if (item.getCardType() == CardModel.TEXT_CARD_TYPE) {
            holder.cardValue.setText(item.getCardValue());
            holder.cardImage.setVisibility(View.GONE);
            holder.cardValue.setVisibility(View.VISIBLE);
            holder.cardValue.setTextSize(TypedValue.COMPLEX_UNIT_PX, height / 2);
        } else if (item.getCardType() == CardModel.IMAGE_CARD_TYPE) {
            glideWrapper.loadImage(holder.cardImage, R.drawable.ic_small_coffee_card);
            holder.cardValue.setVisibility(View.GONE);
            holder.cardImage.setVisibility(View.VISIBLE);
            holder.cardImage.getLayoutParams().height = height / 2;
            holder.cardImage.getLayoutParams().width = width / 2;
        }

        GradientDrawable gd = new GradientDrawable();
        gd.setColor(Color.parseColor(item.getBackgroundColour()));
        gd.setCornerRadii(new float[]{30, 30, 30, 30, 30, 30, 30, 30});
        holder.itemBackground.setImageDrawable(gd);

        ViewCompat.setTransitionName(holder.cardLayout, "card" + position);
        holder.bind(item, listener);
    }

    @Override
    public int getItemCount() {
        if (cardList != null)
            return cardList.size();
        else
            return 0;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.rlDeckCard)
        RelativeLayout cardLayout;
        @BindView(R.id.ivCardImage)
        ImageView cardImage;
        @BindView(R.id.tvCardValue)
        TextView cardValue;
        @BindView(R.id.itemBackground)
        ImageView itemBackground;


        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(final CardModel pickedCard, final CardClickListener listener) {
            cardLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onCardPicked(cardLayout, pickedCard, ViewCompat.getTransitionName(cardLayout));
                }
            });
        }
    }
}
