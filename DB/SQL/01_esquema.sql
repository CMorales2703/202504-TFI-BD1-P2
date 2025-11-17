-- ==========================================
-- ESQUEMA DE BASE DE DATOS - CONSTRAINTS
-- Trabajo Final Integrador - Bases de Datos I
-- ==========================================

CREATE DATABASE IF NOT EXISTS db_producto;
USE db_producto;

-- Tabla: rol
CREATE TABLE rol (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(50) UNIQUE NOT NULL,
    descripcion VARCHAR(255)
);

-- Tabla: usuario
CREATE TABLE usuario (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    eliminado BOOLEAN DEFAULT FALSE,
    username VARCHAR(30) UNIQUE NOT NULL,
    email VARCHAR(120) UNIQUE NOT NULL,
    password_hash VARCHAR(255) NOT NULL,
    rol_id BIGINT NOT NULL,
    activo BOOLEAN DEFAULT TRUE,
    fecha_registro TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (rol_id) REFERENCES rol(id)
);

-- Tabla: producto
CREATE TABLE producto (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    eliminado BOOLEAN DEFAULT FALSE,
    nombre VARCHAR(120) NOT NULL,
    marca VARCHAR(80),
    categoria VARCHAR(80),
    precio DECIMAL(10,2) NOT NULL CHECK (precio > 0),
    peso DECIMAL(10,3),
    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Tabla: codigo_barras (Relación 1:1 con producto)
CREATE TABLE codigo_barras (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    eliminado BOOLEAN DEFAULT FALSE,
    producto_id BIGINT UNIQUE NOT NULL,
    tipo ENUM('EAN13', 'EAN8', 'UPC') NOT NULL,
    valor VARCHAR(20) UNIQUE NOT NULL,
    fecha_asignacion DATE NOT NULL,
    observaciones VARCHAR(255),
    FOREIGN KEY (producto_id) REFERENCES producto(id) ON DELETE CASCADE
);

-- Tablas de soporte para transacciones
CREATE TABLE historico_precios (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    producto_id BIGINT NOT NULL,
    precio_anterior DECIMAL(10,2) NOT NULL,
    precio_nuevo DECIMAL(10,2) NOT NULL,
    usuario_id BIGINT,
    fecha_cambio TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (producto_id) REFERENCES producto(id)
);

CREATE TABLE historico_categorias (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    producto_id BIGINT NOT NULL,
    categoria_anterior VARCHAR(80) NOT NULL,
    categoria_nueva VARCHAR(80) NOT NULL,
    usuario_id BIGINT,
    fecha_cambio TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (producto_id) REFERENCES producto(id)
);

CREATE TABLE auditoria_operaciones_masivas (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    categoria VARCHAR(80) NOT NULL,
    porcentaje_aumento DECIMAL(5,2) NOT NULL,
    productos_afectados INT NOT NULL,
    usuario_id BIGINT,
    fecha_operacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);