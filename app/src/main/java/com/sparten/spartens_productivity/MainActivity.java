package com.sparten.spartens_productivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.WindowManager;
import android.widget.CheckBox;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

          Objects.requireNonNull(getSupportActionBar()).hide();
        View decorView = getWindow().getDecorView();
        // Hide the status bar.
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

        setContentView(R.layout.activity_main);
        where_to_go();
    }

    private void where_to_go() {


           SharedPreferences sh = getSharedPreferences("MySharedPref", MODE_PRIVATE);
           String namev = sh.getString("value", "data not found !");
        Log.e("Main splash","name"+namev);
        if(String.valueOf(namev).equals(String.valueOf(1))){
           new Handler().postDelayed(new Runnable(){
               @Override
               public void run() {

                   Intent mainIntent = new Intent(MainActivity.this, MainActivity3.class);
                   MainActivity.this.startActivity(mainIntent);
                   MainActivity.this.finish();
               }
           }, CONSTANTS.SPLASH_DISPLAY_LENGTH);
       }else{

           new Handler().postDelayed(new Runnable(){
               @Override
               public void run() {

                   Intent mainIntent = new Intent(MainActivity.this, MainActivity2.class);
                   MainActivity.this.startActivity(mainIntent);
                   MainActivity.this.finish();
               }
           }, CONSTANTS.SPLASH_DISPLAY_LENGTH);

       }

    }


}