package com.sanchez.jesus.protest;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Listado extends AppCompatActivity {
    private ArrayList<Pregunta> lista2;


    private static final String TAG="activity_splash_screen";
    private Context myContext;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myContext = Listado.this;
        setContentView(R.layout.activity_listado);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.editar);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Listado.this, Anadir.class);
                intent.putExtra("editar", false );
                startActivity(intent);
            }
        });

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return false;
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
        lista2 = new ArrayList<>();
        lista2 = Repositorio.getLista();

        if (lista2 != null){
            TextView informacion = findViewById(R.id.textView);
            informacion.setVisibility(View.INVISIBLE);
            Collections.reverse(lista2);
            Adaptador ad = new Adaptador(lista2);

            final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
            recyclerView.setHasFixedSize(true);

            // Crea el Adaptador con los datos de la lista anterior
            Adaptador adaptador = new Adaptador(lista2);
            ad.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Acción al pulsar el elemento
                    int position = recyclerView.getChildAdapterPosition(v);
                    Intent it = new Intent(Listado.this, Anadir.class);
                    it.putExtra(Constantes.CODPREGUNTA, lista2.get(position).getCodigo() );
                    it.putExtra(Constantes.EDITAR, true );
                    startActivity(it);

                }
            });

            // Asocia el Adaptador al RecyclerView
            recyclerView.setAdapter(ad);

            ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
                @Override
                public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                    return false;
                }
                @Override
                public void onSwiped(final RecyclerView.ViewHolder viewHolder, int direction) {
                    final int position = viewHolder.getAdapterPosition();

                    if (direction == ItemTouchHelper.LEFT) {
                        //si deslizamos a la derecha vamos a eliminar la pregunta,
                        // pero antes debemos confirmar esta accion


                        //Recuperación de la vista del AlertDialog a partir del layout de la Actividad
                        LayoutInflater layoutActivity = LayoutInflater.from(myContext);
                        View viewAlertDialog = layoutActivity.inflate(R.layout.eliminar_dialog, null);

                        //Definición del AlertDialog
                        android.support.v7.app.AlertDialog.Builder alertDialog = new android.support.v7.app.AlertDialog.Builder(myContext);

                        //Asignación del AlertDialog a su vista
                        alertDialog.setView(viewAlertDialog);


                        //Configuración del AlertDialog
                        alertDialog
                                .setCancelable(false)
                                //BotónAñadir
                                .setPositiveButton(getResources().getString(R.string.delete),
                                        new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialogBox, int id) {

                                                String codigo = Integer.toString(lista2.get(position).getCodigo());
                                                String enunciado = lista2.get(position).getEnunciado();
                                                String categoria = lista2.get(position).getCategoria();
                                                String respuestaCorrecta = lista2.get(position).getPreguntaCorrecta();
                                                String respuestaIncorrecta1 = lista2.get(position).getPreguntaInc1();
                                                String respuestaIncorrecta2 = lista2.get(position).getPreguntaInc2();
                                                String respuestaIncorrecta3 = lista2.get(position).getPreguntaInc3();
                                                String photo = lista2.get(position).getPhoto();

                                                Pregunta borrar = new Pregunta(codigo, enunciado, categoria, respuestaCorrecta, respuestaIncorrecta1, respuestaIncorrecta2, respuestaIncorrecta3, photo);

                                                Repositorio.eliminaPregunta(borrar, myContext);

                                                onResume();

                                            }
                                        })
                                //BotónCancelar
                                .setNegativeButton(getResources().getString(R.string.cancel),
                                        new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialogBox, int id) {
                                                dialogBox.cancel();
                                                onResume();
                                            }
                                        }).create()
                                .show();
                    }
                }
            };
            ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
            itemTouchHelper.attachToRecyclerView(recyclerView);
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
