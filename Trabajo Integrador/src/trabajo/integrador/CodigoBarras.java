package trabajo.integrador;

import java.sql.Date;

public class CodigoBarras {
    private long id;
    private boolean eliminado;
    private int productoId;
    private String tipo;
    private tipoCodigoBarras valor;
    private Date Asignacion; 
    private String observaciones;
    private MysqlProductoDAO p;
    
    public CodigoBarras(long id, String tipo, String valor) {
        this.id = id;
        this.tipo = tipo;
        this.valor = valor;
    }

    public CodigoBarras(int productoId, String tipo, String valor) {
        this.productoId = productoId;
        this.tipo = tipo;
        this.valor = valor;
    }

    public boolean validarFormato() {
        return true; // HARDCODE falta implementar

    }

    public String generarDigitoVerificador() {
        return "0"; // HARDCODE falta implementar
    }

//    public Producto obtenerProducto() {
//        p.leerPorId(productoId);
//    }

}