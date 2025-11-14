package DAO.conexionDB;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;

public class conexion {
    Connection conectar = null;

    String usuario = "postgres";
    String password = "fran123";
    String bd = "Hotelia";
    String ip = "localhost" ;
    String puerto = "5432";

    String cadena = "jdbc:postgresql://"+ip+":"+puerto+"/"+bd;

    public Connection establecerConexion(){
        try{
            Class.forName("org.postgresql.Driver");
            conectar = DriverManager.getConnection(cadena,usuario,password);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return conectar;
    }

}
