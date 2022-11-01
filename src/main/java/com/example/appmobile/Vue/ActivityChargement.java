package com.example.appmobile.Vue;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.appmobile.R;

import java.util.Timer;
import java.util.TimerTask;

public class ActivityChargement extends AppCompatActivity {



    private void init(){
        Timer timer=new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Intent in =new Intent(ActivityChargement.this, ActivityAcceuil.class);
                startActivity(in);
                overridePendingTransition(R.anim.slide_in_bottom,R.anim.slide_out_top);
                finish();

            }
        },2000);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }
}







                //ActivityOptions op= ActivityOptions.makeSceneTransitionAnimation(MainActivity.this,img,"nametrans");
               // startActivity(in,op.toBundle());
