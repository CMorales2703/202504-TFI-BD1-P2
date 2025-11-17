-- ==========================================
-- CREACIÓN DE ÍNDICES - OPTIMIZACIÓN
-- ==========================================

USE db_producto;

-- Índices para tabla producto
CREATE INDEX idx_producto_categoria ON producto(categoria);
CREATE INDEX idx_producto_precio ON producto(precio);
CREATE INDEX idx_producto_eliminado ON producto(eliminado);
CREATE INDEX idx_producto_marca ON producto(marca);

-- Índices para tabla codigo_barras
CREATE INDEX idx_codigo_barras_valor ON codigo_barras(valor);
CREATE INDEX idx_codigo_barras_eliminado ON codigo_barras(eliminado);
CREATE INDEX idx_codigo_barras_tipo ON codigo_barras(tipo);

-- Índices para tabla usuario
CREATE INDEX idx_usuario_username ON usuario(username);
CREATE INDEX idx_usuario_email ON usuario(email);
CREATE INDEX idx_usuario_rol_id ON usuario(rol_id);
CREATE INDEX idx_usuario_activo ON usuario(activo);

-- Índices para tablas de histórico
CREATE INDEX idx_historico_precios_producto ON historico_precios(producto_id);
CREATE INDEX idx_historico_precios_fecha ON historico_precios(fecha_cambio);
CREATE INDEX idx_historico_categorias_producto ON historico_categorias(producto_id);
CREATE INDEX idx_auditoria_operaciones_fecha ON auditoria_operaciones_masivas(fecha_operacion);

-- Verificar índices creados
SELECT 
    TABLE_NAME,
    INDEX_NAME,
    COLUMN_NAME
FROM information_schema.STATISTICS 
WHERE TABLE_SCHEMA = 'db_producto'
ORDER BY TABLE_NAME, INDEX_NAME;