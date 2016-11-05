package nuxapp.com.chatontv.ChatonTasks;


import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import nuxapp.com.chatontv.HMovie;

/**
 * LastMoviesTask
 * =============
 *
 * This task get the new movie on website
 */
public class LastMoviesTask extends AsyncTask<Void, Void, List<ArrayList<HMovie>>>{

    private LastMoviesTaskListener listener;

    public LastMoviesTask(LastMoviesTaskListener listener) {
        this.listener = listener;
    }

    @Override
    protected List<ArrayList<HMovie>> doInBackground(Void... voids) {
        Log.i(getClass().getName(), "Get all the movie for each bloc ");
        List<ArrayList<HMovie>> mainList = new ArrayList<>();
        try {
            for (int a=1;a<=3;a++){
                mainList.add(generatingMovieList(a));
                for (HMovie movie : mainList.get(a-1)){
                    Log.i(getClass().getName(), "Get all the movie for bloc "+movie.toString());
                }
            }
            return mainList;
        } catch (IOException e) {
            return null;
        }
    }

    @Override
    protected void onPostExecute(List<ArrayList<HMovie>> arrayLists) {
        listener.onHMovieRecupered(arrayLists);
        super.onPostExecute(arrayLists);
    }

    private static ArrayList<HMovie> generatingMovieList(int bloc) throws IOException {
        ArrayList<HMovie> hMovieArrayList = new ArrayList<>();
        Document doc = Jsoup.connect("http://www.hooper.fr/").get();
        Elements newMoviesElements = doc.getElementsByClass("view-display-id-block_"+bloc);
        for(Element movies : newMoviesElements){
            Elements image = movies.getElementsByClass("imagecache-video_apercu");
            Elements Embeded = movies.getElementsByClass("views-row-last");
            Elements titles = movies.getElementsByClass("views-field-title");
            Elements plateform = movies.getElementsByClass("views-field-tid-1");
            Elements date = movies.getElementsByClass("views-field-created");
            Elements details = movies.getElementsByClass("views-field-field-video-description-value");
            for (int a=0;a<titles.size();a++){
                String dailyUrl = getMovieURL(titles.select("span").select("a").attr("href"));
                String imgUrl = null;
                if (bloc!=1){
                    imgUrl = image.get(a).attr("src");
                }
                else {
                    imgUrl = "http://www.hooper.fr"+Embeded.get(a).getElementsByClass("views-field-field-video-embed").select("img").attr("src").toString();
                }
                HMovie hMovie = new HMovie(
                        titles.get(a).text(),
                        plateform.get(a).text(),
                        date.get(a).text(),
                        details.get(a).text(),
                        dailyUrl,
                        imgUrl
                );
                hMovieArrayList.add(hMovie);
            }
        }
        return hMovieArrayList;
    }

    private static String getMovieURL(String href) throws IOException {
        Document doc = Jsoup.connect("http://www.hooper.fr"+href).get();
        Element iframeDaily = doc.select("iframe").first();
        return "http:"+iframeDaily.attr("src");
    }

}
