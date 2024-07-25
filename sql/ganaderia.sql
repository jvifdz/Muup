-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 25-07-2024 a las 16:22:11
-- Versión del servidor: 10.4.18-MariaDB
-- Versión de PHP: 8.0.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `ganaderia`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `alimentos`
--

CREATE TABLE `alimentos` (
  `id_alimentos` int(3) NOT NULL,
  `nombre` varchar(50) DEFAULT NULL,
  `precio` float(8,2) DEFAULT NULL,
  `cantidad` int(11) DEFAULT NULL,
  `fechaCompra` date DEFAULT NULL,
  `fk_alimentos` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `alimentos`
--

INSERT INTO `alimentos` (`id_alimentos`, `nombre`, `precio`, `cantidad`, `fechaCompra`, `fk_alimentos`) VALUES
(4, 'paja', 322.00, 1222, '2021-06-04', 1),
(7, 'hierba', 1.00, 15000, '2021-06-17', 1),
(8, 'alfalfa', 6.58, 6000, '2021-06-18', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `animal`
--

CREATE TABLE `animal` (
  `id_animal` int(5) NOT NULL,
  `nombre` varchar(50) DEFAULT NULL,
  `fechaNacimiento` date DEFAULT NULL,
  `fechaApareamiento` date DEFAULT NULL,
  `dai_madre` varchar(20) DEFAULT NULL,
  `dai` varchar(20) DEFAULT NULL,
  `fechaBaja` date DEFAULT NULL,
  `razonBaja` varchar(50) DEFAULT NULL,
  `fk_tipoGanaderia` int(2) NOT NULL,
  `sexo` enum('Macho','Hembra') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `animal`
--

INSERT INTO `animal` (`id_animal`, `nombre`, `fechaNacimiento`, `fechaApareamiento`, `dai_madre`, `dai`, `fechaBaja`, `razonBaja`, `fk_tipoGanaderia`, `sexo`) VALUES
(70, 'yegua cardina', '2021-06-01', NULL, 'NS', 'ES34573', '2021-06-17', 'Vendido a Manuel', 2, 'Hembra'),
(71, 'potro cardino', '2021-06-03', NULL, 'ES34573', 'ES65454', '2021-06-17', 'Vendido a Manuel', 2, 'Macho'),
(72, 'Toro grande', '2021-06-07', NULL, 'NO TENGO', 'ES3245', '2021-06-17', 'muerte', 1, 'Macho'),
(73, 'toro presentacion', '2021-06-09', NULL, 'ESMADRE', 'ES64523', '2021-06-18', 'Vendido a Samuel', 1, 'Macho'),
(74, 'madre toro12', '2021-06-16', NULL, 'NO HAY', 'ESMADRE', '2021-06-18', 'Vendido a Samuel', 1, 'Hembra'),
(75, 'yegua blanca', '2021-06-07', '2021-06-18', 'ES7621612', 'YEGUAMADRE', '2021-06-18', 'perdida', 2, 'Hembra'),
(76, 'presentacion proyecto', '2021-06-01', NULL, 'NO TIENE', 'PRESENTACION', '2021-11-25', 'Vendido a Samuel', 2, 'Macho'),
(77, 'prueba', '2021-06-16', NULL, 'PRUEBA', 'PRUEBA', '2021-07-07', 'muerto', 1, 'Macho'),
(78, 'Toro semicrol', '2017-07-04', NULL, 'SEMICROL', 'ES545331', '2021-07-16', 'Vendido a Manuel', 1, 'Macho'),
(79, 'yegua', '2021-07-07', '2021-07-16', 'NINGUNO', '45545', '2021-07-16', 'Vendido a Samuel', 2, 'Hembra'),
(80, 'hija semicrol', '2021-07-09', NULL, '45545', 'HIJA SEMICROL', '2021-07-16', 'Vendido a Manuel', 2, 'Macho'),
(81, 'dsfsdf', '2021-11-10', NULL, 'DSFSDF', 'ZDFSDF', '2021-11-25', 'Vendido a Samuel', 1, 'Macho'),
(82, 'ANTONIA', '2022-04-18', '2022-04-18', 'NO TENGO', 'ES123123', '2022-04-18', 'Vendido a Samuel', 1, 'Hembra'),
(83, 'ANTONIO', '2022-04-18', NULL, 'NO TENGO', 'ANTO21123', '2022-04-18', 'CHIMAEV SMESH', 1, 'Macho');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `animalmedicamento`
--

CREATE TABLE `animalmedicamento` (
  `id_animalMedicamento` int(5) NOT NULL,
  `fk_animal` int(5) NOT NULL,
  `fk_medicamento` int(3) NOT NULL,
  `fechaVacunacion` date DEFAULT NULL,
  `numDosis` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `animalmedicamento`
--

INSERT INTO `animalmedicamento` (`id_animalMedicamento`, `fk_animal`, `fk_medicamento`, `fechaVacunacion`, `numDosis`) VALUES
(32, 73, 8, '2021-06-17', 1),
(33, 76, 8, '2021-06-18', 1),
(34, 77, 8, '2021-06-18', 1),
(35, 76, 13, '2021-07-16', 2),
(36, 77, 13, '2021-07-16', 2),
(37, 78, 13, '2021-07-16', 2),
(38, 79, 13, '2021-07-16', 2),
(39, 81, 12, '2021-11-25', 3),
(40, 82, 13, '2022-04-18', 1),
(41, 83, 13, '2022-04-18', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cliente`
--

CREATE TABLE `cliente` (
  `id_cliente` int(3) NOT NULL,
  `dni` varchar(9) DEFAULT NULL,
  `direccion` varchar(50) DEFAULT NULL,
  `codPostal` int(5) DEFAULT NULL,
  `nombre` varchar(50) DEFAULT NULL,
  `telefono` int(11) DEFAULT NULL,
  `correo` varchar(50) DEFAULT NULL,
  `codExplotacion` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `cliente`
--

INSERT INTO `cliente` (`id_cliente`, `dni`, `direccion`, `codPostal`, `nombre`, `telefono`, `correo`, `codExplotacion`) VALUES
(3, '7654362K', 'Maliaño', 76543, 'Manuel', 65432341, 'manu@correo.com', '764234234'),
(5, '71249957R', 'maliaño', 67458, 'Samuel', 645114209, 'javiason@gmail.com', 'ES5435727323');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `datosnegocio`
--

CREATE TABLE `datosnegocio` (
  `id_datosNegocio` int(3) NOT NULL,
  `dni` varchar(9) NOT NULL,
  `direccion` varchar(50) DEFAULT NULL,
  `codPostal` int(5) DEFAULT NULL,
  `nombre` varchar(50) DEFAULT NULL,
  `telefono` int(11) DEFAULT NULL,
  `correo` varchar(50) DEFAULT NULL,
  `codExplotacion` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `datosnegocio`
--

INSERT INTO `datosnegocio` (`id_datosNegocio`, `dni`, `direccion`, `codPostal`, `nombre`, `telefono`, `correo`, `codExplotacion`) VALUES
(2, '71349957R', 'Padierniga voto', 39006, 'Javier Fernandez Perez', 645114209, 'javiason@gmail.com', 'ES324234234234');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `detallefactura`
--

CREATE TABLE `detallefactura` (
  `id_detalle` int(5) NOT NULL,
  `fk_factura` int(5) NOT NULL,
  `fk_animal` int(5) NOT NULL,
  `precio` float(8,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `detallefactura`
--

INSERT INTO `detallefactura` (`id_detalle`, `fk_factura`, `fk_animal`, `precio`) VALUES
(25, 20, 70, 245.00),
(26, 20, 71, 245.00),
(27, 21, 73, 450.00),
(28, 21, 74, 450.00),
(29, 22, 79, 500.00),
(30, 23, 78, 300.00),
(31, 23, 80, 300.00),
(32, 24, 76, 300.00),
(33, 24, 81, 300.00),
(34, 25, 82, 350.00);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `enfermedad`
--

CREATE TABLE `enfermedad` (
  `id_enfermedad` int(3) NOT NULL,
  `nombre` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `enfermedad`
--

INSERT INTO `enfermedad` (`id_enfermedad`, `nombre`) VALUES
(1, 'Mamitis'),
(8, 'Pata rota'),
(10, 'vaca loca'),
(19, 'ojo lloroso'),
(20, 'cojera'),
(21, 'catarro'),
(22, 'catarro'),
(23, 'rara');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `factura`
--

CREATE TABLE `factura` (
  `id_factura` int(5) NOT NULL,
  `fechaVenta` date DEFAULT NULL,
  `precioTotal` float(8,2) DEFAULT NULL,
  `IVA` float(8,2) DEFAULT NULL,
  `fk_cliente` int(3) NOT NULL,
  `fk_datosNegocio` int(3) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `factura`
--

INSERT INTO `factura` (`id_factura`, `fechaVenta`, `precioTotal`, `IVA`, `fk_cliente`, `fk_datosNegocio`) VALUES
(20, '2021-06-17', 490.00, 51.45, 3, 2),
(21, '2021-06-18', 900.00, 94.50, 5, 2),
(22, '2021-07-16', 500.00, 105.00, 5, 2),
(23, '2021-07-16', 600.00, 63.00, 3, 2),
(24, '2021-11-25', 600.00, 63.00, 5, 2),
(25, '2022-04-18', 350.00, 73.50, 5, 2);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `historialenf`
--

CREATE TABLE `historialenf` (
  `id_historialEnf` int(5) NOT NULL,
  `fechaEnfermedad` date DEFAULT NULL,
  `fk_animal` int(5) NOT NULL,
  `fk_enfermedad` int(3) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `historialenf`
--

INSERT INTO `historialenf` (`id_historialEnf`, `fechaEnfermedad`, `fk_animal`, `fk_enfermedad`) VALUES
(62, '2021-06-17', 73, 8),
(63, '2021-06-18', 76, 20),
(64, '2021-06-18', 77, 20),
(65, '2021-07-16', 80, 23);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `historialparcela`
--

CREATE TABLE `historialparcela` (
  `id_historialParcela` int(5) NOT NULL,
  `fechaEntrada` date DEFAULT NULL,
  `fechaSalida` date DEFAULT NULL,
  `fk_animal` int(5) NOT NULL,
  `fk_parcela` int(3) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `historialparcela`
--

INSERT INTO `historialparcela` (`id_historialParcela`, `fechaEntrada`, `fechaSalida`, `fk_animal`, `fk_parcela`) VALUES
(90, '2021-06-17', '2021-06-17', 70, 11),
(91, '2021-06-17', '2021-06-17', 71, 11),
(92, '2021-06-17', '2021-06-17', 72, 15),
(93, '2021-06-18', '2021-06-25', 73, 16),
(94, '2021-06-18', '2021-06-25', 74, 16),
(95, '2021-06-18', '2021-06-25', 75, 16),
(96, '2021-06-25', '2021-06-18', 73, 15),
(97, '2021-06-25', '2021-06-18', 74, 15),
(98, '2021-06-25', '2021-06-18', 75, 15),
(99, '2021-06-18', '2021-07-16', 76, 14),
(100, '2021-06-18', '2021-07-16', 77, 14),
(101, '2021-07-16', '2021-07-16', 76, 17),
(102, '2021-07-16', '2021-07-07', 77, 17),
(103, '2021-07-16', '2021-07-16', 78, 17),
(104, '2021-07-16', '2021-07-16', 79, 17),
(105, '2021-07-16', '2021-07-16', 79, 15),
(106, '2021-07-16', '2021-11-25', 76, 14),
(107, '2021-07-16', '2021-07-16', 78, 14),
(108, '2021-07-16', '2021-07-16', 80, 14),
(109, '2021-11-25', '2021-11-25', 76, 14),
(110, '2021-11-25', '2021-11-25', 81, 14),
(111, '2021-11-25', '2021-11-25', 81, 11),
(112, '2022-04-18', '2022-04-18', 82, 14),
(113, '2022-04-18', '2022-04-18', 83, 14),
(114, '2022-04-18', '2022-04-18', 82, 17),
(115, '2022-04-18', '2022-04-18', 83, 17);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `medicamento`
--

CREATE TABLE `medicamento` (
  `id_medicamento` int(3) NOT NULL,
  `nombre` varchar(50) DEFAULT NULL,
  `volumen` int(11) DEFAULT NULL,
  `volumenDosis` int(11) DEFAULT NULL,
  `precio` float(8,2) DEFAULT NULL,
  `fechaCompra` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `medicamento`
--

INSERT INTO `medicamento` (`id_medicamento`, `nombre`, `volumen`, `volumenDosis`, `precio`, `fechaCompra`) VALUES
(8, 'sabiopen', 1220, 1192, 39.90, '2021-06-01'),
(10, 'moscas', 122, 0, 34.40, '2021-06-04'),
(12, 'pastillas', 600, 597, 655.00, '2021-06-18'),
(13, 'desabiopen', 1, 30, 60.00, '2021-07-16');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `parcela`
--

CREATE TABLE `parcela` (
  `id_parcela` int(3) NOT NULL,
  `nombre` varchar(50) DEFAULT NULL,
  `lugarSituada` varchar(100) DEFAULT NULL,
  `tamano` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `parcela`
--

INSERT INTO `parcela` (`id_parcela`, `nombre`, `lugarSituada`, `tamano`) VALUES
(11, 'monte pico negro', 'junta de voto', 10000),
(13, 'monte peña ventana', 'Padierniga', 890),
(14, 'cabarga', 'astillero', 1111110),
(15, 'agl', 'santander', 10000),
(16, 'parcela 1', 'peña cabarga', 688),
(17, 'semicrol', 'santander', 14);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipoganaderia`
--

CREATE TABLE `tipoganaderia` (
  `id_tipoGanaderia` int(2) NOT NULL,
  `nombre` varchar(50) DEFAULT NULL,
  `tiempoGestacion` int(3) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `tipoganaderia`
--

INSERT INTO `tipoganaderia` (`id_tipoGanaderia`, `nombre`, `tiempoGestacion`) VALUES
(1, 'Bovino', 9),
(2, 'Equino', 11),
(5, 'caprino', 6),
(6, 'elefantes', 12);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `alimentos`
--
ALTER TABLE `alimentos`
  ADD PRIMARY KEY (`id_alimentos`),
  ADD KEY `fk_alimentos` (`fk_alimentos`);

--
-- Indices de la tabla `animal`
--
ALTER TABLE `animal`
  ADD PRIMARY KEY (`id_animal`),
  ADD KEY `fk_tipoGanaderia` (`fk_tipoGanaderia`);

--
-- Indices de la tabla `animalmedicamento`
--
ALTER TABLE `animalmedicamento`
  ADD PRIMARY KEY (`id_animalMedicamento`),
  ADD KEY `fk_animal` (`fk_animal`),
  ADD KEY `fk_medicamento` (`fk_medicamento`);

--
-- Indices de la tabla `cliente`
--
ALTER TABLE `cliente`
  ADD PRIMARY KEY (`id_cliente`);

--
-- Indices de la tabla `datosnegocio`
--
ALTER TABLE `datosnegocio`
  ADD PRIMARY KEY (`id_datosNegocio`);

--
-- Indices de la tabla `detallefactura`
--
ALTER TABLE `detallefactura`
  ADD PRIMARY KEY (`id_detalle`),
  ADD KEY `fk_factura` (`fk_factura`),
  ADD KEY `fk_animal` (`fk_animal`);

--
-- Indices de la tabla `enfermedad`
--
ALTER TABLE `enfermedad`
  ADD PRIMARY KEY (`id_enfermedad`);

--
-- Indices de la tabla `factura`
--
ALTER TABLE `factura`
  ADD PRIMARY KEY (`id_factura`),
  ADD KEY `fk_cliente` (`fk_cliente`),
  ADD KEY `fk_datosNegocio` (`fk_datosNegocio`);

--
-- Indices de la tabla `historialenf`
--
ALTER TABLE `historialenf`
  ADD PRIMARY KEY (`id_historialEnf`),
  ADD KEY `fk_animal` (`fk_animal`),
  ADD KEY `fk_enfermedad` (`fk_enfermedad`);

--
-- Indices de la tabla `historialparcela`
--
ALTER TABLE `historialparcela`
  ADD PRIMARY KEY (`id_historialParcela`),
  ADD KEY `fk_animal` (`fk_animal`),
  ADD KEY `fk_parcela` (`fk_parcela`);

--
-- Indices de la tabla `medicamento`
--
ALTER TABLE `medicamento`
  ADD PRIMARY KEY (`id_medicamento`);

--
-- Indices de la tabla `parcela`
--
ALTER TABLE `parcela`
  ADD PRIMARY KEY (`id_parcela`);

--
-- Indices de la tabla `tipoganaderia`
--
ALTER TABLE `tipoganaderia`
  ADD PRIMARY KEY (`id_tipoGanaderia`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `alimentos`
--
ALTER TABLE `alimentos`
  MODIFY `id_alimentos` int(3) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT de la tabla `animal`
--
ALTER TABLE `animal`
  MODIFY `id_animal` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=84;

--
-- AUTO_INCREMENT de la tabla `animalmedicamento`
--
ALTER TABLE `animalmedicamento`
  MODIFY `id_animalMedicamento` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=42;

--
-- AUTO_INCREMENT de la tabla `cliente`
--
ALTER TABLE `cliente`
  MODIFY `id_cliente` int(3) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT de la tabla `datosnegocio`
--
ALTER TABLE `datosnegocio`
  MODIFY `id_datosNegocio` int(3) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `detallefactura`
--
ALTER TABLE `detallefactura`
  MODIFY `id_detalle` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=35;

--
-- AUTO_INCREMENT de la tabla `enfermedad`
--
ALTER TABLE `enfermedad`
  MODIFY `id_enfermedad` int(3) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=24;

--
-- AUTO_INCREMENT de la tabla `factura`
--
ALTER TABLE `factura`
  MODIFY `id_factura` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=26;

--
-- AUTO_INCREMENT de la tabla `historialenf`
--
ALTER TABLE `historialenf`
  MODIFY `id_historialEnf` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=66;

--
-- AUTO_INCREMENT de la tabla `historialparcela`
--
ALTER TABLE `historialparcela`
  MODIFY `id_historialParcela` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=116;

--
-- AUTO_INCREMENT de la tabla `medicamento`
--
ALTER TABLE `medicamento`
  MODIFY `id_medicamento` int(3) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT de la tabla `parcela`
--
ALTER TABLE `parcela`
  MODIFY `id_parcela` int(3) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- AUTO_INCREMENT de la tabla `tipoganaderia`
--
ALTER TABLE `tipoganaderia`
  MODIFY `id_tipoGanaderia` int(2) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `alimentos`
--
ALTER TABLE `alimentos`
  ADD CONSTRAINT `alimentos_ibfk_1` FOREIGN KEY (`fk_alimentos`) REFERENCES `tipoganaderia` (`id_tipoGanaderia`);

--
-- Filtros para la tabla `animal`
--
ALTER TABLE `animal`
  ADD CONSTRAINT `animal_ibfk_1` FOREIGN KEY (`fk_tipoGanaderia`) REFERENCES `tipoganaderia` (`id_tipoGanaderia`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `animalmedicamento`
--
ALTER TABLE `animalmedicamento`
  ADD CONSTRAINT `animalmedicamento_ibfk_1` FOREIGN KEY (`fk_animal`) REFERENCES `animal` (`id_animal`),
  ADD CONSTRAINT `animalmedicamento_ibfk_2` FOREIGN KEY (`fk_medicamento`) REFERENCES `medicamento` (`id_medicamento`);

--
-- Filtros para la tabla `detallefactura`
--
ALTER TABLE `detallefactura`
  ADD CONSTRAINT `detallefactura_ibfk_1` FOREIGN KEY (`fk_factura`) REFERENCES `factura` (`id_factura`) ON UPDATE CASCADE,
  ADD CONSTRAINT `detallefactura_ibfk_2` FOREIGN KEY (`fk_animal`) REFERENCES `animal` (`id_animal`) ON UPDATE CASCADE;

--
-- Filtros para la tabla `factura`
--
ALTER TABLE `factura`
  ADD CONSTRAINT `factura_ibfk_1` FOREIGN KEY (`fk_cliente`) REFERENCES `cliente` (`id_cliente`) ON UPDATE CASCADE,
  ADD CONSTRAINT `factura_ibfk_2` FOREIGN KEY (`fk_datosNegocio`) REFERENCES `datosnegocio` (`id_datosNegocio`) ON UPDATE CASCADE;

--
-- Filtros para la tabla `historialenf`
--
ALTER TABLE `historialenf`
  ADD CONSTRAINT `historialenf_ibfk_1` FOREIGN KEY (`fk_animal`) REFERENCES `animal` (`id_animal`) ON UPDATE CASCADE,
  ADD CONSTRAINT `historialenf_ibfk_2` FOREIGN KEY (`fk_enfermedad`) REFERENCES `enfermedad` (`id_enfermedad`) ON UPDATE CASCADE;

--
-- Filtros para la tabla `historialparcela`
--
ALTER TABLE `historialparcela`
  ADD CONSTRAINT `historialparcela_ibfk_1` FOREIGN KEY (`fk_animal`) REFERENCES `animal` (`id_animal`) ON UPDATE CASCADE,
  ADD CONSTRAINT `historialparcela_ibfk_2` FOREIGN KEY (`fk_parcela`) REFERENCES `parcela` (`id_parcela`) ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
