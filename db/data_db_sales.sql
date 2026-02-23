-- ============================================================
-- SCRIPT DE DATOS DE PRUEBA - MICROSERVICIOS
-- Negocio: Librería y Artículos de Oficina
-- Base de datos: PostgreSQL
-- Microservicios: Cliente | Inventario | Sales
-- ============================================================

-- ============================================================
-- MICROSERVICIO: SALES
-- Tabla: venta_entity (7 ventas con distintos estados)
-- Estado: CONFIRMADO | PENDIENTE | CANCELADO
-- ============================================================

INSERT INTO ventas (
    venta_id, codigo, cliente_id, total,
    fecha_venta, activo, estado
) VALUES
      (
          -- Venta 1: Colegio compra útiles escolares al por mayor
          'f1a2b3c4-0006-0006-0006-000000000001',
          'VTA-2024-001',
          'a1b2c3d4-0001-0001-0001-000000000004',  -- Institución Educativa Los Andes
          575.00, '2024-01-20', true, 'CONFIRMADO'
      ),
      (
          -- Venta 2: Persona natural compra material de escritura
          'f1a2b3c4-0006-0006-0006-000000000002',
          'VTA-2024-002',
          'a1b2c3d4-0001-0001-0001-000000000001',  -- Jorge Ramírez
          65.00, '2024-01-21', true, 'CONFIRMADO'
      ),
      (
          -- Venta 3: Empresa contable compra papel y archivadores
          'f1a2b3c4-0006-0006-0006-000000000003',
          'VTA-2024-003',
          'a1b2c3d4-0001-0001-0001-000000000005',  -- Contadores Asociados
          339.70, '2024-01-22', true, 'CONFIRMADO'
      ),
      (
          -- Venta 4: Persona natural compra artículos varios
          'f1a2b3c4-0006-0006-0006-000000000004',
          'VTA-2024-004',
          'a1b2c3d4-0001-0001-0001-000000000002',  -- Lucía Quispe
          45.00, '2024-01-25', true, 'CONFIRMADO'
      ),
      (
          -- Venta 5: Constructora compra papelería - PENDIENTE de pago
          'f1a2b3c4-0006-0006-0006-000000000005',
          'VTA-2024-005',
          'a1b2c3d4-0001-0001-0001-000000000007',  -- Constructora Horizonte
          464.00, '2024-02-01', true, 'PENDIENTE'
      ),
      (
          -- Venta 6: Cliente particular compra grapadora - PENDIENTE
          'f1a2b3c4-0006-0006-0006-000000000006',
          'VTA-2024-006',
          'a1b2c3d4-0001-0001-0001-000000000008',  -- Carmen Ttito
          39.80, '2024-02-05', true, 'PENDIENTE'
      ),
      (
          -- Venta 7: Venta CANCELADA - cliente desistió
          'f1a2b3c4-0006-0006-0006-000000000007',
          'VTA-2024-007',
          'a1b2c3d4-0001-0001-0001-000000000003',  -- Roberto Flores
          25.00, '2024-02-10', false, 'CANCELADO'
      );


-- ============================================================
-- MICROSERVICIO: SALES
-- Tabla: detalle_venta_entity
-- ============================================================

INSERT INTO detalle_ventas (
    producto_id, cantidad, precio_unitario, subtotal, venta_id
) VALUES
-- VTA-2024-001: Colegio Los Andes (cuadernos + colores + cartulinas)
('c1d2e3f4-0003-0003-0003-000000000001', 50, 8.50,  425.00, 'f1a2b3c4-0006-0006-0006-000000000001'),
('c1d2e3f4-0003-0003-0003-000000000002', 5,  18.00, 90.00,  'f1a2b3c4-0006-0006-0006-000000000001'),
('c1d2e3f4-0003-0003-0003-000000000004', 4,  15.00, 60.00,  'f1a2b3c4-0006-0006-0006-000000000001'),

-- VTA-2024-002: Jorge Ramírez (bolígrafos + resaltadores + corrector)
('c1d2e3f4-0003-0003-0003-000000000005', 1,  25.00, 25.00,  'f1a2b3c4-0006-0006-0006-000000000002'),
('c1d2e3f4-0003-0003-0003-000000000006', 2,  14.50, 29.00,  'f1a2b3c4-0006-0006-0006-000000000002'),
('c1d2e3f4-0003-0003-0003-000000000009', 2,  5.50,  11.00,  'f1a2b3c4-0006-0006-0006-000000000002'),

-- VTA-2024-003: Contadores Asociados (papel + archivadores + grapadoras)
('c1d2e3f4-0003-0003-0003-000000000003', 10, 22.00, 220.00, 'f1a2b3c4-0006-0006-0006-000000000003'),
('c1d2e3f4-0003-0003-0003-000000000007', 5,  12.00, 60.00,  'f1a2b3c4-0006-0006-0006-000000000003'),
('c1d2e3f4-0003-0003-0003-000000000008', 3,  19.90, 59.70,  'f1a2b3c4-0006-0006-0006-000000000003'),

-- VTA-2024-004: Lucía Quispe (cuadernos + corrector)
('c1d2e3f4-0003-0003-0003-000000000001', 4,  8.50,  34.00,  'f1a2b3c4-0006-0006-0006-000000000004'),
('c1d2e3f4-0003-0003-0003-000000000009', 2,  5.50,  11.00,  'f1a2b3c4-0006-0006-0006-000000000004'),

-- VTA-2024-005: Constructora Horizonte (papel + archivadores + bolígrafos)
('c1d2e3f4-0003-0003-0003-000000000003', 15, 22.00, 330.00, 'f1a2b3c4-0006-0006-0006-000000000005'),
('c1d2e3f4-0003-0003-0003-000000000007', 7,  12.00, 84.00,  'f1a2b3c4-0006-0006-0006-000000000005'),
('c1d2e3f4-0003-0003-0003-000000000005', 2,  25.00, 50.00,  'f1a2b3c4-0006-0006-0006-000000000005'),

-- VTA-2024-006: Carmen Ttito (grapadoras)
('c1d2e3f4-0003-0003-0003-000000000008', 2,  19.90, 39.80,  'f1a2b3c4-0006-0006-0006-000000000006'),

-- VTA-2024-007: Roberto Flores - CANCELADA (caja bolígrafos)
('c1d2e3f4-0003-0003-0003-000000000005', 1,  25.00, 25.00,  'f1a2b3c4-0006-0006-0006-000000000007');


-- ============================================================
-- MICROSERVICIO: SALES
-- Tabla: pago_entity
-- ============================================================

INSERT INTO pagos (
    venta_id, monto, metodo_pago, fecha_pago, activo, estado
) VALUES
-- VTA-2024-001: Colegio Los Andes paga por transferencia bancaria
('f1a2b3c4-0006-0006-0006-000000000001', 575.00, 'TRANSFERENCIA',   '2024-01-20', true,  'CONFIRMADO'),

-- VTA-2024-002: Jorge Ramírez paga en efectivo
('f1a2b3c4-0006-0006-0006-000000000002', 65.00,  'EFECTIVO',        '2024-01-21', true,  'CONFIRMADO'),

-- VTA-2024-003: Contadores Asociados paga en 2 cuotas con tarjeta
('f1a2b3c4-0006-0006-0006-000000000003', 169.85, 'TARJETA_CREDITO', '2024-01-22', true,  'CONFIRMADO'),
('f1a2b3c4-0006-0006-0006-000000000003', 169.85, 'TARJETA_CREDITO', '2024-02-22', true,  'PENDIENTE'),

-- VTA-2024-004: Lucía Quispe paga en efectivo
('f1a2b3c4-0006-0006-0006-000000000004', 45.00,  'EFECTIVO',        '2024-01-25', true,  'CONFIRMADO'),

-- VTA-2024-005: Constructora Horizonte, pago pendiente por transferencia
('f1a2b3c4-0006-0006-0006-000000000005', 464.00, 'TRANSFERENCIA',   '2024-02-01', true,  'PENDIENTE'),

-- VTA-2024-006: Carmen Ttito, pago pendiente en efectivo
('f1a2b3c4-0006-0006-0006-000000000006', 39.80,  'EFECTIVO',        '2024-02-05', true,  'PENDIENTE'),

-- VTA-2024-007: Pago CANCELADO
('f1a2b3c4-0006-0006-0006-000000000007', 25.00,  'EFECTIVO',        '2024-02-10', false, 'CANCELADO');


-- ============================================================
-- MICROSERVICIO: SALES
-- Tabla: facturacion_entity
-- IGV Perú: 18% | subtotal = total / 1.18 | impuesto = total - subtotal
-- ============================================================

INSERT INTO facturas (
    facturacion_id, numero_factura, fecha_factura, venta_id,
    metodo_pago, total, subtotal, impuestos, activo, estado
) VALUES
      (
          'a2b3c4d5-0007-0007-0007-000000000001',
          'FAC-2024-001', '2024-01-20',
          'f1a2b3c4-0006-0006-0006-000000000001',
          'TRANSFERENCIA', 575.00, 487.29, 87.71, true, 'EMITIDA'
      ),
      (
          'a2b3c4d5-0007-0007-0007-000000000002',
          'FAC-2024-002', '2024-01-21',
          'f1a2b3c4-0006-0006-0006-000000000002',
          'EFECTIVO', 65.00, 55.08, 9.92, true, 'EMITIDA'
      ),
      (
          'a2b3c4d5-0007-0007-0007-000000000003',
          'FAC-2024-003', '2024-01-22',
          'f1a2b3c4-0006-0006-0006-000000000003',
          'TARJETA_CREDITO', 339.70, 287.88, 51.82, true, 'EMITIDA'
      ),
      (
          'a2b3c4d5-0007-0007-0007-000000000004',
          'FAC-2024-004', '2024-01-25',
          'f1a2b3c4-0006-0006-0006-000000000004',
          'EFECTIVO', 45.00, 38.14, 6.86, true, 'EMITIDA'
      ),
      (
          'a2b3c4d5-0007-0007-0007-000000000005',
          'FAC-2024-005', '2024-02-01',
          'f1a2b3c4-0006-0006-0006-000000000005',
          'TRANSFERENCIA', 464.00, 393.22, 70.78, true, 'PENDIENTE'
      ),
      (
          'a2b3c4d5-0007-0007-0007-000000000006',
          'FAC-2024-006', '2024-02-05',
          'f1a2b3c4-0006-0006-0006-000000000006',
          'EFECTIVO', 39.80, 33.73, 6.07, true, 'PENDIENTE'
      );


-- ============================================================
-- MICROSERVICIO: SALES
-- Tabla: detalle_factura_entity
-- ============================================================

INSERT INTO detalle_facturas (
    cantidad, descripcion, valor_unitario, subtotal, facturacion_id
) VALUES
-- FAC-2024-001: Colegio Los Andes
(50, 'Cuaderno Universitario 100 hojas A4 cuadriculado',   8.50,  425.00, 'a2b3c4d5-0007-0007-0007-000000000001'),
(5,  'Set Colores Faber-Castell x24 unidades',             18.00, 90.00,  'a2b3c4d5-0007-0007-0007-000000000001'),
(4,  'Pack Cartulinas de Colores x50 tamaño A3',           15.00, 60.00,  'a2b3c4d5-0007-0007-0007-000000000001'),

-- FAC-2024-002: Jorge Ramírez
(1,  'Caja Bolígrafos Bic Azul x50 unidades',              25.00, 25.00,  'a2b3c4d5-0007-0007-0007-000000000002'),
(2,  'Set Resaltadores Stabilo Boss x6 colores',           14.50, 29.00,  'a2b3c4d5-0007-0007-0007-000000000002'),
(2,  'Corrector Líquido Faber-Castell 20ml',               5.50,  11.00,  'a2b3c4d5-0007-0007-0007-000000000002'),

-- FAC-2024-003: Contadores Asociados
(10, 'Resma Papel Bond A4 75gr 500 hojas',                 22.00, 220.00, 'a2b3c4d5-0007-0007-0007-000000000003'),
(5,  'Archivador Palanca Lomo Ancho A4 7.5cm',             12.00, 60.00,  'a2b3c4d5-0007-0007-0007-000000000003'),
(3,  'Grapadora Maped Tigra 26/6 cap. 20 hojas',           19.90, 59.70,  'a2b3c4d5-0007-0007-0007-000000000003'),

-- FAC-2024-004: Lucía Quispe
(4,  'Cuaderno Universitario 100 hojas A4 cuadriculado',   8.50,  34.00,  'a2b3c4d5-0007-0007-0007-000000000004'),
(2,  'Corrector Líquido Faber-Castell 20ml',               5.50,  11.00,  'a2b3c4d5-0007-0007-0007-000000000004'),

-- FAC-2024-005: Constructora Horizonte
(15, 'Resma Papel Bond A4 75gr 500 hojas',                 22.00, 330.00, 'a2b3c4d5-0007-0007-0007-000000000005'),
(7,  'Archivador Palanca Lomo Ancho A4 7.5cm',             12.00, 84.00,  'a2b3c4d5-0007-0007-0007-000000000005'),
(2,  'Caja Bolígrafos Bic Azul x50 unidades',              25.00, 50.00,  'a2b3c4d5-0007-0007-0007-000000000005'),

-- FAC-2024-006: Carmen Ttito
(2,  'Grapadora Maped Tigra 26/6 cap. 20 hojas',           19.90, 39.80,  'a2b3c4d5-0007-0007-0007-000000000006');