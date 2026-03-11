CREATE TABLE usuarios (
	id_usuario INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(200) NOT NULL,
    correo VARCHAR(100) NOT NULL UNIQUE,
    contrasena VARCHAR(200) NOT NULL,
    tipo VARCHAR(20) NOT NULL
);
CREATE TABLE libros (
	id_libro INT PRIMARY KEY AUTO_INCREMENT,
    titulo VARCHAR(200),
    autor VARCHAR(100),
    editorial VARCHAR(100),
    ano_publicacion CHAR(4),
    categoria VARCHAR(50),
    disponibilidad VARCHAR(20)
);
CREATE TABLE prestamos (
	id_prestamo INT PRIMARY KEY AUTO_INCREMENT,
    fecha_prestamo DATE,
    fecha_devolucion DATE,
    estado VARCHAR(20) NULL,
    id_usuario INT,
    id_libro INT,

    CONSTRAINT FK_PRESTAMO_USUARIO FOREIGN KEY (id_usuario) REFERENCES usuarios(id_usuario),
    CONSTRAINT FK_PRESTAMO_LIBRO FOREIGN KEY (id_libro) REFERENCES libros(id_libro)
);

CREATE TABLE reservas (
	id_reserva INT PRIMARY KEY AUTO_INCREMENT,
    fecha_inicio DATE,
    fecha_fin DATE,
    estado VARCHAR(20),
    id_usuario INT NOT NULL,
    id_libro INT NOT NULL,
    CONSTRAINT FK_RESERVA_USUARIO FOREIGN KEY (id_usuario) REFERENCES usuarios(id_usuario),
    CONSTRAINT FK_RESERVA_LIBRO FOREIGN KEY (id_libro) REFERENCES libros(id_libro)
);