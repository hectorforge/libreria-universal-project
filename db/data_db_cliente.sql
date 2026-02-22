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

INSERT INTO  tb_clientes(
    id, date_created, date_updated, created_by, updated_by,
    is_active, is_deleted, keycloak_id, apellidos, nombre,
    email, tipo_persona, tipo_documento, rol
) VALUES
      (
          -- Cliente 1: Persona natural activa
          'a1b2c3d4-0001-0001-0001-000000000001',
          '2024-01-10 08:00:00', '2024-01-10 08:00:00',
          'admin', 'admin', true, false,
          'kc-001', 'Ramírez Huanca', 'Jorge',
          'jorge.ramirez@email.com', 'NATURAL', 'DNI', 'CLIENTE'
      ),
      (
          -- Cliente 2: Persona natural activa
          'a1b2c3d4-0001-0001-0001-000000000002',
          '2024-01-11 09:00:00', '2024-01-11 09:00:00',
          'admin', 'admin', true, false,
          'kc-002', 'Quispe Mamani', 'Lucía',
          'lucia.quispe@email.com', 'NATURAL', 'DNI', 'CLIENTE'
      ),
      (
          -- Cliente 3: Persona natural activa
          'a1b2c3d4-0001-0001-0001-000000000003',
          '2024-01-12 10:00:00', '2024-01-12 10:00:00',
          'admin', 'admin', true, false,
          'kc-003', 'Flores Ccopa', 'Roberto',
          'roberto.flores@email.com', 'NATURAL', 'DNI', 'CLIENTE'
      ),
      (
          -- Cliente 4: Empresa jurídica activa (colegio)
          'a1b2c3d4-0001-0001-0001-000000000004',
          '2024-01-13 11:00:00', '2024-01-13 11:00:00',
          'admin', 'admin', true, false,
          'kc-004', 'Educativa Los Andes S.A.C.', 'Institución',
          'logistica@colegiolosandes.edu.pe', 'JURIDICA', 'RUC', 'CLIENTE'
      ),
      (
          -- Cliente 5: Empresa jurídica activa (estudio de contabilidad)
          'a1b2c3d4-0001-0001-0001-000000000005',
          '2024-01-14 12:00:00', '2024-01-14 12:00:00',
          'admin', 'admin', true, false,
          'kc-005', 'Contadores Asociados S.R.L.', 'Estudio',
          'compras@contadoresasociados.com', 'JURIDICA', 'RUC', 'CLIENTE'
      ),
      (
          -- Cliente 6: Persona natural INACTIVA y eliminada
          'a1b2c3d4-0001-0001-0001-000000000006',
          '2024-01-15 13:00:00', '2024-03-01 10:00:00',
          'admin', 'admin', false, true,
          'kc-006', 'Condori Apaza', 'Miriam',
          'miriam.condori@email.com', 'NATURAL', 'DNI', 'CLIENTE'
      ),
      (
          -- Cliente 7: Empresa jurídica activa (constructora)
          'a1b2c3d4-0001-0001-0001-000000000007',
          '2024-02-01 08:30:00', '2024-02-01 08:30:00',
          'admin', 'admin', true, false,
          'kc-007', 'Constructora Horizonte S.A.C.', 'Empresa',
          'admin@constructorahorizonte.com', 'JURIDICA', 'RUC', 'CLIENTE'
      ),
      (
          -- Cliente 8: Persona natural activa
          'a1b2c3d4-0001-0001-0001-000000000008',
          '2024-02-05 09:00:00', '2024-02-05 09:00:00',
          'admin', 'admin', true, false,
          'kc-008', 'Ttito Huillca', 'Carmen',
          'carmen.ttito@email.com', 'NATURAL', 'DNI', 'CLIENTE'
      );