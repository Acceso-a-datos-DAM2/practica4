
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class App {
    static Connection c;
    public static void main(String[] args) throws Exception {
        
        Menu();
    }

    private static void Menu() {

        // Mostramos las opciones de ejercicos que podemos hacer
        System.out.println("MENU:" + "\n" + "1 Crear Tabla" + "\n" + "2 Insetar Valores" +"\n" + "3 Insetar Valores" + "\n" + "0 SALIR");
        System.out.println("¿Que deseas hacer?");
        // hacemos un scanner para leer de consola la accion a realizar por el menu
        Scanner input = new Scanner(System.in);
        int Ejercicio = input.nextInt();
        // selecionamos que hacer con un switch
        switch (Ejercicio) {
            case 1:
                CrearTabla();
                break;
            case 2:
                CrearTabla();
                break;
            case 3:
                InsertarValores();
                break;
            case 0:
                System.exit(0);
                break;
            default:
                System.out.println("no existe ese ejercicio, escriba uno");
                Menu();
        }

        input.close();
    }

    private static void InsertarValores() {    
        Conector conectar = new Conector();
        Connection conexion = conectar.getConexion();

        String InsertarValores1 = "INSERT INTO bd_2_saavedra_j.categoria(Descripcion,SalarioBase)VALUES('Vendedor',1000);" ;
        String InsertarValores2 = "INSERT INTO bd_2_saavedra_j.categoria(Descripcion,SalarioBase)VALUES('Reponedor',900); " ;
        String InsertarValores3 = "INSERT INTO bd_2_saavedra_j.categoria(Descripcion,SalarioBase)VALUES('Encargado',1500); " ;
        String InsertarValores4 = "INSERT INTO bd_2_saavedra_j.categoria(Descripcion,SalarioBase)VALUES('Limpiador',1000);  ";
        String InsertarValores5 = "INSERT INTO bd_2_saavedra_j.categoria(Descripcion,SalarioBase)VALUES('Gerente',2000);";
        PreparedStatement s;
        try {
            s = conexion.prepareStatement(InsertarValores1);
            s.executeUpdate(); 
            s = conexion.prepareStatement(InsertarValores2);
            s.executeUpdate();
            s = conexion.prepareStatement(InsertarValores3);
            s.executeUpdate();
            s = conexion.prepareStatement(InsertarValores4);
            s.executeUpdate();
            s = conexion.prepareStatement(InsertarValores5);
            s.executeUpdate();
            conexion.close();                       
        }    catch (SQLException e) {
            e.printStackTrace();
        }
        Menu();
    }

    private static void CrearTabla() {       
        Conector conectar = new Conector();
        Connection conexion = conectar.getConexion();

        //string para crear la tabla
        String crearTabla ="CREATE TABLE Categoria(Cod_Categoria int  primary key AUTO_INCREMENT,Descripcion varchar (25),SalarioBase int);";
        //string para crear la columna en usuario
        String crarCodCategoria = "ALTER TABLE  empleados ADD Categoria INT;";
        //string para hacerlo fk de usuario
        String CrearFK = "ALTER TABLE empleados  ADD FOREIGN KEY (Categoria) REFERENCES Categoria(Cod_Categoria);";
        PreparedStatement s;
        try {
            s = conexion.prepareStatement(crearTabla);
            s.executeUpdate();
            s = conexion.prepareStatement(crarCodCategoria);
            s.executeUpdate();
            s = conexion.prepareStatement(CrearFK);
            s.executeUpdate();      
            conexion.close();        
        }    catch (SQLException e) {
            e.printStackTrace();
        }        
        
        
        Menu();
    }

    
    
}
