package com.sanchez.jesus.protest;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Listado extends AppCompatActivity {
    private ArrayList<Pregunta> lista2;


    private static final String TAG="activity_splash_screen";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.editar);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                       .setAction("Action", null).show();*/
                Intent intent = new Intent(Listado.this, Anadir.class);
                startActivity(intent);
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
        Repositorio.recoger(this);
        lista2 = Repositorio.getLista();
        final RecyclerView recyclerView = findViewById(R.id.recyclerView);
        if (lista2 != null){
            TextView informacion = findViewById(R.id.textView);
            informacion.setVisibility(View.INVISIBLE);
            Adaptador ad = new Adaptador(lista2);
            ad.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Acción al pulsar el elemento
                    int position = recyclerView.getChildAdapterPosition(v);
                    Toast.makeText(Listado.this, "Posición: " + String.valueOf(position) + " Categoria: " + lista2.get(position).getCategoria() + " Pregunta: " + lista2.get(position).getEnunciado(), Toast.LENGTH_SHORT)
                            .show();
                }
            });

            // Asocia el Adaptador al RecyclerView
            recyclerView.setAdapter(ad);

            // Muestra el RecyclerView en vertical
            recyclerView.setLayoutManager(new LinearLayoutManager(this));

        } else {
            TextView informacion = findViewById(R.id.textView);
            informacion.setVisibility(View.VISIBLE);
        }

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
