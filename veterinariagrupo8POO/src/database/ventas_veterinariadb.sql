-- Crear base de datos
CREATE DATABASE IF NOT EXISTS ventas_veterinariadb;
USE ventas_veterinariadb;

-- Tabla Cliente
CREATE TABLE Cliente (
    ID_Cliente INT AUTO_INCREMENT PRIMARY KEY,
    Nombre VARCHAR(100),
    Apellido VARCHAR(100),
    Dni INT
);

-- Tabla Usuario
CREATE TABLE Usuario (
    ID_Usuario INT AUTO_INCREMENT PRIMARY KEY,
    Usuario VARCHAR(100),
    Contrasena VARCHAR(100),
    Rol VARCHAR(50)
);

-- Tabla Producto
CREATE TABLE Producto (
    ID_Producto INT AUTO_INCREMENT PRIMARY KEY,
    Nombre VARCHAR(100),
    Descripcion TEXT,
    Categoria VARCHAR(100),
    Precio DECIMAL(10,2),
    Stock INT,
    Estado VARCHAR(50)
);

-- Tabla Venta
CREATE TABLE Venta (
    ID_Venta INT AUTO_INCREMENT PRIMARY KEY,
    Fecha DATE,
    Total DECIMAL(10,2),
    ID_Cliente INT,
    ID_Usuario INT,
    FOREIGN KEY (ID_Cliente) REFERENCES Cliente(ID_Cliente),
    FOREIGN KEY (ID_Usuario) REFERENCES Usuario(ID_Usuario)
);

-- Tabla Detalle_Venta
CREATE TABLE Detalle_Venta (
    ID_DetalleVenta INT AUTO_INCREMENT PRIMARY KEY,
    ID_Venta INT,
    ID_Producto INT,
    Cantidad INT,
    Precio DECIMAL(10,2),
    Total DECIMAL(10,2),
    FOREIGN KEY (ID_Venta) REFERENCES Venta(ID_Venta),
    FOREIGN KEY (ID_Producto) REFERENCES Producto(ID_Producto)
);

-- Tabla Ingreso_Producto
CREATE TABLE Ingreso_Producto (
    ID_Ingreso INT AUTO_INCREMENT PRIMARY KEY,
    Fecha DATE,
    ID_Usuario INT,
    FOREIGN KEY (ID_Usuario) REFERENCES Usuario(ID_Usuario)
);

-- Tabla Detalle_Ingreso
CREATE TABLE Detalle_Ingreso (
    ID_DetalleIngreso INT AUTO_INCREMENT PRIMARY KEY,
    ID_Ingreso INT,
    ID_Producto INT,
    Nombre VARCHAR(100),
    Cantidad INT,
    Categoria VARCHAR(100),
    Precio_Compra DECIMAL(10,2),
    FOREIGN KEY (ID_Ingreso) REFERENCES Ingreso_Producto(ID_Ingreso),
    FOREIGN KEY (ID_Producto) REFERENCES Producto(ID_Producto)
);

-- Insertar datos en Cliente
INSERT INTO Cliente (Nombre, Apellido, Dni) VALUES
('Carlos', 'Domínguez', 12345678),
('Lucía', 'Martínez', 23456789),
('Andrés', 'Vega', 34567890),
('Patricia', 'Salazar', 45678901),
('Jorge', 'Ríos', 56789012);

-- Insertar datos en Usuario
INSERT INTO Usuario (Usuario, Contrasena, Rol) VALUES
('vet_admin', 'admin123', 'Administrador'),
('recepcion1', 'clave123', 'Recepcionista'),
('almacen1', 'almacen123', 'Almacenero'),
('doctor1', 'medico123', 'Veterinario'),
('ventas1', 'ventas123', 'Vendedor');

-- Insertar datos en Producto
INSERT INTO Producto (Nombre, Descripcion, Categoria, Precio, Stock, Estado) VALUES
('Vacuna Rabia', 'Vacuna antirrábica para caninos y felinos', 'Medicamento', 45.00, 30, 'Activo'),
('Alimento Pro Plan', 'Alimento seco para perros adultos 15kg', 'Alimento', 180.00, 20, 'Activo'),
('Juguete Masticable', 'Juguete para perros con goma resistente', 'Accesorio', 35.00, 50, 'Activo'),
('Antipulgas NexGard', 'Tableta masticable contra pulgas y garrapatas', 'Medicamento', 90.00, 40, 'Activo'),
('Shampoo Canino', 'Shampoo dermatológico para perros', 'Higiene', 28.00, 25, 'Activo');

-- Insertar datos en Venta
INSERT INTO Venta (Fecha, Total, ID_Cliente, ID_Usuario) VALUES
('2025-05-20', 225.00, 1, 1),
('2025-05-21', 90.00, 2, 2),
('2025-05-22', 63.00, 3, 3),
('2025-05-23', 180.00, 4, 4),
('2025-05-24', 45.00, 5, 5);

-- Insertar datos en Detalle_Venta
INSERT INTO Detalle_Venta (ID_Venta, ID_Producto, Cantidad, Precio, Total) VALUES
(1, 1, 1, 45.00, 45.00),
(1, 2, 1, 180.00, 180.00),
(2, 4, 1, 90.00, 90.00),
(3, 3, 1, 35.00, 35.00),
(3, 5, 1, 28.00, 28.00);

-- Insertar datos en Ingreso_Producto
INSERT INTO Ingreso_Producto (Fecha, ID_Usuario) VALUES
('2025-05-10', 1),
('2025-05-11', 2),
('2025-05-12', 3),
('2025-05-13', 4),
('2025-05-14', 5);

-- Insertar datos en Detalle_Ingreso
INSERT INTO Detalle_Ingreso (ID_Ingreso, ID_Producto, Nombre, Cantidad, Categoria, Precio_Compra) VALUES
(1, 1, 'Vacuna Rabia', 50, 'Medicamento', 30.00),
(2, 2, 'Alimento Pro Plan', 10, 'Alimento', 150.00),
(3, 3, 'Juguete Masticable', 60, 'Accesorio', 20.00),
(4, 4, 'Antipulgas NexGard', 40, 'Medicamento', 70.00),
(5, 5, 'Shampoo Canino', 30, 'Higiene', 20.00);

