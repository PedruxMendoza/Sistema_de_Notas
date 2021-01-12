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

public class NotasTeoricas extends AppCompatActivity {
    private SeekBar Periodos;
    public TextView cantperiodos;
    public EditText cantmaterias;
    public TextView porcentaje;
    private Button Ingresar;
    private Button Mostrar;
    public double porcNotas [];
    public double porcPeriodo [];
    public double NotasP1T [];
    public double NotasP2T [];
    public double NotasP3T [];
    public double porcfinal, porcfinalper;
    public int cmat, cper;
    public double pp1T, pp2T, pp3T, nfT, nfR, nfE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notas_teoricas);
        Ingresar = (Button) findViewById(R.id.btnIngresar);
        Mostrar = (Button) findViewById(R.id.btnMostrar);
        Periodos = (SeekBar) findViewById(R.id.skbPeriodo);
        porcentaje = (TextView) findViewById(R.id.tvPorcentaje);
        cantperiodos = (TextView) findViewById(R.id.txtcantperiodo);
        cantmaterias = (EditText) findViewById(R.id.txtcantNotas);
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
        cmat = Integer.parseInt(cantmaterias.getText().toString());
        cper = Integer.parseInt(cantperiodos.getText().toString());

        NotasP1T = new double[cmat];
        NotasP2T = new double[cmat];
        NotasP3T = new double[cmat];
        porcNotas = new double[cmat];
        porcPeriodo = new double[cper];

        for(int j=0;j<cmat;j++){
            IngresarPorcentajeNotas(j,"Ingrese el Porcentaje de la Nota "+ (j + 1) + ":");
        }
        for(int j=0;j<cmat;j++){
            IngresarPeriodo3(j,"Ingrese la Nota "+ (j + 1) + ":");
        }
        for(int j=0;j<cmat;j++){
            IngresarPeriodo2(j,"Ingrese la Nota "+ (j + 1) + ":");
        }
        for(int j=0;j<cmat;j++){
            IngresarPeriodo1(j,"Ingrese la Nota "+ (j + 1) + ":");
        }
        for(int j=0;j<cper;j++){
            IngresarPorcentajePeriodos(j,"Ingrese el Porcentaje del Periodo "+ (j + 1) + ":");
        }
        porcentaje.setText("");
    }

    private void IngresarPeriodo2() {
        cmat = Integer.parseInt(cantmaterias.getText().toString());
        cper = Integer.parseInt(cantperiodos.getText().toString());

        NotasP1T = new double[cmat];
        NotasP2T = new double[cmat];
        porcNotas = new double[cmat];
        porcPeriodo = new double[cper];

        for(int j=0;j<cmat;j++){
            IngresarPorcentajeNotas(j,"Ingrese el Porcentaje de la Nota "+ (j + 1) + ":");
        }
        for(int j=0;j<cmat;j++){
            IngresarPeriodo2(j,"Ingrese la Nota "+ (j + 1) + ":");
        }
        for(int j=0;j<cmat;j++){
            IngresarPeriodo1(j,"Ingrese la Nota "+ (j + 1) + ":");
        }
        for(int j=0;j<cper;j++){
            IngresarPorcentajePeriodos(j,"Ingrese el Porcentaje del Periodo "+ (j + 1) + ":");
        }
        IngresarNotaEsperada("Ingrese la Nota que desea sacar:");
        porcentaje.setText("");
    }

    private void IngresarPeriodo1() {
        cmat = Integer.parseInt(cantmaterias.getText().toString());
        cper = Integer.parseInt(cantperiodos.getText().toString());

        NotasP1T = new double[cmat];
        porcNotas = new double[cmat];
        porcPeriodo = new double[cper];

        for(int j=0;j<cmat;j++){
            IngresarPorcentajeNotas(j,"Ingrese el Porcentaje de la Nota "+ (j + 1) + ":");
        }
        for(int j=0;j<cmat;j++){
            IngresarPeriodo1(j,"Ingrese la Nota "+ (j + 1) + ":");
        }
        for(int j=0;j<cper;j++){
            IngresarPorcentajePeriodos(j,"Ingrese el Porcentaje del Periodo "+ (j + 1) + ":");
        }
        IngresarNotaEsperada("Ingrese la Nota que desea sacar:");
        porcentaje.setText("");
    }

    private void CalcularPeriodo1() {
        porcfinal =0;
        pp1T=0;
        for(int j=0;j<cmat;j++){
            porcfinal = porcNotas[j] + porcfinal;
        }

        if (porcfinal != 1)
        {
            showError();
        }
        else
        {
            for(int j=0;j<cper;j++){
                porcfinalper = porcPeriodo[j] + porcfinalper;
            }

            for (int i = 0; i < cmat; i++)
            {
                porcentaje.append("Nota "+ (i+1)+ " : " + NotasP1T[i]);
                porcentaje.append("\n");
                porcentaje.append("Porcentaje de la Nota "+ (i+1)+ " : " + porcNotas[i]);
                porcentaje.append("\n");
                pp1T += (NotasP1T[i] * porcNotas[i]);
            }
            porcentaje.append("Promedio del Periodo 1: "+ (double)Math.round(pp1T * 100d) / 100d);
            porcentaje.append("\n");
            nfT = pp1T * porcPeriodo[0];
            porcentaje.append("Acumulado del Periodo 1: "+ (double)Math.round(nfT* 100d) / 100d);
            porcentaje.append("\n");
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
        porcfinal =0;
        pp1T=0;
        pp2T=0;
        for(int j=0;j<cmat;j++){
            porcfinal = porcNotas[j] + porcfinal;
        }

        if (porcfinal != 1)
        {
            showError();
        }
        else
        {
            for(int j=0;j<cper;j++){
                porcfinalper = porcPeriodo[j] + porcfinalper;
            }

            for (int i = 0; i < cmat; i++)
            {
                porcentaje.append("Nota "+ (i+1)+ " : " + NotasP1T[i]);
                porcentaje.append("\n");
                porcentaje.append("Porcentaje de la Nota "+ (i+1)+ " : " + porcNotas[i]);
                porcentaje.append("\n");
                pp1T += (NotasP1T[i] * porcNotas[i]);
            }
            porcentaje.append("Promedio del Periodo 1: "+ (double)Math.round(pp1T * 100d) / 100d);
            porcentaje.append("\n");
            porcentaje.append("Acumulado del Periodo 1: "+ (double)Math.round((pp1T * porcPeriodo[0])* 100d) / 100d);
            porcentaje.append("\n");
            porcentaje.append("=============================");
            porcentaje.append("\n");
            for (int i = 0; i < cmat; i++)
            {
                porcentaje.append("Nota "+ (i+1)+ " : " + NotasP2T[i]);
                porcentaje.append("\n");
                porcentaje.append("Porcentaje de la Nota "+ (i+1)+ " : " + porcNotas[i]);
                porcentaje.append("\n");
                pp2T += (NotasP2T[i] * porcNotas[i]);
            }
            porcentaje.append("Promedio del Periodo 2: "+ (double)Math.round(pp2T * 100d) / 100d);
            porcentaje.append("\n");;
            porcentaje.append("Acumulado del Periodo 2: "+ (double)Math.round((pp2T * porcPeriodo[1])* 100d) / 100d);
            porcentaje.append("\n");
            nfT = (pp1T * porcPeriodo[0]) + (pp2T * porcPeriodo[1]);
            porcentaje.append("Nota Promedio: "+ (double)Math.round(nfT* 100d) / 100d);
            porcentaje.append("\n");
            porcentaje.append("Nota que deseas Sacar: "+ (double)Math.round(nfE* 100d) / 100d);
            porcentaje.append("\n");
            nfR = ((nfE - nfT)/(1 - ( porcPeriodo[0] +  porcPeriodo[1])));
            porcentaje.append("Nota que necesitas sacar en los Periodos Restante: "+ (double)Math.round(nfR* 100d) / 100d);
            porcentaje.append("\n");
            showAcierto();
        }
    }

    private void CalcularPeriodo3() {
        porcfinal =0;
        pp1T=0;
        pp2T=0;
        pp3T=0;
        for(int j=0;j<cmat;j++){
            porcfinal = porcNotas[j] + porcfinal;
        }
        if (porcfinal != 1)
        {
            showError();
        }
        else
        {
            for (int i = 0; i < cmat; i++)
            {
                porcentaje.append("Nota "+ (i+1)+ " : " + NotasP1T[i]);
                porcentaje.append("\n");
                porcentaje.append("Porcentaje de la Nota "+ (i+1)+ " : " + porcNotas[i]);
                porcentaje.append("\n");
                pp1T += (NotasP1T[i] * porcNotas[i]);
            }
            porcentaje.append("Promedio del Periodo 1: "+ (double)Math.round(pp1T * 100d) / 100d);
            porcentaje.append("\n");
            porcentaje.append("Acumulado del Periodo 1: "+ (double)Math.round((pp1T * porcPeriodo[0])* 100d) / 100d);
            porcentaje.append("\n");
            porcentaje.append("=============================");
            porcentaje.append("\n");
            for (int i = 0; i < cmat; i++)
            {
                porcentaje.append("Nota "+ (i+1)+ " : " + NotasP2T[i]);
                porcentaje.append("\n");
                porcentaje.append("Porcentaje de la Nota "+ (i+1)+ " : " + porcNotas[i]);
                porcentaje.append("\n");
                pp2T += (NotasP2T[i] * porcNotas[i]);
            }
            porcentaje.append("Promedio del Periodo 2: "+ (double)Math.round(pp2T * 100d) / 100d);
            porcentaje.append("\n");;
            porcentaje.append("Acumulado del Periodo 2: "+ (double)Math.round((pp2T * porcPeriodo[1])* 100d) / 100d);
            porcentaje.append("\n");
            porcentaje.append("=============================");
            porcentaje.append("\n");
            for (int i = 0; i < cmat; i++)
            {
                porcentaje.append("Nota "+ (i+1)+ " : " + NotasP3T[i]);
                porcentaje.append("\n");
                porcentaje.append("Porcentaje de la Nota "+ (i+1)+ " : " + porcNotas[i]);
                porcentaje.append("\n");
                pp3T += (NotasP3T[i] * porcNotas[i]);
            }
            porcentaje.append("Promedio del Periodo 3: "+ (double)Math.round(pp3T * 100d) / 100d);
            porcentaje.append("\n");;
            porcentaje.append("Acumulado del Periodo 3: "+ (double)Math.round((pp3T * porcPeriodo[2])* 100d) / 100d);
            porcentaje.append("\n");
            nfT = (pp1T * porcPeriodo[0]) + (pp2T * porcPeriodo[1])+ (pp3T * porcPeriodo[2]);
            porcentaje.append("Nota Final del Ciclo: "+ (double)Math.round(nfT* 100d) / 100d);
            porcentaje.append("\n");
            showAcierto();
        }
    }



    public void IngresarPorcentajeNotas(final int i, String Mensaje)
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
                                porcNotas[i] = num;
                            }
                            else
                            {
                                porcNotas[i] = num / 100;
                            }
                        }
                        catch (NumberFormatException nf)
                        {
                            IngresarPorcentajeNotas(i, "Repita el porcentaje, el formato es incorrecto");
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

    public void IngresarPorcentajePeriodos(final int i, String Mensaje)
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
                                porcPeriodo[i] = num;
                            }
                            else
                            {
                                porcPeriodo[i] = num / 100;
                            }
                        }
                        catch (NumberFormatException nf)
                        {
                            IngresarPorcentajeNotas(i, "Repita el porcentaje, el formato es incorrecto");
                        }
                    }
                }).show();
    }

    public void IngresarPeriodo1(final int i, String Mensaje)
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
                            IngresarPeriodo1(i, "Repita su Nota, el formato es incorrecto");
                        }
                    }
                }).show();
    }

    public void IngresarPeriodo2(final int i, String Mensaje)
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
                            IngresarPeriodo2(i, "Repita su Nota, el formato es incorrecto");
                        }
                    }
                }).show();
    }

    public void IngresarPeriodo3(final int i, String Mensaje)
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
                            IngresarPeriodo3(i, "Repita su Nota, el formato es incorrecto");
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
