
    

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;

public class Conector {

  public static void muestraErrorSQL(SQLException e) {
    System.err.println("SQL ERROR mensaje: " + e.getMessage());
    System.err.println("SQL Estado: " + e.getSQLState());
    System.err.println("SQL código específico: " + e.getErrorCode());
  } 

    String basedatos = "bd_2_saavedra_j";
    String host = "servidorifc.iesch.org";
    String port = "3306";
    //String port = = "8882";
    String parAdic = "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&useSSL=false";
    String urlConnection = "jdbc:mysql://" + host + ":" + port + "/" + basedatos + parAdic;
    String user = "2_saavedra_j";
    String pwd = "69axe";

    public Connection getConexion(){
      Connection c = null;
    try{
      c = DriverManager.getConnection(urlConnection, user, pwd);
      System.out.println("Conexion abierta.");        
    } catch (SQLException e) {
      System.out.println("SQL mensaje: " + e.getMessage());
      System.out.println("SQL Estado: " + e.getSQLState());
      System.out.println("SQL código específico: " + e.getErrorCode());
    } catch (Exception e) {
      e.printStackTrace(System.err);
    }
    return c;
  }
  public void closeConnection(){
    Connection c = null;
  try{
    c.close();
    System.out.println("Conexión cerrada.");        
  } catch (SQLException e) {
    System.out.println("SQL mensaje: " + e.getMessage());
    System.out.println("SQL Estado: " + e.getSQLState());
    System.out.println("SQL código específico: " + e.getErrorCode());
  } catch (Exception e) {
    e.printStackTrace(System.err);
  }
}
}


