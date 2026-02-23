-- ============================================================
-- SCRIPT DE DATOS DE PRUEBA EXCLUSIVO PARA MS-REPORTE
-- Inserta ventas mensuales durante todo el año 2024
-- Para poblar el Reporte Financiero (Gráfico de Barras)
-- ============================================================

-- IMPORTANTE: Ejecutar sobre la Base de Datos de ms-sales

-- ENERO 2024 (2 ventas)
INSERT INTO ventas (venta_id, codigo, cliente_id, total, fecha_venta, activo, estado) VALUES
                                                                                          ('f1a2b3c4-test-0001-0000-000000000001', 'VTA-REP-001', 'a1b2c3d4-0001-0001-0001-000000000004', 1500.00, '2024-01-15', true, 'CONFIRMADO'),
                                                                                          ('f1a2b3c4-test-0001-0000-000000000002', 'VTA-REP-002', 'a1b2c3d4-0001-0001-0001-000000000005', 850.50, '2024-01-28', true, 'CONFIRMADO');

-- FEBRERO 2024 (1 gran venta)
INSERT INTO ventas (venta_id, codigo, cliente_id, total, fecha_venta, activo, estado) VALUES
    ('f1a2b3c4-test-0002-0000-000000000001', 'VTA-REP-003', 'a1b2c3d4-0001-0001-0001-000000000007', 3200.00, '2024-02-14', true, 'CONFIRMADO');

-- MARZO 2024 (Campaña Escolar - 3 ventas fuertes)
INSERT INTO ventas (venta_id, codigo, cliente_id, total, fecha_venta, activo, estado) VALUES
                                                                                          ('f1a2b3c4-test-0003-0000-000000000001', 'VTA-REP-004', 'a1b2c3d4-0001-0001-0001-000000000004', 4500.00, '2024-03-05', true, 'CONFIRMADO'),
                                                                                          ('f1a2b3c4-test-0003-0000-000000000002', 'VTA-REP-005', 'a1b2c3d4-0001-0001-0001-000000000001', 120.00, '2024-03-10', true, 'CONFIRMADO'),
                                                                                          ('f1a2b3c4-test-0003-0000-000000000003', 'VTA-REP-006', 'a1b2c3d4-0001-0001-0001-000000000007', 1850.00, '2024-03-25', true, 'CONFIRMADO');

-- ABRIL 2024 (Baja después de campaña)
INSERT INTO ventas (venta_id, codigo, cliente_id, total, fecha_venta, activo, estado) VALUES
    ('f1a2b3c4-test-0004-0000-000000000001', 'VTA-REP-007', 'a1b2c3d4-0001-0001-0001-000000000002', 450.00, '2024-04-12', true, 'CONFIRMADO');

-- MAYO 2024
INSERT INTO ventas (venta_id, codigo, cliente_id, total, fecha_venta, activo, estado) VALUES
    ('f1a2b3c4-test-0005-0000-000000000001', 'VTA-REP-008', 'a1b2c3d4-0001-0001-0001-000000000005', 920.00, '2024-05-20', true, 'CONFIRMADO');

-- JUNIO 2024
INSERT INTO ventas (venta_id, codigo, cliente_id, total, fecha_venta, activo, estado) VALUES
    ('f1a2b3c4-test-0006-0000-000000000001', 'VTA-REP-009', 'a1b2c3d4-0001-0001-0001-000000000007', 1150.00, '2024-06-18', true, 'CONFIRMADO');

-- JULIO 2024 (Campaña Fiestas Patrias)
INSERT INTO ventas (venta_id, codigo, cliente_id, total, fecha_venta, activo, estado) VALUES
    ('f1a2b3c4-test-0007-0000-000000000001', 'VTA-REP-010', 'a1b2c3d4-0001-0001-0001-000000000004', 2800.00, '2024-07-15', true, 'CONFIRMADO');

-- AGOSTO 2024
INSERT INTO ventas (venta_id, codigo, cliente_id, total, fecha_venta, activo, estado) VALUES
    ('f1a2b3c4-test-0008-0000-000000000001', 'VTA-REP-011', 'a1b2c3d4-0001-0001-0001-000000000005', 650.00, '2024-08-10', true, 'CONFIRMADO');

-- SEPTIEMBRE 2024
INSERT INTO ventas (venta_id, codigo, cliente_id, total, fecha_venta, activo, estado) VALUES
    ('f1a2b3c4-test-0009-0000-000000000001', 'VTA-REP-012', 'a1b2c3d4-0001-0001-0001-000000000007', 1400.00, '2024-09-22', true, 'CONFIRMADO');

-- OCTUBRE 2024
INSERT INTO ventas (venta_id, codigo, cliente_id, total, fecha_venta, activo, estado) VALUES
    ('f1a2b3c4-test-0010-0000-000000000001', 'VTA-REP-013', 'a1b2c3d4-0001-0001-0001-000000000004', 1900.00, '2024-10-30', true, 'CONFIRMADO');

-- NOVIEMBRE 2024
INSERT INTO ventas (venta_id, codigo, cliente_id, total, fecha_venta, activo, estado) VALUES
    ('f1a2b3c4-test-0011-0000-000000000001', 'VTA-REP-014', 'a1b2c3d4-0001-0001-0001-000000000005', 2100.00, '2024-11-15', true, 'CONFIRMADO');

-- DICIEMBRE 2024 (Navidad - El mes más alto)
INSERT INTO ventas (venta_id, codigo, cliente_id, total, fecha_venta, activo, estado) VALUES
                                                                                          ('f1a2b3c4-test-0012-0000-000000000001', 'VTA-REP-015', 'a1b2c3d4-0001-0001-0001-000000000004', 5500.00, '2024-12-10', true, 'CONFIRMADO'),
                                                                                          ('f1a2b3c4-test-0012-0000-000000000002', 'VTA-REP-016', 'a1b2c3d4-0001-0001-0001-000000000007', 3800.00, '2024-12-20', true, 'CONFIRMADO');

-- (Opcional) Detalles para las ventas más grandes
INSERT INTO detalle_ventas (producto_id, cantidad, precio_unitario, subtotal, venta_id) VALUES
                                                                                            ('c1d2e3f4-0003-0003-0003-000000000001', 500, 8.50, 4250.00, 'f1a2b3c4-test-0003-0000-000000000001'),
                                                                                            ('c1d2e3f4-0003-0003-0003-000000000003', 200, 22.00, 4400.00, 'f1a2b3c4-test-0012-0000-000000000001');
