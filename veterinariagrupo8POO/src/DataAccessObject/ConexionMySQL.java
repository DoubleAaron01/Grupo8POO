package DataAccessObject;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionMySQL {
     private String StrConxMySQL="jdbc:mysql://localhost:3306/ventas_veterinariadb";
     private String StrUserMySQL="root";
     private String StrPassMySQL="";
     private Connection Conexion;
     
     public ConexionMySQL() {
        System.out.println("Me llamaste :)");
        try{ //Control Error
            Class.forName("com.mysql.cj.jdbc.Driver");
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            DriverManager.setLoginTimeout(300);
            Conexion = DriverManager
                    .getConnection(StrConxMySQL,StrUserMySQL,StrPassMySQL);
            if(Conexion != null){
               DatabaseMetaData dm = Conexion.getMetaData();
               System.out.println("Driver Name:" + dm.getDriverName());
               System.out.println("Driver Version:" + dm.getDriverVersion());
               System.out.println("Product Name:" 
                        + dm.getDatabaseProductName());
                System.out.println("Product Version:" 
                        + dm.getDatabaseProductVersion());
            }
        }catch(ClassNotFoundException | SQLException e){
            System.out.println(e.getMessage());
        }
    }
    //Encapsulamiento
    public Connection getConexion() {
        return Conexion;
    }
    
    public static void main(String[] args){
        System.out.println("Haciendo una Instancia de Clase");
        ConexionMySQL cn =new ConexionMySQL();
    } 
}
