-- ==========================================
-- CONSULTAS AVANZADAS - REPORTES Y ANÁLISIS
-- ==========================================

USE db_producto;

-- CONSULTA 1: Productos con información completa (JOIN)
SELECT 
    p.id AS producto_id,
    p.nombre,
    p.marca,
    p.categoria,
    p.precio,
    cb.tipo AS tipo_codigo,
    cb.valor AS codigo_barras,
    cb.fecha_asignacion
FROM producto p
INNER JOIN codigo_barras cb ON p.id = cb.producto_id
WHERE p.eliminado = FALSE
ORDER BY p.categoria, p.precio DESC;

-- CONSULTA 2: Usuarios con información de roles (JOIN Múltiple)
SELECT 
    u.username,
    u.email,
    r.nombre AS rol,
    u.activo,
    DATE(u.fecha_registro) AS fecha_registro
FROM usuario u
INNER JOIN rol r ON u.rol_id = r.id
WHERE u.eliminado = FALSE
ORDER BY r.nombre, u.username;

-- CONSULTA 3: Análisis de precios por categoría (GROUP BY + HAVING)
SELECT 
    categoria,
    COUNT(*) AS total_productos,
    ROUND(AVG(precio), 2) AS precio_promedio,
    ROUND(MAX(precio), 2) AS precio_maximo,
    ROUND(MIN(precio), 2) AS precio_minimo,
    ROUND(SUM(precio), 2) AS valor_total_inventario
FROM producto
WHERE eliminado = FALSE
GROUP BY categoria
HAVING AVG(precio) > 300
ORDER BY precio_promedio DESC;

-- CONSULTA 4: Productos sin código de barras (SUBCONSULTA)
SELECT 
    id,
    nombre,
    marca,
    categoria,
    precio
FROM producto p
WHERE eliminado = FALSE
AND NOT EXISTS (
    SELECT 1 
    FROM codigo_barras cb 
    WHERE cb.producto_id = p.id
)
ORDER BY categoria, nombre;

-- CONSULTA 5: Estadísticas por marca y categoría
SELECT 
    marca,
    categoria,
    COUNT(*) AS total_productos,
    ROUND(AVG(precio), 2) AS promedio_precio,
    ROUND(SUM(precio), 2) AS valor_total
FROM producto
WHERE eliminado = FALSE AND marca IS NOT NULL
GROUP BY marca, categoria
HAVING COUNT(*) > 10
ORDER BY total_productos DESC;