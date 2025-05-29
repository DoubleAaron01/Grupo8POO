package DataAccessObject;
import BusinessEntity.VentaBE;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class VentaDAO extends ConexionMySQL implements IBaseDAO<VentaBE>{

     public static void main(String[] ama){        
        System.out.println("PROBANDO EL DAO");
        VentaDAO myDAO = new VentaDAO();
        List<VentaBE> myList = myDAO.ReadAll();
        for(VentaBE elemento: myList){
            System.out.print(" Cod: " + elemento.getCodigo());
            System.out.print(" Fecha: " + elemento.getFecha());
            System.out.println(" Total: "+ elemento.getTotal());
        }
    }   
    @Override
    public boolean Create(VentaBE input) {
        boolean result =false;
        try{
            String SQL ="INSERT venta(ID_Venta, Fecha, Total, ID_Cliente, ID_Usuario) VALUES(?,?,?,?,?)";
            PreparedStatement pst = getConexion().prepareStatement(SQL);
            pst.setString(1, String.valueOf(UUID.randomUUID())); //AUTOGENERADO UUID
            pst.setString(2, input.getFecha());
            pst.setFloat(3, input.getTotal());
            pst.setString(4, input.getCodigo_cliente());
            pst.setString(5, input.getCodigo_usuario());
            
            result = pst.execute();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return result;
    }

    @Override
    public VentaBE Read(String id) {
        VentaBE item = new VentaBE();
        try{
            PreparedStatement pst = getConexion()
                    .prepareStatement("SELECT * FROM venta WHERE ID_Venta=?");
            pst.setString(1, id);
            ResultSet res = pst.executeQuery();
            while(res.next()){
                item.setCodigo(id);
                item.setFecha(res.getString("Fecha"));
                item.setTotal(res.getFloat("Total"));
                item.setCodigo_cliente(res.getString("ID_Cliente"));
                item.setCodigo_usuario(res.getString("ID_Usuario"));
            }            
        }catch(Exception e){ 
            System.out.println(e.getMessage());
        }
        return item;
    }
    
    @Override
    public List<VentaBE> ReadAll() {
        List<VentaBE> lst = null; //NULA
        try{
            Statement stm = getConexion().createStatement();
            ResultSet res = stm.executeQuery("SELECT * FROM venta");
            
            lst = new ArrayList<>(); //VACIA
            while(res.next()){
                VentaBE item = new VentaBE();
                item.setCodigo(res.getString("ID_Venta"));
                item.setFecha(res.getString("Fecha"));
                item.setTotal(res.getFloat("Total"));
                item.setCodigo_cliente(res.getString("ID_Cliente"));
                item.setCodigo_usuario(res.getString("ID_Usuario"));
                
                lst.add(item); //AGREGAMOS A LA LISTA
            }
            
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return lst;
    }

    @Override
    public boolean Update(VentaBE input) {
        boolean result =false;
        try{
            String SQL ="UPDATE venta SET Fecha=?, Total=?, ID_Cliente=?, ID_Usuario WHERE ID_Venta=?";
            PreparedStatement pst =getConexion()
                    .prepareStatement(SQL);
            pst.setString(1, input.getFecha());
            pst.setFloat(2, input.getTotal());
            pst.setString(3, input.getCodigo_cliente());
            pst.setString(4, input.getCodigo_usuario());
            pst.setString(5, input.getCodigo());
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
                    .prepareStatement("DELETE FROM venta WHERE ID_Venta=?");
            pst.setString(1, id);
            result = pst.execute();
        }catch(Exception e){//Exception
            System.out.println(e.getMessage());
        }
        return result;
    } 
}
