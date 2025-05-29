package DataAccessObject;
import BusinessEntity.ProductoBE;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ProductoDAO extends ConexionMySQL implements IBaseDAO<ProductoBE>  {
    
    public static void main(String[] ama){        
        System.out.println("PROBANDO EL DAO");
        ProductoDAO myDAO = new ProductoDAO();
        List<ProductoBE> myList = myDAO.ReadAll();
        for(ProductoBE elemento: myList){
            System.out.print(" Cod: " + elemento.getCodigo());
            System.out.print(" Nombre: " + elemento.getNombre());
            System.out.print(" Descripcion: "+ elemento.getDescripcion());
            System.out.print(" Categoria: "+ elemento.getCategoria());
            System.out.print(" Precio: "+ elemento.getPrecio());
            System.out.print(" Stock: "+ elemento.getStock());
            System.out.println(" Estado: "+ elemento.getEstado());
        }
    }
    
    @Override
    public boolean Create(ProductoBE input) {
       boolean result =false;
        try{
            String SQL ="INSERT producto(ID_Producto, Nombre, Descripcion, Categoria, Precio, Stock, Estado) VALUES(?,?,?,?,?,?,?)";
            PreparedStatement pst = getConexion().prepareStatement(SQL);
            pst.setString(1, String.valueOf(UUID.randomUUID())); //AUTOGENERADO UUID
            pst.setString(2, input.getNombre());
            pst.setString(3, input.getDescripcion());
            pst.setString(4, input.getCategoria());
            pst.setFloat(5, input.getPrecio());
            pst.setString(6, input.getStock());
            pst.setString(7, input.getEstado());
            
            result = pst.execute();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return result;
    }

    @Override
    public ProductoBE Read(String id) {
       ProductoBE item = new ProductoBE();
        try{
            PreparedStatement pst = getConexion()
                    .prepareStatement("SELECT * FROM producto WHERE ID_Producto=?");
            pst.setString(1, id);
            ResultSet res = pst.executeQuery();
            while(res.next()){
                item.setCodigo(id);
                item.setNombre(res.getString("Nombre"));
                item.setDescripcion(res.getString("Descripcion"));
                item.setCategoria(res.getString("Categoria"));
                item.setPrecio(res.getFloat("Precio"));
                item.setStock(res.getString("Stock"));
                item.setEstado(res.getString("Estado"));
            }            
        }catch(Exception e){ 
            System.out.println(e.getMessage());
        }
        return item; 
    }

    @Override
    public List<ProductoBE> ReadAll() {
        List<ProductoBE> lst = null; //NULA
        try{
            Statement stm = getConexion().createStatement();
            ResultSet res = stm.executeQuery("SELECT * FROM producto");
            
            lst = new ArrayList<>(); //VACIA
            while(res.next()){
                ProductoBE item = new ProductoBE();
                item.setCodigo(res.getString("ID_Producto"));
                item.setNombre(res.getString("Nombre"));
                item.setDescripcion(res.getString("Descripcion"));
                item.setCategoria(res.getString("Categoria"));
                item.setPrecio(res.getFloat("Precio"));
                item.setStock(res.getString("Stock"));
                item.setEstado(res.getString("Estado"));
                
                lst.add(item); //AGREGAMOS A LA LISTA
            }
            
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return lst;
    }

    @Override
    public boolean Update(ProductoBE input) {
       boolean result =false;
        try{
            String SQL ="UPDATE producto SET Nombre=?, Descripcion=?, Categoria=?, Precio=?, Stock=?, Estado=? WHERE ID_Producto=?";
            PreparedStatement pst =getConexion()
                    .prepareStatement(SQL);
            pst.setString(1, input.getNombre());
            pst.setString(2, input.getDescripcion());
            pst.setString(3, input.getCategoria());
            pst.setFloat(4, input.getPrecio());
            pst.setString(5, input.getStock());
            pst.setString(6, input.getEstado());
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
                    .prepareStatement("DELETE FROM producto WHERE ID_Producto=?");
            pst.setString(1, id);
            result = pst.execute();
        }catch(Exception e){//Exception
            System.out.println(e.getMessage());
        }
        return result;
    }
}
