package com.example.krankbusiness.helper;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;

import androidx.appcompat.app.AlertDialog;

import com.example.krankbusiness.R;
import com.google.android.material.button.MaterialButton;

public class NetworkChangeListener extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (!Common.isConnectedWithInternet(context)){
            AlertDialog.Builder builder=new AlertDialog.Builder(context);
            View layout_dialog= LayoutInflater.from(context).inflate(R.layout.internet_check_dialog,null);
            builder.setView(layout_dialog);

            MaterialButton retry=layout_dialog.findViewById(R.id.retryBtnInternet);

            AlertDialog dialog=builder.create();
            dialog.show();
            dialog.setCancelable(false);

            retry.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                    onReceive(context,intent);
                }
            });
        }
    }
}
