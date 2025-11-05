package trabajo.integrador;

import java.util.List;

public class Rol {
    private long id;
    private String nombre;
    private String descripcion;

    public Rol(long id, String nombre, String descripcion) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }
    
    // Constructor para crear nuevo (sin ID)
    public Rol(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    // Getters y Setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<String> getPermisos() {
        System.out.println("Permisos del rol"); // HARDCODE falta implementar
        return null;
    }

    public void asignarPermisos(List<String> permisos) {
        System.out.println("Permisos otorgados"); // HARDCODE falta implementar
    }

    @Override
    public String toString() {
        return "Rol{" + "id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + '}';
    }
}
