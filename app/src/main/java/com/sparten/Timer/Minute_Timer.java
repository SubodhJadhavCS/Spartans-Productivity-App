package com.sparten.Timer;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.sparten.Overlay.Foreground;
import com.sparten.spartens_productivity.CONSTANTS;
import com.sparten.spartens_productivity.MainActivity;
import com.sparten.spartens_productivity.MainActivity3;

import me.tankery.lib.circularseekbar.CircularSeekBar;

public class Minute_Timer {
   public static final String TAG = "STOPTIMER ";

    private static CountDownTimer countDownTimer;
    static  Context context;

    public Minute_Timer(Context context) {
        this.context =context;
   }

    public static void Set_timer(int timevalue, final TextView textvalue, final CircularSeekBar seekBar, final CircularSeekBar seconds_slider) {


                                             //minute     //minute divider
        countDownTimer = new CountDownTimer(timevalue, CONSTANTS.MINUTES) {
            @Override
            public void onTick(long l) {
                // long value =  l-5000;
                try {
                    seekBar.setProgress(l / CONSTANTS.MINUTES);
                    textvalue.setText( l / CONSTANTS.MINUTES+ " min " );
                    Log.e(TAG,"minutes  "+l / CONSTANTS.MINUTES + "   -- "+l );
                    SecondsTimer.Set_timer(seconds_slider,CONSTANTS.SECONDS);
                    SecondsTimer.Start();
                } catch (Exception e) {
                    textvalue.setText(String.valueOf(e));
                }
            }

            @Override
            public void onFinish() {
                textvalue.setText("Finnish Counting");

                SecondsTimer.Stop();
                seconds_slider.setProgress(0);
                context.stopService(new Intent(context, Foreground.class));
            }
        };



    }

    public static void Start() {
        countDownTimer.start();
        Log.e(TAG,"STOP TIMER ---on start executed");
    }

    public static void Stop() {

        countDownTimer.cancel();
        Log.e(TAG,"STOP TIMER ---on stop executed");
    }
}


