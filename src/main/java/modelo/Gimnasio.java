/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author aitor
 */
public class Gimnasio {
    private int id;
    private String lider;
    private int idRegion;

    public Gimnasio() {
    }

    public Gimnasio(int id, String lider, int idRegion) {
        this.id = id;
        this.lider = lider;
        this.idRegion = idRegion;
    }

    public Gimnasio(String lider, int idRegion) {
        this.lider = lider;
        this.idRegion = idRegion;
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLider() {
        return lider;
    }

    public void setLider(String lider) {
        this.lider = lider;
    }

    public int getIdRegion() {
        return idRegion;
    }

    public void setIdRegion(int idRegion) {
        this.idRegion = idRegion;
    }

    @Override
    public String toString() {
        return "Gimnasio{" + "id=" + id + ", lider=" + lider + ", idRegion=" + idRegion + '}';
    }

    
}