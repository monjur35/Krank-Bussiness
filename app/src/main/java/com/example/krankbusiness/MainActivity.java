package com.example.krankbusiness;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    Animation topAnim,bottomAnim;
    ImageView logo,icon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();

        topAnim= AnimationUtils.loadAnimation(this,R.anim.top_anim);
        bottomAnim= AnimationUtils.loadAnimation(this,R.anim.bottom_anim);

        logo=findViewById(R.id.logo_text);
        icon=findViewById(R.id.icon);

        logo.setAnimation(topAnim);
        icon.setAnimation(bottomAnim);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent authentication=new Intent(MainActivity.this,AuthenticationActivity.class);
                startActivity(authentication);
                finish();
            }
        },3000);


    }
}