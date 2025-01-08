/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package principal;

import controlador.Controller;
import modelo.Entrenador;
import modelo.EntrenadorRepository;
import modelo.GimnasioRepository;
import modelo.PokemonRepository;
import modelo.RegionRepository;
import vista.Vista;

/**
 *
 * @author Diurno
 */
public class Principal {

    public static void main(String[] args) {
        Vista v =new Vista();
        PokemonRepository pr=new PokemonRepository();
        GimnasioRepository gr=new GimnasioRepository();
        EntrenadorRepository er=new EntrenadorRepository();
        RegionRepository rr=new RegionRepository();
        Controller c=new Controller(v, pr, rr, er, gr);
    }
}
