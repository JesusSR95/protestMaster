package com.sanchez.jesus.protest;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.SimpleCursorAdapter;

import java.util.ArrayList;

public class Repositorio {
private static ArrayList<Pregunta> lista;


    public static void recoger (Context myContext){
        lista = new ArrayList<>();
//ARREGLAR ESTO
        PreguntaSQLiteHelper psdbh = new PreguntaSQLiteHelper(myContext, "DBPregunta.db", null, 1);

        SQLiteDatabase db = psdbh.getWritableDatabase();
        String[] campos = new String[] {"categoria", "Pregunta"};

        Cursor c = db.rawQuery("SELECT * FROM Pregunta", null);

//Nos aseguramos de que existe al menos un registro
        if (c.moveToFirst()) {
            //Recorremos el cursor hasta que no haya más registros
            do {
                int codigo= c.getInt(c.getColumnIndex("codigo"));
                String pregunta = c.getString(c.getColumnIndex("pregunta"));
                String spinner = c.getString(c.getColumnIndex("categoria"));
                String respuestacorrecta = c.getString(c.getColumnIndex("respuestaCorrecta"));
                String respuestaInc1 = c.getString(c.getColumnIndex("respuestaInc1"));
                String respuestaInc2 = c.getString(c.getColumnIndex("respuestaInc2"));
                String respuestaInc3 = c.getString(c.getColumnIndex("respuestaInc3"));

                Pregunta p = new Pregunta(codigo, pregunta, spinner, respuestacorrecta, respuestaInc1, respuestaInc2, respuestaInc3);
                lista.add(p);
            } while(c.moveToNext());
        }

    }

    public static void insertar(Context myContext, Pregunta pregunta)
    {
        //Abrimos la base de datos 'DBUsuarios' en modo escritura
        PreguntaSQLiteHelper psdbh =
                new PreguntaSQLiteHelper(myContext, "DBPregunta.db", null, 1);

        SQLiteDatabase db = psdbh.getWritableDatabase();

        //Si hemos abierto correctamente la base de datos
        if(db != null)
        {
                //Insertamos los datos en la tabla Usuarios

                db.execSQL("INSERT INTO Pregunta (pregunta, categoria, respuestaCorrecta, respuestaInc1, respuestaInc2, respuestaInc3) " +
                        "VALUES ('" + pregunta.getEnunciado() +"', '"+ pregunta.getCategoria() +"', '"+ pregunta.getPreguntaCorrecta() +"', '"+ pregunta.getPreguntaInc1() +"', '"+ pregunta.getPreguntaInc2() +"', '"+ pregunta.getPreguntaInc3() +"')");


            //Cerramos la base de datos
            db.close();
        }


        }

    public static ArrayList<Pregunta> getLista() {
        return lista;
    }

    public static void setLista(ArrayList<Pregunta> lista) {
        Repositorio.lista = lista;
    }
}
