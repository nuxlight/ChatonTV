package nuxapp.com.chatontv;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

/**
 * PlaybackOverlayActivity
 * =======================
 * @author Pellissier Thibaud
 * @version 0.1
 *
 * Video player for Hooper Website on AndroidTV.
 * This class use the Dailymotion SDK
 */
public class PlaybackOverlayActivity extends Activity {

    private DMWebVideoView mVideoView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playback_overlay);

        mVideoView = ((DMWebVideoView) findViewById(R.id.dmWebVideoView));
        mVideoView.setControls(false);
        mVideoView.setAutoPlay(true);
        mVideoView.loadUrl("http://www.dailymotion.com/embed/video/k7vUCcIu7nPTaoklJkW&amp;autoPlay=1");
    }

    public void playAndPauseMovie(boolean playPause){
        Log.i(getLocalClassName(),"DEBUG "+playPause);
        if (!playPause){
            mVideoView.pause();
        }
        else {
            mVideoView.play();
        }
    }
}
