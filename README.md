
# ChallengeLiteratura 30-10-2024

## Introducción.

Con el siguiente proyecto se pretende desarrollar un Catálogo de Libros que ofrezca interacción textual (vía consola) con los usuarios, proporcionando al menos 5 opciones de interacción. Los libros se buscarán a través de una API específica. Como resolución a challenge de la plataforma de estudio [ALURA LATAM](https://www.aluracursos.com) para grupo de estudio G7.

## Indice.
1.[ Contexto de negocio.](#1-contexto-de-negocio)

2.[ Visión de la solución.](#2-visión-de-la-solución)

3.[ Alcance y limitaciones.](#3-alcance-y-limitaciones)

4.[ Contexto del sistema.](#4-contexto-del-sistema)

5.[ Informacion adiccional.](#5-informacion-adiccional)

## 1 Contexto de negocio

### 1.1 Antecedentes

Sobre el challenge; En este emocionante desafío de programación, te invitamos a construir tu propio catálogo de libros: el LiterAlura. Aprenderás a realizar solicitudes a una API de libros, a manipular datos JSON, guardarlos en una base de datos y, finalmente, a filtrar y mostrar los libros y autores de interés.

OBJETIVO: Desarrollar un Catálogo de Libros que ofrezca interacción textual (vía consola) con los usuarios, proporcionando al menos 5 opciones de interacción. Los libros se buscarán a través de una API específica.

Los pasos para completar este desafío se detallarán a continuación y estarán disponibles en la sección adyacente:

Configuración del Ambiente Java, Creación del Proyecto,
Consumo de la API, Análisis de la Respuesta JSON, Inserción y consulta en la base de datos, Exibición de resultados a los usuarios.

### 1.2. Frase del problema.
Crear un catálogo de Libros interactivo llamado "LiterAlura", que permita a los usuarios buscar libros a través de una API, manipular y almacenar los datos en una base de datos local, y ofrecer diversas opciones de interacción a través de la consola, como filtrar por autores o títulos. Se debe asegurar que el sistema sea capaz de gestionar al menos cinco tipos de consultas diferentes.

### 1.3 Objectivos del negocio

| Objectivo         |Descripción del objetivo de negocio                                 |
| ----------------- | ------------------------------------------------------------------ |
| on-1              |Crear un catálogo de Libros interactivo llamado "LiterAlura".       |
| on-2              |Facilitar el acceso a información actualizada de libros y autores a través de la integración con una API de libros, permitiendo a los usuarios consultar catálogos en tiempo real.|
| on-3              |Almacenar y gestionar eficientemente la información en una base de datos, permitiendo la consulta de datos de forma rápida y persistente.|
| on-4              |Generar estadísticas relevantes sobre los libros y autores almacenados, proporcionando datos útiles a los usuarios para mejorar su experiencia de consulta y selección.|
| on-5              |Posibilitar el crecimiento futuro del sistema mediante la implementación de funcionalidades opcionales que permitan al proyecto escalar en términos de consultas y presentación de datos.|

ob -> objectivo del negocio

## 2 Visión de la solución

### 2.1 Frase de Vision

Desarrollar una aplicación capaz de conectarse a una API de libros, obtener y procesar los datos de manera eficiente, y almacenarlos en una base de datos relacional, permitiendo a los usuarios acceder y consultar esta información de forma interactiva a través de una consola. El sistema brindará una experiencia dinámica, ofreciendo funcionalidades avanzadas como filtrado, estadísticas, y consultas personalizadas sobre libros y autores, para facilitar la toma de decisiones informadas y mejorar el acceso a la literatura disponible.

### 2.2 Caracteristicas del sistema

| Caracteristica|Descripción|Prioridad|
| --------| --------| --------|
| crs-01| Tecnologias open source | alta |
| crs-02| Requiere acceso a internet | alta |
| crs-03| Conexion a api externa | alta |
| crs-04| Independiente de plataforma (windows, linux, etc) | alta |
| crs-05| Rapido, distribuido, escalable | alta |
| crs-06| Persistencia de los datos | alta |
| crs-07| Manejo por medio de consola | alta |
| crs-08| Consulta y filtrado personalizado | alta |
| crs-09| Estadisticas | alta |

crs -> Caracteristica del sistema.

## 3 Alcance y limitaciones

### 3.1 Alcance

| Número de reléase|Tema principal|
| --------| --------|
| Version 1.0| Funcionalidad completa |

## 4 Contexto del sistema

### 4.1 Resumen de involucrados

| Nombre|Descripción|Responsabilidades|
| --------| --------| --------|
| Luis Felipe Nieves Ch.| Analista y Desarrollador de sistemas de Información grupo G7 | Analista y Desarrollador el sistema |

### 4.2 Diagramas de Contexto



### 4.3 Entorno de operacion

- Lenguaje: Java
- IDE: IntelliJ IDEA / Eclipse
- Dependencias: Maven
- API: https://gutendex.com
- JSON: Jackson
- Base de Datos: PostgreSQL
- Framework : Spring Boot
- Sistema Operativo: Multiplataforma
- Control de Versiones: Git

## 5 Informacion adiccional

Licencia MIT

Copyright (c) 2024, LUIS FELIPE NIEVES CH.

Por la presente se concede permiso, de forma gratuita, a cualquier persona que obtenga una copia
de este software y los archivos de documentación asociados (el "Software"), para utilizar
el Software sin restricción, incluyendo sin limitación los derechos para usar, copiar, modificar,
fusionar, publicar, distribuir, sublicenciar y/o vender copias del Software, y para permitir a las
personas a las que se les proporcione el Software hacerlo, sujeto a las siguientes condiciones:

El aviso de copyright anterior y este aviso de permiso se incluirán en todas las copias o partes sustanciales
del Software.

EL SOFTWARE SE PROPORCIONA "TAL CUAL", SIN GARANTÍA DE NINGÚN TIPO, EXPRESA O IMPLÍCITA,
INCLUYENDO PERO NO LIMITADO A GARANTÍAS DE COMERCIALIZACIÓN, IDONEIDAD PARA UN PROPÓSITO PARTICULAR
Y NO INFRACCIÓN. EN NINGÚN CASO LOS AUTORES O TITULARES DEL COPYRIGHT SERÁN RESPONSABLES DE
NINGUNA RECLAMACIÓN, DAÑO U OTRA RESPONSABILIDAD, YA SEA EN UNA ACCIÓN CONTRACTUAL, EXTRACONTRACTUAL
O DE OTRO TIPO, QUE SURJA DE, O EN CONEXIÓN CON EL SOFTWARE O EL USO U OTROS TRATOS EN EL
SOFTWARE.
