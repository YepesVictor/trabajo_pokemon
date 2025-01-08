/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author Diurno
 */
public class Pokemon {
    int id, idRegion;
    String nombre, tipo1, tipo2;

    public Pokemon(int id, int idRegion, String nombre, String tipo1, String tipo2) {
        this.id = id;
        this.idRegion = idRegion;
        this.nombre = nombre;
        this.tipo1 = tipo1;
        this.tipo2 = tipo2;
    }

    public Pokemon(int id, int idRegion, String nombre, String tipo1) {
        this.id = id;
        this.idRegion = idRegion;
        this.nombre = nombre;
        this.tipo1 = tipo1;
    }

    
    public Pokemon() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getTipo1() {
        return tipo1;
    }

    public void setTipo1(String tipo1) {
        this.tipo1 = tipo1;
    }

    public String getTipo2() {
        return tipo2;
    }

    public void setTipo2(String tipo2) {
        this.tipo2 = tipo2;
    }

    @Override
    public String toString() {
        return "Pokemon{" + "id=" + id + ", idRegion=" + idRegion + ", nombre=" + nombre + ", tipo1=" + tipo1 + ", tipo2=" + tipo2 + '}';
    }
    
}
