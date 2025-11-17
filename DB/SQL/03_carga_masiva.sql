-- ==========================================
-- CARGA MASIVA DE DATOS - 10,000+ REGISTROS
-- ==========================================

USE db_producto;

-- Generar 10,000 productos
INSERT INTO producto (nombre, marca, categoria, precio, peso)
SELECT 
    CONCAT('Producto ', numbers.seq),
    ELT(1 + FLOOR(RAND() * 8), 'Samsung', 'LG', 'Sony', 'Philips', 'Panasonic', 'Xiaomi', 'Apple', 'Huawei'),
    ELT(1 + FLOOR(RAND() * 6), 'Electrónicos', 'Hogar', 'Ropa', 'Deportes', 'Juguetes', 'Libros'),
    ROUND(10.50 + (RAND() * 989.50), 2),
    ROUND(0.1 + (RAND() * 9.9), 3)
FROM (
    SELECT a.N + b.N * 10 + c.N * 100 + d.N * 1000 AS seq
    FROM 
    (SELECT 0 AS N UNION SELECT 1 UNION SELECT 2 UNION SELECT 3 UNION SELECT 4 UNION SELECT 5 UNION SELECT 6 UNION SELECT 7 UNION SELECT 8 UNION SELECT 9) a,
    (SELECT 0 AS N UNION SELECT 1 UNION SELECT 2 UNION SELECT 3 UNION SELECT 4 UNION SELECT 5 UNION SELECT 6 UNION SELECT 7 UNION SELECT 8 UNION SELECT 9) b,
    (SELECT 0 AS N UNION SELECT 1 UNION SELECT 2 UNION SELECT 3 UNION SELECT 4 UNION SELECT 5 UNION SELECT 6 UNION SELECT 7 UNION SELECT 8 UNION SELECT 9) c,
    (SELECT 0 AS N UNION SELECT 1 UNION SELECT 2 UNION SELECT 3 UNION SELECT 4 UNION SELECT 5 UNION SELECT 6 UNION SELECT 7 UNION SELECT 8 UNION SELECT 9) d
) numbers
WHERE seq BETWEEN 1 AND 10000;

-- Generar códigos de barras para los productos (relación 1:1)
INSERT INTO codigo_barras (producto_id, tipo, valor, fecha_asignacion)
SELECT 
    p.id,
    ELT(1 + FLOOR(RAND() * 3), 'EAN13', 'EAN8', 'UPC'),
    CASE 
        WHEN FLOOR(RAND() * 3) = 0 THEN CONCAT('1234567', LPAD(p.id, 5, '0'))
        WHEN FLOOR(RAND() * 3) = 1 THEN CONCAT('9876543', LPAD(p.id, 5, '0'))
        ELSE CONCAT('4567891', LPAD(p.id, 5, '0'))
    END,
    DATE_SUB(CURRENT_DATE, INTERVAL FLOOR(RAND() * 365) DAY)
FROM producto p
WHERE p.id BETWEEN 1 AND 10000;

-- Verificación de datos generados
SELECT 
    'Productos generados:' AS tipo,
    COUNT(*) AS cantidad 
FROM producto
UNION ALL
SELECT 
    'Códigos de barras generados:',
    COUNT(*) 
FROM codigo_barras
UNION ALL
SELECT 
    'Productos sin código de barras:',
    COUNT(*) 
FROM producto p
LEFT JOIN codigo_barras cb ON p.id = cb.producto_id
WHERE cb.id IS NULL;