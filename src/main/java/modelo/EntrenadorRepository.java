package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EntrenadorRepository {

    private ConexionOracle c;
    private Connection conexion;

    public EntrenadorRepository() {
        c = new ConexionOracle();
    }

    public void conectarConexion() {
        conexion = c.getConnection();
    }

    public void desconectarConexion() {
        c.desconectar(conexion);
    }

    public int addEntrenador(String nombre, int idRegion, boolean esLider) {
        conectarConexion();
        PreparedStatement ps = null;
        String sql = "INSERT INTO entrenador (nombre, id_region, es_lider) VALUES (?, ?, ?)";
        int filas = 0;
        try {
            ps = conexion.prepareStatement(sql);
            ps.setString(1, nombre);
            ps.setInt(2, idRegion);
            ps.setBoolean(3, esLider);
            filas = ps.executeUpdate();
        } catch (SQLException ex) {
            System.err.println("Error al añadir el entrenador: " + ex.getMessage());
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

    public int deleteEntrenador(int id) {
        conectarConexion();
        PreparedStatement ps = null;
        String sql = "DELETE FROM entrenador WHERE id = ?";
        int filas = 0;
        try {
            ps = conexion.prepareStatement(sql);
            ps.setInt(1, id);
            filas = ps.executeUpdate();
        } catch (SQLException ex) {
            System.err.println("Error al eliminar el entrenador: " + ex.getMessage());
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

    public ArrayList<Entrenador> leerEntrenador() {
        conectarConexion();
        ArrayList<Entrenador> entrenadores = new ArrayList<>();
        String sql = "SELECT * FROM ENTRENADOR";
        ResultSet rs = null;
        Statement st = null;
        try {
            st = conexion.createStatement();
            rs = st.executeQuery(sql);
            entrenadores = recorrerEntrenador(rs);
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
        return entrenadores;
    }

    public ArrayList<Entrenador> recorrerEntrenador(ResultSet rs) {
        ArrayList<Entrenador> listaEntrenadores = new ArrayList<>();
        try {
            while (rs.next()) {
                int id = rs.getInt(1);
                String nombre = rs.getString(2);
                int idGimnasio = rs.getInt(3);
                boolean esLider = rs.getBoolean(4);

                Entrenador e = new Entrenador(id, nombre, idGimnasio, esLider);
                listaEntrenadores.add(e);
            }
        } catch (SQLException ex) {
            Logger.getLogger(EntrenadorRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaEntrenadores;
    }
}
