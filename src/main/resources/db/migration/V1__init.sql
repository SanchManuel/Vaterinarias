create table veterinarias (
                              id bigserial primary key,
                              nombre varchar(150) not null,
                              telefono varchar(50),
                              direccion text,
                              created_at timestamptz not null default now(),
                              updated_at timestamptz not null default now()
);

create table clientes (
                          id bigserial primary key,
                          veterinaria_id bigint not null references veterinarias(id),
                          nombre varchar(150) not null,
                          telefono varchar(50),
                          email varchar(150),
                          created_at timestamptz not null default now(),
                          updated_at timestamptz not null default now()
);

create table mascotas (
                          id bigserial primary key,
                          cliente_id bigint not null references clientes(id),
                          nombre varchar(150) not null,
                          especie varchar(50) not null,
                          raza varchar(80),
                          fecha_nacimiento date,
                          created_at timestamptz not null default now(),
                          updated_at timestamptz not null default now()
);

create index idx_clientes_veterinaria on clientes(veterinaria_id);
create index idx_mascotas_cliente on mascotas(cliente_id);
