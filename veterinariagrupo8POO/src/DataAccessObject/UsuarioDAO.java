package DataAccessObject;
import BusinessEntity.UsuarioBE;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UsuarioDAO extends ConexionMySQL implements IBaseDAO<UsuarioBE> {

    public static void main(String[] ama){        
        System.out.println("PROBANDO EL DAO");
        UsuarioDAO myDAO = new UsuarioDAO();
        List<UsuarioBE> myList = myDAO.ReadAll();
        for(UsuarioBE elemento: myList){
            System.out.print(" Cod: " + elemento.getCodigo());
            System.out.print(" Usuario: " + elemento.getUsuario());
            System.out.print(" Contraseña: " + elemento.getContraseña());
            System.out.println(" Rol: " + elemento.getRol());
        }
    }
    
    @Override
    public boolean Create(UsuarioBE input) {
         boolean result =false;
        try{
            String SQL ="INSERT usuario(ID_Usuario, Usuario, Contrasena, Rol) VALUES(?,?,?,?)";
            PreparedStatement pst = getConexion().prepareStatement(SQL);
            pst.setString(1, String.valueOf(UUID.randomUUID())); //AUTOGENERADO UUID
            pst.setString(2, input.getUsuario());
            pst.setString(3, input.getContraseña());
            pst.setString(4, input.getRol());
            
            result = pst.execute();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return result;
    }

    @Override
    public UsuarioBE Read(String id) {
       UsuarioBE item = new UsuarioBE();
        try{
            PreparedStatement pst = getConexion()
                    .prepareStatement("SELECT * FROM usuario WHERE ID_Usuario=?");
            pst.setString(1, id);
            ResultSet res = pst.executeQuery();
            while(res.next()){
                item.setCodigo(id);
                item.setUsuario(res.getString("Usuario"));
                item.setContraseña(res.getString("Contrasena"));
                item.setRol(res.getString("Rol"));
            }            
        }catch(Exception e){ 
            System.out.println(e.getMessage());
        }
        return item;
    }

    @Override
    public List<UsuarioBE> ReadAll() {
        List<UsuarioBE> lst = null; //NULA
        try{
            Statement stm = getConexion().createStatement();
            ResultSet res = stm.executeQuery("SELECT * FROM usuario");
            
            lst = new ArrayList<>(); //VACIA
            while(res.next()){
                UsuarioBE item = new UsuarioBE();
                item.setCodigo(res.getString("ID_Usuario"));
                item.setUsuario(res.getString("Usuario"));
                item.setContraseña(res.getString("Contrasena"));
                item.setRol(res.getString("Rol"));
                
                lst.add(item); //AGREGAMOS A LA LISTA
            }
            
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return lst;
    }

    @Override
    public boolean Update(UsuarioBE input) {
         boolean result =false;
        try{
            String SQL ="UPDATE usuario SET Usuario=?, Contrasena=?, Rol=? WHERE ID_Usuario=?";
            PreparedStatement pst =getConexion()
                    .prepareStatement(SQL);
            pst.setString(1, input.getUsuario());
            pst.setString(2, input.getContraseña());
            pst.setString(3, input.getRol());
            pst.setString(4, input.getCodigo());
            result = pst.execute();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return result;
    }

    @Override
    public boolean Delete(String id) {
         boolean result =false;
        try{//Control
            PreparedStatement pst = getConexion()
                    .prepareStatement("DELETE FROM usuario WHERE ID_Usuario=?");
            pst.setString(1, id);
            result = pst.execute();
        }catch(Exception e){//Exception
            System.out.println(e.getMessage());
        }
        return result;
    }
}