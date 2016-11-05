package nuxapp.com.chatontv;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v17.leanback.app.BrowseFragment;
import android.support.v17.leanback.widget.ArrayObjectAdapter;
import android.support.v17.leanback.widget.HeaderItem;
import android.support.v17.leanback.widget.ListRow;
import android.support.v17.leanback.widget.ListRowPresenter;
import android.support.v17.leanback.widget.OnItemViewClickedListener;
import android.support.v17.leanback.widget.Presenter;
import android.support.v17.leanback.widget.Row;
import android.support.v17.leanback.widget.RowPresenter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.List;

import nuxapp.com.chatontv.ChatonTasks.LastMoviesTask;
import nuxapp.com.chatontv.ChatonTasks.LastMoviesTaskListener;

/**
 * ChatonMainFragment
 * ==================
 * @author Thibaud Pellissier
 * @version 0.1
 * This main fragment show all the new videos of hooper
 * website and create card for each movies available
 */

public class ChatonMainFragment extends BrowseFragment implements LastMoviesTaskListener {

    private List<ArrayList<HMovie>> hMoviesList;
    private ProgressDialog progressDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setBrandColor(getResources().getColor(R.color.hooper_main_color));

        setupEventListeners();
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setTitle("Chaton récupère les vidéos ;) ");
        progressDialog.show();
        LastMoviesTask moviesTask = new LastMoviesTask(this);
        moviesTask.execute();
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    private void setupEventListeners() {
        setOnItemViewClickedListener(new ItemViewClickedListener());
    }

    private void newMoviesList() {
        //This is the first row
        ArrayObjectAdapter mNewMoviesRow = new ArrayObjectAdapter(new ListRowPresenter());
        for (int a=0;a<hMoviesList.size();a++){

            //Create the header of the last row
            HeaderItem mHeaderItem = null;
            if (a==0){
                mHeaderItem = new HeaderItem(0, "Hooper.fr");
            }
            else if(a==1){
                mHeaderItem = new HeaderItem(1, "Les derniers lives");
            }
            else {
                mHeaderItem = new HeaderItem(2, "Les dernières vidéos");
            }

            //Create each card for the new row
            HCardPresenter hCardPresenter = new HCardPresenter();
            ArrayObjectAdapter gridRowAdapter = new ArrayObjectAdapter(hCardPresenter);
            for (HMovie movie : hMoviesList.get(a)){
                gridRowAdapter.add(movie);
            }
            mNewMoviesRow.add(new ListRow(mHeaderItem, gridRowAdapter));
        }
        setAdapter(mNewMoviesRow);
    }

    @Override
    public void onHMovieRecupered(List<ArrayList<HMovie>> hMoviesList) {
        this.hMoviesList = hMoviesList;
        this.progressDialog.dismiss();
        newMoviesList();
    }

    @Override
    public void onHMovieError() {

    }

    private final class ItemViewClickedListener implements OnItemViewClickedListener {

        @Override
        public void onItemClicked(Presenter.ViewHolder itemViewHolder, Object item, RowPresenter.ViewHolder rowViewHolder, Row row) {
            HMovie movie = (HMovie) item;
            Log.i(getClass().getName(), "Movie selected is : "+movie.getTitle());
            Intent intent = new Intent(getContext(), PlaybackOverlayActivity.class);
            intent.putExtra("url_play", movie.getDailyURL());
            startActivity(intent);
        }
    }
}
