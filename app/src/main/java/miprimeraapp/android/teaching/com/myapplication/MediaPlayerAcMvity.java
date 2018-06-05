package miprimeraapp.android.teaching.com.myapplication;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.VideoView;

public class MediaPlayerAcMvity extends BaseActivity {
    private class ContadorAsyncTask extends AsyncTask<Void, Integer, Integer> {
        protected Integer doInBackground(Void... voids) {
            int count = 0;
                for (count = 0; count <= 100; count++) {
                    Log.d("AsyncTask", "Contador: " + count);
                    try {
                        Thread.sleep(1000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            return null;
        }

        protected void onProgressUpdate(Integer... progress) {
            Log.d("AsyncTask", "Valor: " + progress[0].toString());
        }

        protected void onPostExecute(Integer result) {
            Log.d("AsyncTask", "Valor final: " + result);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_media_player_ac_mvity);
        MediaController mediaController = new MediaController(this);

        VideoView view = (VideoView)findViewById(R.id.video_view);
        String path = "https://img-9gag-fun.9cache.com/photo/aBxGoNN_460sv.mp4";
        view.setVideoURI(Uri.parse(path));
        view.setMediaController(mediaController);
        view.start();
        new ContadorAsyncTask().execute();
    }
    public void onClickPlay (View view) {
        Intent myIntent = new Intent(this, MediaPlayerService.class);
        startService(myIntent);
    }
    public void onClickPause (View view) {
        Intent myIntent = new Intent(this, MediaPlayerService.class);
        stopService(myIntent);
    }
    @Override
    protected void onDestroy () {
        Intent myIntent = new Intent(this, MediaPlayerService.class);
        stopService(myIntent);
        super.onDestroy();
    }
    @Override
    protected void onPause () {
        Intent myIntent = new Intent(this, MediaPlayerService.class);
        stopService(myIntent);
        super.onPause();
    }
}
