-- ==========================================
-- CONFIGURACIÓN DE SEGURIDAD - USUARIOS Y PERMISOS
-- ==========================================

USE db_producto;

-- Eliminar usuarios existentes si es necesario
DROP USER IF EXISTS 'usuario_consulta'@'localhost';
DROP USER IF EXISTS 'app_vendedor'@'localhost';
DROP USER IF EXISTS 'admin_inventario'@'localhost';

-- Crear usuarios especializados
CREATE USER 'usuario_consulta'@'localhost' IDENTIFIED BY 'Consulta123!';
CREATE USER 'app_vendedor'@'localhost' IDENTIFIED BY 'Vendedor456!';
CREATE USER 'admin_inventario'@'localhost' IDENTIFIED BY 'Admin789!';

-- Asignar permisos mínimos necesarios

-- Usuario de solo consulta (solo lectura en vistas)
GRANT SELECT ON db_producto.vista_inventario_completo TO 'usuario_consulta'@'localhost';
GRANT SELECT ON db_producto.vista_estadisticas_categorias TO 'usuario_consulta'@'localhost';
GRANT SELECT ON db_producto.vista_productos_publica TO 'usuario_consulta'@'localhost';

-- Usuario de aplicación (operaciones básicas)
GRANT SELECT ON db_producto.vista_productos_publica TO 'app_vendedor'@'localhost';
GRANT SELECT, INSERT, UPDATE ON db_producto.vista_inventario_completo TO 'app_vendedor'@'localhost';

-- Usuario administrativo (gestión de inventario)
GRANT SELECT, INSERT, UPDATE ON db_producto.producto TO 'admin_inventario'@'localhost';
GRANT SELECT, INSERT, UPDATE ON db_producto.codigo_barras TO 'admin_inventario'@'localhost';
GRANT SELECT ON db_producto.vista_usuarios_publica TO 'admin_inventario'@'localhost';

-- Aplicar cambios de permisos
FLUSH PRIVILEGES;

-- Verificar permisos asignados
SHOW GRANTS FOR 'usuario_consulta'@'localhost';
SHOW GRANTS FOR 'app_vendedor'@'localhost';
SHOW GRANTS FOR 'admin_inventario'@'localhost';