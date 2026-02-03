create table veterinarias (
                              id bigserial primary key,
                              nombre varchar(150) not null,
                              telefono varchar(50) not null,
                              direccion text not null,
                              activo boolean not null default true,
                              created_at timestamptz not null default now(),
                              updated_at timestamptz not null default now()
);

create table clientes (
                          id bigserial primary key,
                          veterinaria_id bigint not null
                              references veterinarias(id) on delete cascade,
                          nombre varchar(150) not null,
                          telefono varchar(50) not null,
                          email varchar(150) not null,
                          direccion varchar(200) not null,
                          activo boolean not null default true,
                          created_at timestamptz not null default now(),
                          updated_at timestamptz not null default now(),
                          unique (veterinaria_id, email)
);

create table mascotas (
                          id bigserial primary key,
                          veterinaria_id bigint not null
                              references veterinarias(id) on delete cascade,
                          cliente_id bigint not null,
                          nombre varchar(150) not null,
                          especie varchar(50) not null,
                          raza varchar(80) not null,
                          fecha_nacimiento date not null,
                          sexo varchar(2) not null check (sexo in ('M','F')),
                          pesokg numeric(6,3) not null,
                          created_at timestamptz not null default now(),
                          updated_at timestamptz not null default now()
);

create index idx_clientes_veterinaria on clientes(veterinaria_id);
create index idx_mascotas_cliente on mascotas(cliente_id);
create index idx_mascotas_veterinaria on mascotas(veterinaria_id);
