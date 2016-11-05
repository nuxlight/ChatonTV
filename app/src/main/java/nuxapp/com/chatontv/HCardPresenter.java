package nuxapp.com.chatontv;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v17.leanback.widget.ImageCardView;
import android.support.v17.leanback.widget.Presenter;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.io.IOException;

/**
 * Created by thibaud on 03/11/2016.
 */

public class HCardPresenter extends Presenter {

    private static final int GRID_ITEM_WIDTH = 313;
    private static final int GRID_ITEM_HEIGHT = 176;
    private Context context;

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent) {
        context = parent.getContext();

        ImageCardView imageCardView = new ImageCardView(context);
        imageCardView.setFocusable(true);
        imageCardView.setFocusableInTouchMode(true);
        imageCardView.setBackgroundColor(context.getResources().getColor(R.color.fastlane_background));
        return new ViewHolder(imageCardView);
    }

    @Override
    public void onBindViewHolder(Presenter.ViewHolder viewHolder, Object item) {
        HMovie mHMovie = (HMovie) item;

        ImageCardView cardView = (ImageCardView) viewHolder.view;
        cardView.setTitleText(mHMovie.getTitle());
        try {
            cardView.setMainImage(mHMovie.getDrawableImage());
        } catch (IOException e) {
            cardView.setMainImage(null);
        }
        cardView.setContentText(mHMovie.getDetails());
        cardView.setMainImageDimensions(GRID_ITEM_WIDTH, GRID_ITEM_HEIGHT);
    }

    @Override
    public void onUnbindViewHolder(Presenter.ViewHolder viewHolder) {

    }
}
