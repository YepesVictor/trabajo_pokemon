/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Diurno
 */
public class ConexionOracle {
    // Datos de conexión
    String user = "equipopokemon";
    String pwd = "123456";
    String host = "localhost";
    String url = "jdbc:oracle:thin:@localhost:1521:xe";

    public ConexionOracle() {
    }

    public Connection getConnection() {
        Connection c = null;
        try {
            c = DriverManager.getConnection(url, user, pwd);
        } catch (SQLException ex) {
            System.err.println("Error al conectar: " + ex.getMessage());
        }
        return c;
    }

    public void desconectar(Connection c) {
        if (c != null) {
            try {
                c.close();
            } catch (SQLException ex) {
                System.err.println("Error al cerrar la conexión: " + ex.getMessage());
            }
        }
    }
}
