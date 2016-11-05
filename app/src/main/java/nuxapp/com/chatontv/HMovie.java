package nuxapp.com.chatontv;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.StrictMode;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * HMovie
 * ======
 * This class describe how to define a movie recovered on Hooper website
 */
public class HMovie {

    private String title;
    private String plateform;
    private String date;
    private String details;
    private String dailyURL;
    private String thumbURL;

    public HMovie(String title, String plateform, String date, String details, String dailyURL, String thumbURL) {
        this.title = title;
        this.plateform = plateform;
        this.date = date;
        this.details = details;
        this.dailyURL = dailyURL;
        this.thumbURL = thumbURL;
    }

    public String getTitle() {
        return title;
    }

    public String getPlateform() {
        return plateform;
    }

    public String getDate() {
        return date;
    }

    public String getDetails() {
        return details;
    }

    public String getDailyURL() {
        return dailyURL;
    }

    public String getThumbURL() {
        return thumbURL;
    }

    public Drawable getDrawableImage() throws IOException {
        Bitmap x;
        OkHttpClient httpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .get()
                .url(getThumbURL())
                .build();
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Response response = httpClient.newCall(request).execute();
        x = BitmapFactory.decodeStream(response.body().byteStream());
        return new BitmapDrawable(x);
    }

    @Override
    public String toString() {
        return "HMovie{" +
                "title='" + title + '\'' +
                ", plateform='" + plateform + '\'' +
                ", date='" + date + '\'' +
                ", details='" + details + '\'' +
                ", dailyURL='" + dailyURL + '\'' +
                ", thumbURL='" + thumbURL + '\'' +
                '}';
    }
}