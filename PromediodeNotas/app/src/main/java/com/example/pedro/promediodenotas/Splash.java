package com.example.pedro.promediodenotas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class Splash extends AppCompatActivity {
    private TextView tv;
    private TextView tw;
    private ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getSupportActionBar().hide();
        tw = (TextView) findViewById(R.id.tw);
        tv = (TextView) findViewById(R.id.tv);
        iv = (ImageView) findViewById(R.id.iv);

        Animation myAnimm = AnimationUtils.loadAnimation(this,R.anim.mytransition);
        tv.startAnimation(myAnimm);
        iv.startAnimation(myAnimm);
        tw.startAnimation(myAnimm);
        final Intent main = new Intent(this,MainActivity.class);
        Thread timer = new Thread()
        {
            public void run()
            {
                try
                {
                    sleep(3000);
                }catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
                finally {
                    startActivity(main);
                    finish();
                }
            }
        };
        timer.start();
    }
}
