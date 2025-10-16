package trabajo.integrador;

import java.sql.Date;

public class Producto {
    private long id;
    private boolean eliminado;
    private String nombre;
    private String marca;
    private String categoria;
    private double precio;
    private double peso;
    private Date fechaCreacion;

    public Producto(long id, boolean eliminado, String nombre, String marca, String categoria, double precio, double peso, String fechaCreacion) {
        this.id = id;
        this.eliminado = eliminado;
        this.nombre = nombre;
        this.marca = marca;
        this.categoria = categoria;
        this.precio = precio;
        this.peso = peso;
        this.fechaCreacion = fechaCreacion;
    }

    public double getPrecioConIva(double iva) {
        return this.precio * (1 + iva);
    }

    public double getPesoEnKilos() { // El peso se guarda en gramos?
        return this.peso / 1000;
    }

    public boolean validarPrecio() {
        return this.precio > 0;
    }
    
    public CodigoBarras obtenerCodigoBarras() {
        return new CodigoBarras(this.id, this.nombre, this.marca);
    }
}
