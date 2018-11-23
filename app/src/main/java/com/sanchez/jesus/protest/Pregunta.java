package com.sanchez.jesus.protest;

public class Pregunta {

    private int codigo;



    private String enunciado;
    private String categoria;
    private String preguntaCorrecta;
    private String PreguntaInc1;
    private String PreguntaInc2;
    private String PreguntaInc3;

    public Pregunta(int codigo, String enunciado, String categoria, String preguntaCorrecta, String preguntaInc1, String preguntaInc2, String preguntaInc3) {
        this.codigo = codigo;
        this.enunciado = enunciado;
        this.categoria = categoria;
        this.preguntaCorrecta = preguntaCorrecta;
        this.PreguntaInc1 = preguntaInc1;
        this.PreguntaInc2 = preguntaInc2;
        this.PreguntaInc3 = preguntaInc3;
    }

    public Pregunta(String enunciado, String categoria, String preguntaCorrecta, String preguntaInc1, String preguntaInc2, String preguntaInc3) {
        this.enunciado = enunciado;
        this.categoria = categoria;
        this.preguntaCorrecta = preguntaCorrecta;
        this.PreguntaInc1 = preguntaInc1;
        this.PreguntaInc2 = preguntaInc2;
        this.PreguntaInc3 = preguntaInc3;
    }


   /* public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }*/

    public String getEnunciado() {
        return enunciado;
    }

    public void setEnunciado(String enunciado) {
        this.enunciado = enunciado;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getPreguntaCorrecta() {
        return preguntaCorrecta;
    }

    public void setPreguntaCorrecta(String preguntaCorrecta) {
        this.preguntaCorrecta = preguntaCorrecta;
    }

    public String getPreguntaInc1() {
        return PreguntaInc1;
    }

    public void setPreguntaInc1(String preguntaInc1) {
        PreguntaInc1 = preguntaInc1;
    }

    public String getPreguntaInc2() {
        return PreguntaInc2;
    }

    public void setPreguntaInc2(String preguntaInc2) {
        PreguntaInc2 = preguntaInc2;
    }

    public String getPreguntaInc3() {
        return PreguntaInc3;
    }

    public void setPreguntaInc3(String preguntaInc3) {
        PreguntaInc3 = preguntaInc3;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
}
