package com.sparten.Overlay;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import me.tankery.lib.circularseekbar.CircularSeekBar;

public class NotificationReceiver extends BroadcastReceiver {
    private static CircularSeekBar seekBarrrrr;
    private static CircularSeekBar secnds_seekbaaaaar;

    public void sendata(CircularSeekBar seekBarrrrr, CircularSeekBar secnds_seekbaaaaar) {
        this.seekBarrrrr = seekBarrrrr;
        this.secnds_seekbaaaaar =secnds_seekbaaaaar;
    }



    @Override
    public void onReceive(Context context, Intent intent) {
        String message = intent.getStringExtra("toastMessage");
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();

        //whenver filter executes it will be set to the zero
        seekBarrrrr.setProgress(0);
        secnds_seekbaaaaar.setProgress(0);
        context.stopService(new Intent(context, Foreground.class));
        Log.e("NotificationReceiver","Brodacast Executed");
    }


}

