package trabajo.integrador;

import java.sql.Connection;
import java.sql.Date;
import java.util.List;
import java.util.Scanner;

import trabajo.integrador.dao.MysqlCodigoBarrasDAO;
import trabajo.integrador.dao.MysqlProductoDAO;
import trabajo.integrador.dao.MysqlRolDAO;
import trabajo.integrador.dao.MysqlUsuarioDAO;
import trabajo.integrador.dao.tipoCodigoBarras;
import trabajo.integrador.entities.CodigoBarras;
import trabajo.integrador.entities.Producto;
import trabajo.integrador.entities.Rol;
import trabajo.integrador.entities.Usuario;

public class AppMenu {
	private final Scanner scanner = new Scanner(System.in);
	private final MysqlProductoDAO productoDAO;
	private final MysqlCodigoBarrasDAO codigoBarrasDAO;
	private final MysqlUsuarioDAO usuarioDAO;
	private final MysqlRolDAO rolDAO;

	public AppMenu(Connection conn, MysqlProductoDAO productoDAO, MysqlCodigoBarrasDAO codigoBarrasDAO, MysqlUsuarioDAO usuarioDAO, MysqlRolDAO rolDAO) {
		this.productoDAO = productoDAO;
		this.codigoBarrasDAO = codigoBarrasDAO;
		this.usuarioDAO = usuarioDAO;
		this.rolDAO = rolDAO;
	}

	public void start() {
		int option = -1;
		do {
			System.out.println("\n===== MENU PRINCIPAL CRUD =====");
			System.out.println("1) Productos");
			System.out.println("2) Códigos de Barras");
			System.out.println("3) Usuarios");
			System.out.println("4) Roles");
			System.out.println("0) Salir");
			System.out.print("Opción: ");
			option = readInt();
			switch (option) {
				case 1: menuProducto(); break;
				case 2: menuCodigoBarras(); break;
				case 3: menuUsuario(); break;
				case 4: menuRol(); break;
				case 0: System.out.println("Saliendo..."); break;
				default: System.out.println("Opción inválida");
			}
		} while (option != 0);
	}

	private void menuProducto() {
		int op;
		do {
			System.out.println("\n--- Productos ---");
			System.out.println("1) Crear");
			System.out.println("2) Listar");
			System.out.println("3) Buscar por ID");
			System.out.println("4) Actualizar");
			System.out.println("5) Eliminar");
			System.out.println("0) Volver");
			System.out.print("Opción: ");
			op = readInt();
			switch (op) {
				case 1: crearProducto(); break;
				case 2: listarProductos(); break;
				case 3: buscarProductoPorId(); break;
				case 4: actualizarProducto(); break;
				case 5: eliminarProducto(); break;
				case 0: break;
				default: System.out.println("Opción inválida");
			}
		} while (op != 0);
	}

	private void crearProducto() {
		System.out.print("Nombre: ");
		String nombre = readLine();
		System.out.print("Marca: ");
		String marca = readLine();
		System.out.print("Categoria: ");
		String categoria = readLine();
		System.out.print("Precio: ");
		double precio = readDouble();
		System.out.print("Peso (gramos): ");
		double peso = readDouble();

		Producto p = new Producto(0, nombre, precio);
		p.setMarca(marca);
		p.setCategoria(categoria);
		p.setPeso(peso);
		productoDAO.crear(p);
	}

	private void listarProductos() {
		List<Producto> lista = productoDAO.leerTodos();
		for (Producto p : lista) {
			System.out.println(p.getId() + " | " + p.getNombre() + " | $" + p.getPrecio());
		}
	}

	private void buscarProductoPorId() {
		System.out.print("ID: ");
		int id = readInt();
		Producto p = productoDAO.leerPorId(id);
		if (p != null) {
			System.out.println(p);
		} else {
			System.out.println("No encontrado");
		}
	}

	private void actualizarProducto() {
		System.out.print("ID a actualizar: ");
		int id = readInt();
		Producto p = productoDAO.leerPorId(id);
		if (p == null) {
			System.out.println("No existe el producto");
			return;
		}
		System.out.print("Nuevo nombre (actual: " + p.getNombre() + "): ");
		String nombre = readLine();
		System.out.print("Nueva marca (actual: " + p.getMarca() + "): ");
		String marca = readLine();
		System.out.print("Nueva categoria (actual: " + p.getCategoria() + "): ");
		String categoria = readLine();
		System.out.print("Nuevo precio (actual: " + p.getPrecio() + "): ");
		double precio = readDouble();
		System.out.print("Nuevo peso (g) (actual: " + p.getPeso() + "): ");
		double peso = readDouble();

		p.setNombre(nombre);
		p.setMarca(marca);
		p.setCategoria(categoria);
		p.setPrecio(precio);
		p.setPeso(peso);
		productoDAO.actualizar(p);
	}

	private void eliminarProducto() {
		System.out.print("ID a eliminar: ");
		int id = readInt();
		productoDAO.eliminar(id);
	}

	private void menuCodigoBarras() {
		int op;
		do {
			System.out.println("\n--- Códigos de Barras ---");
			System.out.println("1) Crear");
			System.out.println("2) Listar");
			System.out.println("3) Buscar por ID");
			System.out.println("4) Actualizar");
			System.out.println("5) Eliminar");
			System.out.println("0) Volver");
			System.out.print("Opción: ");
			op = readInt();
			switch (op) {
				case 1: crearCodigoBarras(); break;
				case 2: listarCodigos(); break;
				case 3: buscarCodigoPorId(); break;
				case 4: actualizarCodigo(); break;
				case 5: eliminarCodigo(); break;
				case 0: break;
				default: System.out.println("Opción inválida");
			}
		} while (op != 0);
	}

	private void crearCodigoBarras() {
		System.out.print("Producto ID: ");
		int productoId = readInt();
		System.out.print("Tipo (EAN13/EAN8/UPC): ");
		String tipoStr = readLine().toUpperCase();
		tipoCodigoBarras tipo = tipoCodigoBarras.valueOf(tipoStr);
		System.out.print("Valor (numérico): ");
		String valor = readLine();
		System.out.print("Fecha asignación (YYYY-MM-DD): ");
		Date fecha = Date.valueOf(readLine());
		System.out.print("Observaciones: ");
		String obs = readLine();

		CodigoBarras c = new CodigoBarras(productoId, tipo, valor, fecha, obs);
		codigoBarrasDAO.crear(c);
	}

	private void listarCodigos() {
		List<CodigoBarras> lista = codigoBarrasDAO.leerTodos();
		for (CodigoBarras c : lista) {
			System.out.println(c.getId() + " | " + c.getTipo() + " | " + c.getValor());
		}
	}

	private void buscarCodigoPorId() {
		System.out.print("ID: ");
		int id = readInt();
		CodigoBarras c = codigoBarrasDAO.leerPorId(id);
		if (c != null) {
			System.out.println(c);
		} else {
			System.out.println("No encontrado");
		}
	}

	private void actualizarCodigo() {
		System.out.print("ID a actualizar: ");
		int id = readInt();
		CodigoBarras existente = codigoBarrasDAO.leerPorId(id);
		if (existente == null) {
			System.out.println("No existe el código de barras");
			return;
		}
		System.out.print("Producto ID (actual: " + existente.getProductoId() + "): ");
		int productoId = readInt();
		System.out.print("Tipo (EAN13/EAN8/UPC) (actual: " + existente.getTipo() + "): ");
		tipoCodigoBarras tipo = tipoCodigoBarras.valueOf(readLine().toUpperCase());
		System.out.print("Valor (actual: " + existente.getValor() + "): ");
		String valor = readLine();
		System.out.print("Fecha asignación YYYY-MM-DD (actual: " + existente.getFechaAsignacion() + "): ");
		Date fecha = Date.valueOf(readLine());
		System.out.print("Observaciones (actual: " + existente.getObservaciones() + "): ");
		String obs = readLine();

		CodigoBarras actualizado = new CodigoBarras(existente.getId(), productoId, tipo.toString(), valor, fecha, obs);
		codigoBarrasDAO.actualizar(actualizado);
	}

	private void eliminarCodigo() {
		System.out.print("ID a eliminar: ");
		int id = readInt();
		codigoBarrasDAO.eliminar(id);
	}

	private void menuUsuario() {
		int op;
		do {
			System.out.println("\n--- Usuarios ---");
			System.out.println("1) Crear");
			System.out.println("2) Listar");
			System.out.println("3) Buscar por ID");
			System.out.println("4) Actualizar");
			System.out.println("5) Eliminar");
			System.out.println("0) Volver");
			System.out.print("Opción: ");
			op = readInt();
			switch (op) {
				case 1: crearUsuario(); break;
				case 2: listarUsuarios(); break;
				case 3: buscarUsuarioPorId(); break;
				case 4: actualizarUsuario(); break;
				case 5: eliminarUsuario(); break;
				case 0: break;
				default: System.out.println("Opción inválida");
			}
		} while (op != 0);
	}

	private void crearUsuario() {
		System.out.print("Username: ");
		String username = readLine();
		System.out.print("Email: ");
		String email = readLine();
		System.out.print("Password (texto plano, se guardará hasheado): ");
		String password = readLine();
		System.out.print("Rol ID: ");
		int rolId = readInt();

		Usuario u = new Usuario(username, email, "", rolId);
		u.cambiarPassword(password);
		usuarioDAO.crear(u);
	}

	private void listarUsuarios() {
		List<Usuario> lista = usuarioDAO.leerTodos();
		for (Usuario u : lista) {
			System.out.println(u.getId() + " | " + u.getUsername() + " | " + u.getEmail() + " | Rol:" + u.getRolId());
		}
	}

	private void buscarUsuarioPorId() {
		System.out.print("ID: ");
		int id = readInt();
		Usuario u = usuarioDAO.leerPorId(id);
		if (u != null) {
			System.out.println(u);
		} else {
			System.out.println("No encontrado");
		}
	}

	private void actualizarUsuario() {
		System.out.print("ID a actualizar: ");
		int id = readInt();
		Usuario u = usuarioDAO.leerPorId(id);
		if (u == null) {
			System.out.println("No existe el usuario");
			return;
		}
		System.out.print("Nuevo username (actual: " + u.getUsername() + "): ");
		String username = readLine();
		System.out.print("Nuevo email (actual: " + u.getEmail() + "): ");
		String email = readLine();
		System.out.print("Nuevo rolId (actual: " + u.getRolId() + "): ");
		int rolId = readInt();
		System.out.print("Cambiar password? (s/n): ");
		String cambiar = readLine();
		String passwordHash = u.getPasswordHash();
		if ("s".equalsIgnoreCase(cambiar)) {
			System.out.print("Nueva password: ");
			String pass = readLine();
			u.cambiarPassword(pass);
			passwordHash = u.getPasswordHash();
		}

		Usuario actualizado = new Usuario(u.getId(), u.isEliminado(), username, email, passwordHash, rolId, u.getActivo(), u.getFechaRegistro());
		usuarioDAO.actualizar(actualizado);
	}

	private void eliminarUsuario() {
		System.out.print("ID a eliminar: ");
		int id = readInt();
		usuarioDAO.eliminar(id);
	}

	private void menuRol() {
		int op;
		do {
			System.out.println("\n--- Roles ---");
			System.out.println("1) Crear");
			System.out.println("2) Listar");
			System.out.println("3) Buscar por ID");
			System.out.println("4) Actualizar");
			System.out.println("5) Eliminar");
			System.out.println("0) Volver");
			System.out.print("Opción: ");
			op = readInt();
			switch (op) {
				case 1: crearRol(); break;
				case 2: listarRoles(); break;
				case 3: buscarRolPorId(); break;
				case 4: actualizarRol(); break;
				case 5: eliminarRol(); break;
				case 0: break;
				default: System.out.println("Opción inválida");
			}
		} while (op != 0);
	}

	private void crearRol() {
		System.out.print("Nombre: ");
		String nombre = readLine();
		System.out.print("Descripción: ");
		String desc = readLine();
		rolDAO.crear(new Rol(nombre, desc));
	}

	private void listarRoles() {
		List<Rol> lista = rolDAO.leerTodos();
		for (Rol r : lista) {
			System.out.println(r.getId() + " | " + r.getNombre() + " | " + r.getDescripcion());
		}
	}

	private void buscarRolPorId() {
		System.out.print("ID: ");
		int id = readInt();
		Rol r = rolDAO.leerPorId(id);
		if (r != null) {
			System.out.println(r);
		} else {
			System.out.println("No encontrado");
		}
	}

	private void actualizarRol() {
		System.out.print("ID a actualizar: ");
		int id = readInt();
		Rol r = rolDAO.leerPorId(id);
		if (r == null) {
			System.out.println("No existe el rol");
			return;
		}
		System.out.print("Nuevo nombre (actual: " + r.getNombre() + "): ");
		String nombre = readLine();
		System.out.print("Nueva descripción (actual: " + r.getDescripcion() + "): ");
		String desc = readLine();
		rolDAO.actualizar(new Rol(id, nombre, desc));
	}

	private void eliminarRol() {
		System.out.print("ID a eliminar: ");
		int id = readInt();
		rolDAO.eliminar(id);
	}

	private int readInt() {
		while (true) {
			try {
				String s = scanner.nextLine();
				return Integer.parseInt(s.trim());
			} catch (Exception e) {
				System.out.print("Ingrese un número entero válido: ");
			}
		}
	}


	private double readDouble() {
		while (true) {
			try {
				String s = scanner.nextLine();
				return Double.parseDouble(s.trim());
			} catch (Exception e) {
				System.out.print("Ingrese un número decimal válido: ");
			}
		}
	}

	private String readLine() {
		String s = scanner.nextLine();
		return s != null ? s.trim() : "";
	}
}
