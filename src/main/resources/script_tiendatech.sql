-- Crear la base de datos
CREATE DATABASE IF NOT EXISTS tiendatech;
USE tiendatech;

-- Crear la tabla 'administrador'
CREATE TABLE `administrador` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `nombre_completo` varchar(100) DEFAULT NULL,
  `email` varchar(100) NOT NULL,
  `clave` varchar(255) NOT NULL,
  `foto` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Crear la tabla 'categoria'
CREATE TABLE `categoria` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) NOT NULL,
  `imagen` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Crear la tabla 'inicio'
CREATE TABLE `inicio` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `titulo` varchar(100) DEFAULT NULL,
  `bienvenida_texto` text,
  `imagen_banner` text,
  `ubicacion_mapa` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Crear la tabla 'oferta'
CREATE TABLE `oferta` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `titulo` varchar(100) DEFAULT NULL,
  `descripcion` text,
  `descuento` decimal(5,2) DEFAULT NULL,
  `fecha_inicio` date DEFAULT NULL,
  `fecha_fin` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Crear la tabla 'producto'
CREATE TABLE `producto` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) NOT NULL,
  `descripcion` text,
  `precio` decimal(10,2) NOT NULL,
  `stock` int NOT NULL,
  `id_categoria` bigint DEFAULT NULL,
  `imagen` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id_categoria` (`id_categoria`),
  CONSTRAINT `producto_ibfk_1` FOREIGN KEY (`id_categoria`) REFERENCES `categoria` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Crear la tabla 'producto_oferta'
CREATE TABLE `producto_oferta` (
  `id_producto` bigint NOT NULL,
  `id_oferta` bigint NOT NULL,
  PRIMARY KEY (`id_producto`,`id_oferta`),
  KEY `fk_oferta` (`id_oferta`),
  CONSTRAINT `fk_oferta` FOREIGN KEY (`id_oferta`) REFERENCES `oferta` (`id`) ON DELETE CASCADE,
  CONSTRAINT `fk_producto` FOREIGN KEY (`id_producto`) REFERENCES `producto` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Crear la tabla 'usuario'
CREATE TABLE `usuario` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `nombre_completo` varchar(100) DEFAULT NULL,
  `email` varchar(100) NOT NULL,
  `clave` varchar(255) NOT NULL,
  `direccion` text,
  `telefono` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


INSERT INTO inicio (titulo, bienvenida_texto, imagen_banner, ubicacion_mapa)
VALUES ('Bienvenido a TiendaTech', 'Explora la mejor tecnología a precios accesibles.', 'tecnologia.jpg', 'https://maps.google.com/maps?q=San%20José%20Costa%20Rica&t=&z=13&ie=UTF8&iwloc=&output=embed');

INSERT INTO administrador (nombre_completo, email, clave, foto)
VALUES ('Admin Principal', 'admin@tiendatech.com', 'admin123', 'admin.png');

COMMIT;

/* =========================
   ACERCA (Intro / Misión / Visión / Valores)
   ========================= */
CREATE TABLE IF NOT EXISTS `acerca` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `titulo` VARCHAR(150) NOT NULL,
  `intro`   TEXT,
  `mision`  TEXT,
  `vision`  TEXT,
  `valores` TEXT,
  `imagen`  VARCHAR(255) DEFAULT NULL,
  `actualizado_en` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


INSERT INTO acerca (titulo,intro,mision,vision,valores,imagen,actualizado_en)
VALUES (
  'Acerca de TiendaTech',
  'En TiendaTech nos apasiona la tecnología. Ofrecemos productos y servicio de primera.',
  'Proveer tecnología de calidad y accesible para mejorar la vida de las personas.',
  'Ser la tienda líder en tecnología en Latinoamérica, reconocida por la innovación y el servicio.',
  '• Compromiso\n• Innovación\n• Calidad\n• Servicio al cliente',
  'equipo.jpg',          -- o NULL si no tienes imagen aún
  NOW()
);

COMMIT;



/* =========================
   FAQ (acordeón administrable)
   ========================= */
CREATE TABLE IF NOT EXISTS `faq` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `pregunta` VARCHAR(255) NOT NULL,
  `respuesta` TEXT NOT NULL,
  `activa` TINYINT(1) NOT NULL DEFAULT 1,
  `orden` INT DEFAULT 0,
  PRIMARY KEY (`id`),
  KEY `idx_faq_activa_orden` (`activa`, `orden`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO faq (pregunta, respuesta, activa, orden) VALUES
('¿Cuáles son los métodos de pago aceptados?', 'Tarjeta, transferencia y Sinpe Móvil.', 1, 1),
('¿Cuánto tiempo tarda en llegar mi pedido?', 'Entre 24 y 72 horas hábiles según tu ubicación.', 1, 2),
('¿Ofrecen garantía en los productos?', 'Sí, garantía oficial del fabricante.', 1, 3),
('¿Cómo puedo solicitar un reembolso?', 'Escríbenos con tu número de orden y te guiamos.', 1, 4);
COMMIT;

/* =========================
   FAQ_FORM (respuestas del formulario grande en FAQ)
   ========================= */
CREATE TABLE IF NOT EXISTS `faq_form` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `usuario_email` VARCHAR(120) NOT NULL,
  `clave` VARCHAR(255) DEFAULT NULL,
  `nombre_completo` VARCHAR(120) DEFAULT NULL,
  `color_favorito` VARCHAR(30) DEFAULT NULL,
  `foto_path` VARCHAR(255) DEFAULT NULL,
  `telefono` VARCHAR(25) DEFAULT NULL,
  `sitio_web` VARCHAR(200) DEFAULT NULL,
  `rango_salarial` VARCHAR(50) DEFAULT NULL,
  `zona` ENUM('GAM','FUERA_GAM') DEFAULT NULL,
  `nivel_satisfaccion` TINYINT UNSIGNED NOT NULL DEFAULT 0,
  `direccion_exacta` VARCHAR(255) DEFAULT NULL,
  `nivel_educativo` ENUM('PRIMARIA','SECUNDARIA','TECNICO','BACHILLER',
                         'LICENCIATURA','MAESTRIA','DOCTORADO') DEFAULT NULL,
  `creado_en` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `idx_faqform_email_creado` (`usuario_email`,`creado_en`),
  CONSTRAINT `chk_faqform_satisfaccion` CHECK (`nivel_satisfaccion` BETWEEN 0 AND 10)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/* =========================
   CONTACTO (mensajes del formulario)
   ========================= */
CREATE TABLE IF NOT EXISTS `contacto` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(100) NOT NULL,
  `correo` VARCHAR(120) NOT NULL,
  `mensaje` TEXT NOT NULL,
  `estado` ENUM('NUEVO','EN_PROCESO','CERRADO') NOT NULL DEFAULT 'NUEVO',
  `creado_en` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `idx_contacto_estado_creado` (`estado`, `creado_en`),
  KEY `idx_contacto_correo` (`correo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/* =========================
   CONTACTO_INFO (datos fijos mostrados: Teléfono, Email, Dirección/Mapa, Horario)
   ========================= */
CREATE TABLE IF NOT EXISTS `contacto_info` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `telefono_publico` VARCHAR(25) DEFAULT NULL,
  `email_publico`    VARCHAR(120) DEFAULT NULL,
  `direccion`        VARCHAR(255) DEFAULT NULL,
  `mapa_embed`       TEXT DEFAULT NULL,
  `horario`          VARCHAR(200) DEFAULT NULL,
  `actualizado_en`   TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/* =========================
   CARRITO (encabezado)
   ========================= */
CREATE TABLE IF NOT EXISTS `carrito` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `id_usuario` BIGINT DEFAULT NULL,
  `estado` ENUM('ABIERTO','COMPLETADO','CANCELADO') NOT NULL DEFAULT 'ABIERTO',
  `creado_en` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `actualizado_en` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `idx_carrito_usuario_estado` (`id_usuario`, `estado`),
  CONSTRAINT `fk_carrito_usuario` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id`)
    ON DELETE SET NULL ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/* =========================
   CARRITO_ITEM (detalle con cantidad ± y precio snapshot)
   ========================= */
CREATE TABLE IF NOT EXISTS `carrito_item` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `id_carrito` BIGINT NOT NULL,
  `id_producto` BIGINT NOT NULL,
  `cantidad` INT NOT NULL DEFAULT 1,
  `precio_unitario` DECIMAL(10,2) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uq_carrito_producto` (`id_carrito`,`id_producto`),
  KEY `idx_item_carrito` (`id_carrito`),
  KEY `idx_item_producto` (`id_producto`),
  CONSTRAINT `fk_item_carrito` FOREIGN KEY (`id_carrito`) REFERENCES `carrito` (`id`)
    ON DELETE CASCADE ON UPDATE RESTRICT,
  CONSTRAINT `fk_item_producto` FOREIGN KEY (`id_producto`) REFERENCES `producto` (`id`)
    ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Quitar el único antiguo para permitir la combinación con color
ALTER TABLE carrito_item
  DROP INDEX uq_carrito_producto;

-- Agregar columnas para variantes y un snapshot visual opcional
ALTER TABLE carrito_item
  ADD COLUMN color VARCHAR(30) NOT NULL DEFAULT '' AFTER id_producto,
  -- Si tu MySQL soporta JSON (5.7+ / 8.0+), deja JSON; si no, usa TEXT (ver alternativa abajo)
  ADD COLUMN atributos JSON NULL AFTER color,
  ADD COLUMN nombre_producto VARCHAR(120) NULL AFTER precio_unitario,
  ADD COLUMN imagen VARCHAR(255) NULL AFTER nombre_producto;

-- Evita duplicados del mismo producto+color dentro del mismo carrito
CREATE UNIQUE INDEX uq_carrito_producto_color
  ON carrito_item (id_carrito, id_producto, color);