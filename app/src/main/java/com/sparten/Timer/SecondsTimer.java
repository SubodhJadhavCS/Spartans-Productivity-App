package com.sparten.Timer;

import android.os.CountDownTimer;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.sparten.spartens_productivity.CONSTANTS;

import me.tankery.lib.circularseekbar.CircularSeekBar;

public class SecondsTimer {

    public static final String TAG ="SECONDS TIMER";

    private static CountDownTimer countDownTimer;

    public static void Set_timer(final CircularSeekBar seconds_slider, final int Seconds) {


                                             //minute     //minute divider
        countDownTimer = new CountDownTimer(CONSTANTS.MINUTES, Seconds) {
            @Override
            public void onTick(long l) {
                try {
                   int  value = (int) (l/Seconds);
                  int  parts = 100/value;

                  seconds_slider.setProgress((float) (1.6666666*value));
                    Log.e(TAG,"value"+(float) (1.6666666*value));
                } catch (Exception e) {

                }
            }

            @Override
            public void onFinish() {
               // textvalue.setText("Finnish Counting");


            }
        };

    }

    public static void Start() {
        countDownTimer.start();
        Log.e(TAG,"   on Start executed  ");
    }

    public static void Stop() {
        countDownTimer.cancel();
        Log.e(TAG,"   on stop executed   ");
    }
}


