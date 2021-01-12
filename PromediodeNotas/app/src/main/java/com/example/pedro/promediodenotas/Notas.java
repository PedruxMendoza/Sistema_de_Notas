package com.example.pedro.promediodenotas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class Notas extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notas);

        LinearLayout ATeoria = (LinearLayout) findViewById(R.id.teoria);
        LinearLayout ALaboratorio = (LinearLayout) findViewById(R.id.laboratorio);

        ATeoria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nuevaVentana = new Intent(Notas.this,NotasTeoricas.class);
                startActivity(nuevaVentana);
                overridePendingTransition(R.anim.zoom_forward_in, R.anim.zoom_forward_out);
            }
        });

        ALaboratorio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nuevaVentana = new Intent(Notas.this,NotasLaboratorio.class);
                startActivity(nuevaVentana);
                overridePendingTransition(R.anim.zoom_forward_in, R.anim.zoom_forward_out);
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.right_in, R.anim.right_out);
    }
}
