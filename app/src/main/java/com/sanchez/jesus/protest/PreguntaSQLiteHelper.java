package com.sanchez.jesus.protest;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class PreguntaSQLiteHelper extends SQLiteOpenHelper {
    //Sentencia SQL para crear la tabla de Usuarios
    String sqlCreate = "CREATE TABLE "+ Constantes.NOMBRETABLA +" (codigo INTEGER PRIMARY KEY AUTOINCREMENT, pregunta TEXT, categoria TEXT, respuestaCorrecta TEXT, respuestaInc1 TEXT, respuestaInc2 TEXT, respuestaInc3 TEXT, photo TEXT)";

    public PreguntaSQLiteHelper(Context contexto, String pregunta, CursorFactory factory, int version) {
        super(contexto, pregunta, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Se ejecuta la sentencia SQL de creación de la tabla
        db.execSQL(sqlCreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int versionAnterior, int versionNueva) {
        //Por simplicidad del ejemplo aquí utilizamos directamente la opción de
        //eliminar la tabla anterior y crearla de nuevo vacía con el nuevo formato.
        //Sin embargo lo normal será que haya que migrar datos de la tabla antigua
        //a la nueva, por lo que este método debería ser más elaborado.

        //Se elimina la versión anterior de la tabla
        db.execSQL("DROP TABLE IF EXISTS Pregunta");

        //Se crea la nueva versión de la tabla
        db.execSQL(sqlCreate);
    }
}

