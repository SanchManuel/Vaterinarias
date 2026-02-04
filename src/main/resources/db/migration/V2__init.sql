-- =========================================================
-- USUARIOS / RBAC (roles por veterinaria + permisos globales)
-- =========================================================

create table if not exists usuarios (
                                        id bigserial primary key,
                                        nombre varchar(100) not null,
    email varchar(200) not null unique,
    password varchar(200) not null,
    activo boolean not null default true,
    created_at timestamptz not null default now(),
    updated_at timestamptz not null default now()
    );

create table if not exists roles (
                                     id bigserial primary key,
                                     veterinaria_id bigint not null references veterinarias(id) on delete cascade,
    nombre varchar(100) not null,
    descripcion text,
    activo boolean not null default true,
    created_at timestamptz not null default now(),
    updated_at timestamptz not null default now(),
    unique (veterinaria_id, nombre),
    -- para FK compuesta (blindaje multi-tenant)
    constraint roles_id_vet_unique unique (id, veterinaria_id)
    );

create table if not exists permisos (
                                        id bigserial primary key,
                                        clave varchar(100) not null unique,
    descripcion text,
    activo boolean not null default true
    );

create table if not exists rol_permiso (
                                           rol_id bigint not null references roles(id) on delete cascade,
    permiso_id bigint not null references permisos(id),
    primary key (rol_id, permiso_id)
    );

create table if not exists usuario_veterinaria (
                                                   id bigserial primary key,
                                                   usuario_id bigint not null references usuarios(id) on delete cascade,
    veterinaria_id bigint not null references veterinarias(id) on delete cascade,
    activo boolean not null default true,
    created_at timestamptz not null default now(),
    unique (usuario_id, veterinaria_id),
    -- para FK compuesta (blindaje multi-tenant)
    constraint uv_id_vet_unique unique (id, veterinaria_id)
    );

-- IMPORTANTE:
-- Esta tabla lleva veterinaria_id para amarrar:
-- (usuario_veterinaria_id, veterinaria_id) y (rol_id, veterinaria_id)
-- evitando asignar roles de otra veterinaria.
create table if not exists usuario_veterinaria_rol (
                                                       usuario_veterinaria_id bigint not null,
                                                       rol_id bigint not null,
                                                       veterinaria_id bigint not null,
                                                       primary key (usuario_veterinaria_id, rol_id),

    foreign key (usuario_veterinaria_id, veterinaria_id)
    references usuario_veterinaria (id, veterinaria_id) on delete cascade,

    foreign key (rol_id, veterinaria_id)
    references roles (id, veterinaria_id) on delete cascade
    );

-- =====================
-- DOMINIO VETERINARIA
-- =====================

-- Perfil de veterinario dentro de una veterinaria (usuario + datos profesionales)
create table if not exists veterinarios (
                                            id bigserial primary key,
                                            veterinaria_id bigint not null references veterinarias(id) on delete cascade,
    usuario_id bigint not null references usuarios(id) on delete cascade,
    cedula varchar(50) not null,
    telefono varchar(20) not null,
    img varchar(100),
    activo boolean not null default true,
    created_at timestamptz not null default now(),
    updated_at timestamptz not null default now(),
    unique (veterinaria_id, usuario_id),
    unique (veterinaria_id, cedula)
    );

create table if not exists citas (
                                     id bigserial primary key,
                                     veterinaria_id bigint not null references veterinarias(id) on delete cascade,
    mascota_id bigint not null references mascotas(id) on delete cascade,
    veterinario_id bigint not null references veterinarios(id) on delete restrict,
    fecha_hora timestamptz not null,
    estado varchar(100) not null,
    motivo text not null,
    notas text,
    created_at timestamptz not null default now(),
    updated_at timestamptz not null default now()
    );

create table if not exists recetas (
                                       id bigserial primary key,
                                       cita_id bigint not null references citas(id) on delete cascade,
    fecha_hora timestamptz not null,
    created_at timestamptz not null default now(),
    updated_at timestamptz not null default now()
    );

create table if not exists categoria_medicamento (
                                                     id bigserial primary key,
                                                     veterinaria_id bigint not null references veterinarias(id) on delete cascade,
    nombre varchar(100) not null,
    created_at timestamptz not null default now(),
    updated_at timestamptz not null default now(),
    unique (veterinaria_id, nombre)
    );

create table if not exists medicamento (
                                           id bigserial primary key,
                                           veterinaria_id bigint not null references veterinarias(id) on delete cascade,
    categoria_id bigint not null references categoria_medicamento(id) on delete restrict,
    nombre varchar(200) not null,
    unidad varchar(100) not null,
    activo boolean not null default true,
    created_at timestamptz not null default now(),
    updated_at timestamptz not null default now(),
    unique (veterinaria_id, nombre)
    );

create table if not exists receta_item (
                                           id bigserial primary key,
                                           receta_id bigint not null references recetas(id) on delete cascade,
    medicamento_id bigint not null references medicamento(id) on delete restrict,
    dosis varchar(100) not null,
    frecuencia varchar(500) not null,
    duracion varchar(200) not null,
    cantidad int not null,
    notas text,
    created_at timestamptz not null default now(),
    updated_at timestamptz not null default now()
    );

create table if not exists medicamento_precio (
                                                  id bigserial primary key,
                                                  medicamento_id bigint not null references medicamento(id) on delete cascade,
    precio decimal(16,2) not null default 0.00,
    moneda varchar(10) not null default 'MXN',
    vigencia_desde date not null,
    vigencia_hasta date,
    created_at timestamptz not null default now(),
    updated_at timestamptz not null default now(),
    constraint chk_vigencia_fechas check (vigencia_hasta is null or vigencia_hasta >= vigencia_desde),
    unique (medicamento_id, vigencia_desde)
    );

-- =====================
-- ÍNDICES RECOMENDADOS
-- =====================

create index if not exists idx_roles_vet on roles(veterinaria_id);
create index if not exists idx_rol_permiso_rol on rol_permiso(rol_id);
create index if not exists idx_rol_permiso_permiso on rol_permiso(permiso_id);

create index if not exists idx_uv_usuario on usuario_veterinaria(usuario_id);
create index if not exists idx_uv_vet on usuario_veterinaria(veterinaria_id);

create index if not exists idx_uvrol_vet on usuario_veterinaria_rol(veterinaria_id);
create index if not exists idx_uvrol_uv on usuario_veterinaria_rol(usuario_veterinaria_id);

create index if not exists idx_veterinarios_vet on veterinarios(veterinaria_id);
create index if not exists idx_citas_vet_fecha on citas(veterinaria_id, fecha_hora);

create index if not exists idx_categoria_med_vet on categoria_medicamento(veterinaria_id);
create index if not exists idx_medicamento_vet on medicamento(veterinaria_id);

create index if not exists idx_precio_medicamento on medicamento_precio(medicamento_id);
