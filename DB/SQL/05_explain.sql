-- ==========================================
-- EXPLAIN DE CONSULTAS - ANÁLISIS DE PERFORMANCE
-- ==========================================

USE db_producto;

-- Análisis de Consulta 1: JOIN productos-códigos
EXPLAIN 
SELECT 
    p.id AS producto_id,
    p.nombre,
    p.categoria,
    p.precio,
    cb.tipo AS tipo_codigo,
    cb.valor AS codigo_barras
FROM producto p
INNER JOIN codigo_barras cb ON p.id = cb.producto_id
WHERE p.eliminado = FALSE
ORDER BY p.categoria, p.precio DESC;

-- Análisis de Consulta 3: GROUP BY con HAVING
EXPLAIN 
SELECT 
    categoria,
    COUNT(*) AS total_productos,
    ROUND(AVG(precio), 2) AS precio_promedio
FROM producto
WHERE eliminado = FALSE
GROUP BY categoria
HAVING AVG(precio) > 300;

-- Análisis de Consulta 4: Subconsulta con NOT EXISTS
EXPLAIN 
SELECT 
    id,
    nombre,
    categoria,
    precio
FROM producto p
WHERE eliminado = FALSE
AND NOT EXISTS (
    SELECT 1 
    FROM codigo_barras cb 
    WHERE cb.producto_id = p.id
);

-- Verificar uso de índices
SELECT 
    TABLE_NAME,
    INDEX_NAME,
    SEQ_IN_INDEX,
    COLUMN_NAME
FROM information_schema.STATISTICS 
WHERE TABLE_SCHEMA = 'db_producto'
ORDER BY TABLE_NAME, INDEX_NAME, SEQ_IN_INDEX;