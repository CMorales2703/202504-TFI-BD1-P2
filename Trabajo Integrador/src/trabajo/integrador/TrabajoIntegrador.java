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

			// Lanzar menú interactivo
			AppMenu menu = new AppMenu(conn, productoDAO, codigoBarrasDAO, usuarioDAO, rolDAO);
			menu.start();
		} catch (SQLException e) {
			System.err.println("❌ Error de conexión SQL: " + e.getMessage());
		}
	}
}
