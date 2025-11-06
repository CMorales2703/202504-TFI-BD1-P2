package trabajo.integrador.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import trabajo.integrador.entities.Producto;

public class MysqlProductoDAO implements DAO<Producto, Integer> {

    private Connection conn;

    public MysqlProductoDAO(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void crear(Producto producto) {
        String sql = "INSERT INTO producto (nombre, marca, categoria, precio, peso) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, producto.getNombre());
            ps.setString(2, producto.getMarca());
            ps.setString(3, producto.getCategoria());
            ps.setDouble(4, producto.getPrecio());
            ps.setDouble(5, producto.getPeso());
            ps.executeUpdate();
            
            // Obtener el ID generado con el auto_increment
            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    long idGenerado = generatedKeys.getLong(1);
                    producto.setId(idGenerado);
                    System.out.println("Producto creado correctamente con ID: " + idGenerado);
                } else {
                    System.out.println("Producto creado correctamente, pero no se pudo obtener el ID generado.");
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al crear producto: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
public void actualizar(Producto producto) {
    String sql = "UPDATE producto SET nombre = ?, precio = ?, marca = ?, categoria = ?, peso = ? WHERE id = ?";
    
    try (PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setString(1, producto.getNombre());
        ps.setDouble(2, producto.getPrecio());
        ps.setString(3, producto.getMarca());
        ps.setString(4, producto.getCategoria());
        ps.setDouble(5, producto.getPeso());
        ps.setLong(6, producto.getId());
        
        int filas = ps.executeUpdate();
        if (filas > 0) {
            System.out.println("Producto actualizado correctamente. " + producto);
        } else {
            System.out.println("No se encontró el producto con ID: " + producto.getId());
        }
    } catch (SQLException e) {
        System.err.println("Error al actualizar el producto: " + e.getMessage());
        e.printStackTrace();
    }
}


    @Override
    public void eliminar(Integer id) {
        String sql = "DELETE FROM producto WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            int filas = ps.executeUpdate();
            if (filas > 0) {
                System.out.println("Producto eliminado: " + id);
            } else {
                System.out.println("No se encontró el producto con ID: " + id);
            }
        } catch (SQLException e) {
            System.err.println("Error al eliminar producto: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public Producto leerPorId(Integer id) {
        String sql = "SELECT * FROM producto WHERE id = ? AND eliminado = FALSE";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Producto(
                    rs.getLong("id"),
                    rs.getBoolean("eliminado"),
                    rs.getString("nombre"),
                    rs.getString("marca"),
                    rs.getString("categoria"),
                    rs.getDouble("precio"),
                    rs.getDouble("peso"),
                    rs.getTimestamp("fecha_creacion") != null ? new Date(rs.getTimestamp("fecha_creacion").getTime()) : null
                );
            }
        } catch (SQLException e) {
            System.err.println("Error al leer producto: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Producto> leerTodos() {
        List<Producto> lista = new ArrayList<>();
        String sql = "SELECT * FROM producto WHERE eliminado = FALSE";
        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                lista.add(new Producto(
                    rs.getLong("id"),
                    rs.getBoolean("eliminado"),
                    rs.getString("nombre"),
                    rs.getString("marca"),
                    rs.getString("categoria"),
                    rs.getDouble("precio"),
                    rs.getDouble("peso"),
                    rs.getTimestamp("fecha_creacion") != null ? new Date(rs.getTimestamp("fecha_creacion").getTime()) : null
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error al leer productos: " + e.getMessage());
            e.printStackTrace();
        }
        return lista;
    }
}
