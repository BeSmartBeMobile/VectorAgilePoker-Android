package com.vectormobile.agilepoker.ui.card;

import android.app.ActionBar;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.GradientDrawable;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.util.TypedValue;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;

import com.vectormobile.agilepoker.AppController;
import com.vectormobile.agilepoker.R;
import com.vectormobile.agilepoker.config.di.component.CardComponent;
import com.vectormobile.agilepoker.config.di.component.DaggerCardComponent;
import com.vectormobile.agilepoker.config.di.module.CardModule;
import com.vectormobile.agilepoker.model.CardModel;
import com.vectormobile.agilepoker.ui.base.BaseActivity;

import javax.inject.Inject;

/**
 * Created by kurt on 03/05/17.
 */
public class CardActivity extends BaseActivity<CardPresenter> implements CardView, Animation.AnimationListener {

    public static final String EXTRA_PICKED_CARD = "extra_picked_card";
    public static final String EXTRA_TRANSITION_NAME = "extra_transiotion_name";

    @Inject
    CardPresenter presenter;

    @BindView(R.id.rlCard)
    RelativeLayout layout;
    @BindView(R.id.ivCardBack)
    ImageView cardBackground;
    @BindView(R.id.ivCardImage)
    ImageView cardImage;
    @BindView(R.id.tvCardValue)
    TextView cardValue;

    private CardComponent component;

    private CardModel card;
    private String transitionName;
    private int height;

    private Animation toMiddleAnim;
    private Animation fromMiddleAnim;
    private AnimationDrawable animation;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.abs_layout);
        retrieveExtras();
        new SetUpsAsyncTask().execute();
        cardValue.setText(card.getCardValue());
        cardValue.setTextSize(TypedValue.COMPLEX_UNIT_PX, height / 4);

        setUpView();
    }

    private void retrieveExtras() {
        card = getIntent().getExtras().getParcelable(EXTRA_PICKED_CARD);
        transitionName = getIntent().getExtras().getString(EXTRA_TRANSITION_NAME);
        height = getIntent().getExtras().getInt("HEIGHT");
    }

    private void setUpAnimations() {
        toMiddleAnim = AnimationUtils.loadAnimation(this, R.anim.to_middle);
        toMiddleAnim.setAnimationListener(this);
        fromMiddleAnim = AnimationUtils.loadAnimation(this, R.anim.from_middle);
        fromMiddleAnim.setAnimationListener(this);
    }

    private void setUpView() {
        if (Build.VERSION.SDK_INT > 20) {
            cardBackground.setTransitionName(transitionName);
        }
    }

    @Override
    protected void injectModule() {
        component = DaggerCardComponent.builder()
                .appComponent(((AppController) getApplication()).getComponent())
                .cardModule(new CardModule(this))
                .build();
        component.inject(this);
    }

    @Override
    protected CardPresenter getPresenter() {
        return presenter;
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_card;
    }

    @Override
    public CardComponent getComponent() {
        return component;
    }

    @Override
    public void showError(int errorId) {
        View rootView = getWindow().getDecorView().findViewById(android.R.id.content);
        Snackbar.make(rootView, errorId, Snackbar.LENGTH_SHORT).show();
    }

    @OnClick(R.id.ivCardBack)
    public void onReverseSideHasBeenClicked() {
        presenter.showCard();
    }

    @Override
    public void flipCard() {
        layout.clearAnimation();
        layout.setAnimation(toMiddleAnim);
        layout.setAnimation(toMiddleAnim);
    }

    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {
        if (animation == toMiddleAnim) {
            if (!presenter.isCardFlipped()) {
                switch (card.getCardType()) {
                    case CardModel.IMAGE_CARD_TYPE:
                        cardValue.setVisibility(View.INVISIBLE);
                        break;

                    default:
                        cardValue.setVisibility(View.VISIBLE);
                        cardValue.setVisibility(View.VISIBLE);
                        break;
                }
                cardImage.setVisibility(View.VISIBLE);
                cardBackground.getLayoutParams().width = height * 47 / 73;

                GradientDrawable gd = new GradientDrawable();
                gd.setColor(Color.parseColor(card.getBackgroundColour()));
                gd.setCornerRadii(new float[]{150, 150, 150, 150, 150, 150, 150, 150});
                cardBackground.setImageDrawable(gd);

                animation.start();
                presenter.setFlipCard(true);
            }
            layout.clearAnimation();
            layout.setAnimation(fromMiddleAnim);
            layout.setAnimation(fromMiddleAnim);
        }
    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }

    private void buildFrameAnimation() {
        animation = new AnimationDrawable();
        animation.addFrame(getResources().getDrawable(card.getBigBackgroundCardImages().get(0)), 250);
        animation.addFrame(getResources().getDrawable(card.getBigBackgroundCardImages().get(1)), 250);
        animation.addFrame(getResources().getDrawable(card.getBigBackgroundCardImages().get(2)), 250);
        animation.addFrame(getResources().getDrawable(card.getBigBackgroundCardImages().get(1)), 250);
        animation.setOneShot(false);

        cardImage.setImageDrawable(animation);
        animation.start();
    }

    @Override
    public void closeView() {
        finish();
    }

    public class SetUpsAsyncTask extends AsyncTask<String, Integer, Integer> {

        @Override
        protected Integer doInBackground(String... params) {
            setUpAnimations();
            buildFrameAnimation();
            return null;
        }
    }
}

