package DataAccessObject;
import BusinessEntity.ClienteBE;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;


public class ClienteDAO extends ConexionMySQL implements IBaseDAO<ClienteBE> {
    
    public static void main(String[] ama){        
        System.out.println("PROBANDO EL DAO");
        ClienteDAO myDAO = new ClienteDAO();
        List<ClienteBE> myList = myDAO.ReadAll();
        for(ClienteBE elemento: myList){
            System.out.print(" Cod: " + elemento.getCodigo());
            System.out.print(" Nombre: " + elemento.getNombre());
            System.out.print(" Apellido: "+ elemento.getApellido());
            System.out.println(" Documento: "+ elemento.getDocumento());
        }
    }
    
    @Override
    public boolean Create(ClienteBE input) {
         boolean result =false;
        try{
            String SQL ="INSERT cliente(ID_Cliente, Nombre, Apellido, Dni) VALUES(?,?,?,?)";
            PreparedStatement pst = getConexion().prepareStatement(SQL);
            pst.setString(1, String.valueOf(UUID.randomUUID())); //AUTOGENERADO UUID
            pst.setString(2, input.getNombre());
            pst.setString(3, input.getApellido());
            pst.setString(4, input.getDocumento());
            
            result = pst.execute();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return result;
    }

    @Override
    public ClienteBE Read(String id) {
        ClienteBE item = new ClienteBE();
        try{
            PreparedStatement pst = getConexion()
                    .prepareStatement("SELECT * FROM cliente WHERE ID_Cliente=?");
            pst.setString(1, id);
            ResultSet res = pst.executeQuery();
            while(res.next()){
                item.setCodigo(id);
                item.setNombre(res.getString("Nombre"));
                item.setApellido(res.getString("Apellido"));
                item.setDocumento(res.getString("Dni"));
            }            
        }catch(Exception e){ 
            System.out.println(e.getMessage());
        }
        return item;
    }

    @Override
    public List<ClienteBE> ReadAll() {
        List<ClienteBE> lst = null; //NULA
        try{
            Statement stm = getConexion().createStatement();
            ResultSet res = stm.executeQuery("SELECT * FROM cliente");
            
            lst = new ArrayList<>(); //VACIA
            while(res.next()){
                ClienteBE item = new ClienteBE();
                item.setCodigo(res.getString("ID_Cliente"));
                item.setNombre(res.getString("Nombre"));
                item.setApellido(res.getString("Apellido"));
                item.setDocumento(res.getString("Dni"));
                
                lst.add(item); //AGREGAMOS A LA LISTA
            }
            
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return lst;
    }

    @Override
    public boolean Update(ClienteBE input) {
        boolean result =false;
        try{
            String SQL ="UPDATE cliente SET Nombre=?, Apellido=?, Dni=? WHERE ID_Cliente=?";
            PreparedStatement pst =getConexion()
                    .prepareStatement(SQL);
            pst.setString(1, input.getNombre());
            pst.setString(2, input.getApellido());
            pst.setString(3, input.getDocumento());
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
                    .prepareStatement("DELETE FROM cliente WHERE ID_Cliente=?");
            pst.setString(1, id);
            result = pst.execute();
        }catch(Exception e){//Exception
            System.out.println(e.getMessage());
        }
        return result;
    }
}
