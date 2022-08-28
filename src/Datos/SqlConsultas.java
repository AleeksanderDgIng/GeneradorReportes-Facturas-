package Datos;

import Datos.ConexionSQL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author facastaneda
 * Clase que realiza las consultas a la base de datos
 */
public class SqlConsultas {

    private ConexionSQL miConexion;

    public SqlConsultas() {
        miConexion = new ConexionSQL();
    }

    /*
    * Metodo que inserta un nuevo registro en la base de datos
    * Parametros(Array de String con los datos a insertar, la instruccion sql)
     */
    public boolean Ejecutar_Insruccion_Sql(String datos[], String sql) {
        boolean ok = false;
        try {
            PreparedStatement pstm = miConexion.getConnection().prepareStatement(sql);
            for (int i = 0; i <= datos.length - 1; i++) {
                pstm.setString(i + 1, datos[i]);
            }
            pstm.execute();
            pstm.close();
            ok = true;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return ok;
    }

    
    // Metodo que valida si existe el usuario
    public boolean existe(String campo, String from_where) {
        int registros = 0;
        try {
            PreparedStatement pstm = miConexion.getConnection().prepareStatement("SELECT count(" + campo + ") as total  " + from_where);
            ResultSet res = pstm.executeQuery();
            res.next();
            registros = res.getInt("total");
            res.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        if (registros > 0) {
            return true;
        } else {
            return false;
        }
    }

    public String GetDataString(String colName, String sql) {
        String data = "";
        try {
            PreparedStatement pstm = miConexion.getConnection().prepareStatement(sql);
            ResultSet res = pstm.executeQuery();
            while (res.next()) {
                data = res.getString(colName);
            }
            res.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return data;
    }

    public Double GetDataDouble(String colName, String sql) {
        double data = 0;
        try {
            PreparedStatement pstm = miConexion.getConnection().prepareStatement(sql);
            ResultSet res = pstm.executeQuery();
            while (res.next()) {
                data = res.getDouble(colName);
            }
            res.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return data;
    }

    public Object[] GetColumna(String tabla, String colName, String sql) {
        int registros = 0;
        //obtenemos la cantidad de registros existentes en la tabla
        try {
            PreparedStatement pstm = miConexion.getConnection().prepareStatement("SELECT count(*) as total FROM " + tabla);
            ResultSet res = pstm.executeQuery();
            res.next();
            registros = res.getInt("total");
            res.close();
        } catch (SQLException e) {
            System.out.println(e);
        }

        Object[] data = new Object[registros];
        try {
            PreparedStatement pstm = miConexion.getConnection().prepareStatement(sql);
            ResultSet res = pstm.executeQuery();
            int i = 0;
            while (res.next()) {
                data[i] = res.getObject(colName);
                i++;
            }
            res.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return data;
    }

    /* Metodo para obtener todos los datos de una tablas
    *  parametros (Un array con los nombres de las columnas, el nombre de la tabla, la instruccion sql)
     */
    public Object[][] GetTabla(String colName[], String tabla, String sql) {
        int registros = 0;
        //obtenemos la cantidad de registros existentes en la tabla
        try {
            PreparedStatement pstm = miConexion.getConnection().prepareStatement("SELECT count(*) as total FROM " + tabla);
            ResultSet res = pstm.executeQuery();
            res.next();
            registros = res.getInt("total");
            res.close();
        } catch (SQLException e) {
            System.out.println(e);
        }

        Object[][] data = new String[registros][colName.length];
        String col[] = new String[colName.length];
        
        //realizamos la consulta sql y llenamos los datos en "Object"
        try {
            PreparedStatement pstm = miConexion.getConnection().prepareStatement(sql);
            ResultSet res = pstm.executeQuery();
            int i = 0;
            while (res.next()) {
                for (int j = 0; j <= colName.length - 1; j++) {
                    col[j] = res.getString(colName[j]);
                    data[i][j] = col[j];
                }
                i++;
            }
            res.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return data;
    }

}
