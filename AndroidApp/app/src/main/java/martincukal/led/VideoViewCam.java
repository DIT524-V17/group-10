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
 * Created by Mr.Horse on 2017-04-08.
 */

public class VideoViewCam extends AppCompatActivity {


    ProgressDialog pDialog;
    VideoView videoview;
    ImageButton foreward,backward,left,right;
    String videoURL  = "http://192.168.0.1:8000/picam.webm";





    protected void onCreate(Bundle savedInstanceState) {

        foreward = (ImageButton) findViewById(R.id.forwardBtn);
        backward = (ImageButton) findViewById(R.id.backwardBtn);
        left = (ImageButton) findViewById(R.id.leftBtn);
        right = (ImageButton) findViewById(R.id.rightBtn);
        //foreward.getBackground().setAlpha(70);



//        foreward.setOnTouchListener(new  View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                switch(event.getAction()) {
//                    case MotionEvent.ACTION_DOWN:
//                        // Do something
//                        ledControl.moveforward();
//
//                        return true;
//                    case MotionEvent.ACTION_UP:
//                        // No longer down
//                        stop();
//                        return true;
//                }
//                return false;
//            }
//
//        });
//
//        backward.setOnTouchListener(new  View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                switch(event.getAction()) {
//                    case MotionEvent.ACTION_DOWN:
//                        // Do something
//                        method.movebackward();
//
//                        return true;
//                    case MotionEvent.ACTION_UP:
//                        // No longer down
//                        method.stop();
//                        return true;
//                }
//                return false;
//            }
//
//        });
//
//        left.setOnTouchListener(new  View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                switch(event.getAction()) {
//                    case MotionEvent.ACTION_DOWN:
//                        // Do something
//                        method.moveleft();
//
//                        return true;
//                    case MotionEvent.ACTION_UP:
//                        // No longer down
//                        method.stop();
//                        return true;
//                }
//                return false;
//            }
//
//        });
//
//        right.setOnTouchListener(new  View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                switch(event.getAction()) {
//                    case MotionEvent.ACTION_DOWN:
//                        // Do something
//                        method.moveright();
//
//                        return true;
//                    case MotionEvent.ACTION_UP:
//                        // No longer down
//                        method.stop();
//                        return true;
//                }
//                return false;
//            }
//
//        });


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
