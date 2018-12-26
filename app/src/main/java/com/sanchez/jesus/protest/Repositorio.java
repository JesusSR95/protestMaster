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
    private static ArrayList<String> categorias;

    private static final Repositorio ourInstance = new Repositorio();
    static Repositorio getInstance() {
        return ourInstance;
    }
    private Repositorio() {
    }


    public static void recoger(Context myContext) {
        lista = new ArrayList<>();
        PreguntaSQLiteHelper psdbh = new PreguntaSQLiteHelper(myContext, Constantes.NOMBREDB, null, 1);

        SQLiteDatabase db = psdbh.getWritableDatabase();
        String[] campos = new String[]{"categoria", "Pregunta"};

        Cursor c = db.rawQuery("SELECT * FROM " + Constantes.NOMBRETABLA, null);

//Nos aseguramos de que existe al menos un registro
        if (c.moveToFirst()) {
            //Recorremos el cursor hasta que no haya más registros
            do {
                int codigo = c.getInt(c.getColumnIndex("codigo"));
                String pregunta = c.getString(c.getColumnIndex("pregunta"));
                String spinner = c.getString(c.getColumnIndex("categoria"));
                String respuestacorrecta = c.getString(c.getColumnIndex("respuestaCorrecta"));
                String respuestaInc1 = c.getString(c.getColumnIndex("respuestaInc1"));
                String respuestaInc2 = c.getString(c.getColumnIndex("respuestaInc2"));
                String respuestaInc3 = c.getString(c.getColumnIndex("respuestaInc3"));

                Pregunta p = new Pregunta(codigo, pregunta, spinner, respuestacorrecta, respuestaInc1, respuestaInc2, respuestaInc3);
                lista.add(p);
            } while (c.moveToNext());
        }

    }

    public static void insertar(Context myContext, Pregunta pregunta) {
        //Abrimos la base de datos 'DBUsuarios' en modo escritura
        PreguntaSQLiteHelper psdbh =
                new PreguntaSQLiteHelper(myContext, Constantes.NOMBREDB, null, 1);

        SQLiteDatabase db = psdbh.getWritableDatabase();

        //Si hemos abierto correctamente la base de datos
        if (db != null) {
            //Insertamos los datos en la tabla Usuarios

            db.execSQL("INSERT INTO " + Constantes.NOMBRETABLA + " (pregunta, categoria, respuestaCorrecta, respuestaInc1, respuestaInc2, respuestaInc3) " +
                    "VALUES ('" + pregunta.getEnunciado() + "', '" + pregunta.getCategoria() + "', '" + pregunta.getPreguntaCorrecta() + "', '" + pregunta.getPreguntaInc1() + "', '" + pregunta.getPreguntaInc2() + "', '" + pregunta.getPreguntaInc3() + "')");


            //Cerramos la base de datos
            db.close();
        }
    }

    public static ArrayList<Pregunta> recuperarPreguntas(Context myContext) {

        PreguntaSQLiteHelper DBPregunta = new PreguntaSQLiteHelper(myContext, Constantes.NOMBREDB, null, 1);

        SQLiteDatabase db = DBPregunta.getReadableDatabase();

        ArrayList<Pregunta> listadoPreguntas = new ArrayList<>();

        Cursor c = db.rawQuery(" SELECT * FROM " + Constantes.NOMBRETABLA, null);

        if (c.moveToFirst()) {

            do {
                int codigo = Integer.parseInt(c.getString(0));
                String enunciado = c.getString(1);
                String categoria = c.getString(2);
                String respCorrecta = c.getString(3);
                String respInc1 = c.getString(4);
                String respInc2 = c.getString(5);
                String respInc3 = c.getString(6);

                Pregunta preguntaRecogida = new Pregunta(codigo, enunciado, categoria, respCorrecta, respInc1, respInc2, respInc3);

                listadoPreguntas.add(preguntaRecogida);

            } while (c.moveToNext());

        }

        //Cerramos la base de datos
        db.close();

        return listadoPreguntas;
    }


    public static void editarpregu(Context myContext, Pregunta pregunta) {
        PreguntaSQLiteHelper psdbh = new PreguntaSQLiteHelper(myContext, Constantes.NOMBREDB, null, 1);
        SQLiteDatabase db = psdbh.getWritableDatabase();

        if (db != null) {
            //Insertamos los datos en la tabla Usuarios

            db.execSQL("UPDATE " + Constantes.NOMBRETABLA + " SET pregunta = " + "'" + pregunta.getEnunciado() + "', categoria = " + "'" + pregunta.getCategoria() + "', respuestaCorrecta = " + "'" + pregunta.getPreguntaCorrecta() + "', respuestaInc1 = " + "'" + pregunta.getPreguntaInc1() + "', respuestaInc2 = " + "'" + pregunta.getPreguntaInc2() + "', respuestaInc3 = " + "'" + pregunta.getPreguntaInc3() + "' WHERE codigo = " + pregunta.getCodigo());
        }

        db.close();

    }


    public static void consultaCategorias(Context myContext) {
        categorias = new ArrayList<>();
        PreguntaSQLiteHelper helper = new PreguntaSQLiteHelper(myContext, Constantes.NOMBREDB, null, 1);

        SQLiteDatabase db = helper.getWritableDatabase();
        Cursor c = db.rawQuery(" SELECT DISTINCT categoria FROM " + Constantes.NOMBRETABLA, null);

        if (c.moveToFirst()) {

            do {
                String categoria = c.getString(c.getColumnIndex("categoria"));

                categorias.add(categoria);

            } while (c.moveToNext());
        }


    }

    public static ArrayList<String> getCategorias(){

        return categorias;

    }

    public static void eliminaPregunta(Pregunta p, Context myContext){

        //Abrimos la basededatos en modo escritura
        PreguntaSQLiteHelper psdbh=
                new PreguntaSQLiteHelper(myContext,Constantes.NOMBREDB,null,1);

        SQLiteDatabase db= psdbh.getWritableDatabase();

        //Si hemos abierto correctamente la base de datos
        if(db!=null)
        {

            db.execSQL("DELETE FROM '"+Constantes.NOMBRETABLA+"' WHERE codigo='"+p.getCodigo()+"' ");
            //Cerramoslabasededatos
            db.close();
        }
        else{
        }


    }

    public static ArrayList<Pregunta> getLista() {
        return lista;
    }

    public static void setLista(ArrayList<Pregunta> lista) {
        Repositorio.lista = lista;
    }


}
