package trabajo.integrador;

import java.sql.Date;

public class Usuario {
    private long id;
    private boolean eliminado;
    private String username;
    private String email;
    private String passwordHash;
    private long rolId;
    private Boolean activo;
    private Date fechaRegistro;

    public Usuario(long id, boolean eliminado, String username, String email, String passwordHash, long rolId, Boolean activo, Date fechaRegistro) {
        this.id = id;
        this.eliminado = eliminado;
        this.username = username;
        this.email = email;
        this.passwordHash = passwordHash;
        this.rolId = rolId;
        this.activo = activo;
        this.fechaRegistro = fechaRegistro;
    }

    public boolean login(String username, String password) {
        return this.username.equals(username) && this.passwordHash.equals(password); // HARDCODE falta implementar

    }

    public void cambiarPassword(String newPassword) {
        this.passwordHash = newPassword; // HARDCODE falta implementar
    }

    public void activarUsuario() {
        this.activo = true;
    }

    public void desactivarUsuario() {
        this.activo = false;
    }

    public Rol obtenerRol() {
        return new Rol(); // HARDCODE falta implementar
    }
}
