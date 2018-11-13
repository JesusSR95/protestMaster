package com.sanchez.jesus.protest;

import android.app.Activity;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

public class Repositorio {
    public static void insertar(Context myContext, Pregunta pregunta)
    {
        //Abrimos la base de datos 'DBUsuarios' en modo escritura
        PreguntaSQLiteHelper psdbh =
                new PreguntaSQLiteHelper(myContext, "DBPregunta", null, 1);

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

}
