package trabajo.integrador;

import java.sql.Date;

public class CodigoBarras {
    private long id;
    private boolean eliminado;
    private long productoId;
    private tipoCodigoBarras tipo;
    private String valor;
    private Date fechaAsignacion; 
    private String observaciones;
    
    // Constructor completo
    public CodigoBarras(long id, long productoId, String tipo, String valor, Date fechaAsignacion, String observaciones) {
        this.id = id;
        this.productoId = productoId;
        this.tipo = tipoCodigoBarras.valueOf(tipo.toUpperCase());
        this.valor = valor;
        this.fechaAsignacion = fechaAsignacion;
        this.observaciones = observaciones;
    }
    
    // Constructor para crear nuevo (sin ID)
    public CodigoBarras(long productoId, tipoCodigoBarras tipo, String valor, Date fechaAsignacion, String observaciones) {
        this.productoId = productoId;
        this.tipo = tipo;
        this.valor = valor;
        this.fechaAsignacion = fechaAsignacion;
        this.observaciones = observaciones;
    }

    // Getters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isEliminado() {
        return eliminado;
    }

    public void setEliminado(boolean eliminado) {
        this.eliminado = eliminado;
    }

    public long getProductoId() {
        return productoId;
    }

    public void setProductoId(long productoId) {
        this.productoId = productoId;
    }

    public tipoCodigoBarras getTipo() {
        return tipo;
    }

    public void setTipo(tipoCodigoBarras tipo) {
        this.tipo = tipo;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public Date getFechaAsignacion() {
        return fechaAsignacion;
    }

    public void setFechaAsignacion(Date fechaAsignacion) {
        this.fechaAsignacion = fechaAsignacion;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public boolean validarFormato() {
        return true; // HARDCODE falta implementar
    }

    public String generarDigitoVerificador() {
        return "0"; // HARDCODE falta implementar
    }

    @Override
    public String toString() {
        return "CodigoBarras{" + "id=" + id + ", productoId=" + productoId + ", tipo=" + tipo + ", valor=" + valor + ", fechaAsignacion=" + fechaAsignacion + ", observaciones=" + observaciones + '}';
    }
}