-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Servidor: localhost
-- Tiempo de generación: 22-11-2021 a las 09:16:18
-- Versión del servidor: 10.4.20-MariaDB
-- Versión de PHP: 8.0.8

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `dbfactura`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `clientes`
--

CREATE TABLE `clientes` (
  `ID` char(7) NOT NULL,
  `nombre` varchar(60) NOT NULL,
  `direccion` varchar(100) NOT NULL,
  `persona_telefono` varchar(12) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `clientes`
--

INSERT INTO `clientes` (`ID`, `nombre`, `direccion`, `persona_telefono`) VALUES
('1234567', 'Oscar Nivia', 'Calle estructura #20-21', '321-43259826'),
('8734629', 'Alexander Castañeda', 'Calle 85 #77-11', '319-43729471'),
('9263845', 'Sarahi Marien Lozano', 'Avenida El Dorado Color #26-55', '316-59836701');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `detalle`
--

CREATE TABLE `detalle` (
  `cantidad` int(11) DEFAULT 0,
  `precioU` float DEFAULT 0,
  `total` float DEFAULT 0,
  `id_producto` char(10) DEFAULT NULL,
  `num_factura` varchar(10) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `detalle`
--

INSERT INTO `detalle` (`cantidad`, `precioU`, `total`, `id_producto`, `num_factura`) VALUES
(2, 70000, 140000, 'VMp01A', 'FACT-JUCKC');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `factura`
--

CREATE TABLE `factura` (
  `NUM` varchar(10) NOT NULL,
  `f_Registro` timestamp NOT NULL DEFAULT current_timestamp(),
  `vendedor` varchar(60) DEFAULT NULL,
  `Observacion` varchar(200) DEFAULT NULL,
  `id_cliente` char(7) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `factura`
--

INSERT INTO `factura` (`NUM`, `f_Registro`, `vendedor`, `Observacion`, `id_cliente`) VALUES
('FACT-UYEXK', '2021-11-22 07:28:53', 'Ramirez Carlos', '', '1234567'),
('FACT-JUCKC', '2021-11-22 08:13:54', 'Ramirez Carlos', '', '8734629');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `producto`
--

CREATE TABLE `producto` (
  `ID` char(10) NOT NULL,
  `nombre` varchar(60) NOT NULL,
  `precioU` float DEFAULT 0,
  `cantidad` int(11) DEFAULT 0
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `producto`
--

INSERT INTO `producto` (`ID`, `nombre`, `precioU`, `cantidad`) VALUES
('VMp01A', 'Balón Baloncesto Spalding Gold', 70000, 47),
('VMp02A', 'Red Malla Tenis De Campo', 303000, 19),
('VMp03A', 'Balón Voleibol Molten V5m 5000', 284900, 2),
('VMp04A', 'Raqueta profesional Bádminton', 89000, 5);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `clientes`
--
ALTER TABLE `clientes`
  ADD PRIMARY KEY (`ID`);

--
-- Indices de la tabla `detalle`
--
ALTER TABLE `detalle`
  ADD KEY `id_producto` (`id_producto`),
  ADD KEY `num_factura` (`num_factura`);

--
-- Indices de la tabla `factura`
--
ALTER TABLE `factura`
  ADD PRIMARY KEY (`NUM`),
  ADD KEY `id_cliente` (`id_cliente`);

--
-- Indices de la tabla `producto`
--
ALTER TABLE `producto`
  ADD PRIMARY KEY (`ID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
