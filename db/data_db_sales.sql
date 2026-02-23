-- ============================================================
-- SCRIPT COMPLEMENTARIO: VENTAS DE MARZO Y ABRIL 2024
-- Agrega datos para probar gráficos de tendencia en reportes
-- ============================================================

-- 1. INSERTAR VENTAS (Marzo y Abril)
INSERT INTO ventas (
    venta_id, codigo, cliente_id, total,
    fecha_venta, activo, estado
) VALUES
-- MARZO 2024
-- Venta a Colegio Los Andes (Cliente 4)
('f1a2b3c4-aaaa-0000-0000-000000000008', 'VTA-2024-008', 'a1b2c3d4-0001-0001-0001-000000000004', 850.00, '2024-03-05', true, 'CONFIRMADO'),
-- Venta a Contadores Asociados (Cliente 5)
('f1a2b3c4-aaaa-0000-0000-000000000009', 'VTA-2024-009', 'a1b2c3d4-0001-0001-0001-000000000005', 440.00, '2024-03-18', true, 'CONFIRMADO'),

-- ABRIL 2024
-- Venta a Constructora Horizonte (Cliente 7)
('f1a2b3c4-aaaa-0000-0000-000000000010', 'VTA-2024-010', 'a1b2c3d4-0001-0001-0001-000000000007', 240.00, '2024-04-10', true, 'CONFIRMADO'),
-- Venta a Jorge Ramírez (Cliente 1)
('f1a2b3c4-aaaa-0000-0000-000000000011', 'VTA-2024-011', 'a1b2c3d4-0001-0001-0001-000000000001', 75.00, '2024-04-22', true, 'CONFIRMADO');


-- 2. INSERTAR DETALLES DE VENTA (Crucial para no romper ms-sales)
INSERT INTO detalle_ventas (
    producto_id, cantidad, precio_unitario, subtotal, venta_id
) VALUES
-- Detalles VTA-2024-008 (100 Cuadernos Universitarios a S/8.50)
('c1d2e3f4-0003-0003-0003-000000000001', 100, 8.50, 850.00, 'f1a2b3c4-aaaa-0000-0000-000000000008'),

-- Detalles VTA-2024-009 (20 Resmas de Papel Bond a S/22.00)
('c1d2e3f4-0003-0003-0003-000000000003', 20, 22.00, 440.00, 'f1a2b3c4-aaaa-0000-0000-000000000009'),

-- Detalles VTA-2024-010 (20 Archivadores a S/12.00)
('c1d2e3f4-0003-0003-0003-000000000007', 20, 12.00, 240.00, 'f1a2b3c4-aaaa-0000-0000-000000000010'),

-- Detalles VTA-2024-011 (3 Cajas de Bolígrafos a S/25.00)
('c1d2e3f4-0003-0003-0003-000000000005', 3, 25.00, 75.00, 'f1a2b3c4-aaaa-0000-0000-000000000011');
