package com.mad.bmicalculator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class SplashActivity extends AppCompatActivity {

    ImageView im1;
    Animation animation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        im1= (ImageView) findViewById(R.id.im1);
        animation= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.alpha);
        im1.startAnimation(animation);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                Intent i = new Intent(SplashActivity.this,MainActivity.class);
                startActivity(i);
                finish();
            }
        }).start();


    }

//    @Override
//    protected void onStart() {
//        super.onStart();
//        setContentView(R.layout.activity_splash);
//        im1= (ImageView) findViewById(R.id.im1);
//        animation= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.translate);
//        im1.startAnimation(animation);
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    Thread.sleep(5000);
//
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//
//                Intent i = new Intent(SplashActivity.this,MainActivity.class);
//                startActivity(i);
//                finish();
//            }
//        }).start();
//    }
}













