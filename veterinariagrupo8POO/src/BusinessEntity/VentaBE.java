package BusinessEntity;

public class VentaBE{
    private String Codigo;
    private String Fecha;
    private float Total;
    private String Codigo_cliente;
    private String Codigo_usuario;

    public String getCodigo() {
        return Codigo;
    }

    public void setCodigo(String Codigo) {
        this.Codigo = Codigo;
    }

    public String getFecha() {
        return Fecha;
    }

    public void setFecha(String Fecha) {
        this.Fecha = Fecha;
    }

    public float getTotal() {
        return Total;
    }

    public void setTotal(float Total) {
        this.Total = Total;
    }

    public String getCodigo_cliente() {
        return Codigo_cliente;
    }

    public void setCodigo_cliente(String Codigo_cliente) {
        this.Codigo_cliente = Codigo_cliente;
    }

    public String getCodigo_usuario() {
        return Codigo_usuario;
    }

    public void setCodigo_usuario(String Codigo_usuario) {
        this.Codigo_usuario = Codigo_usuario;
    }
    
    
}
