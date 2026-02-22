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


-- ============================================================
-- MICROSERVICIO: INVENTARIO
-- Tabla: categoria_entity (5 categorías de librería/oficina)
-- ============================================================

INSERT INTO categorias (
    id_categoria, nombre, descripcion, estado
) VALUES
      (
          'b1c2d3e4-0002-0002-0002-000000000001',
          'Útiles Escolares',
          'Cuadernos, lápices, colores y materiales para el estudio', true
      ),
      (
          'b1c2d3e4-0002-0002-0002-000000000002',
          'Papelería y Papel',
          'Papel bond, papel de colores, cartulinas y sobres', true
      ),
      (
          'b1c2d3e4-0002-0002-0002-000000000003',
          'Escritura y Trazado',
          'Bolígrafos, plumas, marcadores, resaltadores y correctores', true
      ),
      (
          'b1c2d3e4-0002-0002-0002-000000000004',
          'Organización y Archivo',
          'Carpetas, archivadores, separadores, clips y grapas', true
      ),
      (
          -- Categoría inactiva, producto asociado también desactivado
          'b1c2d3e4-0002-0002-0002-000000000005',
          'Impresión y Tecnología',
          'Tóner, cartuchos de tinta, USB y accesorios de impresora', false
      );


-- ============================================================
-- MICROSERVICIO: INVENTARIO
-- Tabla: producto_entity (10 productos de librería/oficina)
-- ============================================================

INSERT INTO productos (
    id_producto, codigo, nombre, descripcion,
    precio_actual, id_categoria, estado, url_imagen, fecha_creacion
) VALUES
      (
          'c1d2e3f4-0003-0003-0003-000000000001',
          'ESC-001', 'Cuaderno Universitario 100 hojas',
          'Cuaderno A4 cuadriculado, tapa dura, 100 hojas bond 75gr',
          8.50, 'b1c2d3e4-0002-0002-0002-000000000001',
          true, 'https://images.libreria.com/cuaderno-univ.jpg', '2024-01-05 08:00:00'
      ),
      (
          'c1d2e3f4-0003-0003-0003-000000000002',
          'ESC-002', 'Set Colores Faber-Castell x24',
          'Caja de 24 lápices de colores largos, mina gruesa resistente',
          18.00, 'b1c2d3e4-0002-0002-0002-000000000001',
          true, 'https://images.libreria.com/colores-faber.jpg', '2024-01-05 08:30:00'
      ),
      (
          'c1d2e3f4-0003-0003-0003-000000000003',
          'PAP-001', 'Resma Papel Bond A4 500 hojas',
          'Papel bond blanco A4 75gr, paquete de 500 hojas, ideal para impresora',
          22.00, 'b1c2d3e4-0002-0002-0002-000000000002',
          true, 'https://images.libreria.com/papel-bond-a4.jpg', '2024-01-06 09:00:00'
      ),
      (
          'c1d2e3f4-0003-0003-0003-000000000004',
          'PAP-002', 'Pack Cartulinas de Colores x50',
          'Pack de 50 cartulinas de colores surtidos tamaño A3, 180gr',
          15.00, 'b1c2d3e4-0002-0002-0002-000000000002',
          true, 'https://images.libreria.com/cartulinas.jpg', '2024-01-07 10:00:00'
      ),
      (
          'c1d2e3f4-0003-0003-0003-000000000005',
          'ESC-003', 'Caja Bolígrafos Bic Azul x50',
          'Caja de 50 bolígrafos Bic punta fina azul, escritura fluida',
          25.00, 'b1c2d3e4-0002-0002-0002-000000000003',
          true, 'https://images.libreria.com/boligrafos-bic.jpg', '2024-01-07 10:30:00'
      ),
      (
          'c1d2e3f4-0003-0003-0003-000000000006',
          'ESC-004', 'Set Resaltadores Stabilo x6 colores',
          'Set de 6 resaltadores Stabilo Boss colores surtidos, punta biselada',
          14.50, 'b1c2d3e4-0002-0002-0002-000000000003',
          true, 'https://images.libreria.com/resaltadores-stabilo.jpg', '2024-01-08 11:00:00'
      ),
      (
          'c1d2e3f4-0003-0003-0003-000000000007',
          'ORG-001', 'Archivador Palanca Lomo Ancho A4',
          'Archivador de palanca lomo ancho 7.5cm, forro plastificado, A4',
          12.00, 'b1c2d3e4-0002-0002-0002-000000000004',
          true, 'https://images.libreria.com/archivador.jpg', '2024-01-08 11:30:00'
      ),
      (
          'c1d2e3f4-0003-0003-0003-000000000008',
          'ORG-002', 'Grapadora Maped Tigra 26/6',
          'Grapadora de escritorio capacidad 20 hojas, incluye caja de grapas',
          19.90, 'b1c2d3e4-0002-0002-0002-000000000004',
          true, 'https://images.libreria.com/grapadora.jpg', '2024-01-09 12:00:00'
      ),
      (
          -- Stock bajo del mínimo para probar alertas
          'c1d2e3f4-0003-0003-0003-000000000009',
          'ESC-005', 'Corrector Líquido Faber-Castell 20ml',
          'Corrector líquido de secado rápido, punta de metal, 20ml',
          5.50, 'b1c2d3e4-0002-0002-0002-000000000003',
          true, 'https://images.libreria.com/corrector.jpg', '2024-01-09 12:30:00'
      ),
      (
          -- Producto desactivado, categoría inactiva
          'c1d2e3f4-0003-0003-0003-000000000010',
          'IMP-001', 'Cartucho Tinta HP 664 Negro',
          'Cartucho de tinta negra HP 664, compatible con series 2135, 3635',
          38.00, 'b1c2d3e4-0002-0002-0002-000000000005',
          false, 'https://images.libreria.com/cartucho-hp.jpg', '2024-01-10 08:00:00'
      );


-- ============================================================
-- MICROSERVICIO: INVENTARIO
-- Tabla: inventario_entity (stock actual y mínimo por producto)
-- Nota: corrector (009) y cartucho (010) están bajo el mínimo
-- ============================================================

INSERT INTO inventario (
    id_inventario, id_producto, stock_actual, stock_minimo
) VALUES
      ('d1e2f3a4-0004-0004-0004-000000000001', 'c1d2e3f4-0003-0003-0003-000000000001', 200, 30),
      ('d1e2f3a4-0004-0004-0004-000000000002', 'c1d2e3f4-0003-0003-0003-000000000002', 80,  20),
      ('d1e2f3a4-0004-0004-0004-000000000003', 'c1d2e3f4-0003-0003-0003-000000000003', 150, 25),
      ('d1e2f3a4-0004-0004-0004-000000000004', 'c1d2e3f4-0003-0003-0003-000000000004', 120, 20),
      ('d1e2f3a4-0004-0004-0004-000000000005', 'c1d2e3f4-0003-0003-0003-000000000005', 100, 15),
      ('d1e2f3a4-0004-0004-0004-000000000006', 'c1d2e3f4-0003-0003-0003-000000000006', 60,  10),
      ('d1e2f3a4-0004-0004-0004-000000000007', 'c1d2e3f4-0003-0003-0003-000000000007', 45,  10),
      ('d1e2f3a4-0004-0004-0004-000000000008', 'c1d2e3f4-0003-0003-0003-000000000008', 35,  8),
      ('d1e2f3a4-0004-0004-0004-000000000009', 'c1d2e3f4-0003-0003-0003-000000000009', 8,   10),  -- BAJO MÍNIMO
      ('d1e2f3a4-0004-0004-0004-000000000010', 'c1d2e3f4-0003-0003-0003-000000000010', 4,   10);  -- BAJO MÍNIMO


-- ============================================================
-- MICROSERVICIO: INVENTARIO
-- Tabla: movimiento_inventario_entity
-- Registra entradas iniciales y salidas por ventas
-- ============================================================

INSERT INTO movimientos_inventario (
    id_movimiento_inventario, id_producto, tipo, cantidad, fecha
) VALUES
-- Entradas iniciales de mercadería
('e1f2a3b4-0005-0005-0005-000000000001', 'c1d2e3f4-0003-0003-0003-000000000001', 'ENTRADA', 250, '2024-01-05 08:00:00'),
('e1f2a3b4-0005-0005-0005-000000000002', 'c1d2e3f4-0003-0003-0003-000000000002', 'ENTRADA', 100, '2024-01-05 08:15:00'),
('e1f2a3b4-0005-0005-0005-000000000003', 'c1d2e3f4-0003-0003-0003-000000000003', 'ENTRADA', 200, '2024-01-06 09:00:00'),
('e1f2a3b4-0005-0005-0005-000000000004', 'c1d2e3f4-0003-0003-0003-000000000004', 'ENTRADA', 150, '2024-01-07 09:30:00'),
('e1f2a3b4-0005-0005-0005-000000000005', 'c1d2e3f4-0003-0003-0003-000000000005', 'ENTRADA', 120, '2024-01-07 10:00:00'),
('e1f2a3b4-0005-0005-0005-000000000006', 'c1d2e3f4-0003-0003-0003-000000000006', 'ENTRADA', 80,  '2024-01-08 10:00:00'),
('e1f2a3b4-0005-0005-0005-000000000007', 'c1d2e3f4-0003-0003-0003-000000000007', 'ENTRADA', 60,  '2024-01-08 10:30:00'),
('e1f2a3b4-0005-0005-0005-000000000008', 'c1d2e3f4-0003-0003-0003-000000000008', 'ENTRADA', 50,  '2024-01-09 11:00:00'),
('e1f2a3b4-0005-0005-0005-000000000009', 'c1d2e3f4-0003-0003-0003-000000000009', 'ENTRADA', 30,  '2024-01-09 11:30:00'),
-- Salidas por ventas realizadas
('e1f2a3b4-0005-0005-0005-000000000010', 'c1d2e3f4-0003-0003-0003-000000000001', 'SALIDA',  50,  '2024-01-20 14:00:00'),
('e1f2a3b4-0005-0005-0005-000000000011', 'c1d2e3f4-0003-0003-0003-000000000003', 'SALIDA',  50,  '2024-01-21 10:00:00'),
('e1f2a3b4-0005-0005-0005-000000000012', 'c1d2e3f4-0003-0003-0003-000000000005', 'SALIDA',  20,  '2024-01-22 11:00:00'),
('e1f2a3b4-0005-0005-0005-000000000013', 'c1d2e3f4-0003-0003-0003-000000000009', 'SALIDA',  22,  '2024-01-25 15:00:00'),
('e1f2a3b4-0005-0005-0005-000000000014', 'c1d2e3f4-0003-0003-0003-000000000002', 'SALIDA',  20,  '2024-02-01 09:00:00');


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