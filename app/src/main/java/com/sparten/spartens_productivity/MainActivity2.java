package com.sparten.spartens_productivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;

import java.util.Objects;

public class MainActivity2 extends AppCompatActivity {
    CheckBox checkBox;
    int Value = 0;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
// Hide the status bar.
        Objects.requireNonNull(getSupportActionBar()).hide();
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
        checkBox = findViewById(R.id.cheak);

         cheack();
    }

    private void cheack() {

        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                  if(checkBox.isChecked()){
                      Value = 1;
                      sharedPreferences = getSharedPreferences("MySharedPref",MODE_PRIVATE);
                      SharedPreferences.Editor myEdit = sharedPreferences.edit();
                      myEdit.putString("value", String.valueOf(Value));
                      myEdit.apply();

                  }else{
                      Value =0;
                  }
            }
        });

    }


    public void next(View view) {

        Intent intent = new Intent(MainActivity2.this,MainActivity3.class);
        startActivity(intent);
        finish();
    }
}