package trabajo.integrador;

import java.sql.Date;

public class CodigoBarras {
    private long id;
    private boolean eliminado;
    private long produtoId;
    private String tipo;
    private String valor;
    private Date Asignacion; 
    private String observaciones;

    public CodigoBarras(long produtoId, String tipo, String valor) {
        this.produtoId = produtoId;
        this.tipo = tipo;
        this.valor = valor;
    }

    public boolean validarFormato() {
        return true; // HARDCODE falta implementar

    }

    public String generarDigitoVerificador() {
        return "0"; // HARDCODE falta implementar
    }

    public Producto obtenerProducto() {
        return new Producto(this.produtoId, false, "nombre", "marca", "categoria", 100, 1000, "2025-01-01"); // HARDCODE falta implementar
    }

}