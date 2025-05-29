package DataAccessObject;
import BusinessEntity.IngresoProductoBE;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class IngresoProductoDAO extends ConexionMySQL implements IBaseDAO<IngresoProductoBE> {

     public static void main(String[] ama){        
        System.out.println("PROBANDO EL DAO");
        IngresoProductoDAO myDAO = new IngresoProductoDAO();
        List<IngresoProductoBE> myList = myDAO.ReadAll();
        for(IngresoProductoBE elemento: myList){
            System.out.print(" Cod: " + elemento.getCodigo());
            System.out.print(" Fecha: " + elemento.getFecha());
            System.out.println(" Codigo Usuario: "+ elemento.getCodigo_usuario());
        }
    }
    
    @Override
    public boolean Create(IngresoProductoBE input) {
        boolean result =false;
        try{
            String SQL ="INSERT ingreso_producto(ID_Ingreso, Fecha, ID_Usuario) VALUES(?,?,?)";
            PreparedStatement pst = getConexion().prepareStatement(SQL);
            pst.setString(1, String.valueOf(UUID.randomUUID())); //AUTOGENERADO UUID
            pst.setString(2, input.getFecha());
            pst.setString(3, input.getCodigo_usuario());
            
            result = pst.execute();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return result;
    }

    @Override
    public IngresoProductoBE Read(String id) {
        IngresoProductoBE item = new IngresoProductoBE();
        try{
            PreparedStatement pst = getConexion()
                    .prepareStatement("SELECT * FROM ingreso_producto WHERE ID_Ingreso=?");
            pst.setString(1, id);
            ResultSet res = pst.executeQuery();
            while(res.next()){
                item.setCodigo(id);
                item.setFecha(res.getString("Fecha"));
                item.setCodigo_usuario(res.getString("ID_Usuario"));
            }            
        }catch(Exception e){ 
            System.out.println(e.getMessage());
        }
        return item;
    }

    @Override
    public List<IngresoProductoBE> ReadAll() {
        List<IngresoProductoBE> lst = null; //NULA
        try{
            Statement stm = getConexion().createStatement();
            ResultSet res = stm.executeQuery("SELECT * FROM ingreso_producto");
            
            lst = new ArrayList<>(); //VACIA
            while(res.next()){
                IngresoProductoBE item = new IngresoProductoBE();
                item.setCodigo(res.getString("ID_Ingreso"));
                item.setFecha(res.getString("Fecha"));
                item.setCodigo_usuario(res.getString("ID_Usuario"));
                
                lst.add(item); //AGREGAMOS A LA LISTA
            }
            
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return lst;
    }

    @Override
    public boolean Update(IngresoProductoBE input) {
         boolean result =false;
        try{
            String SQL ="UPDATE ingreso_producto SET Fecha=?, ID_Usuario=? WHERE ID_Ingreso=?";
            PreparedStatement pst =getConexion()
                    .prepareStatement(SQL);
            pst.setString(1, input.getFecha());
            pst.setString(2, input.getCodigo_usuario());
            pst.setString(3, input.getCodigo());
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
                    .prepareStatement("DELETE FROM ingreso_producto WHERE ID_Ingreso=?");
            pst.setString(1, id);
            result = pst.execute();
        }catch(Exception e){//Exception
            System.out.println(e.getMessage());
        }
        return result;
    } 
}
