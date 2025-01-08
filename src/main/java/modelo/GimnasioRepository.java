package modelo;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GimnasioRepository {

    private ConexionOracle c;
    private Connection conexion;

    public GimnasioRepository() {
        c = new ConexionOracle();
    }

    public void conectarConexion() {
        conexion = c.getConnection();
    }

    public void desconectarConexion() {
        c.desconectar(conexion);
    }

    public int addGimnasio(int idLider, int idRegion) {
        conectarConexion();
        PreparedStatement ps = null;
        String sql = "INSERT INTO gimnasio (lider, id_region) VALUES (?, ?)";
        int filas = 0;
        try {
            ps = conexion.prepareStatement(sql);
            ps.setInt(1, idLider);
            ps.setInt(2, idRegion);
            filas = ps.executeUpdate();
        } catch (SQLException ex) {
            System.err.println("Error al añadir el gimnasio: " + ex.getMessage());
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

    public int deleteGimnasio(int id) {
        conectarConexion();
        PreparedStatement ps = null;
        String sql = "DELETE FROM gimnasio WHERE id = ?";
        int filas = 0;
        try {
            ps = conexion.prepareStatement(sql);
            ps.setInt(1, id);
            filas = ps.executeUpdate();
        } catch (SQLException ex) {
            System.err.println("Error al eliminar el gimnasio: " + ex.getMessage());
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

    public ArrayList<Gimnasio> leerGimnasio() {
        conectarConexion();
        ArrayList<Gimnasio> gimnasios = new ArrayList<>();
        String sql = "SELECT * FROM GIMNASIO";
        ResultSet rs = null;
        Statement st = null;
        try {
            st = conexion.createStatement();
            rs = st.executeQuery(sql);
            gimnasios = recorrerGimnasios(rs);
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
        return gimnasios;
    }

    public ArrayList<String> gimnasioLiderRegion() {
        conectarConexion();
        ArrayList<String> todo = new ArrayList<>();
        String sql = "SELECT g.id, e.nombre,r.nombre FROM GIMNASIO g inner join entrenador e on g.lider=e.id inner join region r on g.id_region=r.id";
        ResultSet rs = null;
        Statement st = null;
        try {
            st = conexion.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt(1);
                String nombre = rs.getString(2);
                String region = rs.getString(3);
                todo.add("ID: " + id + ", Lider: " + nombre + ", Region: " + region);
            }
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
        return todo;
    }

    //Método para llamar al procedimiento
    public void llamaProcedimiento(int idGim, int lider, int idRegion) {
        conectarConexion();
        CallableStatement cs = null;
        String sql = "{CALL actualizaGimnasio (?, ?, ?)}";

        try {
            cs = conexion.prepareCall(sql);
            cs.setInt(1, idGim);
            cs.setInt(2, lider);
            cs.setInt(3, idRegion);
           cs.execute();
        } catch (SQLException ex) {
            Logger.getLogger(GimnasioRepository.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                cs.close();
                desconectarConexion();
            } catch (SQLException ex) {
                Logger.getLogger(GimnasioRepository.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
     
    }

    public ArrayList<Gimnasio> recorrerGimnasios(ResultSet rs) {
        ArrayList<Gimnasio> listaGimnasios = new ArrayList<>();
        try {
            while (rs.next()) {
                int id = rs.getInt(1);
                String lider = rs.getString(2);
                int idRegion = rs.getInt(3);
                Gimnasio g = new Gimnasio(id, lider, idRegion);
                listaGimnasios.add(g);
            }
        } catch (SQLException ex) {
            Logger.getLogger(GimnasioRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaGimnasios;
    }
}
