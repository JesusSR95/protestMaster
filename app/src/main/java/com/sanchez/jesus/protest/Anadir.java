package com.sanchez.jesus.protest;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class Anadir extends AppCompatActivity {

    private static final String TAG="activity_splash_screen";
    final private int CODE_WRITE_EXTERNAL_STORAGE_PERMISSION = 123;
    private Context myContext;
    private CoordinatorLayout coordinatorLayout;
    EditText edtext1, edtext2, edtext3,  edtext4, edtext5;
    Spinner spinner1;
    Button bt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anadir);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        String[] categoria = {"Montaje y mantenimiento de equipos","Redes locales","Aplicaciones ofimáticas","Sistemas operativos monopuesto","FOL"};
        spinner.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categoria));

        bt = (Button) findViewById(R.id.botonAceptar);
        edtext1 = (EditText) findViewById(R.id.PreguntaeditText);
        edtext2 = (EditText) findViewById(R.id.respuestaCorrecta);
        spinner1 = (Spinner) findViewById(R.id.spinner);
        edtext3 = (EditText) findViewById(R.id.respuestaIncorrecta1);
        edtext4 = (EditText) findViewById(R.id.respuestaIncorrecta2);
        edtext5 = (EditText) findViewById(R.id.respuestaIncorrecta3);

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edtext1.getText().toString().isEmpty()) {
                    Snackbar.make(v, "Rellenar todos los campos", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();

                }else if(edtext2.getText().toString().isEmpty()){
                    Snackbar.make(v, "Rellenar todos los campos", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();

                }else if(edtext3.getText().toString().isEmpty()){
                    Snackbar.make(v, "Rellenar todos los campos", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();

                }else if(edtext4.getText().toString().isEmpty()){
                    Snackbar.make(v, "Rellenar todos los campos", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();

                }else if(edtext5.getText().toString().isEmpty()){
                    Snackbar.make(v, "Rellenar todos los campos", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
                else{

                    myContext = Anadir.this;
                    coordinatorLayout = findViewById(R.id.coordinatorLayout);
                    bt.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            int WriteExternalStoragePermission = ContextCompat.checkSelfPermission(myContext, Manifest.permission.WRITE_EXTERNAL_STORAGE);
                            Log.d("MainActivity", "WRITE_EXTERNAL_STORAGE Permission: " + WriteExternalStoragePermission);

                            if (WriteExternalStoragePermission != PackageManager.PERMISSION_GRANTED) {
                                // Permiso denegado
                                // A partir de Marshmallow (6.0) se pide aceptar o rechazar el permiso en tiempo de ejecución
                                // En las versiones anteriores no es posible hacerlo
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                                    ActivityCompat.requestPermissions(Anadir.this, new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE}, CODE_WRITE_EXTERNAL_STORAGE_PERMISSION);
                                    // Una vez que se pide aceptar o rechazar el permiso se ejecuta el método "onRequestPermissionsResult" para manejar la respuesta
                                    // Si el usuario marca "No preguntar más" no se volverá a mostrar este diálogo
                                } else {
                                    Snackbar.make(coordinatorLayout, getResources().getString(R.string.write_permiso_denegado), Snackbar.LENGTH_LONG)
                                            .show();
                                }
                            } else {
                                // Permiso aceptado
                                Snackbar.make(coordinatorLayout, getResources().getString(R.string.write_permiso_aceptado), Snackbar.LENGTH_LONG)
                                        .show();
                            }

                        }
                    });

                    //AQUI VA LO DE INSERTAR
                    Pregunta pregunta = new Pregunta(edtext1.getText().toString(), spinner1.getSelectedItem().toString() , edtext2.getText().toString(), edtext3.getText().toString(), edtext4.getText().toString(), edtext5.getText().toString());

                    Repositorio.insertar(myContext, pregunta);

                    finish();
                }

            }
        });
}


    @Override
    protected void onStart() {
        Mylog.d(TAG, "Iniciando OnStart");
        super.onStart();
        Mylog.d(TAG, "Finalizando OnStart");
    }

    @Override
    protected void onResume() {
        Mylog.d(TAG, "Iniciando OnResume");
        super.onResume();
        Mylog.d(TAG, "Finalizando OnResume");
    }

    @Override
    protected void onPause() {
        Mylog.d(TAG, "Iniciando OnPause");
        super.onPause();
        Mylog.d(TAG, "Finalizando OnPause");
    }

    @Override
    protected void onStop() {
        Mylog.d(TAG, "Iniciando OnStop");
        super.onStop();
        Mylog.d(TAG, "Finalizando OnStop");
    }

    @Override
    protected void onRestart() {
        Mylog.d(TAG, "Iniciando OnRestart");
        super.onRestart();
        Mylog.d(TAG, "Finalizando OnRestart");
    }

    @Override
    protected void onDestroy() {
        Mylog.d(TAG, "Iniciando OnDestroy");
        super.onDestroy();
        Mylog.d(TAG, "Finalizando OnDestroy");
    }
}
