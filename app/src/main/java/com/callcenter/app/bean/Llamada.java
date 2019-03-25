package com.callcenter.app.bean;

/**
 * Clase que guarda la informacion de una llamada. La informacion guardada es el
 * nombre y la duracion.
 */
public class Llamada {

    /* Nombre de la llamda */
    private String nombre;

    /* Duracion de la llamada */
    private int duracionLlamada;

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the duracionLlamada
     */
    public int getDuracionLlamada() {
        return duracionLlamada;
    }

    /**
     * @param duracionLlamada the duracionLlamada to set
     */
    public void setDuracionLlamada(int duracionLlamada) {
        this.duracionLlamada = duracionLlamada;
    }

    public String toString() {
        return this.getNombre() + " con duracion " + this.getDuracionLlamada();
    }
}