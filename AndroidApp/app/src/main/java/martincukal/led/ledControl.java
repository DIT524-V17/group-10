/*
Creator Martin Chukaleski February/2017
 */
package martincukal.led;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.os.AsyncTask;
import android.widget.ToggleButton;

import java.io.IOException;
import java.util.UUID;


public class ledControl extends AppCompatActivity {

    Button disconnect,incSpeed,decSpeed,leftrotate,rightrotate;
    ToggleButton obstacleA;
    TextView infoTxt,txtspeed;
    String address = null;
    ImageButton forward,backward,left,right,stop;
    ProgressBar speed;
    private ProgressDialog progress;
    BluetoothAdapter myBluetooth = null;
    BluetoothSocket btSocket = null;
    int carSpeed = 33;
    static int newSpeed;
    static int spdCount=1;
    private boolean isBtConnected = false;
    static final UUID myUUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent newint = getIntent();
        address = newint.getStringExtra(DeviceList.EXTRA_ADDRESS); //receive the address of the bluetooth device

        //view of the ledControl
        setContentView(R.layout.activity_led_control);

        //call the widgtes
        forward = (ImageButton) findViewById(R.id.imgForward);
        backward = (ImageButton) findViewById(R.id.imgBackward);
        left = (ImageButton) findViewById(R.id.imgLeft);
        right = (ImageButton) findViewById(R.id.imgRight);
        stop = (ImageButton) findViewById(R.id.imgStop);
        disconnect = (Button) findViewById(R.id.button4);
        infoTxt = (TextView) findViewById(R.id.lumn);
        obstacleA = (ToggleButton) findViewById(R.id.tgl);
        incSpeed = (Button) findViewById(R.id.plus);
        decSpeed = (Button) findViewById(R.id.minus);
        speed = (ProgressBar) findViewById(R.id.speed);
        txtspeed = (TextView) findViewById(R.id.speedtxt);
        leftrotate = (Button) findViewById(R.id.rotateleft);
        rightrotate = (Button) findViewById(R.id.rotateright);

        new ConnectBT().execute(); //Call the class to connect

        speed.setMax(100); //setting maximum value of speed progress bar to 100
        speed.setProgress(carSpeed); //initial starting speed of 20

        newSpeed = carSpeed;



        //commands to be sent to bluetooth
        forward.setOnTouchListener(new  View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch(event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        // Do something
                        moveforward();

                        return true;
                    case MotionEvent.ACTION_UP:
                        // No longer down
                        stop();
                        return true;
                }
                return false;
            }

        });


        backward.setOnTouchListener(new  View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch(event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        // Do something
                        movebackward();

                        return true;
                    case MotionEvent.ACTION_UP:
                        // No longer down
                        stop();
                        return true;
                }
                return false;
            }

        });

        left.setOnTouchListener(new  View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch(event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        // Do something
                        moveleft();

                        return true;
                    case MotionEvent.ACTION_UP:
                        // No longer down
                        stop();
                        return true;
                }
                return false;
            }

        });

        right.setOnTouchListener(new  View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch(event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        // Do something
                        moveright();

                        return true;
                    case MotionEvent.ACTION_UP:
                        // No longer down
                        stop();
                        return true;
                }
                return false;
            }

        });

        stop.setOnTouchListener(new  View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch(event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        // Do something
                        stop();

                        return true;
                    case MotionEvent.ACTION_UP:
                        // No longer down
                        stop();
                        return true;
                }
                return false;
            }

        });
        rightrotate.setOnTouchListener(new  View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch(event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        // Do something
                        inPlaceRotate();

                        return true;
                    case MotionEvent.ACTION_UP:
                        // No longer down
                        stop();
                        return true;
                }
                return false;
            }

        });
        leftrotate.setOnTouchListener(new  View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch(event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        // Do something
                        inPlaceRotateOpp();

                        return true;
                    case MotionEvent.ACTION_UP:
                        // No longer down
                        stop();
                        return true;
                }
                return false;
            }

        });

        obstacleA.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    obsON();
                }
                else {
                    obsOFF();
                }
            }
        });

        disconnect.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Disconnect(); //close connection
            }
        });

        incSpeed.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                if (spdCount==3){

                }
                else {
                     newSpeed =newSpeed +33;
                    speed.setProgress(newSpeed);
                    spdCount++;
                    if(spdCount == 1){
                        speed1();
                        txtspeed.setText("LOW");
                    }
                    else if(spdCount == 2){
                        speed2();
                        txtspeed.setText("MEDIUM");
                    }
                    else if(spdCount == 3){
                        speed3();
                        txtspeed.setText("HIGH");
                    }

                }
            }
        });

        decSpeed.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                if (spdCount==1){

                }
                else {
                    spdCount--;
                    newSpeed =newSpeed -33;
                    speed.setProgress(newSpeed);
                    if(spdCount == 1){
                        speed1();
                        txtspeed.setText("LOW");
                    }
                    else if(spdCount == 2){
                        speed2();
                        txtspeed.setText("MEDIUM");
                    }
                    else if(spdCount == 3){
                        speed3();
                        txtspeed.setText("HIGH");
                    }

                }
            }
        });


    }
    private void Disconnect()
    {
        if (btSocket!=null) //If the btSocket is busy
        {
            try
            {
                btSocket.close(); //close connection
            }
            catch (IOException e)
            { msg("Error");}
        }
        finish(); //return to the first layout

    }
    public void stop()
    {
        if (btSocket!=null)
        {
            try
            {
                btSocket.getOutputStream().write("TF".toString().getBytes());
            }
            catch (IOException e)
            {
                msg("Error");
            }
        }
    }

    public void moveforward()
    {
        if (btSocket!=null)
        {
            try
            {
                btSocket.getOutputStream().write("TO".toString().getBytes());
            }
            catch (IOException e)
            {
                msg("Error");
            }
        }
    }
    public void movebackward()
    {
        if (btSocket!=null)
        {
            try
            {
                btSocket.getOutputStream().write("BO".toString().getBytes());
            }
            catch (IOException e)
            {
                msg("Error");
            }
        }
    }

    public void moveleft()
    {
        if (btSocket!=null)
        {
            try
            {
                btSocket.getOutputStream().write("LE".toString().getBytes());
            }
            catch (IOException e)
            {
                msg("Error");
            }
        }
    }

    public void moveright()
    {
        if (btSocket!=null)
        {
            try
            {
                btSocket.getOutputStream().write("RI".toString().getBytes());
            }
            catch (IOException e)
            {
                msg("Error");
            }
        }
    }

    private void obsON()
    {
        if (btSocket!=null)
        {
            try
            {
                btSocket.getOutputStream().write("ON".toString().getBytes());
            }
            catch (IOException e)
            {
                msg("Error");
            }
        }
    }

    private void obsOFF()
    {
        if (btSocket!=null)
        {
            try
            {
                btSocket.getOutputStream().write("OF".toString().getBytes());
            }
            catch (IOException e)
            {
                msg("Error");
            }
        }
    }
    private void inPlaceRotate()
    {
        if (btSocket!=null)
        {
            try
            {
                btSocket.getOutputStream().write("RO".toString().getBytes());
            }
            catch (IOException e)
            {
                msg("Error");
            }
        }
    }
    private void inPlaceRotateOpp()
    {
        if (btSocket!=null)
        {
            try
            {
                btSocket.getOutputStream().write("OR".toString().getBytes());
            }
            catch (IOException e)
            {
                msg("Error");
            }
        }
    }

    private void speed1()
    {
        if (btSocket!=null)
        {
            try
            {
                btSocket.getOutputStream().write("S1".toString().getBytes());
            }
            catch (IOException e)
            {
                msg("Error");
            }
        }
    }

    private void speed2()
    {
        if (btSocket!=null)
        {
            try
            {
                btSocket.getOutputStream().write("S2".toString().getBytes());
            }
            catch (IOException e)
            {
                msg("Error");
            }
        }
    }
    private void speed3()
    {
        if (btSocket!=null)
        {
            try
            {
                btSocket.getOutputStream().write("S3".toString().getBytes());
            }
            catch (IOException e)
            {
                msg("Error");
            }
        }
    }


    // fast way to call Toast
    private void msg(String s)
    {
        Toast.makeText(getApplicationContext(),s,Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_led_control, menu);
        return true;
    }

//    public void openCam(View v) {
//        Intent cView = new Intent(ledControl.this, VideoViewCam.class);
//        startActivity(cView);
//    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private class ConnectBT extends AsyncTask<Void, Void, Void>  // UI thread
    {
        private boolean ConnectSuccess = true; //if it's here, it's almost connected

        @Override
        protected void onPreExecute()
        {
            progress = ProgressDialog.show(ledControl.this, "Connecting...", "Please wait!!!");  //show a progress dialog
        }


        @Override
        protected Void doInBackground(Void... devices) //while the progress dialog is shown, the connection is done in background
        {
            try
            {
                if (btSocket == null || !isBtConnected)
                {
                    myBluetooth = BluetoothAdapter.getDefaultAdapter();//get the mobile bluetooth device
                    BluetoothDevice dispositivo = myBluetooth.getRemoteDevice(address);//connects to the device's address and checks if it's available
                    btSocket = dispositivo.createInsecureRfcommSocketToServiceRecord(myUUID);//create a RFCOMM (SPP) connection
                    BluetoothAdapter.getDefaultAdapter().cancelDiscovery();
                    btSocket.connect();//start connection
                }
            }
            catch (IOException e)
            {
                ConnectSuccess = false;//if the try failed, you can check the exception here
            }
            return null;
        }
        @Override
        protected void onPostExecute(Void result) //after the doInBackground, it checks if everything went fine
        {
            super.onPostExecute(result);

            if (!ConnectSuccess)
            {
                msg("Connection Failed. Is it a SPP Bluetooth? Try again.");
                finish();
            }
            else
            {
                msg("Connected.");
                isBtConnected = true;
            }
            progress.dismiss();
        }
    }
}
