-- ============================================================
-- SCRIPT DE DATOS DE PRUEBA - MICROSERVICIOS
-- Negocio: Librería y Artículos de Oficina
-- Base de datos: PostgreSQL
-- Microservicios: Cliente | Inventario | Sales
-- ============================================================


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

