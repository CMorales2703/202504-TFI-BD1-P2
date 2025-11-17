-- ==========================================
-- PROCEDIMIENTOS TRANSACCIONALES - OPERACIONES CRÍTICAS
-- ==========================================

USE db_producto;

DELIMITER //

-- Procedimiento transaccional para actualizar precio con histórico
CREATE PROCEDURE sp_transaccion_actualizar_precio(
    IN p_producto_id BIGINT,
    IN p_nuevo_precio DECIMAL(10,2),
    IN p_usuario_id BIGINT,
    OUT p_resultado VARCHAR(300)
)
BEGIN
    DECLARE v_precio_actual DECIMAL(10,2);
    DECLARE v_producto_existe INT DEFAULT 0;
    
    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
        ROLLBACK;
        SET p_resultado = 'ERROR: Fallo en la transacción - cambios revertidos';
    END;
    
    START TRANSACTION;
    
    -- Validar que el producto existe
    SELECT COUNT(*), precio INTO v_producto_existe, v_precio_actual
    FROM producto 
    WHERE id = p_producto_id AND eliminado = FALSE;
    
    IF v_producto_existe = 0 THEN
        SET p_resultado = 'ERROR: Producto no encontrado';
        ROLLBACK;
    ELSEIF p_nuevo_precio <= 0 THEN
        SET p_resultado = 'ERROR: El precio debe ser mayor a 0';
        ROLLBACK;
    ELSE
        -- Registrar en histórico
        INSERT INTO historico_precios (
            producto_id, 
            precio_anterior, 
            precio_nuevo, 
            usuario_id
        ) VALUES (
            p_producto_id,
            v_precio_actual,
            p_nuevo_precio,
            p_usuario_id
        );
        
        -- Actualizar precio
        UPDATE producto 
        SET precio = p_nuevo_precio 
        WHERE id = p_producto_id;
        
        COMMIT;
        SET p_resultado = CONCAT('ÉXITO: Precio actualizado de $', v_precio_actual, ' a $', p_nuevo_precio);
    END IF;
END//

-- Procedimiento para transferir categoría con transacción
CREATE PROCEDURE sp_transaccion_transferir_categoria(
    IN p_producto_id BIGINT,
    IN p_nueva_categoria VARCHAR(80),
    IN p_usuario_id BIGINT,
    OUT p_resultado VARCHAR(300)
)
BEGIN
    DECLARE v_categoria_actual VARCHAR(80);
    DECLARE v_producto_existe INT DEFAULT 0;
    
    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
        ROLLBACK;
        SET p_resultado = 'ERROR: Fallo en la transferencia de categoría';
    END;
    
    START TRANSACTION;
    
    -- Verificar producto y obtener categoría actual
    SELECT COUNT(*), categoria INTO v_producto_existe, v_categoria_actual
    FROM producto 
    WHERE id = p_producto_id AND eliminado = FALSE;
    
    IF v_producto_existe = 0 THEN
        SET p_resultado = 'ERROR: Producto no encontrado';
        ROLLBACK;
    ELSEIF v_categoria_actual = p_nueva_categoria THEN
        SET p_resultado = 'ADVERTENCIA: El producto ya está en esa categoría';
        ROLLBACK;
    ELSE
        -- Registrar cambio en histórico
        INSERT INTO historico_categorias (
            producto_id,
            categoria_anterior,
            categoria_nueva,
            usuario_id
        ) VALUES (
            p_producto_id,
            v_categoria_actual,
            p_nueva_categoria,
            p_usuario_id
        );
        
        -- Actualizar categoría
        UPDATE producto 
        SET categoria = p_nueva_categoria 
        WHERE id = p_producto_id;
        
        COMMIT;
        SET p_resultado = CONCAT('ÉXITO: Producto transferido de "', v_categoria_actual, '" a "', p_nueva_categoria, '"');
    END IF;
END//

DELIMITER ;

-- Ejemplos de uso de transacciones
-- CALL sp_transaccion_actualizar_precio(1, 299.99, 1, @resultado);
-- SELECT @resultado;