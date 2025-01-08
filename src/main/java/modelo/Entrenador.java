/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author aitor
 */
public class Entrenador {
    private int id;
    private String nombre;
    private int idRegion;
    private boolean esLider;

    public Entrenador(int id, String nombre, int idRegion, boolean esLider) {
        this.id = id;
        this.nombre = nombre;
        this.idRegion = idRegion;
        this.esLider = esLider;
    }

    public Entrenador(String nombre, int idRegion, boolean esLider) {
        this.nombre = nombre;
        this.idRegion = idRegion;
        this.esLider = esLider;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getIdRegion() {
        return idRegion;
    }

    public void setIdRegion(int idRegion) {
        this.idRegion = idRegion;
    }

    public boolean isEsLider() {
        return esLider;
    }

    public void setEsLider(boolean esLider) {
        this.esLider = esLider;
    }

    @Override
    public String toString() {
        return "Entrenador{" + "id=" + id + ", nombre=" + nombre + ", idRegion=" + idRegion + ", esLider=" + esLider + '}';
    }
    

}