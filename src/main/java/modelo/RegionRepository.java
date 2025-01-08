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
public class RegionRepository {
    private ConexionOracle c;
    private Connection conexion;

    public RegionRepository() {
        c = new ConexionOracle();
    }

    public void conectarConexion() {
        conexion = c.getConnection();
    }

    public void desconectarConexion() {
        c.desconectar(conexion);
    }
    
    public int addRegion (String nombre) {
        conectarConexion();
        PreparedStatement ps = null;
        String sql = "INSERT INTO region (nombre) VALUES ( ?)";
        int filas = 0;
        try {
            ps = conexion.prepareStatement(sql);
            ps.setString(1, nombre);
            filas = ps.executeUpdate();
        } catch (SQLException ex) {
            System.err.println("Error al añadir la región: " + ex.getMessage());
        } finally {
            try {
                ps.close();
            desconectarConexion();
            } catch (SQLException ex) {
                System.err.println("Error al cerrar el PreparedStatement: " + ex.getMessage());
            }
        }
        return filas;
    }

    public int deleteRegion(int id) {
        conectarConexion();
        PreparedStatement ps = null;
        String sql = "DELETE FROM region WHERE id = ?";
        int filas = 0;
        try {
            ps = conexion.prepareStatement(sql);
            ps.setInt(1, id);
            filas = ps.executeUpdate();
        } catch (SQLException ex) {
            System.err.println("Error al eliminar la región: " + ex.getMessage());
        } finally {
            try {
                ps.close();
            desconectarConexion();
            } catch (SQLException ex) {
                System.err.println("Error al cerrar el PreparedStatement: " + ex.getMessage());
            }
        }
        return filas;
    }
    
      public ArrayList<Region> leerRegion() {
        conectarConexion();
        ArrayList<Region> regiones = new ArrayList<>();
        String sql = "SELECT * FROM REGION";
        ResultSet rs = null;
        Statement st = null;
        try {
            st = conexion.createStatement();
            rs = st.executeQuery(sql);
            regiones = recorrerRegion(rs);
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
        return regiones;
    }
      
    public ArrayList<Region> recorrerRegion(ResultSet rs) {
    ArrayList<Region> listaRegiones = new ArrayList<>();
    try {
        while (rs.next()) {
            int id = rs.getInt(1);
            String nombreReg = rs.getString(2);

            Region r = new Region(id, nombreReg);
            listaRegiones.add(r);
        }
    } catch (SQLException ex) {
        Logger.getLogger(RegionRepository.class.getName()).log(Level.SEVERE, null, ex);
    }
    return listaRegiones;
}
    
}

