package Datos;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;
import java.sql.*;

/**
 *
 * @author facastaneda
 * Clase que realiza la conexión con la base de datos
 */
public class ConexionSQL {

    private final String bd = "dbFactura";
    private final String usuario = "root";
    private final String password = "";
    private final String url = "jdbc:mysql://localhost/" + bd;

    Connection miconexion = null;

    // constructor
    public ConexionSQL() {

        try {
            //driver jdbc para mysql
            Class.forName("com.mysql.jdbc.Driver");

            //Obtenemos la conexión
            miconexion = DriverManager.getConnection(url, usuario, password);
            if (miconexion != null) {
                System.out.println("Conexión a base de datos " + bd + ". listo");
            }

            JOptionPane.showMessageDialog(null, "¡Conexión exitosa!");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Conexión fallida " + e);
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    // retorna la conexion
    public Connection getConnection() {
        return miconexion;
    }

    public void desconectar() {
        miconexion = null;
        JOptionPane.showMessageDialog(null, "La conexion a la  base de datos " + bd + " a terminado");

    }

}
