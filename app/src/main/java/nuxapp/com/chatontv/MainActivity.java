package nuxapp.com.chatontv;

import android.app.Activity;
import android.os.Bundle;

public class MainActivity extends Activity {

    private DMWebVideoView mVideoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mVideoView = ((DMWebVideoView) findViewById(R.id.dmWebVideoView));
        mVideoView.loadUrl("http://www.dailymotion.com/embed/video/k7vUCcIu7nPTaoklJkW&amp;autoPlay=1");
    }
}
