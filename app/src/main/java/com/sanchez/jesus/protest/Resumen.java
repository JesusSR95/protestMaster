package com.sanchez.jesus.protest;

import android.content.Context;
import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class Resumen extends AppCompatActivity {
    private static final String TAG="activity_splash_screen";
    Context myContext;
    CoordinatorLayout coordinatorLayout;
    Repositorio repositorio;


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
        Repositorio.recuperarPreguntas(myContext).size();
        TextView pregunta = findViewById(R.id.pregunta);
        pregunta.setText("Hay un total de: " + Repositorio.recuperarPreguntas(myContext).size()+ " preguntas");
        TextView contarCategoria = findViewById(R.id.contarCategoria);
        contarCategoria.setText("Hay un total de: " + Repositorio.recuperarPreguntas(myContext).size()+ " categorias");
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
