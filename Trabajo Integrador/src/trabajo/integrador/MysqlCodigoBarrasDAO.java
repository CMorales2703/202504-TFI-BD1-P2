/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trabajo.integrador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ramiromoralesdev
 */
public class MysqlCodigoBarrasDAO implements DAO<Producto, Integer> { 

    private Connection conn;

    public MysqlProductoDAO(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void crear(Producto producto) {
        String sql = "INSERT INTO producto (id, nombre, precio) VALUES (?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setDouble(1, 0);
            ps.setString(2, producto.getNombre());
            ps.setDouble(3, producto.getPrecio());
            ps.executeUpdate();
        } catch (SQLException e) {
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
            System.out.println("✅ Producto actualizado correctamente. " + producto);
        } else {
            System.out.println("No se encontró el producto con ID: " + producto.getId());
        }
    } catch (SQLException e) {
        System.err.println("❌ Error al actualizar el producto: " + e.getMessage());
        e.printStackTrace();
    }
}


    @Override
    public void eliminar(Integer id) {
        String sql = "DELETE FROM producto WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Producto eliminado" + id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Producto leerPorId(Integer id) {
        String sql = "SELECT * FROM producto WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Producto(
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getString("marca"),     
                    rs.getDouble("precio"),
                    rs.getDouble("peso")
    
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Producto> leerTodos() {
        List<Producto> lista = new ArrayList<>();
        String sql = "SELECT * FROM producto";
        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                lista.add(new Producto(
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getDouble("precio")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
}

    
}
