
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
        System.out.println("MENU:" + "\n" + "1 Crear Tabla" + "\n" + "2 Insetar Valores" +"\n" + "3 Asignar cstegoria"+"\n" + "4 Subir Salario" + "\n" + "5 Listar" + "\n" + "0 SALIR");
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
                InsertarValores();
                break;
            case 3:
                AsignarCategorias();
                break;
            case 4:
                SubirSalario();
                break;
            case 5:
                Listar();
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

    private static void Listar() {
        Conector conectar = new Conector();
        Connection conexion = conectar.getConexion();

        String Listar = "SELECT Codigo,Nombre,Telefono,DNI,(select Descripcion from categoria where Categoria=Cod_Categoria)  as 'Categoria',(select SalarioBase from categoria where Categoria=Cod_Categoria)  as 'Salario'FROM empleados;";
        PreparedStatement si;
        try {
            si = conexion.prepareStatement(Listar);
            ResultSet rs = si.executeQuery (); 
            while (rs.next()) {
                System.out.println(rs.getInt(1)+ " " +rs.getString(2)+ " " +rs.getString(3)+ " " +rs.getString(4)+ " " +rs.getString(5)+ " " +rs.getInt(6));
            }                     
        }    catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void SubirSalario() {
        Scanner input = new Scanner(System.in);

        Conector conectar = new Conector();
        Connection conexion = conectar.getConexion();

        System.out.println("¿Que porcentaje deseas subir?");
        int porcentaje = input.nextInt();

        String Procedimiento = "call bd_2_saavedra_j.procedimiento2("+porcentaje+");";
        PreparedStatement si;
        try {
            si = conexion.prepareStatement(Procedimiento);
            si.executeUpdate(); 
            System.out.println("Procedimiento Realizado");
            conexion.close();                 
        }    catch (SQLException e) {
            e.printStackTrace();
        }
        
        Menu();
    }

    private static void AsignarCategorias() {
        Scanner input = new Scanner(System.in);

        Conector conectar = new Conector();
        Connection conexion = conectar.getConexion();

        int[] Categoria=new int[6];

        System.out.println("Estas son las distintas categorias: ");
        
        MostrarCategoria(conexion);
        
        System.out.println("Dime el id de la categoria para el primer empleado: ");
        Categoria[0] = input.nextInt();
        System.out.println("Dime el id de la categoria para el segundo empleado: ");
        Categoria[1] = input.nextInt();
        System.out.println("Dime el id de la categoria para el tercero empleado: ");
        Categoria[2] = input.nextInt();
        System.out.println("Dime el id de la categoria para el cuarto empleado: ");
        Categoria[3] = input.nextInt();
        System.out.println("Dime el id de la categoria para el quinto empleado: ");
        Categoria[4] = input.nextInt();
        System.out.println("Dime el id de la categoria para el sexto empleado: ");
        Categoria[5] = input.nextInt();

        for (int i = 0; i < Categoria.length; i++) {
            String ObtenerCategorias = "UPDATE empleados set Categoria = "+Categoria[i]+ " WHERE Codigo = "+(i+1);
            PreparedStatement si;
            try {
                si = conexion.prepareStatement(ObtenerCategorias);
                si.executeUpdate(); 
                System.out.println("Datos actualizado");
                                       
            }    catch (SQLException e) {
                e.printStackTrace();
            }
        }

        try {
            conexion.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        input.close();

    }

    private static void MostrarCategoria(Connection conexion) {
        String ObtenerCategorias = "SELECT Cod_Categoria,descripcion from Categoria";
        PreparedStatement si;
        try {
            si = conexion.prepareStatement(ObtenerCategorias);
            ResultSet rs = si.executeQuery (); 
            while (rs.next()) {
                System.out.println(rs.getInt(1)+ " " +rs.getString(2));
            }                     
        }    catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void InsertarValores() {    
        Conector conectar = new Conector();
        Connection conexion = conectar.getConexion();

        String InsertarValores1 = "INSERT INTO bd_2_saavedra_j.categoria(Descripcion,SalarioBase)VALUES('Vendedor',1000),('Reponedor',900),('Encargado',1500),('Limpiador',1000),('Gerente',2000);" ;
        
        PreparedStatement si;
        try {
            si = conexion.prepareStatement(InsertarValores1);
            si.executeUpdate(); 
            System.out.println("Datos introducidos");
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
