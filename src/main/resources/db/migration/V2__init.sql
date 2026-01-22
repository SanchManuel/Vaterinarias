create table usuarios(
    id bigserial primary key,
    veterinaria_id bigint not null references veterinarias(id),
    nombre varchar(100) not null,
    email varchar(200) not null unique,
    password varchar(200) not null,
    rol varchar(100) not null ,
    activo boolean,
    created_at timestamptz not null default now(),
    updated_at timestamptz not null default now()
);

create table veterinarios (
    id bigserial primary key,
    veterinaria_id bigint not null references veterinarias(id),
    nombre varchar(100) not null,
    cedula varchar(50) not null,
    telefono varchar(20) not null,
    img varchar(100) not null,
    activo boolean,
    created_at timestamptz not null default now(),
    updated_at timestamptz not null default now()
);

create table citas(
    id bigserial primary key,
    veterinaria_id bigint not null references veterinarias(id),
    mascota_id bigint not null references mascotas(id),
    veterinario_id bigint not null  references veterinarios(id),
    fecha_hora timestamptz not null,
    estado varchar(100) not null,
    motivo text not null,
    notas text not null,
    created_at timestamptz not null default now(),
    updated_at timestamptz not null default now()
);

create table recetas(
    id bigserial primary key,
    cita_id bigint not null  references citas(id),
    fecha_hora timestamptz not null,
    created_at timestamptz not null default now(),
    updated_at timestamptz not null default now()
);

create table categoria_medicamento(
    id bigserial primary key,
    veterinaria_id bigint not null references veterinarias(id),
    nombre varchar(100) not null,
    created_at timestamptz not null default now(),
    updated_at timestamptz not null default now()
);

create table medicamento(
    id bigserial primary key,
    veterinaria_id bigint not null references veterinarias(id),
    categoria_id bigint not null references categoria_medicamento(id),
    nombre varchar(200) not null,
    unidad varchar(100) not null,
    activo boolean,
    created_at timestamptz not null default now(),
    updated_at timestamptz not null default now()
);

create table receta_item(
    id bigserial primary key,
    receta_id bigint not null references recetas(id),
    medicamento_id bigint not null references medicamento(id),
    dosis varchar(100) not null,
    fercuencia varchar(500) not null,
    duracion varchar(200) not null,
    cantidad int not null,
    notas text,
    created_at timestamptz not null default now(),
    updated_at timestamptz not null default now()
);

create table medicamento_precio(
    id bigserial primary key,
    medicamento_id bigint not null references medicamento(id),
    precio decimal(16,2) NOT NULL DEFAULT 0.00,
    moneda varchar(10) not null DEFAULT 'MXN',
    vigencia_desde DATE not null,
    vigencia_hasta DATE not null,
    created_at timestamptz not null default now(),
    updated_at timestamptz not null default now()
);
