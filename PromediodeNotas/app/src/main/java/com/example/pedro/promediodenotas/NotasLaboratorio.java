package com.example.pedro.promediodenotas;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class NotasLaboratorio extends AppCompatActivity {
    private SeekBar Periodos;
    public TextView cantperiodos;
    public EditText cantnoteo;
    public EditText cantnotlab;
    public TextView porcentaje;
    private Button Ingresar;
    private Button Mostrar;
    public double porcNotasT [];
    public double porcNotasL [];
    public double porcPeriodoT [];
    public double porcPeriodoL [];
    public double NotasP1T [];
    public double NotasP2T [];
    public double NotasP3T [];
    public double NotasP1L [];
    public double NotasP2L [];
    public double NotasP3L [];
    public double porcfinalT, porcfinalper, porcfinalL;
    public int cnotT, cnotL, cper;
    public double pp1T, pp2T, pp3T, nfT, nfR, nfE, pp1L, pp2L, pp3L;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notas_laboratorio);

        Ingresar = (Button) findViewById(R.id.btnIngresar);
        Mostrar = (Button) findViewById(R.id.btnMostrar);
        Periodos = (SeekBar) findViewById(R.id.skbPeriodo);
        porcentaje = (TextView) findViewById(R.id.tvPorcentaje);
        cantperiodos = (TextView) findViewById(R.id.txtcantperiodo);
        cantnoteo = (EditText) findViewById(R.id.txtcantNotasTeo);
        cantnotlab = (EditText) findViewById(R.id.txtcantNotasLab);
        cantperiodos.setText("1");
        Periodos.setMax(2);

        Ingresar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                cper = Integer.parseInt(cantperiodos.getText().toString());

                if (cper == 1)
                {
                    IngresarPeriodo1();
                }
                else if (cper == 2)
                {
                    IngresarPeriodo2();
                }
                else
                {
                    IngresarPeriodo3();
                }
            }
        });

        Mostrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cper = Integer.parseInt(cantperiodos.getText().toString());

                if (cper == 1)
                {
                    CalcularPeriodo1();
                    lanzarActivity(v, porcentaje.getText().toString());
                }
                else if (cper == 2)
                {
                    CalcularPeriodo2();
                    lanzarActivity(v, porcentaje.getText().toString());
                }
                else
                {
                    CalcularPeriodo3();
                    lanzarActivity(v, porcentaje.getText().toString());
                }
            }
        });

        Periodos.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                //La Seekbar siempre empieza en cero si queremos que el valor minimo sea otro podemos modificarlo
                cantperiodos.setText(progress +  1 + "");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
    }

    private void IngresarPeriodo3() {
        cnotT = Integer.parseInt(cantnoteo.getText().toString());
        cnotL = Integer.parseInt(cantnotlab.getText().toString());
        cper = Integer.parseInt(cantperiodos.getText().toString());

        NotasP1T = new double[cnotT];
        NotasP1L = new  double[cnotL];
        NotasP2T = new double[cnotT];
        NotasP2L = new double[cnotL];
        NotasP3T = new double[cnotT];
        NotasP3L = new double[cnotL];
        porcNotasT = new double[cnotT];
        porcNotasL = new double[cnotL];
        porcPeriodoT = new double[cper];
        porcPeriodoL = new double[cper];

        for(int j=0;j<cnotL;j++){
            IngresarPorcentajeNotasLab(j,"Ingrese el Porcentaje de la Nota Lab "+ (j + 1) + ":");
        }
        for(int j=0;j<cnotT;j++){
            IngresarPorcentajeNotasTeo(j,"Ingrese el Porcentaje de la Nota Teorica "+ (j + 1) + ":");
        }
        for(int j=0;j<cnotL;j++){
            IngresarPeriodo3Lab(j,"Ingrese la Nota Lab "+ (j + 1) + ":");
        }
        for(int j=0;j<cnotT;j++){
            IngresarPeriodo3Teo(j,"Ingrese la Nota Teorica "+ (j + 1) + ":");
        }
        for(int j=0;j<cnotL;j++){
            IngresarPeriodo2Lab(j,"Ingrese la Nota Lab "+ (j + 1) + ":");
        }
        for(int j=0;j<cnotT;j++){
            IngresarPeriodo2Teo(j,"Ingrese la Nota Teorica "+ (j + 1) + ":");
        }
        for(int j=0;j<cnotL;j++){
            IngresarPeriodo1Lab(j,"Ingrese la Nota Lab "+ (j + 1) + ":");
        }
        for(int j=0;j<cnotT;j++){
            IngresarPeriodo1Teo(j,"Ingrese la Nota Teorica "+ (j + 1) + ":");
        }
        for(int j=0;j<cper;j++){
            IngresarPorcentajePeriodosLab(j,"Ingrese el Porcentaje del Periodo Lab "+ (j + 1) + ":");
        }
        for(int j=0;j<cper;j++){
            IngresarPorcentajePeriodosTeo(j,"Ingrese el Porcentaje del Periodo Teorico "+ (j + 1) + ":");
        }
        porcentaje.setText("");
    }

    private void IngresarPeriodo2() {
        cnotT = Integer.parseInt(cantnoteo.getText().toString());
        cnotL = Integer.parseInt(cantnotlab.getText().toString());
        cper = Integer.parseInt(cantperiodos.getText().toString());

        NotasP1T = new double[cnotT];
        NotasP1L = new  double[cnotL];
        NotasP2T = new double[cnotT];
        NotasP2L = new double[cnotL];
        porcNotasT = new double[cnotT];
        porcNotasL = new double[cnotL];
        porcPeriodoT = new double[cper];
        porcPeriodoL = new double[cper];

        for(int j=0;j<cnotL;j++){
            IngresarPorcentajeNotasLab(j,"Ingrese el Porcentaje de la Nota Lab "+ (j + 1) + ":");
        }
        for(int j=0;j<cnotT;j++){
            IngresarPorcentajeNotasTeo(j,"Ingrese el Porcentaje de la Nota Teorica "+ (j + 1) + ":");
        }
        for(int j=0;j<cnotL;j++){
            IngresarPeriodo2Lab(j,"Ingrese la Nota Lab "+ (j + 1) + ":");
        }
        for(int j=0;j<cnotT;j++){
            IngresarPeriodo2Teo(j,"Ingrese la Nota Teorica "+ (j + 1) + ":");
        }
        for(int j=0;j<cnotL;j++){
            IngresarPeriodo1Lab(j,"Ingrese la Nota Lab "+ (j + 1) + ":");
        }
        for(int j=0;j<cnotT;j++){
            IngresarPeriodo1Teo(j,"Ingrese la Nota Teorica "+ (j + 1) + ":");
        }
        for(int j=0;j<cper;j++){
            IngresarPorcentajePeriodosLab(j,"Ingrese el Porcentaje del Periodo Lab "+ (j + 1) + ":");
        }
        for(int j=0;j<cper;j++){
            IngresarPorcentajePeriodosTeo(j,"Ingrese el Porcentaje del Periodo Teorico "+ (j + 1) + ":");
        }
        IngresarNotaEsperada("Ingrese la Nota que desea sacar:");
        porcentaje.setText("");
    }

    private void IngresarPeriodo1() {
        cnotT = Integer.parseInt(cantnoteo.getText().toString());
        cnotL = Integer.parseInt(cantnotlab.getText().toString());
        cper = Integer.parseInt(cantperiodos.getText().toString());

        NotasP1T = new double[cnotT];
        NotasP1L = new  double[cnotL];
        porcNotasT = new double[cnotT];
        porcNotasL = new double[cnotL];
        porcPeriodoT = new double[cper];
        porcPeriodoL = new double[cper];

        for(int j=0;j<cnotL;j++){
            IngresarPorcentajeNotasLab(j,"Ingrese el Porcentaje de la Nota Lab "+ (j + 1) + ":");
        }
        for(int j=0;j<cnotT;j++){
            IngresarPorcentajeNotasTeo(j,"Ingrese el Porcentaje de la Nota Teorica "+ (j + 1) + ":");
        }
        for(int j=0;j<cnotL;j++){
            IngresarPeriodo1Lab(j,"Ingrese la Nota Lab "+ (j + 1) + ":");
        }
        for(int j=0;j<cnotT;j++){
            IngresarPeriodo1Teo(j,"Ingrese la Nota Teorica "+ (j + 1) + ":");
        }
        for(int j=0;j<cper;j++){
            IngresarPorcentajePeriodosLab(j,"Ingrese el Porcentaje del Periodo Lab "+ (j + 1) + ":");
        }
        for(int j=0;j<cper;j++){
            IngresarPorcentajePeriodosTeo(j,"Ingrese el Porcentaje del Periodo Teorico "+ (j + 1) + ":");
        }
        IngresarNotaEsperada("Ingrese la Nota que desea sacar:");
        porcentaje.setText("");
    }

    private void CalcularPeriodo1() {
        porcfinalT =0;
        porcfinalL = 0;
        pp1T=0;
        pp1L=0;
        for(int j=0;j<cnotT;j++){
            porcfinalT = porcNotasT[j] + porcfinalT;
        }
        for(int j=0;j<cnotL;j++){
            porcfinalL = porcNotasL[j] + porcfinalL;
        }

        if ((porcfinalT != 1)||(porcfinalL !=1))
        {
            showError();
        }
        else
        {
            porcfinalper = porcPeriodoT[0] + porcPeriodoL[0];

            for (int i = 0; i < cnotT; i++)
            {
                porcentaje.append("Nota Teorica "+ (i+1)+ " : " + NotasP1T[i]);
                porcentaje.append("\n");
                porcentaje.append("Porcentaje de la Nota Teorica "+ (i+1)+ " : " + porcNotasT[i]);
                porcentaje.append("\n");
                pp1T += (NotasP1T[i] * porcNotasT[i]);
            }
            porcentaje.append("Promedio del Periodo 1 (Teoria): "+ (double)Math.round(pp1T * 100d) / 100d);
            porcentaje.append("\n");
            porcentaje.append("Acumulado del Periodo 1 (Teoria): "+ (double)Math.round((pp1T * porcPeriodoT[0])* 100d) / 100d);
            porcentaje.append("\n");
            for (int i = 0; i < cnotL; i++)
            {
                porcentaje.append("Nota Laboratorio "+ (i+1)+ " : " + NotasP1L[i]);
                porcentaje.append("\n");
                porcentaje.append("Porcentaje de la Nota Laboratorio "+ (i+1)+ " : " + porcNotasL[i]);
                porcentaje.append("\n");
                pp1L += (NotasP1L[i] * porcNotasL[i]);
            }
            porcentaje.append("Promedio del Periodo 1 (Laboratorio): "+ (double)Math.round(pp1L * 100d) / 100d);
            porcentaje.append("\n");
            porcentaje.append("Acumulado del Periodo 1 (Laboratorio): "+ (double)Math.round((pp1L * porcPeriodoL[0])* 100d) / 100d);
            porcentaje.append("\n");
            nfT = (pp1T * porcPeriodoT[0])+ (pp1L * porcPeriodoL[0]);
            porcentaje.append("Nota Promedio: "+ (double)Math.round(nfT* 100d) / 100d);
            porcentaje.append("\n");
            porcentaje.append("Nota que deseas Sacar: "+ (double)Math.round(nfE* 100d) / 100d);
            porcentaje.append("\n");
            nfR = ((nfE - nfT)/(1 - porcfinalper));
            porcentaje.append("Nota que necesitas sacar en los Periodos Restante: "+ (double)Math.round(nfR* 100d) / 100d);
            porcentaje.append("\n");
            showAcierto();
        }
    }

    private void CalcularPeriodo2() {
        porcfinalT =0;
        porcfinalL = 0;
        pp1T=0;
        pp1L=0;
        pp2T=0;
        pp2L=0;
        for(int j=0;j<cnotT;j++){
            porcfinalT = porcNotasT[j] + porcfinalT;
        }
        for(int j=0;j<cnotL;j++){
            porcfinalL = porcNotasL[j] + porcfinalL;
        }
        if ((porcfinalT != 1)||(porcfinalL !=1))
        {
            showError();
        }
        else
        {
            for(int j=0;j<cper;j++){
                porcfinalper = (porcPeriodoT[j] + porcPeriodoL[j]) + porcfinalper;
            }


            for (int i = 0; i < cnotT; i++)
            {
                porcentaje.append("Nota Teorica "+ (i+1)+ " : " + NotasP1T[i]);
                porcentaje.append("\n");
                porcentaje.append("Porcentaje de la Nota Teorica "+ (i+1)+ " : " + porcNotasT[i]);
                porcentaje.append("\n");
                pp1T += (NotasP1T[i] * porcNotasT[i]);
            }
            porcentaje.append("Promedio del Periodo 1 (Teoria): "+ (double)Math.round(pp1T * 100d) / 100d);
            porcentaje.append("\n");
            porcentaje.append("Acumulado del Periodo 1 (Teoria): "+ (double)Math.round((pp1T * porcPeriodoT[0])* 100d) / 100d);
            porcentaje.append("\n");
            for (int i = 0; i < cnotL; i++)
            {
                porcentaje.append("Nota Laboratorio "+ (i+1)+ " : " + NotasP1L[i]);
                porcentaje.append("\n");
                porcentaje.append("Porcentaje de la Nota Laboratorio "+ (i+1)+ " : " + porcNotasL[i]);
                porcentaje.append("\n");
                pp1L += (NotasP1L[i] * porcNotasL[i]);
            }
            porcentaje.append("Promedio del Periodo 1 (Laboratorio): "+ (double)Math.round(pp1L * 100d) / 100d);
            porcentaje.append("\n");
            porcentaje.append("Acumulado del Periodo 1 (Laboratorio): "+ (double)Math.round((pp1L * porcPeriodoL[0])* 100d) / 100d);
            porcentaje.append("\n");
            porcentaje.append("==================================================================");
            porcentaje.append("\n");
            for (int i = 0; i < cnotT; i++)
            {
                porcentaje.append("Nota Teorica "+ (i+1)+ " : " + NotasP2T[i]);
                porcentaje.append("\n");
                porcentaje.append("Porcentaje de la Nota Teorica "+ (i+1)+ " : " + porcNotasT[i]);
                porcentaje.append("\n");
                pp2T += (NotasP2T[i] * porcNotasT[i]);
            }
            porcentaje.append("Promedio del Periodo 2 (Teoria): "+ (double)Math.round(pp2T * 100d) / 100d);
            porcentaje.append("\n");
            porcentaje.append("Acumulado del Periodo 3 (Teoria): "+ (double)Math.round((pp2T * porcPeriodoT[1])* 100d) / 100d);
            porcentaje.append("\n");
            for (int i = 0; i < cnotL; i++)
            {
                porcentaje.append("Nota Laboratorio "+ (i+1)+ " : " + NotasP2L[i]);
                porcentaje.append("\n");
                porcentaje.append("Porcentaje de la Nota Laboratorio "+ (i+1)+ " : " + porcNotasL[i]);
                porcentaje.append("\n");
                pp2L += (NotasP2L[i] * porcNotasL[i]);
            }
            porcentaje.append("Promedio del Periodo 2 (Laboratorio): "+ (double)Math.round(pp2L * 100d) / 100d);
            porcentaje.append("\n");
            porcentaje.append("Acumulado del Periodo 2 (Laboratorio): "+ (double)Math.round((pp2L * porcPeriodoL[1])* 100d) / 100d);
            porcentaje.append("\n");
            nfT = (pp1T * porcPeriodoT[0]) + (pp2T * porcPeriodoT[1])+(pp1L * porcPeriodoL[0]) + (pp2L * porcPeriodoL[1]);
            porcentaje.append("Nota Promedio: "+ (double)Math.round(nfT* 100d) / 100d);
            porcentaje.append("\n");
            porcentaje.append("Nota que deseas Sacar: "+ (double)Math.round(nfE* 100d) / 100d);
            porcentaje.append("\n");
            nfR = ((nfE - nfT)/(1 - ( porcPeriodoT[0] +  porcPeriodoT[1] + porcPeriodoL[0]+ porcPeriodoL[1])));
            porcentaje.append("Nota que necesitas sacar en los Periodos Restante: "+ (double)Math.round(nfR* 100d) / 100d);
            porcentaje.append("\n");
            showAcierto();
        }
    }

    private void CalcularPeriodo3() {
        porcfinalT =0;
        porcfinalL =0;
        pp1T=0;
        pp1L=0;
        pp2T=0;
        pp2L=0;
        pp3T=0;
        pp3L=0;
        for(int j=0;j<cnotT;j++){
            porcfinalT = porcNotasT[j] + porcfinalT;
        }
        for(int j=0;j<cnotL;j++){
            porcfinalL = porcNotasL[j] + porcfinalL;
        }
        if ((porcfinalT != 1)||(porcfinalL !=1))
        {
            showError();
        }
        else
        {
            for(int j=0;j<cper;j++){
                porcfinalper = (porcPeriodoT[j] + porcPeriodoL[j]) + porcfinalper;
            }

            for (int i = 0; i < cnotT; i++)
            {
                porcentaje.append("Nota Teorica "+ (i+1)+ " : " + NotasP1T[i]);
                porcentaje.append("\n");
                porcentaje.append("Porcentaje de la Nota Teorica "+ (i+1)+ " : " + porcNotasT[i]);
                porcentaje.append("\n");
                pp1T += (NotasP1T[i] * porcNotasT[i]);
            }
            porcentaje.append("Promedio del Periodo 1 (Teoria): "+ (double)Math.round(pp1T * 100d) / 100d);
            porcentaje.append("\n");
            porcentaje.append("Acumulado del Periodo 1 (Teoria): "+ (double)Math.round((pp1T * porcPeriodoT[0])* 100d) / 100d);
            porcentaje.append("\n");
            for (int i = 0; i < cnotL; i++)
            {
                porcentaje.append("Nota Laboratorio "+ (i+1)+ " : " + NotasP1L[i]);
                porcentaje.append("\n");
                porcentaje.append("Porcentaje de la Nota Laboratorio "+ (i+1)+ " : " + porcNotasL[i]);
                porcentaje.append("\n");
                pp1L += (NotasP1L[i] * porcNotasL[i]);
            }
            porcentaje.append("Promedio del Periodo 1 (Laboratorio): "+ (double)Math.round(pp1L * 100d) / 100d);
            porcentaje.append("\n");
            porcentaje.append("Acumulado del Periodo 1 (Laboratorio): "+ (double)Math.round((pp1L * porcPeriodoL[0])* 100d) / 100d);
            porcentaje.append("\n");
            porcentaje.append("==================================================================");
            porcentaje.append("\n");
            for (int i = 0; i < cnotT; i++)
            {
                porcentaje.append("Nota Teorica "+ (i+1)+ " : " + NotasP2T[i]);
                porcentaje.append("\n");
                porcentaje.append("Porcentaje de la Nota Teorica "+ (i+1)+ " : " + porcNotasT[i]);
                porcentaje.append("\n");
                pp2T += (NotasP2T[i] * porcNotasT[i]);
            }
            porcentaje.append("Promedio del Periodo 2 (Teoria): "+ (double)Math.round(pp2T * 100d) / 100d);
            porcentaje.append("\n");
            porcentaje.append("Acumulado del Periodo 3 (Teoria): "+ (double)Math.round((pp2T * porcPeriodoT[1])* 100d) / 100d);
            porcentaje.append("\n");
            for (int i = 0; i < cnotL; i++)
            {
                porcentaje.append("Nota Laboratorio "+ (i+1)+ " : " + NotasP2L[i]);
                porcentaje.append("\n");
                porcentaje.append("Porcentaje de la Nota Laboratorio "+ (i+1)+ " : " + porcNotasL[i]);
                porcentaje.append("\n");
                pp2L += (NotasP2L[i] * porcNotasL[i]);
            }
            porcentaje.append("Promedio del Periodo 2 (Laboratorio): "+ (double)Math.round(pp2L * 100d) / 100d);
            porcentaje.append("\n");
            porcentaje.append("Acumulado del Periodo 2 (Laboratorio): "+ (double)Math.round((pp2L * porcPeriodoL[1])* 100d) / 100d);
            porcentaje.append("\n");
            porcentaje.append("==================================================================");
            porcentaje.append("\n");
            for (int i = 0; i < cnotT; i++)
            {
                porcentaje.append("Nota Teorica "+ (i+1)+ " : " + NotasP3T[i]);
                porcentaje.append("\n");
                porcentaje.append("Porcentaje de la Nota Teorica "+ (i+1)+ " : " + porcNotasT[i]);
                porcentaje.append("\n");
                pp3T += (NotasP3T[i] * porcNotasT[i]);
            }
            porcentaje.append("Promedio del Periodo 3 (Teoria): "+ (double)Math.round(pp3T * 100d) / 100d);
            porcentaje.append("\n");
            porcentaje.append("Acumulado del Periodo 3 (Teoria): "+ (double)Math.round((pp3T * porcPeriodoT[2])* 100d) / 100d);
            porcentaje.append("\n");
            for (int i = 0; i < cnotL; i++)
            {
                porcentaje.append("Nota Laboratorio "+ (i+1)+ " : " + NotasP3L[i]);
                porcentaje.append("\n");
                porcentaje.append("Porcentaje de la Nota Laboratorio "+ (i+1)+ " : " + porcNotasL[i]);
                porcentaje.append("\n");
                pp3L += (NotasP3L[i] * porcNotasL[i]);
            }
            porcentaje.append("Promedio del Periodo 3 (Laboratorio): "+ (double)Math.round(pp3L * 100d) / 100d);
            porcentaje.append("\n");
            porcentaje.append("Acumulado del Periodo 3 (Laboratorio): "+ (double)Math.round((pp3L * porcPeriodoL[2])* 100d) / 100d);
            porcentaje.append("\n");
            nfT = (pp1T * porcPeriodoT[0]) + (pp2T * porcPeriodoT[1])+(pp3T * porcPeriodoT[2])+(pp1L * porcPeriodoL[0]) + (pp2L * porcPeriodoL[1])+(pp3L * porcPeriodoL[2]);
            porcentaje.append("Nota Final del Ciclo: "+ (double)Math.round(nfT* 100d) / 100d);
            porcentaje.append("\n");
            showAcierto();
        }
    }



    public void IngresarPorcentajeNotasTeo(final int i, String Mensaje)
    {
        final EditText txtNum = new EditText(this);
        txtNum.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        txtNum.setTextColor(Color.WHITE);
        new AlertDialog.Builder(this, R.style.MyDialogTheme)
                .setTitle("Porcentaje de las Notas")
                .setMessage(Mensaje)
                .setView(txtNum)
                .setPositiveButton("Ingresar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        double num = Double.valueOf(txtNum.getText().toString());
                        try
                        {
                            if (num < 1)
                            {
                                porcNotasT[i] = num;
                            }
                            else
                            {
                                porcNotasT[i] = num / 100;
                            }
                        }
                        catch (NumberFormatException nf)
                        {
                            IngresarPorcentajeNotasTeo(i, "Repita el porcentaje, el formato es incorrecto");
                        }
                    }
                }).show();
    }

    public void IngresarPorcentajeNotasLab(final int i, String Mensaje)
    {
        final EditText txtNum = new EditText(this);
        txtNum.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        txtNum.setTextColor(Color.WHITE);
        new AlertDialog.Builder(this, R.style.MyDialogTheme)
                .setTitle("Porcentaje de las Notas")
                .setMessage(Mensaje)
                .setView(txtNum)
                .setPositiveButton("Ingresar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        double num = Double.valueOf(txtNum.getText().toString());
                        try
                        {
                            if (num < 1)
                            {
                                porcNotasL[i] = num;
                            }
                            else
                            {
                                porcNotasL[i] = num / 100;
                            }
                        }
                        catch (NumberFormatException nf)
                        {
                            IngresarPorcentajeNotasTeo(i, "Repita el porcentaje, el formato es incorrecto");
                        }
                    }
                }).show();
    }

    public void IngresarNotaEsperada(String Mensaje)
    {
        final EditText txtNum = new EditText(this);
        txtNum.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        txtNum.setTextColor(Color.WHITE);
        new AlertDialog.Builder(this, R.style.MyDialogTheme)
                .setTitle("Nota Esperada")
                .setMessage(Mensaje)
                .setView(txtNum)
                .setPositiveButton("Ingresar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        double num = Double.valueOf(txtNum.getText().toString());
                        try
                        {
                            if (num <= 10)
                            {
                                nfE = num;
                            }
                            else
                            {
                                nfE = num / 10;
                            }
                        }
                        catch (NumberFormatException nf)
                        {
                            IngresarNotaEsperada("Repita la nota, el formato es incorrecto");
                        }
                    }
                }).show();
    }

    public void IngresarPorcentajePeriodosTeo(final int i, String Mensaje)
    {
        final EditText txtNum = new EditText(this);
        txtNum.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        txtNum.setTextColor(Color.WHITE);
        new AlertDialog.Builder(this, R.style.MyDialogTheme)
                .setTitle("Porcentaje de las Periodos")
                .setMessage(Mensaje)
                .setView(txtNum)
                .setPositiveButton("Ingresar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        double num = Double.valueOf(txtNum.getText().toString());
                        try
                        {
                            if (num < 1)
                            {
                                porcPeriodoT[i] = num;
                            }
                            else
                            {
                                porcPeriodoT[i] = num / 100;
                            }
                        }
                        catch (NumberFormatException nf)
                        {
                            IngresarPorcentajeNotasTeo(i, "Repita el porcentaje, el formato es incorrecto");
                        }
                    }
                }).show();
    }

    public void IngresarPorcentajePeriodosLab(final int i, String Mensaje)
    {
        final EditText txtNum = new EditText(this);
        txtNum.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        txtNum.setTextColor(Color.WHITE);
        new AlertDialog.Builder(this, R.style.MyDialogTheme)
                .setTitle("Porcentaje de las Periodos")
                .setMessage(Mensaje)
                .setView(txtNum)
                .setPositiveButton("Ingresar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        double num = Double.valueOf(txtNum.getText().toString());
                        try
                        {
                            if (num < 1)
                            {
                                porcPeriodoL[i] = num;
                            }
                            else
                            {
                                porcPeriodoL[i] = num / 100;
                            }
                        }
                        catch (NumberFormatException nf)
                        {
                            IngresarPorcentajeNotasTeo(i, "Repita el porcentaje, el formato es incorrecto");
                        }
                    }
                }).show();
    }

    public void IngresarPeriodo1Teo(final int i, String Mensaje)
    {
        final EditText txtNum = new EditText(this);
        txtNum.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        txtNum.setTextColor(Color.WHITE);
        new AlertDialog.Builder(this, R.style.MyDialogTheme)
                .setTitle("Notas del Primer Periodo")
                .setMessage(Mensaje)
                .setView(txtNum)
                .setPositiveButton("Ingresar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        double num = Double.valueOf(txtNum.getText().toString());
                        try
                        {
                            if (num <= 10)
                            {
                                NotasP1T[i] = num;
                            }
                            else
                            {
                                NotasP1T[i] = num / 10;
                            }
                        }
                        catch (NumberFormatException nf)
                        {
                            IngresarPeriodo1Teo(i, "Repita su Nota, el formato es incorrecto");
                        }
                    }
                }).show();
    }

    public void IngresarPeriodo1Lab(final int i, String Mensaje)
    {
        final EditText txtNum = new EditText(this);
        txtNum.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        txtNum.setTextColor(Color.WHITE);
        new AlertDialog.Builder(this, R.style.MyDialogTheme)
                .setTitle("Notas del Primer Periodo")
                .setMessage(Mensaje)
                .setView(txtNum)
                .setPositiveButton("Ingresar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        double num = Double.valueOf(txtNum.getText().toString());
                        try
                        {
                            if (num <= 10)
                            {
                                NotasP1L[i] = num;
                            }
                            else
                            {
                                NotasP1L[i] = num / 10;
                            }
                        }
                        catch (NumberFormatException nf)
                        {
                            IngresarPeriodo1Teo(i, "Repita su Nota, el formato es incorrecto");
                        }
                    }
                }).show();
    }

    public void IngresarPeriodo2Teo(final int i, String Mensaje)
    {
        final EditText txtNum = new EditText(this);
        txtNum.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        txtNum.setTextColor(Color.WHITE);
        new AlertDialog.Builder(this, R.style.MyDialogTheme)
                .setTitle("Notas del Segundo Periodo")
                .setMessage(Mensaje)
                .setView(txtNum)
                .setPositiveButton("Ingresar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        double num = Double.valueOf(txtNum.getText().toString());
                        try
                        {
                            if (num < 10)
                            {
                                NotasP2T[i] = num;
                            }
                            else
                            {
                                NotasP2T[i] = num / 10;
                            }
                        }
                        catch (NumberFormatException nf)
                        {
                            IngresarPeriodo2Teo(i, "Repita su Nota, el formato es incorrecto");
                        }
                    }
                }).show();
    }

    public void IngresarPeriodo2Lab(final int i, String Mensaje)
    {
        final EditText txtNum = new EditText(this);
        txtNum.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        txtNum.setTextColor(Color.WHITE);
        new AlertDialog.Builder(this, R.style.MyDialogTheme)
                .setTitle("Notas del Segundo Periodo")
                .setMessage(Mensaje)
                .setView(txtNum)
                .setPositiveButton("Ingresar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        double num = Double.valueOf(txtNum.getText().toString());
                        try
                        {
                            if (num < 10)
                            {
                                NotasP2L[i] = num;
                            }
                            else
                            {
                                NotasP2L[i] = num / 10;
                            }
                        }
                        catch (NumberFormatException nf)
                        {
                            IngresarPeriodo2Teo(i, "Repita su Nota, el formato es incorrecto");
                        }
                    }
                }).show();
    }

    public void IngresarPeriodo3Teo(final int i, String Mensaje)
    {
        final EditText txtNum = new EditText(this);
        txtNum.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        txtNum.setTextColor(Color.WHITE);
        new AlertDialog.Builder(this, R.style.MyDialogTheme)
                .setTitle("Notas del Tercer Periodo")
                .setMessage(Mensaje)
                .setView(txtNum)
                .setPositiveButton("Ingresar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        double num = Double.valueOf(txtNum.getText().toString());
                        try
                        {
                            if (num < 10)
                            {
                                NotasP3T[i] = num;
                            }
                            else
                            {
                                NotasP3T[i] = num / 10;
                            }
                        }
                        catch (NumberFormatException nf)
                        {
                            IngresarPeriodo3Teo(i, "Repita su Nota, el formato es incorrecto");
                        }
                    }
                }).show();
    }

    public void IngresarPeriodo3Lab(final int i, String Mensaje)
    {
        final EditText txtNum = new EditText(this);
        txtNum.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        txtNum.setTextColor(Color.WHITE);
        new AlertDialog.Builder(this, R.style.MyDialogTheme)
                .setTitle("Notas del Tercer Periodo")
                .setMessage(Mensaje)
                .setView(txtNum)
                .setPositiveButton("Ingresar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        double num = Double.valueOf(txtNum.getText().toString());
                        try
                        {
                            if (num < 10)
                            {
                                NotasP3L[i] = num;
                            }
                            else
                            {
                                NotasP3L[i] = num / 10;
                            }
                        }
                        catch (NumberFormatException nf)
                        {
                            IngresarPeriodo3Teo(i, "Repita su Nota, el formato es incorrecto");
                        }
                    }
                }).show();
    }

    private void showAcierto()
    {
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.custom_toast_acierto, null);
        Toast toast = new Toast(this);
        toast.setView(view);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL, 0 ,0);
        toast.show();
    }

    private void showError()
    {
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.custom_toast_error, null);
        Toast toast = new Toast(this);
        toast.setView(view);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.show();
    }

    public void lanzarActivity(View v, String texto) {
        //Aquí lanzaremos el segundo activity
        Intent i = new Intent(this, NotasTeoricaResultado.class);
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
        overridePendingTransition(R.anim.zoom_back_in, R.anim.zoom_back_out);
    }
}
