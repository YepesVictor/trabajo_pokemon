/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author Diurno
 */
public class Region {
    int idRegion;
    String nombre;

    public Region(int idRegion, String nombre) {
        this.idRegion = idRegion;
        this.nombre = nombre;
    }

    public Region() {
    }

    public int getIdRegion() {
        return idRegion;
    }

    public void setIdRegion(int idRegion) {
        this.idRegion = idRegion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Region{" + "idRegion=" + idRegion + ", nombre=" + nombre + '}';
    }
    
}
