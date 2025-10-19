-- ==========================================
-- Base de datos: db_producto
-- ==========================================
CREATE DATABASE IF NOT EXISTS db_producto;

-- ==========================================
-- Tabla: rol
-- ==========================================
CREATE TABLE IF NOT EXISTS db_producto.rol (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(50) UNIQUE NOT NULL,
    descripcion VARCHAR(255)
);

-- ==========================================
-- Tabla: usuario
-- ==========================================
CREATE TABLE IF NOT EXISTS db_producto.usuario (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    eliminado BOOLEAN DEFAULT FALSE,
    username VARCHAR(30) UNIQUE NOT NULL,
    email VARCHAR(120) UNIQUE NOT NULL,
    password_hash VARCHAR(255) NOT NULL,
    rol_id BIGINT NOT NULL,
    activo BOOLEAN DEFAULT TRUE,
    fecha_registro TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (rol_id) REFERENCES db_producto.rol(id)
);

-- ==========================================
-- Tabla: producto
-- ==========================================
CREATE TABLE IF NOT EXISTS db_producto.producto (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    eliminado BOOLEAN DEFAULT FALSE,
    nombre VARCHAR(120) NOT NULL,
    marca VARCHAR(80),
    categoria VARCHAR(80),
    precio DECIMAL(10,2) NOT NULL CHECK (precio > 0),
    peso DECIMAL(10,3),
    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- ==========================================
-- Tabla: codigo_barras
-- ==========================================
CREATE TABLE IF NOT EXISTS db_producto.codigo_barras (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    eliminado BOOLEAN DEFAULT FALSE,
    producto_id BIGINT UNIQUE NOT NULL,  -- UNIQUE garantiza relación 1:1
    tipo ENUM('EAN13', 'EAN8', 'UPC') NOT NULL,
    valor VARCHAR(20) UNIQUE NOT NULL,
    fecha_asignacion DATE NOT NULL,
    observaciones VARCHAR(255),
    FOREIGN KEY (producto_id) REFERENCES db_producto.producto(id) ON DELETE CASCADE
);

-- ==========================================
-- Índices para mejorar performance
-- ==========================================
CREATE INDEX IF NOT EXISTS idx_producto_categoria ON db_producto.producto(categoria);
CREATE INDEX IF NOT EXISTS idx_producto_precio ON db_producto.producto(precio);
CREATE INDEX IF NOT EXISTS idx_codigo_barras_valor ON db_producto.codigo_barras(valor);
CREATE INDEX IF NOT EXISTS idx_usuario_username ON db_producto.usuario(username);
CREATE INDEX IF NOT EXISTS idx_usuario_rol_id ON db_producto.usuario(rol_id);

-- ==========================================
-- Datos básicos para pruebas (ROLES)
-- ==========================================
INSERT IGNORE INTO db_producto.rol (nombre, descripcion) VALUES 
('ADMIN', 'Administrador del sistema'),
('VENDEDOR', 'Usuario con permisos de venta'),
('CONSULTA', 'Usuario solo de consulta');

INSERT IGNORE INTO db_producto.usuario (username, email, password_hash, rol_id, activo) VALUES 
('admin', 'admin@empresa.com', 'hash_seguro_password', 1, TRUE),
('vendedor1', 'vendedor1@empresa.com', 'hash_seguro_password', 2, TRUE);

-- ==========================================
-- Confirmación
-- ==========================================
SELECT 'Su Base de datos "db_producto" fue creada y poblada exitosamente. ¡GG! ' AS estado;
