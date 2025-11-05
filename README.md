# Trabajo Integrador - BD1 y Programación 2
## Integrantes: Morales, Morales, Montes y Rios

Sistema de gestión de productos con códigos de barras, usuarios y roles, implementado en Java con MySQL.

## 📁 Estructura del Proyecto

```
202504-TFI-BD1-P2/
├── DB/                          # Documentación y scripts de base de datos
│   ├── DER/                     # Diagramas Entidad-Relación
│   ├── docs/                    # Documentación de diseño
│   ├── sql/                     # Scripts SQL
│   │   ├── create_tables.sql   # Creación de tablas
│   │   └── insert_data.sql     # Datos iniciales
│   ├── uml/                     # Diagramas UML
│   └── vitacora/                # Bitácora de cambios
│
└── Trabajo Integrador/          # Proyecto Java
    └── src/trabajo/integrador/
        ├── Entidades/
        │   ├── Producto.java
        │   ├── CodigoBarras.java
        │   ├── Usuario.java
        │   └── Rol.java
        │
        ├── DAOs/                 # Capa de acceso a datos
        │   ├── DAO.java          # Interfaz genérica
        │   ├── MysqlProductoDAO.java
        │   ├── MysqlCodigoBarrasDAO.java
        │   ├── MysqlUsuarioDAO.java
        │   └── MysqlRolDAO.java
        │
        ├── Configuración/
        │   └── DatabaseConfiguration.java
        │
        ├── Utilidades/
        │   └── tipoCodigoBarras.java  # Enum (EAN13, EAN8, UPC)
        │
        └── TrabajoIntegrador.java     # Clase principal con ejemplos CRUD
```

## 🏗️ Arquitectura

### Patrón DAO (Data Access Object)
El proyecto implementa el patrón DAO para separar la lógica de acceso a datos de la lógica de negocio:

- **Interfaz `DAO<T, K>`**: Define operaciones CRUD genéricas
- **Implementaciones MySQL**: Cada entidad tiene su DAO específico

### Entidades Principales

1. **Producto**: Productos con nombre, marca, categoría, precio y peso
2. **CodigoBarras**: Códigos asociados a productos (tipo EAN13, EAN8, UPC)
3. **Usuario**: Usuarios con autenticación y roles
4. **Rol**: Roles del sistema con permisos

## 🗄️ Base de Datos

- **Motor**: MySQL
- **Base de datos**: `db_producto`
- **Tablas**:
  - `rol` - Roles del sistema
  - `usuario` - Usuarios (FK a rol)
  - `producto` - Catálogo de productos
  - `codigo_barras` - Códigos de barras (FK a producto, UNIQUE producto_id)

### Configuración de Database para levantar

Editar `DatabaseConfiguration.java`:
```java
private static final String URL = "jdbc:mysql://localhost:3306/db_producto";
private static final String USER = "root";
private static final String PASSWORD = "";
```

## 🚀 Ejecución

1. **Crear base de datos**: Ejecutar `DB/sql/create_tables.sql`
2. **Configurar conexión**: Ajustar credenciales en `DatabaseConfiguration.java`
3. **Compilar**: Build del proyecto Java
4. **Ejecutar**: Run `TrabajoIntegrador.java`

## 📝 Uso

Descomentar los ejemplos en `TrabajoIntegrador.java` para probar operaciones CRUD

## 🔧 Tecnologías

- **Java** 
- **MySQL**: Base de datos relacional
- **JDBC**: MySQL Connector/J 8.4.0


## 📋 Validaciones Implementadas

- **Restricciones UNIQUE**: Validación de duplicados en `producto_id`, `username`, `email`
- **Soft Delete**: Filtrado de registros eliminados (`eliminado = FALSE`)
- **Foreign Keys**: Integridad referencial entre tablas
