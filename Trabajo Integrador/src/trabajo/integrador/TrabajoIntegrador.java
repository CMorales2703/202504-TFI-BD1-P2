package trabajo.integrador;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public class TrabajoIntegrador {
    public static void main(String[] args) {

        try (Connection conn = DatabaseConfiguration.getConnection()) {

            MysqlProductoDAO productoDAO = new MysqlProductoDAO(conn); // Objeto DAO
            MysqlCodigoBarrasDAO codigoBarrasDAO = new MysqlCodigoBarrasDAO(conn);
            MysqlRolDAO rolDAO = new MysqlRolDAO(conn);
            MysqlUsuarioDAO usuarioDAO = new MysqlUsuarioDAO(conn);

//            // CREATE
//            Producto nuevo = new Producto(0,"Producto nuevo", 15000.0);
//            productoDAO.crear(nuevo);
//
             // Mostrar todos
//            System.out.println(" Lista de Productos:");
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


//              ---CodigoBarras---
             // Mostrar todos
//           System.out.println(" Lista de Codigos de Barras:");
//           System.out.println("======================");
//           List<CodigoBarras> codigos = codigoBarrasDAO.leerTodos();
//           for (CodigoBarras c : codigos) {
//               System.out.println(c.getId() + " | " + c.getValor()+ " | $" + c.getTipo());
//           }

            // Buscar por ID 
//            CodigoBarras encontrado = codigoBarrasDAO.leerPorId(10);
//            if (encontrado != null) {
//                System.out.println("Codigo encontrado: " 
//                        + "ID " + encontrado.getId()
//                        + " Tipo: " + encontrado.getTipo()
//                        + " Valor: " +  encontrado.getValor()
//                        + " Fecha Asignacion " + encontrado.getFechaAsignacion()
//                     
//                );
//            } else {
//                System.out.println("Producto no encontrado.");
//            }

               // UPDATE
            // Primero leer el código de barras existente para mantener su producto_id
//            CodigoBarras existente = codigoBarrasDAO.leerPorId(1);
//            if (existente != null) {
//                // Actualizar solo los campos necesarios, manteniendo el producto_id actual
//                CodigoBarras c = new CodigoBarras(
//                    existente.getId(),                           // ID del código de barras a actualizar
//                    existente.getProductoId(),                   // Mantener el producto_id actual (evita duplicados)
//                    tipoCodigoBarras.EAN13.toString(),           // tipo actualizado
//                    "1234567890123",                             // nuevo valor del código de barras
//                    Date.valueOf("2025-04-11"),                  // fechaAsignacion actualizada
//                    "Generado por CRUD despues de actualizar"    // observaciones actualizadas
//                );
//                codigoBarrasDAO.actualizar(c);
//            } else {
//                System.out.println("❌ No se encontró código de barras con ID 1 para actualizar.");
//            }
            

              // DELETE
//              codigoBarrasDAO.eliminar(1);

//              ---Rol---
            // CREATE
//            Rol nuevoRol = new Rol("Administrador", "Rol con permisos de administrador");
//            rolDAO.crear(nuevoRol);
//
            // Mostrar todos
//            System.out.println(" Lista de Roles:");
//            System.out.println("======================");
//            List<Rol> roles = rolDAO.leerTodos();
//            for (Rol r : roles) {
//                System.out.println(r.getId() + "  " + r.getNombre() + " | " + r.getDescripcion());
//            }

            // Buscar por ID (ejemplo)
//            Rol rolBuscado = rolDAO.leerPorId(1);
//            if (rolBuscado != null) {
//                System.out.println("Rol encontrado: " 
//                        + "ID " + rolBuscado.getId()
//                        + " Nombre: " + rolBuscado.getNombre()
//                        + " Descripción: " + rolBuscado.getDescripcion()
//                );
//            } else {
//                System.out.println("Rol no encontrado.");
//            }
//
            // UPDATE
//            Rol rolActualizado = new Rol(1, "Usuario", "Rol de usuario estándar");
//            rolDAO.actualizar(rolActualizado);
//
            // DELETE
//            rolDAO.eliminar(1);

//              ---Usuario---

            // CREATE
//            Usuario nuevoUsuario = new Usuario("rama_mor", "rama@example.com", "hash123", 1);
//            usuarioDAO.crear(nuevoUsuario);
//
            // Mostrar todos
//            System.out.println(" Lista de Usuarios:");
//            System.out.println("======================");
//            List<Usuario> usuarios = usuarioDAO.leerTodos();
//            for (Usuario u : usuarios) {
//                System.out.println(u.getId() + " | " + u.getUsername() + " | " + u.getEmail() + " | Rol ID: " + u.getRolId());
//            }

            // Buscar por ID
//            Usuario usuarioBuscado = usuarioDAO.leerPorId(3);
//            if (usuarioBuscado != null) {
//                System.out.println("Usuario encontrado: " 
//                        + "ID " + usuarioBuscado.getId()
//                        + " Username: " + usuarioBuscado.getUsername()
//                        + " Email: " + usuarioBuscado.getEmail()
//                        + " Rol ID: " + usuarioBuscado.getRolId()
//                        + " Activo: " + usuarioBuscado.getActivo()
//                );
//            } else {
//                System.out.println("Usuario no encontrado.");
//            }
//
            // UPDATE
//            Usuario usuarioExistente = usuarioDAO.leerPorId(1);
//            if (usuarioExistente != null) {
//                // Actualizar manteniendo el mismo username y email para evitar duplicados
//                Usuario usuarioActualizado = new Usuario(
//                    usuarioExistente.getId(),
//                    usuarioExistente.isEliminado(),
//                    usuarioExistente.getUsername(),  // Mantener username (evita duplicados)
//                    usuarioExistente.getEmail(),     // Mantener email (evita duplicados)
//                    "nuevo_hash_password",          // nueva contraseña
//                    2,                               // nuevo rol_id
//                    true,                            // activo
//                    usuarioExistente.getFechaRegistro()
//                );
//                usuarioDAO.actualizar(usuarioActualizado);
//            } else {
//                System.out.println("No se encontró usuario con ID 1 para actualizar.");
//            }
//
            // DELETE
            usuarioDAO.eliminar(3);
           
           
             
//            
        } catch (SQLException e) {
            System.err.println("❌ Error de conexión SQL: " + e.getMessage());
        }
    }
}
