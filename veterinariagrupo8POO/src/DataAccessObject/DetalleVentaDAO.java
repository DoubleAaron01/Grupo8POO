package DataAccessObject;
import BusinessEntity.DetalleVentaBE;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class DetalleVentaDAO extends ConexionMySQL implements IBaseDAO<DetalleVentaBE> {

     public static void main(String[] ama){        
        System.out.println("PROBANDO EL DAO");
        DetalleVentaDAO myDAO = new DetalleVentaDAO();
        List<DetalleVentaBE> myList = myDAO.ReadAll();
        for(DetalleVentaBE elemento: myList){
            System.out.print(" Cod: " + elemento.getCodigo());
            System.out.print(" Codigo venta: " + elemento.getCodigo_venta());
            System.out.print(" Codigo producto: "+ elemento.getCodigo_producto());
            System.out.print(" Cantidad: "+ elemento.getCantidad());
            System.out.print(" Precio: "+ elemento.getPrecio());
            System.out.println(" Total: "+ elemento.getTotal());
        }
    }
    
    @Override
    public boolean Create(DetalleVentaBE input) {
        boolean result =false;
        try{
            String SQL ="INSERT detalle_venta(ID_DetalleVenta, ID_Venta, ID_Producto, Cantidad, Precio, Total) VALUES(?,?,?,?,?,?)";
            PreparedStatement pst = getConexion().prepareStatement(SQL);
            pst.setString(1, String.valueOf(UUID.randomUUID())); //AUTOGENERADO UUID
            pst.setString(2, input.getCodigo_venta());
            pst.setString(3, input.getCodigo_producto());
            pst.setString(5, input.getCantidad());
            pst.setFloat(6, input.getPrecio());
            pst.setFloat(7, input.getTotal());
            
            result = pst.execute();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return result;
    }

    @Override
    public DetalleVentaBE Read(String id) {
         DetalleVentaBE item = new DetalleVentaBE();
        try{
            PreparedStatement pst = getConexion()
                    .prepareStatement("SELECT * FROM detalle_venta WHERE ID_DetalleVenta=?");
            pst.setString(1, id);
            ResultSet res = pst.executeQuery();
            while(res.next()){
                item.setCodigo(id);
                item.setCodigo_venta(res.getString("ID_Venta"));
                item.setCodigo_producto(res.getString("ID_Producto"));
                item.setCantidad(res.getString("Cantidad"));
                item.setPrecio(res.getFloat("Precio"));
                item.setTotal(res.getFloat("Total"));
            }            
        }catch(Exception e){ 
            System.out.println(e.getMessage());
        }
        return item;
    }

    @Override
    public List<DetalleVentaBE> ReadAll() {
        List<DetalleVentaBE> lst = null; //NULA
        try{
            Statement stm = getConexion().createStatement();
            ResultSet res = stm.executeQuery("SELECT * FROM detalle_venta");
            
            lst = new ArrayList<>(); //VACIA
            while(res.next()){
                DetalleVentaBE item = new DetalleVentaBE();
                item.setCodigo(res.getString("ID_DetalleVenta"));
                item.setCodigo_venta(res.getString("ID_Venta"));
                item.setCodigo_producto(res.getString("ID_Producto"));
                item.setCantidad(res.getString("Cantidad"));
                item.setPrecio(res.getFloat("Precio"));
                item.setTotal(res.getFloat("Total"));
                
                lst.add(item); //AGREGAMOS A LA LISTA
            }
            
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return lst;
    }

    @Override
    public boolean Update(DetalleVentaBE input) {
        boolean result =false;
        try{
            String SQL ="UPDATE detalle_venta SET ID_Venta=?, ID_Producto=?, Cantidad=?, Precio=?, Total=? WHERE ID_DetalleVenta=?";
            PreparedStatement pst =getConexion()
                    .prepareStatement(SQL);
            pst.setString(1, input.getCodigo_venta());
            pst.setString(2, input.getCodigo_producto());
            pst.setString(3, input.getCantidad());
            pst.setFloat(4, input.getPrecio());
            pst.setFloat(5, input.getTotal());
            pst.setString(6, input.getCodigo());
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
                    .prepareStatement("DELETE FROM detalle_venta WHERE ID_DetalleVenta=?");
            pst.setString(1, id);
            result = pst.execute();
        }catch(Exception e){//Exception
            System.out.println(e.getMessage());
        }
        return result;
    }
}
