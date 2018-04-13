package com.vectormobile.agilepoker.ui.deck;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.Display;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import butterknife.BindView;

import com.vectormobile.agilepoker.AppController;
import com.vectormobile.agilepoker.R;
import com.vectormobile.agilepoker.config.di.component.DaggerDeckComponent;
import com.vectormobile.agilepoker.config.di.component.DeckComponent;
import com.vectormobile.agilepoker.config.di.module.DeckModule;
import com.vectormobile.agilepoker.ui.deck.adapter.CardClickListener;
import com.vectormobile.agilepoker.model.CardModel;
import com.vectormobile.agilepoker.ui.base.BaseActivity;
import com.vectormobile.agilepoker.ui.card.CardActivity;
import com.vectormobile.agilepoker.ui.deck.adapter.DeckAdapter;
import com.vectormobile.agilepoker.ui.deck.adapter.DeckBuilder;
import com.vectormobile.agilepoker.utils.glide.GlideWrapper;

import javax.inject.Inject;

/**
 * Created by kurt on 28/04/17.
 */
public class DeckActivity extends BaseActivity<DeckPresenter> implements DeckView, CardClickListener {

    @Inject
    DeckPresenter presenter;
    @Inject
    GlideWrapper glideWrapper;

    @BindView(R.id.rvDeck)
    RecyclerView rvCardsDeck;

    private DeckComponent component;

    int width;
    int height;
    int barHeight;
    int statusBarHeight;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.abs_layout);

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        width = size.x;
        height = size.y;

        TypedValue tv = new TypedValue();
        if (getTheme().resolveAttribute(android.R.attr.actionBarSize, tv, true)) {
            barHeight = TypedValue.complexToDimensionPixelSize(tv.data, getResources().getDisplayMetrics());
        }

        statusBarHeight = getStatusBarHeight();

        height = height - barHeight - statusBarHeight;

        setUpRecycler();
        populateRecycler();
    }

    private void setUpRecycler() {
        rvCardsDeck.setHasFixedSize(true);
        rvCardsDeck.setLayoutManager(new GridLayoutManager(this, 3));
    }

    private void populateRecycler() {
        DeckBuilder deckBuilder = new DeckBuilder(DeckBuilder.TYPE_STANDARD);
        DeckAdapter adapter = new DeckAdapter(this, glideWrapper, width, height);
        adapter.addItems(deckBuilder.buildDeck());
        rvCardsDeck.setAdapter(adapter);
    }

    @Override
    protected void injectModule() {
        component = DaggerDeckComponent.builder()
                .appComponent(((AppController) getApplication()).getComponent())
                .deckModule(new DeckModule(this))
                .build();
        component.inject(this);
    }

    @Override
    protected DeckPresenter getPresenter() {
        return presenter;
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_deck;
    }

    @Override
    public DeckComponent getComponent() {
        return component;
    }

    @Override
    public void showError(int errorId) {
        View rootView = getWindow().getDecorView().findViewById(android.R.id.content);
        Snackbar.make(rootView, errorId, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onCardPicked(View view, CardModel card, String transitionName) {
        Intent iCard = new Intent(this, CardActivity.class);
        iCard.putExtra(CardActivity.EXTRA_PICKED_CARD, card);
        iCard.putExtra(CardActivity.EXTRA_TRANSITION_NAME, transitionName);
        iCard.putExtra("HEIGHT", height);

        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                this,
                view,
                transitionName);

        startActivity(iCard, options.toBundle());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                supportFinishAfterTransition();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public int getStatusBarHeight() {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

}
