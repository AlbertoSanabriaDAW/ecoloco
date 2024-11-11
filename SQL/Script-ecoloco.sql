-- Drops de todas las tablas
drop table if exists usuario_evento cascade;
drop table if exists aptitud_evento cascade;
drop table if exists aptitud_perfil cascade;
drop table if exists aptitud cascade;
drop table if exists evento cascade;
drop table if exists perfil cascade;
drop table if exists usuario cascade;

-- Creacion de la tabla relacion `usuario`
create table if not exists usuario(
	id serial primary key,
	username varchar(50) not null,
	password varchar(250) not null,
	email varchar (250) not null,
	rol int not null
);

-- Creacion de la tabla relacion `perfil`
create table if not exists perfil(
	id serial primary key,
	nombre varchar(50) not null,
	apellidos varchar(250) not null,
	telefono varchar(50) not null,
	dni varchar(20) not null unique,
	fecha_nacimiento date,
	id_usuario int not null,
	constraint fk_perfil_usuario foreign key (id_usuario) references usuario(id)
);

-- Creacion de la tabla relacion `evento`
create table if not exists evento(
	id serial primary key,
	fecha timestamp,
	ubicacion varchar(250) not null,
	descripcion text,
    imagen varchar(500),
	id_usuario int not null,
	constraint fk_evento_usuario foreign key (id_usuario) references usuario(id)
);

-- Creacion de la tabla relacion `aptitud`
create table if not exists aptitud(
	id serial primary key,
	nombre varchar(100) not null
);

-- Creacion de la tabla relacion `aptitud_perfil`
create table if not exists aptitud_perfil(
	id serial primary key,
	id_aptitud int not null,
	id_perfil int not null,
	constraint fk_aptitud_perfil_perfil foreign key (id_perfil) references perfil(id),
	constraint fk_aptitud_perfil_aptitud foreign key (id_aptitud) references aptitud(id)
);

-- Creacion de la tabla relacion `aptitud_evento`
create table if not exists aptitud_evento(
	id serial primary key,
	id_aptitud int not null,
	id_evento int not null,
	constraint fk_aptitud_evento_evento foreign key (id_evento) references evento(id),
	constraint fk_aptitud_evento_aptitud foreign key (id_aptitud) references aptitud(id)
);

-- Creacion de la tabla relacion `usuario_evento`
create table if not exists usuario_evento(
	id serial primary key,
	id_usuario int not null,
	id_evento int not null,
	constraint fk_usuario_evento_evento foreign key (id_evento) references evento(id),
	constraint fk_usuario_evento_usuario foreign key (id_usuario) references usuario(id)
);

-- Inserción de datos en la tabla `usuario`
INSERT INTO usuario (id, username, password, email, rol) VALUES
(1, 'jgarcia', 'pass123', 'jgarcia@example.com', 1),
(2, 'pmartinez', 'pass456', 'pmartinez@example.com', 0),
(3, 'llopez', 'pass789', 'llopez@example.com', 1),
(4, 'fhernandez', 'pass101', 'fhernandez@example.com', 1);

-- Inserción de datos en la tabla `perfil`
INSERT INTO perfil (id, nombre, apellidos, telefono, dni, fecha_nacimiento, id_usuario) VALUES
(1, 'Juan', 'García', '612345678', '12345678A', '1985-06-12', 1),
(2, 'Pedro', 'Martínez', '623456789', '23456789B', '1990-04-15', 2),
(3, 'Laura', 'López', '634567890', '34567890C', '1992-11-22', 3),
(4, 'Francisco', 'Hernández', '645678901', '45678901D', '1988-03-05', 4);

-- Inserción de datos en la tabla `aptitud`
INSERT INTO aptitud (id, nombre) VALUES
(1, 'Jardinería'),
(2, 'Conducción'),
(3, 'Primeros Auxilios'),
(4, 'Cocina'),
(5, 'Electricidad');

-- Inserción de datos en la tabla `evento`
INSERT INTO evento (id, fecha, ubicacion, descripcion, imagen, id_usuario) VALUES
(1, '2023-09-15', 'Madrid', 'Evento de reforestación', 'foto.png', 1),
(2, '2023-10-01', 'Barcelona', 'Taller de primeros auxilios', 'foto.png', 2),
(3, '2023-11-20', 'Valencia', 'Distribución de alimentos', 'foto.png', 3),
(4, '2023-12-10', 'Sevilla', 'Taller de jardinería urbana', 'foto.pnj', 4);

-- Inserción de datos en la tabla `aptitud_perfil`
INSERT INTO aptitud_perfil (id, id_aptitud, id_perfil) VALUES
(1, 1, 1),
(2, 3, 1),
(3, 2, 2),
(4, 4, 3),
(5, 5, 4);

-- Inserción de datos en la tabla `aptitud_evento`
INSERT INTO aptitud_evento (id, id_aptitud, id_evento) VALUES
(1, 1, 1),
(2, 3, 2),
(3, 4, 3),
(4, 1, 4);

-- Inserción de datos en la tabla `usuario_evento`
INSERT INTO usuario_evento (id, id_usuario, id_evento) VALUES
(1, 1, 1),
(2, 3, 1),
(3, 2, 2),
(4, 3, 3),
(5, 4, 4);



