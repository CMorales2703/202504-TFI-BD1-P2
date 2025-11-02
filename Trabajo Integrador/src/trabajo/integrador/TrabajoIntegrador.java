package trabajo.integrador;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class TrabajoIntegrador {
    public static void main(String[] args) {

        try (Connection conn = DatabaseConfiguration.getConnection()) {

            MysqlProductoDAO productoDAO = new MysqlProductoDAO(conn); // Objeto DAO

//            // CREATE
//            Producto nuevo = new Producto(0,"Producto nuevo", 15000.0);
//            productoDAO.crear(nuevo);
//
//             // Mostrar todos
//            System.out.println("📦 Lista de Productos:");
//            System.out.println("======================");
//            List<Producto> productos = productoDAO.leerTodos();
//            for (Producto p : productos) {
//                System.out.println(p.getId() + " | " + p.getNombre() + " | $" + p.getPrecio());
//            }
//
            // Buscar por ID (ejemplo)
//            Producto buscado = productoDAO.leerPorId(1);
//            if (buscado != null) {
//                System.out.println("Producto encontrado: " 
//                        + "ID " + buscado.getId()
//                        + " Marca: " + buscado.getMarca()
//                        + " Precio: " +  buscado.getPrecio()
//                        + " Peso: " + buscado.getPeso()
//                );
//            } else {
//                System.out.println("Producto no encontrado.");
//            }

//            // UPDATE
//             Producto p = new Producto(1, "Teclado Mecánico", 45000);
//            p.setMarca("Redragon");
//            p.setCategoria("Periféricos");
//            p.setPeso(1200);
//
//            productoDAO.actualizar(p);

              // DELETE
//              productoDAO.eliminar(1);
//            
        } catch (SQLException e) {
            System.err.println("❌ Error de conexión SQL: " + e.getMessage());
        }
    }
}
