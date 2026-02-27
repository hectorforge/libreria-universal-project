create table categorias
(
    id_categoria uuid    not null
        primary key,
    descripcion  varchar(255),
    estado       boolean not null,
    nombre       varchar(255)
);

create table productos
(
    id_producto    uuid         not null
        primary key,
    codigo         varchar(255),
    descripcion    varchar(255),
    estado         boolean      not null,
    fecha_creacion timestamp(6) not null,
    nombre         varchar(255),
    precio_actual  double precision,
    url_imagen     varchar(255),
    id_categoria   uuid         not null
        constraint fkdtoa37luoxhhvbicrfiu5ygbj
            references categorias
);

create table inventario
(
    id_inventario uuid not null
        primary key,
    stock_actual  integer,
    stock_minimo  integer,
    id_producto   uuid not null
        constraint fk92r6ui4b0u2kxqad8cyiusrdn
            references productos
);

create table movimientos_inventario
(
    id_movimiento_inventario uuid         not null
        primary key,
    cantidad                 integer,
    fecha                    timestamp(6) not null,
    tipo                     varchar(255)
        constraint movimientos_inventario_tipo_check
            check ((tipo)::text = ANY ((ARRAY ['ENTRADA'::character varying, 'SALIDA'::character varying])::text[])),
    id_producto              uuid         not null
        constraint fk7ttlcxcscpdu0wa9vhq6celdu
            references productos
);


