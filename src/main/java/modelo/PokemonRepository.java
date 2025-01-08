/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Diurno
 */
public class PokemonRepository {

    private ConexionOracle c;
    private Connection conexion;

    public PokemonRepository() {
        c = new ConexionOracle();
    }

    public void conectarConexion() {
        conexion = c.getConnection();
    }

    public void desconectarConexion() {
        c.desconectar(conexion);
    }

    //Funciona
    public int addPokemon(String nombre, String tipo1, String tipo2, int idRegion) {
        conectarConexion();
        int numFilas = 0;
        PreparedStatement ps = null;

        String sql = "INSERT INTO POKEMON (nombre, tipo1, tipo2, id_region) VALUES (?, ?, ?, ?)";
        try {
            ps = conexion.prepareStatement(sql);
            ps.setString(1, nombre);
            ps.setString(2, tipo1);
            ps.setString(3, tipo2);
            ps.setInt(4, idRegion);
            numFilas = ps.executeUpdate();
        } catch (SQLException ex) {
            System.err.println("Error al crear el PreparedStatement");
            ex.printStackTrace();
        } finally {
            try {
                ps.close();
                desconectarConexion();
            } catch (SQLException ex) {
                System.err.println("Error al cerrar el PreparedStatement");
            }
        }
        return numFilas;
    }

    //Funciona
    public int deletePokemon(int id) {
        conectarConexion();
        int numFilas = 0;
        PreparedStatement ps = null;

        String sql = "DELETE FROM pokemon WHERE id = ?";
        try {
            ps = conexion.prepareStatement(sql);
            ps.setInt(1, id);

            numFilas = ps.executeUpdate();

        } catch (SQLException ex) {
            System.err.println("Error con el PreparedStatement");
        } finally {
            try {
                ps.close();
                desconectarConexion();
            } catch (SQLException ex) {
                System.err.println("Error al cerrar el PreparedStatement");
            }
        }
        return numFilas;
    }

    //Consulta para obtener la información del Pokemon junto con el nombre de su región
    //Funciona
    public ArrayList<String> pokemonNombreRegion() {
        conectarConexion();
        Statement s = null;
        ResultSet rs = null;
        String sql = "SELECT p.id, p.nombre, p.tipo1, p.tipo2, r.nombre "
                + "FROM pokemon p "
                + "INNER JOIN region r ON p.id_region = r.id";
        ArrayList<String> listaPokemons = new ArrayList<>();

        try {
            s = conexion.createStatement();
            rs = s.executeQuery(sql);

            while (rs.next()) {
                int cod = rs.getInt(1);
                String nombre = rs.getString(2);
                String tipo1 = rs.getString(3);
                String tipo2 = rs.getString(4);
                String nombreRegion = rs.getString(5);

                if (tipo2 == null) {
                    tipo2 = "N/A";
                }
                listaPokemons.add("ID: " + cod
                        + ", Nombre: " + nombre
                        + ", Tipo1: " + tipo1
                        + ", Tipo2: " + tipo2
                        + ", Región: " + nombreRegion);
            }
        } catch (SQLException ex) {
            System.err.println("Error al procesar la consulta");
        } finally {
            try {
                s.close();
                rs.close();
                desconectarConexion();

            } catch (SQLException ex) {
                Logger.getLogger(PokemonRepository.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return listaPokemons;
    }

     public ArrayList<Pokemon> leerPokemon() {
        conectarConexion();
        ArrayList<Pokemon> pokemons = new ArrayList<>();
        String sql = "SELECT * FROM POKEMON";
        ResultSet rs = null;
        Statement st = null;
        try {
            st = conexion.createStatement();
            rs = st.executeQuery(sql);
            pokemons = recorrerPokemones(rs);
        } catch (SQLException ex) {
            Logger.getLogger(EntrenadorRepository.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                rs.close();
                st.close();
            } catch (SQLException ex) {
                Logger.getLogger(EntrenadorRepository.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        desconectarConexion();
        return pokemons;
    }
    
    public ArrayList<Pokemon> recorrerPokemones(ResultSet rs) {
        ArrayList<Pokemon> listaPokemones = new ArrayList<>();
        try {
            while (rs.next()) {
                int cod = rs.getInt(1);
                String nombre = rs.getString(2);
                String tipo1 = rs.getString(3);
                String tipo2 = rs.getString(4);
                int idRegion = rs.getInt(5);

                Pokemon p = new Pokemon(cod, idRegion, nombre, tipo1, tipo2);
                listaPokemones.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PokemonRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaPokemones;
    }
}
