-- ==========================================
-- DATOS CATALOGO - CONFIGURACIÓN INICIAL
-- ==========================================

USE db_producto;

-- Insertar roles del sistema
INSERT INTO rol (nombre, descripcion) VALUES 
('ADMIN', 'Administrador del sistema con todos los permisos'),
('VENDEDOR', 'Usuario con permisos de venta y consulta'),
('CONSULTA', 'Usuario con permisos de solo lectura'),
('AUDITOR', 'Usuario con permisos de auditoría'),
('SUPERVISOR', 'Usuario con permisos de supervisión');

-- Insertar usuarios iniciales del sistema
INSERT INTO usuario (username, email, password_hash, rol_id, activo) VALUES 
('admin', 'admin@empresa.com', 'hash_seguro_admin', 1, TRUE),
('vendedor1', 'vendedor1@empresa.com', 'hash_seguro_vendedor', 2, TRUE),
('consulta1', 'consulta1@empresa.com', 'hash_seguro_consulta', 3, TRUE),
('auditor1', 'auditor1@empresa.com', 'hash_seguro_auditor', 4, TRUE),
('supervisor1', 'supervisor1@empresa.com', 'hash_seguro_supervisor', 5, TRUE);

-- Insertar categorías de productos base
-- Nota: Las categorías se generarán dinámicamente en la carga masiva
SELECT 'Catálogos iniciales insertados correctamente' AS estado;