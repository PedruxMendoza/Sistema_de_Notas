package com.example.pedro.promediodenotas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

public class Creditos extends AppCompatActivity {

    private Button Volv;
    private ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creditos);
        Volv = (Button)findViewById(R.id.btnVolver);
        iv = (ImageView) findViewById(R.id.logo);

        rotacion();

        Volv.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent volver= new Intent(Creditos.this,MainActivity.class);
                startActivity(volver);
                overridePendingTransition(R.anim.right_in, R.anim.right_out);
            }
        });
    }

    public void rotacion()
    {
        Animation animation = AnimationUtils.loadAnimation(this,R.anim.rotation);
        animation.setFillAfter(true);
        iv.startAnimation(animation);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.right_in, R.anim.right_out);
    }
}
