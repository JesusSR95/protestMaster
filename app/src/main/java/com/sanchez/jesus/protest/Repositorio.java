package com.sanchez.jesus.protest;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Xml;
import android.widget.SimpleCursorAdapter;

import org.xmlpull.v1.XmlSerializer;

import java.io.IOException;
import java.io.StringWriter;
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
                String codigo=c.getString( c.getColumnIndex("codigo"));
                String pregunta = c.getString(c.getColumnIndex("pregunta"));
                String spinner = c.getString(c.getColumnIndex("categoria"));
                String respuestacorrecta = c.getString(c.getColumnIndex("respuestaCorrecta"));
                String respuestaInc1 = c.getString(c.getColumnIndex("respuestaInc1"));
                String respuestaInc2 = c.getString(c.getColumnIndex("respuestaInc2"));
                String respuestaInc3 = c.getString(c.getColumnIndex("respuestaInc3"));
                String photo = c.getString(c.getColumnIndex("photo"));
                Pregunta p = new Pregunta(codigo, pregunta, spinner, respuestacorrecta, respuestaInc1, respuestaInc2, respuestaInc3, photo);
                lista.add(p);
            } while (c.moveToNext());
        }

    }
    //Metodo que se inserta todas las preguntas incluso la foto si hemos añadido alguna
    public static boolean insertarFoto(Pregunta p, Context myContext) {
        boolean valor = true;
        PreguntaSQLiteHelper psdbh =
                new PreguntaSQLiteHelper(myContext, Constantes.NOMBREDB, null, 1);
        SQLiteDatabase db = psdbh.getWritableDatabase();
        if (db != null) {
            db.execSQL("INSERT INTO '"+Constantes.NOMBRETABLA+"' (pregunta, categoria, respuestaCorrecta, respuestaInc1, respuestaInc2, respuestaInc3, photo)"+
                    "VALUES ('" + p.getEnunciado() + "', '" + p.getCategoria() + "', '"+ p.getPreguntaCorrecta()+"', '"+ p.getPreguntaInc1()+"', '"+p.getPreguntaInc2()+"', '"+p.getPreguntaInc3()+"','"+p.getPhoto()+"')");
            db.close();
        } else {
            valor = false;
        }
        return valor;
    }

    public static void editarpregu(Context myContext, Pregunta pregunta) {
        PreguntaSQLiteHelper psdbh = new PreguntaSQLiteHelper(myContext, Constantes.NOMBREDB, null, 1);
        SQLiteDatabase db = psdbh.getWritableDatabase();
        if (db != null) {
            //Insertamos los datos en la tabla Usuarios

            db.execSQL("UPDATE " + Constantes.NOMBRETABLA + " SET pregunta = " + "'" + pregunta.getEnunciado() + "', categoria = " + "'" + pregunta.getCategoria() + "', respuestaCorrecta = " + "'" + pregunta.getPreguntaCorrecta() + "', respuestaInc1 = " + "'" + pregunta.getPreguntaInc1() + "', respuestaInc2 = " + "'" + pregunta.getPreguntaInc2() + "', respuestaInc3 = " + "'" + pregunta.getPreguntaInc3() + "', photo = " + "'" + pregunta.getPhoto() + "' WHERE codigo = " + pregunta.getCodigo());
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

    public static Pregunta buscarPregunta(int codi, Context myContext){
        Pregunta p = null;
        PreguntaSQLiteHelper helper = new PreguntaSQLiteHelper(myContext, Constantes.NOMBREDB, null, 1);
        SQLiteDatabase db = helper.getWritableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM Pregunta WHERE codigo='"+codi+"'", null);
        if (c.moveToFirst()) {
            String enunciado = c.getString(1);
            String categoria = c.getString(2);
            String respCorrecta = c.getString(3);
            String respInc1 = c.getString(4);
            String respInc2 = c.getString(5);
            String respInc3 = c.getString(6);
            String foto = c.getString(7);
            p = new Pregunta(enunciado, categoria, respCorrecta, respInc1, respInc2, respInc3, foto);
        }
    db.close();
        return p;
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

    public static String getTotalPregunta(Context myContext) {
        String totalPregunta = "";
        PreguntaSQLiteHelper helper =
                new PreguntaSQLiteHelper(myContext, Constantes.NOMBREDB, null, 1);
        SQLiteDatabase db = helper.getWritableDatabase();
        Cursor c = db.rawQuery("SELECT count(distinct codigo) FROM '" + Constantes.NOMBRETABLA + "';", null);
        if (c.moveToFirst()) {
            totalPregunta = c.getString(0);
        }
        c.close();
        return totalPregunta;
    }

    public static String getTotalCategoria(Context myContext) {
        String totalCategoria = "";
        PreguntaSQLiteHelper helper =
                new PreguntaSQLiteHelper(myContext, Constantes.NOMBREDB, null, 1);
        SQLiteDatabase db = helper.getWritableDatabase();
        Cursor c = db.rawQuery("SELECT count(distinct categoria) FROM '" + Constantes.NOMBRETABLA + "';", null);
        if (c.moveToFirst()) {
            totalCategoria = c.getString(0);
        }
        c.close();
        return totalCategoria;
    }

    public static String CreateXMLString() throws IllegalArgumentException, IllegalStateException, IOException
    {
        ArrayList<Pregunta> preguntasXML = new ArrayList<Pregunta>();
        preguntasXML= getLista();
        XmlSerializer xmlSerializer = Xml.newSerializer();
        StringWriter writer = new StringWriter();
        xmlSerializer.setOutput(writer);
        //Start Document
        xmlSerializer.startDocument("UTF-8", true);
        xmlSerializer.setFeature("http://xmlpull.org/v1/doc/features.html#indent-output", true);
        //Open Tag <file>
        xmlSerializer.startTag("", "quiz");
        for (Pregunta p: preguntasXML) {
            //Categoria de cada pregunta
            xmlSerializer.startTag("", "question");
            xmlSerializer.attribute("", "type", p.getCategoria());
            xmlSerializer.startTag("", "category");
            xmlSerializer.text(p.getCategoria());
            xmlSerializer.endTag("", "category");
            xmlSerializer.endTag("", "question");
            //Pregunta de eleccion multiple
            xmlSerializer.startTag("", "question");
            xmlSerializer.attribute("", "type", "multichoice");
            xmlSerializer.startTag("", "name");
            xmlSerializer.text(p.getEnunciado());
            xmlSerializer.endTag("", "name");
            xmlSerializer.startTag("","questiontext");
            xmlSerializer.attribute("", "format", "html");
            xmlSerializer.text(p.getEnunciado());
            xmlSerializer.startTag("","file");
            xmlSerializer.attribute("", "name", p.getPhoto());
            xmlSerializer.attribute("", "path", "/");
            xmlSerializer.attribute("", "encoding", "base64");
            xmlSerializer.endTag("", "file");
            xmlSerializer.endTag("", "questiontext");
            xmlSerializer.startTag("","answernumbering");
            xmlSerializer.endTag("", "answernumbering");
            xmlSerializer.startTag("","answer");
            xmlSerializer.attribute("","fraction", "100");
            xmlSerializer.attribute("", "format", "html");
            xmlSerializer.text(p.getPreguntaCorrecta());
            xmlSerializer.endTag("", "answer");
            xmlSerializer.startTag("","answer");
            xmlSerializer.attribute("","fraction", "0");
            xmlSerializer.attribute("", "format", "html");
            xmlSerializer.text(p.getPreguntaInc1());
            xmlSerializer.endTag("", "answer");
            xmlSerializer.startTag("","answer");
            xmlSerializer.attribute("","fraction", "0");
            xmlSerializer.attribute("", "format", "html");
            xmlSerializer.text(p.getPreguntaInc2());
            xmlSerializer.endTag("", "answer");
            xmlSerializer.startTag("","answer");
            xmlSerializer.attribute("","fraction", "0");
            xmlSerializer.attribute("", "format", "html");
            xmlSerializer.text(p.getPreguntaInc3());
            xmlSerializer.endTag("", "answer");
            xmlSerializer.endTag("","question");
        }

        //end tag <file>
        xmlSerializer.endTag("","quiz");
        xmlSerializer.endDocument();
        return writer.toString();
    }

    public static ArrayList<Pregunta> getLista() {
        return lista;
    }

    public static void setLista(ArrayList<Pregunta> lista) {
        Repositorio.lista = lista;
    }
}
