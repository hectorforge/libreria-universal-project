
-- Datos de prueba para la bd "sales"
-- Tabla: Ventas
INSERT INTO ventas (venta_id, activo, cliente_id, codigo, estado, fecha_venta, total) VALUES
                                                                                          ('11111111-1111-1111-1111-111111111111', true,  'aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa', 'VTA-001', 'CONFIRMADO', '2026-01-10', 450.00),
                                                                                          ('22222222-2222-2222-2222-222222222222', true,  'bbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbbb', 'VTA-002', 'CONFIRMADO', '2026-01-12', 320.00),
                                                                                          ('33333333-3333-3333-3333-333333333333', false, 'cccccccc-cccc-cccc-cccc-cccccccccccc', 'VTA-003', 'CANCELADO',  '2026-01-13', 150.00),
                                                                                          ('44444444-4444-4444-4444-444444444444', true,  'dddddddd-dddd-dddd-dddd-dddddddddddd', 'VTA-004', 'PENDIENTE',  '2026-01-15', 600.00),
                                                                                          ('55555555-5555-5555-5555-555555555555', true,  'eeeeeeee-eeee-eeee-eeee-eeeeeeeeeeee', 'VTA-005', 'CONFIRMADO', '2026-01-18', 280.00),
                                                                                          ('66666666-6666-6666-6666-666666666666', true,  'ffffffff-ffff-ffff-ffff-ffffffffffff', 'VTA-006', 'CONFIRMADO', '2026-01-20', 900.00),
                                                                                          ('77777777-7777-7777-7777-777777777777', false, '11111111-aaaa-bbbb-cccc-222222222222', 'VTA-007', 'CANCELADO',  '2026-01-21', 120.00),
                                                                                          ('88888888-8888-8888-8888-888888888888', true,  '22222222-aaaa-bbbb-cccc-333333333333', 'VTA-008', 'CONFIRMADO', '2026-01-22', 750.00),
                                                                                          ('99999999-9999-9999-9999-999999999999', true,  '33333333-aaaa-bbbb-cccc-444444444444', 'VTA-009', 'PENDIENTE',  '2026-01-25', 500.00),
                                                                                          ('aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa', true,  '44444444-aaaa-bbbb-cccc-555555555555', 'VTA-010', 'CONFIRMADO', '2026-01-28', 1100.00);


-- Tabla: Detalles de Ventas
INSERT INTO detalle_ventas (cantidad, precio_unitario, producto_id, subtotal, venta_id) VALUES
-- Venta 1
(2, 100.00, 'aaaaaaaa-0000-0000-0000-000000000001', 200.00, '11111111-1111-1111-1111-111111111111'),
(1, 250.00, 'aaaaaaaa-0000-0000-0000-000000000002', 250.00, '11111111-1111-1111-1111-111111111111'),

-- Venta 2
(4, 50.00,  'aaaaaaaa-0000-0000-0000-000000000003', 200.00, '22222222-2222-2222-2222-222222222222'),
(2, 60.00,  'aaaaaaaa-0000-0000-0000-000000000004', 120.00, '22222222-2222-2222-2222-222222222222'),

-- Venta 3 (cancelada)
(3, 50.00,  'aaaaaaaa-0000-0000-0000-000000000005', 150.00, '33333333-3333-3333-3333-333333333333'),

-- Venta 4
(2, 300.00, 'aaaaaaaa-0000-0000-0000-000000000006', 600.00, '44444444-4444-4444-4444-444444444444'),

-- Venta 5
(4, 70.00,  'aaaaaaaa-0000-0000-0000-000000000007', 280.00, '55555555-5555-5555-5555-555555555555'),

-- Venta 6
(3, 200.00, 'aaaaaaaa-0000-0000-0000-000000000008', 600.00, '66666666-6666-6666-6666-666666666666'),
(2, 150.00, 'aaaaaaaa-0000-0000-0000-000000000009', 300.00, '66666666-6666-6666-6666-666666666666'),

-- Venta 7 (cancelada)
(2, 60.00,  'aaaaaaaa-0000-0000-0000-00000000000a', 120.00, '77777777-7777-7777-7777-777777777777'),

-- Venta 8
(3, 250.00, 'aaaaaaaa-0000-0000-0000-00000000000b', 750.00, '88888888-8888-8888-8888-888888888888'),

-- Venta 9
(5, 100.00, 'aaaaaaaa-0000-0000-0000-00000000000c', 500.00, '99999999-9999-9999-9999-999999999999'),

-- Venta 10
(4, 200.00, 'aaaaaaaa-0000-0000-0000-00000000000d', 800.00, 'aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa'),
(2, 150.00, 'aaaaaaaa-0000-0000-0000-00000000000e', 300.00, 'aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa');
