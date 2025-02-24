package com.sparten.Overlay;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.os.Build;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import com.sparten.Timer.SecondsTimer;
import com.sparten.Timer.Minute_Timer;
import com.sparten.spartens_productivity.MainActivity3;
import com.sparten.spartens_productivity.Model;
import com.sparten.spartens_productivity.R;

import me.tankery.lib.circularseekbar.CircularSeekBar;

import static android.content.Context.WINDOW_SERVICE;

public class WindowX {
    public static final String TAG = "     WindowX     ";
    private Context context;
    private View mView;
    private WindowManager.LayoutParams mParams;
    private WindowManager mWindowManager;
    private LayoutInflater layoutInflater;

    public WindowX() {
        //  close();viewGone();
    }
    CircularSeekBar seekbbbBar;
    CircularSeekBar seconds_sllllider;
    public void SendData(CircularSeekBar seekBar, CircularSeekBar seconds_slider) {
         this.seekbbbBar = seekBar;
        this.seconds_sllllider = seconds_slider;

    }
    @SuppressLint("ResourceAsColor")
    public WindowX(final Context context) {
        this.context = context;


        Log.e(TAG, "Constructor executed");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            if (MainActivity3.cheak == 2) {
                // set the layout parameters of the window
                mParams = new WindowManager.LayoutParams(
                        WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT,
                        WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
                        WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS |
                                WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN,
                        PixelFormat.TRANSLUCENT
                );
                mParams.gravity = Gravity.TOP | Gravity.CENTER_HORIZONTAL;
                mParams.horizontalMargin = 0;
                mParams.verticalMargin = 0;

            } else {
                // the simple toucheble view this is
                mParams = new WindowManager.LayoutParams(
                        WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT,
                        WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
                        WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE | WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS |
                                WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN,
                        PixelFormat.TRANSLUCENT
                );
                mParams.gravity = Gravity.TOP | Gravity.CENTER_HORIZONTAL;
                mParams.horizontalMargin = 0;
                mParams.verticalMargin = 0;

            }


        }

            layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            // inflating the view with the custom layout we created
            mView = layoutInflater.inflate(R.layout.bubble_layout, null);

            //sparten layout
        if(MainActivity3.cheak == 2) {//the thing is here it been overriden by the  above buuble layout and on the  sprten layout selcted the  view try to assigne which is null
            layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            // inflating the view with the custom layout we created
            mView = layoutInflater.inflate(R.layout.sperten_hardcore, null);


        }


//it will be used  to cancel filter when you click on it
        if (MainActivity3.cheak == 2) {
            mView.findViewById(R.id.cancel).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    close(new Foreground());


                    Toast.makeText(context,"Goal is canceled",Toast.LENGTH_SHORT).show();
                    Log.e(TAG, "filter change /red hardcore executed");

                }
            });


        }

        else {


        mView.findViewById(R.id.window_close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // close(); it will close the window
                Log.e(TAG, "filter change /close method Executed");

            }
        });

    }
        mParams.gravity = Gravity.CENTER|Gravity.FILL_VERTICAL;
        mWindowManager = (WindowManager) context.getSystemService(WINDOW_SERVICE);

    }



    //starting of the filter will also through app screen will be done
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void open() {
        Log.e(TAG, "OPEN executed");

        try {
            // check if the view is already
            // inflated or present in the window
            if (mView.getWindowToken() == null) {
                if (mView.getParent() == null) {

                    if(MainActivity3.cheak == 2) {
                        if(Model.getPost() !=  null) {
                            TextView textView = mView.findViewById(R.id.goal);
                            textView.setText(Model.getPost());
                        }else {
                            TextView textView = mView.findViewById(R.id.goal);
                            textView.setText("Please Write you goal here");
                        }
                        mWindowManager.addView(mView, mParams);
                    }else{

                        mView.findViewById(R.id.window_close).setBackgroundColor(Color.parseColor("#74F34242"));
                        mWindowManager.addView(mView, mParams);
                    }
                }
            }
        } catch (Exception e) {
            Log.e("Error1", e.toString());
        }

    }


    //closing od  the filter is done by the Notification component as button will be given on it
    //close();
    public void close(Foreground foreground) {
        Log.e(TAG, "CLOSE executed");

        try {
            SecondsTimer.Stop();
            Minute_Timer.Stop();

            // remove the view from the window
            ((WindowManager) context.getSystemService(WINDOW_SERVICE)).removeView(mView);
            // invalidate the view
            mView.invalidate();
            // remove all views
            ((ViewGroup) mView.getParent()).removeAllViews();
            // the above steps are necessary when you are adding and removing
            // the view simultaneously, it might give some exceptions
       } catch (Exception e) {
            Log.e("Error2", e.toString());
       }
    }

    //going to remove the filter when we want
    public  void viewGone() {

    }



}