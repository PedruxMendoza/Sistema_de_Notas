package com.example.pedro.promediodenotas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class NotasTeoricaResultado extends AppCompatActivity {

    private TextView texto;
    private Button Volv;
    private Button correo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notas_teorica_resultado);

        texto = (TextView) findViewById(R.id.texto2);

        recogerExtras();

        Volv = (Button)findViewById(R.id.btnVolver);
        Volv.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent volver= new Intent(NotasTeoricaResultado.this,MainActivity.class);
                startActivity(volver);
                overridePendingTransition(R.anim.zoom_back_in, R.anim.zoom_back_out);
            }
        });

        correo = (Button) findViewById(R.id.btnCorreo);
        correo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lanzarActivity(v, texto.getText().toString());
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }
        });
    }

    public void recogerExtras() {
        //Aquí recogemos y tratamos los parámetros
        Bundle extras= getIntent().getExtras();
        String s= extras.getString("texto");
        texto.setText(s);
    }

    public void lanzarActivity(View v, String texto) {
        //Aquí lanzaremos el segundo activity
        Intent i = new Intent(this, Correo.class);
        if (texto.equals("") || texto.equals(null))
            i.putExtra("texto", "ERROR, NO SE PUEDE MOSTRAR EL RESULTADO");
        else
            i.putExtra("texto", texto.toString());
        startActivity(i);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }
}
