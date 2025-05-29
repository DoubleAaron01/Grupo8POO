package BusinessEntity;

public class DetalleIngresoBE {
    private String Codigo;
    private String Codigo_ingreso;
    private String Codigo_producto;
    private String Nombre;
    private String Cantidad;
    private String Categoria;
    private float Precio_compra;

    public String getCodigo() {
        return Codigo;
    }

    public void setCodigo(String Codigo) {
        this.Codigo = Codigo;
    }

    public String getCodigo_ingreso() {
        return Codigo_ingreso;
    }

    public void setCodigo_ingreso(String Codigo_ingreso) {
        this.Codigo_ingreso = Codigo_ingreso;
    }

    public String getCodigo_producto() {
        return Codigo_producto;
    }

    public void setCodigo_producto(String Codigo_producto) {
        this.Codigo_producto = Codigo_producto;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getCantidad() {
        return Cantidad;
    }

    public void setCantidad(String Cantidad) {
        this.Cantidad = Cantidad;
    }

    public String getCategoria() {
        return Categoria;
    }

    public void setCategoria(String Categoria) {
        this.Categoria = Categoria;
    }

    public float getPrecio_compra() {
        return Precio_compra;
    }

    public void setPrecio_compra(float Precio_compra) {
        this.Precio_compra = Precio_compra;
    }
    
}
