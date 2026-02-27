create table tb_clientes
(
    id               uuid         not null
        primary key,
    apellidos        varchar(100) not null,
    created_by       varchar(50),
    date_created     timestamp(6) not null,
    date_updated     timestamp(6) not null,
    email            varchar(150) not null
        constraint uk9o6dwdqix5udt21xn0tsq4m7m
            unique,
    is_active        boolean      not null,
    is_deleted       boolean      not null,
    keycloak_id      varchar(50),
    nombre           varchar(100) not null,
    numero_documento varchar(30)  not null,
    rol              varchar(50),
    tipo_documento   varchar(20)
        constraint tb_clientes_tipo_documento_check
            check ((tipo_documento)::text = ANY ((ARRAY ['DNI'::character varying, 'RUC'::character varying])::text[])),
    tipo_persona     varchar(20)  not null
        constraint tb_clientes_tipo_persona_check
            check ((tipo_persona)::text = ANY
                   ((ARRAY ['NATURAL'::character varying, 'JURIDICA'::character varying])::text[])),
    updated_by       varchar(50)
);

alter table tb_clientes
    owner to admin;

create index idx_cliente_email
    on tb_clientes (email);

create index idx_cliente_rol
    on tb_clientes (rol);

create index idx_cliente_tipo_persona
    on tb_clientes (tipo_persona);

create index idx_cliente_tipo_documento
    on tb_clientes (tipo_documento);


