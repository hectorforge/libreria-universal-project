-- =========================
-- CATEGORIAS
-- =========================

INSERT INTO categorias (id_categoria, descripcion, estado, nombre) VALUES
                                                                       ('11111111-1111-1111-1111-111111111111', 'Cuadernos escolares y universitarios', true, 'Cuadernos'),
                                                                       ('22222222-2222-2222-2222-222222222222', 'Instrumentos de escritura', true, 'Lapiceros y Lápices'),
                                                                       ('33333333-3333-3333-3333-333333333333', 'Organización de documentos', true, 'Archivadores y Carpetas'),
                                                                       ('44444444-4444-4444-4444-444444444444', 'Materiales de dibujo y pintura', true, 'Arte y Colores'),
                                                                       ('55555555-5555-5555-5555-555555555555', 'Suministros varios de oficina', true, 'Oficina General');


-- =========================
-- PRODUCTOS
-- =========================

INSERT INTO productos
(id_producto, codigo, descripcion, estado, fecha_creacion, nombre, precio_actual, url_imagen, id_categoria)
VALUES

-- CUADERNOS
('a1111111-0000-0000-0000-000000000001', 'CUAD-001', 'Cuaderno cuadriculado A4 100 hojas', true, NOW(), 'Cuaderno A4 100 hojas', 12.50, 'img/cuaderno1.jpg', '11111111-1111-1111-1111-111111111111'),
('a1111111-0000-0000-0000-000000000002', 'CUAD-002', 'Cuaderno rayado A5 80 hojas', true, NOW(), 'Cuaderno A5 80 hojas', 8.90, 'img/cuaderno2.jpg', '11111111-1111-1111-1111-111111111111'),
('a1111111-0000-0000-0000-000000000003', 'CUAD-003', 'Cuaderno universitario tapa dura 200 hojas', true, NOW(), 'Cuaderno universitario 200 hojas', 18.00, 'img/cuaderno3.jpg', '11111111-1111-1111-1111-111111111111'),
('a1111111-0000-0000-0000-000000000004', 'CUAD-004', 'Cuaderno espiral 150 hojas cuadriculado', true, NOW(), 'Cuaderno espiral 150 hojas', 14.30, 'img/cuaderno4.jpg', '11111111-1111-1111-1111-111111111111'),

-- ESCRITURA
('b2222222-0000-0000-0000-000000000001', 'LAPI-001', 'Lapicero tinta azul punta fina', true, NOW(), 'Lapicero azul', 1.50, 'img/lapicero1.jpg', '22222222-2222-2222-2222-222222222222'),
('b2222222-0000-0000-0000-000000000002', 'LAPI-002', 'Lapicero tinta negra punta media', true, NOW(), 'Lapicero negro', 1.50, 'img/lapicero2.jpg', '22222222-2222-2222-2222-222222222222'),
('b2222222-0000-0000-0000-000000000003', 'LAPI-003', 'Lápiz grafito HB escolar', true, NOW(), 'Lápiz HB', 0.80, 'img/lapiz1.jpg', '22222222-2222-2222-2222-222222222222'),
('b2222222-0000-0000-0000-000000000004', 'LAPI-004', 'Lápiz 2B para dibujo técnico', true, NOW(), 'Lápiz 2B', 1.20, 'img/lapiz2.jpg', '22222222-2222-2222-2222-222222222222'),
('b2222222-0000-0000-0000-000000000005', 'LAPI-005', 'Resaltador amarillo fluorescente', true, NOW(), 'Resaltador amarillo', 3.50, 'img/resaltador1.jpg', '22222222-2222-2222-2222-222222222222'),
('b2222222-0000-0000-0000-000000000006', 'LAPI-006', 'Resaltador rosado fluorescente', true, NOW(), 'Resaltador rosado', 3.50, 'img/resaltador2.jpg', '22222222-2222-2222-2222-222222222222'),

-- ARCHIVO
('c3333333-0000-0000-0000-000000000001', 'ARCH-001', 'Archivador palanca A4 color negro', true, NOW(), 'Archivador palanca A4', 15.00, 'img/arch1.jpg', '33333333-3333-3333-3333-333333333333'),
('c3333333-0000-0000-0000-000000000002', 'ARCH-002', 'Folder manila A4 paquete x25', true, NOW(), 'Folder manila A4', 22.00, 'img/arch2.jpg', '33333333-3333-3333-3333-333333333333'),
('c3333333-0000-0000-0000-000000000003', 'ARCH-003', 'Carpeta plástica con mica transparente', true, NOW(), 'Carpeta plástica', 4.00, 'img/arch3.jpg', '33333333-3333-3333-3333-333333333333');


-- =========================
-- INVENTARIO
-- =========================

INSERT INTO inventario (id_inventario, stock_actual, stock_minimo, id_producto) VALUES
                                                                                    ('f0000000-0000-0000-0000-000000000001', 120, 20, 'a1111111-0000-0000-0000-000000000001'),
                                                                                    ('f0000000-0000-0000-0000-000000000002', 80, 15, 'a1111111-0000-0000-0000-000000000002'),
                                                                                    ('f0000000-0000-0000-0000-000000000003', 300, 50, 'b2222222-0000-0000-0000-000000000001'),
                                                                                    ('f0000000-0000-0000-0000-000000000004', 250, 50, 'b2222222-0000-0000-0000-000000000002'),
                                                                                    ('f0000000-0000-0000-0000-000000000005', 60, 10, 'c3333333-0000-0000-0000-000000000001');


-- =========================
-- MOVIMIENTOS
-- =========================

INSERT INTO movimientos_inventario (id_movimiento_inventario, cantidad, fecha, tipo, id_producto) VALUES
                                                                                                      ('d0000000-0000-0000-0000-000000000001', 150, NOW(), 'ENTRADA', 'a1111111-0000-0000-0000-000000000001'),
                                                                                                      ('d0000000-0000-0000-0000-000000000002', 400, NOW(), 'ENTRADA', 'b2222222-0000-0000-0000-000000000001'),
                                                                                                      ('d0000000-0000-0000-0000-000000000003', 80, NOW(), 'ENTRADA', 'c3333333-0000-0000-0000-000000000001'),
                                                                                                      ('d0000000-0000-0000-0000-000000000004', 30, NOW(), 'SALIDA', 'a1111111-0000-0000-0000-000000000001'),
                                                                                                      ('d0000000-0000-0000-0000-000000000005', 100, NOW(), 'SALIDA', 'b2222222-0000-0000-0000-000000000001');
