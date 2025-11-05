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

    // Constructor completo
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
    
    // Constructor para crear nuevo (sin ID)
    public Usuario(String username, String email, String passwordHash, long rolId) {
        this.username = username;
        this.email = email;
        this.passwordHash = passwordHash;
        this.rolId = rolId;
        this.activo = true;
        this.eliminado = false;
    }

    // Getters y Setters
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public long getRolId() {
        return rolId;
    }

    public void setRolId(long rolId) {
        this.rolId = rolId;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
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
        return null; // HARDCODE falta implementar - necesita acceso al DAO
    }
    
    @Override
    public String toString() {
        return "Usuario{" + "id=" + id + ", username=" + username + ", email=" + email + ", rolId=" + rolId + ", activo=" + activo + ", fechaRegistro=" + fechaRegistro + '}';
    }
}
