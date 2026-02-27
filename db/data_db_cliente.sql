-- ============================================================
-- SCRIPT DE DATOS DE PRUEBA - MICROSERVICIOS
-- Negocio: Librería y Artículos de Oficina
-- Base de datos: PostgreSQL
-- Microservicios: Cliente | Inventario | Sales
-- ============================================================


-- ============================================================
-- MICROSERVICIO: CLIENTE
-- Tabla: cliente_entity (8 clientes: personas naturales y empresas)
-- TipoCliente: NATURAL | JURIDICA
-- TipoDocumento: DNI | RUC
-- ============================================================

INSERT INTO tb_clientes
(id, apellidos, created_by, date_created, date_updated, email,
 is_active, is_deleted, keycloak_id, nombre,
 numero_documento, rol, tipo_documento, tipo_persona, updated_by)
VALUES
    (
        'a1b2c3d4-0001-0001-0001-000000000001',
        'Ramírez Huanca',
        'admin',
        '2024-01-10 08:00:00',
        '2024-01-10 08:00:00',
        'jorge.ramirez@email.com',
        true,
        false,
        'kc-001',
        'Jorge',
        '12345678',
        'CLIENTE',
        'DNI',
        'NATURAL',
        'admin'
    ),
    (
        'a1b2c3d4-0001-0001-0001-000000000002',
        'Quispe Mamani',
        'admin',
        '2024-01-11 09:00:00',
        '2024-01-11 09:00:00',
        'lucia.quispe@email.com',
        true,
        false,
        'kc-002',
        'Lucía',
        '23456789',
        'CLIENTE',
        'DNI',
        'NATURAL',
        'admin'
    ),
    (
        'a1b2c3d4-0001-0001-0001-000000000003',
        'Flores Ccopa',
        'admin',
        '2024-01-12 10:00:00',
        '2024-01-12 10:00:00',
        'roberto.flores@email.com',
        true,
        false,
        'kc-003',
        'Roberto',
        '34567890',
        'CLIENTE',
        'DNI',
        'NATURAL',
        'admin'
    ),
    (
        'a1b2c3d4-0001-0001-0001-000000000004',
        'Educativa Los Andes S.A.C.',
        'admin',
        '2024-01-13 11:00:00',
        '2024-01-13 11:00:00',
        'logistica@colegiolosandes.edu.pe',
        true,
        false,
        'kc-004',
        'Institución',
        '20123456789',
        'CLIENTE',
        'RUC',
        'JURIDICA',
        'admin'
    ),
    (
        'a1b2c3d4-0001-0001-0001-000000000005',
        'Contadores Asociados S.R.L.',
        'admin',
        '2024-01-14 12:00:00',
        '2024-01-14 12:00:00',
        'compras@contadoresasociados.com',
        true,
        false,
        'kc-005',
        'Estudio',
        '20567890123',
        'CLIENTE',
        'RUC',
        'JURIDICA',
        'admin'
    ),
    (
        'a1b2c3d4-0001-0001-0001-000000000006',
        'Condori Apaza',
        'admin',
        '2024-01-15 13:00:00',
        '2024-03-01 10:00:00',
        'miriam.condori@email.com',
        false,
        true,
        'kc-006',
        'Miriam',
        '45678901',
        'CLIENTE',
        'DNI',
        'NATURAL',
        'admin'
    ),
    (
        'a1b2c3d4-0001-0001-0001-000000000007',
        'Constructora Horizonte S.A.C.',
        'admin',
        '2024-02-01 08:30:00',
        '2024-02-01 08:30:00',
        'admin@constructorahorizonte.com',
        true,
        false,
        'kc-007',
        'Empresa',
        '20987654321',
        'CLIENTE',
        'RUC',
        'JURIDICA',
        'admin'
    ),
    (
        'a1b2c3d4-0001-0001-0001-000000000008',
        'Ttito Huillca',
        'admin',
        '2024-02-05 09:00:00',
        '2024-02-05 09:00:00',
        'carmen.ttito@email.com',
        true,
        false,
        'kc-008',
        'Carmen',
        '56789012',
        'CLIENTE',
        'DNI',
        'NATURAL',
        'admin'
    );