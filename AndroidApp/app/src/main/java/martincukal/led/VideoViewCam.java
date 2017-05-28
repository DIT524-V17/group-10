package martincukal.led;

import android.app.ProgressDialog;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.VideoView;


/**
 * Created by Aleksandar Isakovski
 */

public class VideoViewCam extends AppCompatActivity {


    ProgressDialog pDialog;
    VideoView videoview;
    String videoURL  = "http://192.168.0.1:8000/picam.webm";





    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);


        setContentView(R.layout.videoview);

        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE);

//        setContentView(R.layout.activity_device_list);
        videoview = (VideoView) findViewById(R.id.VideoView);


        pDialog = new ProgressDialog(VideoViewCam.this);
        pDialog.setTitle("Android Video Streaming");
        pDialog.setMessage("Buffering...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();

        try {
            Uri video = Uri.parse(videoURL);
            videoview.setVideoURI(video);
        }catch (Exception e){
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }
        videoview.requestFocus();
        pDialog.dismiss();
        videoview.start();


    }

}
