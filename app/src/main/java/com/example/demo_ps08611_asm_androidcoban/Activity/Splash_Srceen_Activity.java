package com.example.demo_ps08611_asm_androidcoban.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

import com.example.demo_ps08611_asm_androidcoban.R;

public class Splash_Srceen_Activity extends AppCompatActivity {
    private ImageView ivs;
    private ProgressBar progressBar ;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash__srceen);
        ivs= findViewById(R.id.ivs);
        Animation myanim = AnimationUtils.loadAnimation(this,R.anim.anima) ;
        ivs.startAnimation(myanim);
        final Intent i = new Intent(this, dangki.class);
        CountDownTimer countDownTimer = new CountDownTimer(2000,2000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                finish();
                startActivity(new Intent(getApplicationContext(),Login.class));
            }
        }.start();
    }
}

