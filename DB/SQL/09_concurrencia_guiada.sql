-- ==========================================
-- CONCURRENCIA GUIADA - DEADLOCKS Y NIVELES DE AISLAMIENTO
-- ==========================================

USE db_producto;

-- Script 1: Simulación de Deadlock (ejecutar en dos sesiones)

-- SESIÓN 1 - Ejecutar estos comandos:
START TRANSACTION;
UPDATE producto SET precio = precio + 1.1 WHERE id = 1;
-- Esperar unos segundos...
UPDATE producto SET precio = precio + 1.05 WHERE id = 2;
COMMIT;

-- SESIÓN 2 - Ejecutar simultáneamente:
START TRANSACTION;
UPDATE producto SET precio = precio + 1.15 WHERE id = 2;
-- Esperar unos segundos...
UPDATE producto SET precio = precio + 1.08 WHERE id = 1;
COMMIT;

-- Script 2: Comparación de niveles de aislamiento

-- Prueba con READ COMMITTED
SET SESSION TRANSACTION ISOLATION LEVEL READ COMMITTED;
START TRANSACTION;
SELECT precio FROM producto WHERE id = 10; -- Primera lectura
-- En otra sesión: UPDATE producto SET precio = 200 WHERE id = 10; COMMIT;
SELECT precio FROM producto WHERE id = 10; -- Segunda lectura (puede ser diferente)
COMMIT;

-- Prueba con REPEATABLE READ  
SET SESSION TRANSACTION ISOLATION LEVEL REPEATABLE READ;
START TRANSACTION;
SELECT precio FROM producto WHERE id = 15; -- Primera lectura
-- En otra sesión: UPDATE producto SET precio = 250 WHERE id = 15; COMMIT;
SELECT precio FROM producto WHERE id = 15; -- Segunda lectura (será la misma)
COMMIT;

-- Monitoreo de deadlocks
SHOW ENGINE INNODB STATUS;

-- Consulta de transacciones bloqueadas
SELECT 
    r.trx_id waiting_trx_id,
    r.trx_mysql_thread_id waiting_thread,
    b.trx_id blocking_trx_id, 
    b.trx_mysql_thread_id blocking_thread
FROM information_schema.innodb_lock_waits w
INNER JOIN information_schema.innodb_trx b ON b.trx_id = w.blocking_trx_id
INNER JOIN information_schema.innodb_trx r ON r.trx_id = w.requesting_trx_id;

-- Ver nivel de aislamiento actual
SELECT @@tx_isolation;