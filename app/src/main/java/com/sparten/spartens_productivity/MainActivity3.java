package com.sparten.spartens_productivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.sparten.Overlay.Foreground;
import com.sparten.Overlay.NotificationReceiver;
import com.sparten.Overlay.WindowX;
import com.sparten.Timer.SecondsTimer;
import com.sparten.Timer.Minute_Timer;

import java.util.Objects;

import me.tankery.lib.circularseekbar.CircularSeekBar;

public class MainActivity3 extends AppCompatActivity {
    public static final String TAG = " MainActivity3 ";
    public static int cheak;
    CircularSeekBar seekBar;
    CircularSeekBar seconds_slider;
    TextView textView;
    EditText editText;
    String time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        // Hide the status bar.
        Objects.requireNonNull(getSupportActionBar()).hide();
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

        seekBar = (CircularSeekBar) findViewById(R.id.circular_range_slider);
        seconds_slider =(CircularSeekBar) findViewById(R.id.seconds_slider);
        textView =findViewById(R.id.textView);
        editText = findViewById(R.id.editetext);
        intialize_seekbar();

          //getting permission
        checkOverlayPermission();

        //initalize object of Stoptimer need of context
        new Minute_Timer(MainActivity3.this);

        //sending value to the notification reicever so it can be get the seekbar vlaue  and seconds seek bar value to it whenrver excutes removed filter it will change the vlaue to 0
        NotificationReceiver notificationReceiver = new NotificationReceiver();
        notificationReceiver.sendata(seekBar,seconds_slider);

        //sending value to the windows x to update the view
        WindowX windowX = new WindowX();
        windowX.SendData(seekBar,seconds_slider);

        // saturating the object
        time ="1";

    }

    private void intialize_seekbar() {

        seekBar.setOnSeekBarChangeListener(new CircularSeekBar.OnCircularSeekBarChangeListener() {
            @Override
            public void onProgressChanged(CircularSeekBar circularSeekBar, float progress, boolean fromUser) {


                    seconds_slider.setProgress(seconds_slider.getMax());
                    time = String.valueOf(Math.round(progress));
                    textView.setText(time +" Min ");

            }

            @Override
            public void onStopTrackingTouch(CircularSeekBar seekBar) {
               // textView.setText("on Stop  tracking");
            }

            @Override



            public void onStartTrackingTouch(CircularSeekBar seekBar) {
               // textView.setText("start touched | ");
            }
        });


    }

    int touch =0;
    public void softcore(View view) {  cheak = 1;
        touch++;

        if(time != null) {

            if (!(time == "0")) {
                if(touch>=2 ){
                SecondsTimer.Stop();
                Minute_Timer.Stop();

                    Minute_Timer.Set_timer(Integer.parseInt(time) * CONSTANTS.MINUTES, textView, seekBar, seconds_slider);
                    Minute_Timer.Start();

                stopService(new Intent(MainActivity3.this, Foreground.class));
                startService(new Intent(MainActivity3.this, Foreground.class));
               }
                else{

                    Minute_Timer.Set_timer(Integer.parseInt(time) * CONSTANTS.MINUTES, textView, seekBar, seconds_slider);
                    Minute_Timer.Start();
                    Log.e(TAG, "else    not Equals ignore case +  Zero not accepted ++ not null value");
                    stopService(new Intent(MainActivity3.this, Foreground.class));
                    startService(new Intent(MainActivity3.this, Foreground.class));

                }

            }else{

                Toast.makeText(MainActivity3.this,"Make sure you dont chose zero",Toast.LENGTH_SHORT).show();

            }
        }

    }


//             if(time!=null){//make sure value not equlea to null and then after 2 touch events when you assign value it  stop timer as think it was asssigned before
//        if (touch >= 2) {
//            if(time.equals("0")){Toast.makeText(MainActivity3.this,"Do Not Select Zero Time",Toast.LENGTH_SHORT).show();}
//            else {
//                SecondsTimer.Stop();
//                StopTimer.Stop();
//                StopTimer.Set_timer(CONSTANTS.NULL,textView,seekBar,seconds_slider);
//                StopTimer.Start();
//
//                SecondsTimer.Set_timer(seconds_slider,CONSTANTS.NULL);
//                SecondsTimer.Start();
//
//                //final stopping the timer
//                SecondsTimer.Stop();
//                StopTimer.Stop();
//
//            }
//        }}
//
//
//
//        if(time!=null ){
//        if(time.equals("0")){Toast.makeText(MainActivity3.this,"Do Not Select Zero Time",Toast.LENGTH_SHORT).show();}
//        else {
//            seconds_slider.setProgress(0);
//            stopService(new Intent(MainActivity3.this,Foreground.class));
//            startService(new Intent(MainActivity3.this, Foreground.class));
//            StopTimer.Set_timer(Integer.parseInt(time)*CONSTANTS.MINUTES,textView,seekBar,seconds_slider);
//            StopTimer.Start();}
//    }else{
//        Toast.makeText(MainActivity3.this,"Make Sure you selected time",Toast.LENGTH_SHORT).show();
////        Log.e(TAG,"make sure you selected time"); }

    public void HardCore(View view) { //cheacking the click event assigning it to true
        cheak = 2;

          if(time != null) {

              if (!(time == "0")) {

                  Minute_Timer.Set_timer(CONSTANTS.NULL,textView,seekBar,seconds_slider);
                  Minute_Timer.Start();
                  SecondsTimer.Set_timer(seconds_slider,CONSTANTS.NULL);
                  SecondsTimer.Start();
                  //final stopping the timer
                  SecondsTimer.Stop();
                  Minute_Timer.Stop();

                  Log.e(TAG, "not Equals ignore case +  Zero not accepted ++ not null value");
                  stopService(new Intent(MainActivity3.this, Foreground.class));
                  startService(new Intent(MainActivity3.this, Foreground.class));
                  Minute_Timer.Set_timer(Integer.parseInt(time) * CONSTANTS.MINUTES, textView, seekBar, seconds_slider);
                  Minute_Timer.Start();

              }else{

                  Toast.makeText(MainActivity3.this,"Make sure you dont chose zero",Toast.LENGTH_SHORT).show();

              }
         }


    }

    public void SAVE(View view) {
       String post = editText.getText().toString();
       Model.setPost(post);
      Toast.makeText(MainActivity3.this,"Saved",Toast.LENGTH_SHORT).show();
    }

    public void Cancle(View view) {

        if(touch >= 2){

        SecondsTimer.Stop();
       Minute_Timer.Stop();}
        stopService(new Intent(MainActivity3.this,Foreground.class));
        seconds_slider.setProgress(0);
        seekBar.setProgress(0);
        Toast.makeText(MainActivity3.this,"Restriction Canceled",Toast.LENGTH_SHORT).show();
    }


    public void checkOverlayPermission(){
        Log.e(TAG, "checkOverlayPermission Method Executed");

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!Settings.canDrawOverlays(this)) {
                // send user to the device settings
                Intent myIntent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION);
                startActivity(myIntent);
            }
        }
    }


}


