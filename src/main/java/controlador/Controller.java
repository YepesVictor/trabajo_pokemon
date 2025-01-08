/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.util.ArrayList;
import java.util.Scanner;
import modelo.EntrenadorRepository;
import modelo.Entrenador;
import modelo.GimnasioRepository;
import modelo.Gimnasio;
import modelo.PokemonRepository;
import modelo.Pokemon;
import modelo.RegionRepository;
import modelo.Region;
import vista.Vista;

/**
 *
 * @author Diurno
 */
public class Controller {

    Vista v;
    PokemonRepository pr;
    RegionRepository rr;
    EntrenadorRepository er;
    GimnasioRepository gr;

    public Controller(Vista v, PokemonRepository pr, RegionRepository rr, EntrenadorRepository er, GimnasioRepository gr) {
        this.v = v;
        this.pr = pr;
        this.rr = rr;
        this.er = er;
        this.gr = gr;
        menu();
    }

    public void menu() {
        Scanner sc = new Scanner(System.in);
        int x = -1;
        while (x != 0) {
            v.mostrarTexto("""
                           ------------------------
                           POKEMON
                           1. Agregar pokemon
                           2. Borrar pokemon
                           3. Pokemon con su region 
                           4. Todos los pokemons
                           GIMNASIO
                           5. Agregar gimnasio
                           6. Borrar gimnasio
                           7. Todos los gimnasios
                           8. Gimnasios con lider y region
                           9. Procedimiento - Actualizacion de Gimnasio
                           ENTRENADOR
                           10. Agregar entrenador
                           11. Borrar entrenador
                           12. Todos los entrenadores
                           REGION
                           13. Agregar region
                           14. Borrar region
                           15. Todas las regiones
                           0.SALIR
                           """);
            x = sc.nextInt();
            switch (x) {
                case 1:
                    addPokemon();
                    break;
                case 2:
                    deletePokemon();
                    break;
                case 3:
                    pokemonsRegion();
                    break;
                case 4:
                    leerPokemon();
                    break;
                case 5:
                    addGimnasio();
                    break;
                case 6:
                    deleteGimnasio();
                    break;
                case 7:
                    leerGimnasio();
                    break;
                case 8:
                    gimnasioLiderRegion();
                    break;
                case 9:
                    procedimientoGimnasio();
                    break;
                case 10:
                    addEntrenador();
                    break;
                case 11:
                    deleteEntrenador();
                    break;
                case 12:
                    leerEntrenadores();
                    break;
                case 13:
                    addRegion();
                    break;
                case 14:
                    deleteRegion();
                    break;
                case 15:
                    leerRegion();
                    break;
                case 0:
                    System.exit(0);
                default:
                   v.mostrarTexto("Numero fuera de rango");
            }
        }
    }

    /*POKEMON*/
    public void addPokemon() {
        int num = pr.addPokemon("Chaco", "fuego", "hada", 2);
        v.mostrarTexto("Se han editado " + num + " filas");
    }

    public void deletePokemon() {
        int num = pr.deletePokemon(7);
        v.mostrarTexto("Se ha eliminado " + num + " Pokémon");
    }

    public void pokemonsRegion() {
        ArrayList<String> pokemons = pr.pokemonNombreRegion();
        v.mostrarTexto("Pokémon con sus regiones:");
        for (String p : pokemons) {
            v.mostrarTexto(p);
        }
    }

    public void leerPokemon() {
        ArrayList<Pokemon> pokemons = pr.leerPokemon();
        for (Pokemon pokemon : pokemons) {
            v.mostrarTexto(pokemon.toString());
        }
    }

    /*GIMNASIO*/
    public void addGimnasio() {
        int num = gr.addGimnasio(5, 3);
        v.mostrarTexto("Se ha añadido " + num + " entrenador");
    }

    public void deleteGimnasio() {
        int num = gr.deleteGimnasio(6);
        v.mostrarTexto("Se ha eliminado " + num + " gim");
    }

    public void leerGimnasio() {
        ArrayList<Gimnasio> gim = gr.leerGimnasio();
        for (Gimnasio gimnasio : gim) {
            v.mostrarTexto(gimnasio.toString());
        }
    }

    public void gimnasioLiderRegion() {
        ArrayList<String> todo = gr.gimnasioLiderRegion();
        for (String string : todo) {
            v.mostrarTexto(string);
        }
    }

    public void procedimientoGimnasio() {
        int id = 3;
        int lider = 2;
        int idRegion = 1;
        gr.llamaProcedimiento(id, lider, idRegion);
    }

    /*ENTRENADOR*/
    public void addEntrenador() {
        int num = er.addEntrenador("Aitor", 4, true);
        v.mostrarTexto("Se ha añadido " + num + " entrenador");
    }

    public void deleteEntrenador() {
        int num = er.deleteEntrenador(7);
        v.mostrarTexto("Filas borradas: " + num + " filas");
    }

    public void leerEntrenadores() {
        ArrayList<Entrenador> entrenadores = er.leerEntrenador();
        for (Entrenador entrenadore : entrenadores) {
            v.mostrarTexto(entrenadore.toString());
        }
    }

    /*REGION*/
    public void addRegion() {
        int num = rr.addRegion("Región de Murcia");
        v.mostrarTexto("Se han añadido " + num + " regiones.");
    }

    public void deleteRegion() {
        int num = rr.deleteRegion(5);
        v.mostrarTexto("Filas eliminadas: " + num + " fila");
    }

    public void leerRegion() {
        ArrayList<Region> regiones = rr.leerRegion();
        for (Region regione : regiones) {
            v.mostrarTexto(regione.toString());
        }
    }

}
