package com.sanchez.jesus.protest;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Resumen extends AppCompatActivity {
    private static final String TAG="Resumen";
    final private int CODE_WRITE_EXTERNAL_STORAGE_PERMISSION = 123;
    private Context myContext;
    private Button bt;
    CoordinatorLayout coordinatorLayout;
    Repositorio repositorio;
    private TextView contarCategorias;
    private TextView pregunta;


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_resumen, menu);
        return true;
    }
//Son las opciones que nos muestra en la pantalla resumen (los 3 puntos de arriba derecha)
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_buscar:
                Log.i("ActionBar", "Buscar");;
                return true;
            case R.id.action_settings:
                Log.i("ActionBar", "Opciones");
                return true;
            case R.id.action_preguntas:
                Log.i("ActionBar", "Preguntas");
                Intent intent = new Intent(Resumen.this, Listado.class);
                startActivity(intent);
                return true;
            case R.id.action_acerca:
                Log.i("ActionBar", "AcercaDe");
                Intent intent2 = new Intent(Resumen.this, AcercaDe.class);
                startActivity(intent2);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resumen);
        myContext = this;
        compruebaPermisos();
        coordinatorLayout = findViewById(R.id.coordinatorLayout);
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
        pregunta = findViewById(R.id.pregunta);
        pregunta.setText("Hay un total de " + Repositorio.getTotalPregunta(myContext)+ " preguntas");

        contarCategorias = findViewById(R.id.contarCategorias);
        contarCategorias.setText("Hay un total de " + Repositorio.getTotalCategoria(myContext)+ " categorias");

        //permisos();
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

    private void compruebaPermisos() {
        int WriteExternalStoragePermission = ContextCompat.checkSelfPermission(myContext, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        Mylog.d("MainActivity", "WRITE_EXTERNAL_STORAGE Permission: " + WriteExternalStoragePermission);

        if (WriteExternalStoragePermission != PackageManager.PERMISSION_GRANTED) {


            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                ActivityCompat.requestPermissions(Resumen.this, new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE}, CODE_WRITE_EXTERNAL_STORAGE_PERMISSION);

            } else {

                Mylog.e("Permisos: ","Rechazados");

            }
        } else {

            Mylog.e("Permisos: ","Rechazados");
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case CODE_WRITE_EXTERNAL_STORAGE_PERMISSION:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {



                } else {

                    Mylog.e("Permisos: ","Rechazados");

                }

                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }
}
