-- ==========================================
-- VISTAS PARA REPORTES - SEGURIDAD Y SIMPLIFICACIÓN
-- ==========================================

USE db_producto;

-- Vista para inventario completo
CREATE OR REPLACE VIEW vista_inventario_completo AS
SELECT 
    p.id,
    p.nombre,
    p.marca,
    p.categoria,
    p.precio,
    p.peso,
    cb.tipo AS tipo_codigo,
    cb.valor AS codigo_barras,
    cb.fecha_asignacion,
    CASE 
        WHEN p.precio > 500 THEN 'ALTO'
        WHEN p.precio > 100 THEN 'MEDIO'
        ELSE 'BAJO'
    END AS segmento_precio
FROM producto p
LEFT JOIN codigo_barras cb ON p.id = cb.producto_id
WHERE p.eliminado = FALSE;

-- Vista para reportes de categorías
CREATE OR REPLACE VIEW vista_estadisticas_categorias AS
SELECT 
    categoria,
    COUNT(*) AS total_productos,
    ROUND(AVG(precio), 2) AS precio_promedio,
    ROUND(MAX(precio), 2) AS precio_maximo,
    ROUND(MIN(precio), 2) AS precio_minimo,
    ROUND(SUM(precio), 2) AS valor_total_inventario
FROM producto
WHERE eliminado = FALSE
GROUP BY categoria;

-- Vista segura para información de usuarios
CREATE OR REPLACE VIEW vista_usuarios_publica AS
SELECT 
    u.id,
    u.username,
    u.email,
    r.nombre AS rol,
    u.activo,
    DATE(u.fecha_registro) AS fecha_registro
FROM usuario u
INNER JOIN rol r ON u.rol_id = r.id
WHERE u.eliminado = FALSE;

-- Vista para productos públicos (oculta información sensible)
CREATE OR REPLACE VIEW vista_productos_publica AS
SELECT 
    p.id,
    p.nombre,
    p.marca,
    p.categoria,
    p.precio,
    cb.tipo AS tipo_codigo,
    cb.valor AS codigo_barras
FROM producto p
LEFT JOIN codigo_barras cb ON p.id = cb.producto_id
WHERE p.eliminado = FALSE AND (cb.eliminado = FALSE OR cb.eliminado IS NULL);

-- Verificar vistas creadas
SELECT TABLE_NAME 
FROM information_schema.VIEWS 
WHERE TABLE_SCHEMA = 'db_producto';