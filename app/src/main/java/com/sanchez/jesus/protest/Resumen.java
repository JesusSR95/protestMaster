package com.sanchez.jesus.protest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class Resumen extends AppCompatActivity {
    private static final String TAG="activity_splash_screen";

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_resumen, menu);
        return true;
    }

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
        Mylog.d(TAG, "Iniciando OnCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resumen);
        Mylog.d(TAG, "Finalizando OnCreate");

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
