package DataAccessObject;
import BusinessEntity.DetalleIngresoBE;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class DetalleIngresoDAO extends ConexionMySQL implements IBaseDAO<DetalleIngresoBE> {

    public static void main(String[] ama){        
        System.out.println("PROBANDO EL DAO");
        DetalleIngresoDAO myDAO = new DetalleIngresoDAO();
        List<DetalleIngresoBE> myList = myDAO.ReadAll();
        for(DetalleIngresoBE elemento: myList){
            System.out.print(" Cod: " + elemento.getCodigo());
            System.out.print(" Codigo ingreso: " + elemento.getCodigo_ingreso());
            System.out.print(" Codigo producto: "+ elemento.getCodigo_producto());
            System.out.print(" Nombre: "+ elemento.getNombre());
            System.out.print(" Cantidad: "+ elemento.getCantidad());
            System.out.print(" Categoria: "+ elemento.getCategoria());
            System.out.println(" Precio compra: "+ elemento.getPrecio_compra());
        }
    }
    
    @Override
    public boolean Create(DetalleIngresoBE input) {
        boolean result =false;
        try{
            String SQL ="INSERT detalle_ingreso(ID_DetalleIngreso, ID_Ingreso, ID_Producto, Nombre, Cantidad, Categoria, Precio_Compra) VALUES(?,?,?,?,?,?,?)";
            PreparedStatement pst = getConexion().prepareStatement(SQL);
            pst.setString(1, String.valueOf(UUID.randomUUID())); //AUTOGENERADO UUID
            pst.setString(2, input.getCodigo_ingreso());
            pst.setString(3, input.getCodigo_producto());
            pst.setString(4, input.getNombre());
            pst.setString(5, input.getCantidad());
            pst.setString(6, input.getCategoria());
            pst.setFloat(7, input.getPrecio_compra());
            
            result = pst.execute();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return result;
    }

    @Override
    public DetalleIngresoBE Read(String id) {
        DetalleIngresoBE item = new DetalleIngresoBE();
        try{
            PreparedStatement pst = getConexion()
                    .prepareStatement("SELECT * FROM detalle_ingreso WHERE ID_DetalleIngreso=?");
            pst.setString(1, id);
            ResultSet res = pst.executeQuery();
            while(res.next()){
                item.setCodigo(id);
                item.setCodigo_ingreso(res.getString("ID_Ingreso"));
                item.setCodigo_producto(res.getString("ID_Producto"));
                item.setNombre(res.getString("Nombre"));
                item.setCantidad(res.getString("Cantidad"));
                item.setCategoria(res.getString("Categoria"));
                item.setPrecio_compra(res.getFloat("Precio_Compra"));
            }            
        }catch(Exception e){ 
            System.out.println(e.getMessage());
        }
        return item; 
    }

    @Override
    public List<DetalleIngresoBE> ReadAll() {
        List<DetalleIngresoBE> lst = null; //NULA
        try{
            Statement stm = getConexion().createStatement();
            ResultSet res = stm.executeQuery("SELECT * FROM detalle_ingreso");
            
            lst = new ArrayList<>(); //VACIA
            while(res.next()){
                DetalleIngresoBE item = new DetalleIngresoBE();
                item.setCodigo(res.getString("ID_DetalleIngreso"));
                item.setCodigo_ingreso(res.getString("ID_Ingreso"));
                item.setCodigo_producto(res.getString("ID_Producto"));
                item.setNombre(res.getString("Nombre"));
                item.setCantidad(res.getString("Cantidad"));
                item.setCategoria(res.getString("Categoria"));
                item.setPrecio_compra(res.getFloat("Precio_Compra"));
                
                lst.add(item); //AGREGAMOS A LA LISTA
            }
            
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return lst;
    }

    @Override
    public boolean Update(DetalleIngresoBE input) {
        boolean result =false;
        try{
            String SQL ="UPDATE detalle_ingreso SET ID_Ingreso=?, ID_Producto=?, Nombre=?, Cantidad=?, Categoria=?, Precio_Compra=? WHERE ID_DetalleIngreso=?";
            PreparedStatement pst =getConexion()
                    .prepareStatement(SQL);
            pst.setString(1, input.getCodigo_ingreso());
            pst.setString(2, input.getCodigo_producto());
            pst.setString(3, input.getNombre());
            pst.setString(4, input.getCantidad());
            pst.setString(5, input.getCategoria());
            pst.setFloat(6, input.getPrecio_compra());
            pst.setString(7, input.getCodigo());
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
                    .prepareStatement("DELETE FROM detalle_ingreso WHERE ID_DetalleIngreso=?");
            pst.setString(1, id);
            result = pst.execute();
        }catch(Exception e){//Exception
            System.out.println(e.getMessage());
        }
        return result;
    }
}
