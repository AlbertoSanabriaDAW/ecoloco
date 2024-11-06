drop table if exists evento;
drop table if exists perfil;
drop table if exists usuario;

create table if not exists usuario(
	id serial primary key,
	username varchar(50) not null,
	password varchar(250) not null,
	email varchar (250) not null,
	rol int
);

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

create table if not exists evento(
	id serial primary key,
	fecha timestamp,
	ubicacion varchar(250) not null,
	descripcion text,
	id_usuario int not null,
	constraint fk_evento_usuario foreign key (id_usuario) references usuario(id)
);

