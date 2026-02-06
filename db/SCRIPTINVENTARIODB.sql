INSERT INTO categorias (id, nombre, descripcion, estado) VALUES
                                                             ('a1111111-1111-1111-1111-111111111111', 'Cuadernos', 'Cuadernos escolares y universitarios', true),
                                                             ('a2222222-2222-2222-2222-222222222222', 'Escritura', 'Artículos para escribir', true),
                                                             ('a3333333-3333-3333-3333-333333333333', 'Oficina', 'Útiles de oficina', true),
                                                             ('a4444444-4444-4444-4444-444444444444', 'Archivadores', 'Carpetas y archivadores', true),
                                                             ('a5555555-5555-5555-5555-555555555555', 'Arte', 'Material de dibujo y arte', true),
                                                             ('a6666666-6666-6666-6666-666666666666', 'Papelería', 'Papelería general', true),
                                                             ('a7777777-7777-7777-7777-777777777777', 'Tecnología', 'Accesorios tecnológicos de oficina', true),
                                                             ('a8888888-8888-8888-8888-888888888888', 'Organización', 'Organización personal y laboral', true);



INSERT INTO productos (
    id, categoria_id, codigo, nombre, descripcion,
    precio_actual, estado, url_imagen, fecha_creacion
) VALUES
-- Cuadernos
('b1111111-1111-1111-1111-111111111111', 'a1111111-1111-1111-1111-111111111111', 'CUAD-A4-01', 'Cuaderno A4 Rayado', 'Cuaderno tamaño A4 rayado', 12.50, true, 'https://images.pexels.com/photos/4145192/pexels-photo-4145192.jpeg', now()),
('b1111111-1111-1111-1111-111111111112', 'a1111111-1111-1111-1111-111111111111', 'CUAD-A5-01', 'Cuaderno A5 Cuadriculado', 'Cuaderno A5 cuadriculado', 8.90, true, 'https://images.pexels.com/photos/4145193/pexels-photo-4145193.jpeg', now()),

-- Escritura
('b2222222-2222-2222-2222-222222222221', 'a2222222-2222-2222-2222-222222222222', 'LAP-001', 'Lápiz HB', 'Lápiz grafito HB', 1.50, true, 'https://images.pexels.com/photos/4145194/pexels-photo-4145194.jpeg', now()),
('b2222222-2222-2222-2222-222222222222', 'a2222222-2222-2222-2222-222222222222', 'BOL-001', 'Bolígrafo Azul', 'Bolígrafo tinta azul', 2.00, true, 'https://images.pexels.com/photos/4145195/pexels-photo-4145195.jpeg', now()),

-- Oficina
('b3333333-3333-3333-3333-333333333331', 'a3333333-3333-3333-3333-333333333333', 'ENG-001', 'Engrapador', 'Engrapador metálico mediano', 18.90, true,  'https://images.pexels.com/photos/4145196/pexels-photo-4145196.jpeg', now()),
('b3333333-3333-3333-3333-333333333332', 'a3333333-3333-3333-3333-333333333333', 'GRAP-001', 'Grapas', 'Caja de grapas estándar', 4.50, true, 'https://images.pexels.com/photos/4145197/pexels-photo-4145197.jpeg', now()),

-- Archivadores
('b4444444-4444-4444-4444-444444444441', 'a4444444-4444-4444-4444-444444444444', 'CARP-001', 'Carpeta A4', 'Carpeta plástica A4', 3.80, true, 'https://images.pexels.com/photos/4145198/pexels-photo-4145198.jpeg', now()),
('b4444444-4444-4444-4444-444444444442', 'a4444444-4444-4444-4444-444444444444', 'ARCH-001', 'Archivador', 'Archivador de palanca', 15.00, true, 'https://images.pexels.com/photos/4145199/pexels-photo-4145199.jpeg', now()),

-- Arte
('b5555555-5555-5555-5555-555555555551', 'a5555555-5555-5555-5555-555555555555', 'CRAY-001', 'Crayones', 'Caja de crayones x12', 6.90, true, 'https://images.pexels.com/photos/4145200/pexels-photo-4145200.jpeg', now()),
('b5555555-5555-5555-5555-555555555552', 'a5555555-5555-5555-5555-555555555555', 'PINT-001', 'Pinturas Acrílicas', 'Set de pinturas acrílicas', 22.00, true, 'https://images.pexels.com/photos/4145201/pexels-photo-4145201.jpeg', now()),

-- Papelería
('b6666666-6666-6666-6666-666666666661', 'a6666666-6666-6666-6666-666666666666', 'HOJ-A4-001', 'Hojas Bond A4', 'Paquete de hojas bond A4', 14.50, true, 'https://images.pexels.com/photos/4145202/pexels-photo-4145202.jpeg', now()),
('b6666666-6666-6666-6666-666666666662', 'a6666666-6666-6666-6666-666666666666', 'SOB-001', 'Sobres', 'Paquete de sobres manila', 7.20, true, 'https://images.pexels.com/photos/4145203/pexels-photo-4145203.jpeg', now()),

-- Tecnología
('b7777777-7777-7777-7777-777777777771', 'a7777777-7777-7777-7777-777777777777', 'USB-001', 'Memoria USB 32GB', 'USB 32GB', 28.00, true, 'https://images.pexels.com/photos/4145204/pexels-photo-4145204.jpeg', now()),
('b7777777-7777-7777-7777-777777777772', 'a7777777-7777-7777-7777-777777777777', 'MOU-001', 'Mouse USB', 'Mouse óptico USB', 19.90, true, 'https://images.pexels.com/photos/4145205/pexels-photo-4145205.jpeg', now()),

-- Organización
('b8888888-8888-8888-8888-888888888881', 'a8888888-8888-8888-8888-888888888888', 'ORG-001', 'Organizador Escritorio', 'Organizador plástico', 16.00, true, 'https://images.pexels.com/photos/4145206/pexels-photo-4145206.jpeg', now()),
('b8888888-8888-8888-8888-888888888882', 'a8888888-8888-8888-8888-888888888888', 'AGEN-001', 'Agenda', 'Agenda diaria', 20.00, true, 'https://images.pexels.com/photos/4145207/pexels-photo-4145207.jpeg', now());


INSERT INTO inventario (id, producto_id, stock_actual, stock_minimo) VALUES
-- Cuadernos
('f1111111-1111-4111-8111-111111111111','b1111111-1111-1111-1111-111111111111',120,20),
('f1111111-1111-4111-8111-111111111112','b1111111-1111-1111-1111-111111111112',90,15),

-- Escritura
('f2222222-2222-4222-8222-222222222221','b2222222-2222-2222-2222-222222222221',200,30),
('f2222222-2222-4222-8222-222222222222','b2222222-2222-2222-2222-222222222222',150,25),

-- Oficina
('f3333333-3333-4333-8333-333333333331','b3333333-3333-3333-3333-333333333331',75,15),
('f3333333-3333-4333-8333-333333333332','b3333333-3333-3333-3333-333333333332',180,30),

-- Archivadores
('f4444444-4444-4444-8444-444444444441','b4444444-4444-4444-4444-444444444441',60,10),
('f4444444-4444-4444-8444-444444444442','b4444444-4444-4444-4444-444444444442',55,10),

-- Arte
('f5555555-5555-4555-8555-555555555551','b5555555-5555-5555-5555-555555555551',95,20),
('f5555555-5555-4555-8555-555555555552','b5555555-5555-5555-5555-555555555552',70,15),

-- Papelería
('f6666666-6666-4666-8666-666666666661','b6666666-6666-6666-6666-666666666661',140,30),
('f6666666-6666-4666-8666-666666666662','b6666666-6666-6666-6666-666666666662',85,20),

-- Tecnología
('f7777777-7777-4777-8777-777777777771','b7777777-7777-7777-7777-777777777771',110,25),
('f7777777-7777-4777-8777-777777777772','b7777777-7777-7777-7777-777777777772',65,15),

-- Organización
('f8888888-8888-4888-8888-888888888881','b8888888-8888-8888-8888-888888888881',80,20),
('f8888888-8888-4888-8888-888888888882','b8888888-8888-8888-8888-888888888882',50,10);

INSERT INTO movimientos_inventario (id, cantidad, fecha, producto_id, tipo) VALUES
                                                                                ('e1111111-1111-4111-8111-111111111111',50,NOW(),'b1111111-1111-1111-1111-111111111111','ENTRADA'),
                                                                                ('e1111111-1111-4111-8111-111111111112',20,NOW(),'b1111111-1111-1111-1111-111111111112','SALIDA'),

                                                                                ('e2222222-2222-4222-8222-222222222221',100,NOW(),'b2222222-2222-2222-2222-222222222221','ENTRADA'),
                                                                                ('e2222222-2222-4222-8222-222222222222',30,NOW(),'b2222222-2222-2222-2222-222222222222','SALIDA'),

                                                                                ('e3333333-3333-4333-8333-333333333331',25,NOW(),'b3333333-3333-3333-3333-333333333331','ENTRADA'),
                                                                                ('e3333333-3333-4333-8333-333333333332',40,NOW(),'b3333333-3333-3333-3333-333333333332','SALIDA'),

                                                                                ('e4444444-4444-4444-8444-444444444441',15,NOW(),'b4444444-4444-4444-4444-444444444441','ENTRADA'),
                                                                                ('e4444444-4444-4444-8444-444444444442',10,NOW(),'b4444444-4444-4444-4444-444444444442','SALIDA'),

                                                                                ('e5555555-5555-4555-8555-555555555551',35,NOW(),'b5555555-5555-5555-5555-555555555551','ENTRADA'),
                                                                                ('e5555555-5555-4555-8555-555555555552',12,NOW(),'b5555555-5555-5555-5555-555555555552','SALIDA');


select * from productos