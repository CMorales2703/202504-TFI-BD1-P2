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
public class MysqlCodigoBarrasDAO implements DAO<CodigoBarras, Integer> { 

    private Connection conn;

    public  MysqlCodigoBarrasDAO(Connection conn) {
        this.conn = conn;
    }

    
    public void crear(CodigoBarras c) {
        String sql = "INSERT INTO producto (id, nombre, precio) VALUES (?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setDouble(1, 0);
            ps.setString(2, CodigoBarras.getNombre());
            ps.setDouble(3, CodigoBarras.getPrecio());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

public void actualizar(Producto CodigoBarras) {
    String Producto = "UPDATE producto SET nombre = ?, precio = ?, marca = ?, categoria = ?, peso = ? WHERE id = ?";
    
    try (PreparedStatement ps = conn.prepareStatement(Producto)) {
        ps.setString(1, CodigoBarras.getNombre());
        ps.setDouble(2, CodigoBarras.getPrecio());
        ps.setString(3, CodigoBarras.getMarca());
        ps.setString(4, CodigoBarras.getCategoria());
        ps.setDouble(5, CodigoBarras.getPeso());
        ps.setLong(6, CodigoBarras.getId());
        
        int filas = ps.executeUpdate();
        if (filas > 0) {
            System.out.println("✅ Producto actualizado correctamente. " + CodigoBarras);
        } else {
            System.out.println("No se encontró el producto con ID: " + CodigoBarras.getId());
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
    public CodigoBarras leerPorId(Integer id) {
        String sql = "SELECT * FROM producto WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new CodigoBarras(
                    rs.getInt("id"),
                    rs.getString("tipo"),
                    rs.getString("valor")
               );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<CodigoBarras> leerTodos() {
        List<CodigoBarras> lista = new ArrayList<>();
        String sql = "SELECT * FROM codigo_barras";
        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                lista.add(new CodigoBarras(
                    rs.getInt("id"),
                    rs.getObject("valor"),
                    rs.getDouble("producto_id"),
                    
                ));
                return lista;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
       

    
}
    