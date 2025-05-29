package BusinessEntity;

public class DetalleVentaBE {
    private String Codigo;
    private String Codigo_venta;
    private String Codigo_producto;
    private String Cantidad;
    private float Precio;
    private float Total;

    public String getCodigo() {
        return Codigo;
    }

    public void setCodigo(String Codigo) {
        this.Codigo = Codigo;
    }

    public String getCodigo_venta() {
        return Codigo_venta;
    }

    public void setCodigo_venta(String Codigo_venta) {
        this.Codigo_venta = Codigo_venta;
    }

    public String getCodigo_producto() {
        return Codigo_producto;
    }

    public void setCodigo_producto(String Codigo_producto) {
        this.Codigo_producto = Codigo_producto;
    }

    public String getCantidad() {
        return Cantidad;
    }

    public void setCantidad(String Cantidad) {
        this.Cantidad = Cantidad;
    }

    public float getPrecio() {
        return Precio;
    }

    public void setPrecio(float Precio) {
        this.Precio = Precio;
    }

    public float getTotal() {
        return Total;
    }

    public void setTotal(float Total) {
        this.Total = Total;
    }

    
}
