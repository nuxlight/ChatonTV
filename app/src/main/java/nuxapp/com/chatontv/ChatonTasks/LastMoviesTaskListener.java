package nuxapp.com.chatontv.ChatonTasks;

import java.util.ArrayList;
import java.util.List;

import nuxapp.com.chatontv.HMovie;

public interface LastMoviesTaskListener {
    void onHMovieRecupered(List<ArrayList<HMovie>> hMoviesList);
    void onHMovieError();
}
