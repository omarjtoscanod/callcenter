package com.callcenter.app.bean;

import org.springframework.stereotype.Service;

/**
 * Clase que guarda la informacion del empleado. La informacion guardada es:
 * identificador del empleado, cargo y si esta o no disponible para atender la
 * llamada.
 */
@Service
public class Empleado {

    /* Identificador del empleado */
    private int id;

    /* Cargo del empleado: Operador, supervisor o director */
    private String cargo;

    /* Indica si esta disponible para recibir llamadas */
    private boolean disponible;

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the cargo
     */
    public String getCargo() {
        return cargo;
    }

    /**
     * @param cargo the cargo to set
     */
    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    /**
     * @return the disponible
     */
    public boolean isDisponible() {
        return disponible;
    }

    /**
     * @param disponible the disponible to set
     */
    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public String toString() {
        return this.getCargo() + ": " + this.id;
    }

}