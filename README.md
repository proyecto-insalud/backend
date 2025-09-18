# Backend Proyecto - Insalud

## Descripción

Este proyecto de backend ha sido desarrollado utilizando **Java 23**, **Spring Boot**, **PostgreSQL**, **Spring Security** y **Domain-Driven Design (DDD)**.

- **Lenguaje:** Java 23
- **Framework:** Spring Boot
- **Base de datos:** PostgreSQL
- **Autenticación y autorización:** Spring Security
- **Patrón arquitectónico:** Domain-Driven Design (DDD)
- **Entorno de desarrollo:** IntelliJ IDEA (pero puede ser usado con otros entornos de desarrollo compatibles con Java)

Este sistema gestiona la información de atención médica, permitiendo la administración de **médicos**, **atenciones**, y la autenticación de usuarios. Además, incluye la validación de campos obligatorios mediante las anotaciones de **Spring Validation**.

### ¿Por qué DDD?

El proyecto fue estructurado utilizando **Domain-Driven Design (DDD)** para facilitar la gestión de un dominio complejo como es la atención médica. El uso de DDD nos permite:

- Organizar el código en dominios específicos, reflejando un aspecto del negocio (por ejemplo, **Médicos**, **Atenciones**, **Usuarios**).
- Separar el código en capas (como **Capa de Dominio**, **Capa de Aplicación**, **Capa de Infraestructura**) para mayor mantenibilidad.
- Fomentar el uso de **Entidades**, **Agregados**, **Servicios de Dominio** y **Repositorios**, mejorando la claridad y coherencia del diseño.

### Autenticación y Autorización con Spring Security

El proyecto utiliza **Spring Security** para la autenticación y autorización de los usuarios. Implementa un sistema de autenticación basado en **JWT (JSON Web Tokens)** para garantizar que solo los usuarios autenticados puedan acceder a los recursos protegidos de la API.

#### Flujo de autenticación:
1. **Registro de usuario**: Los usuarios pueden registrarse mediante el endpoint de autenticación, proporcionando sus credenciales.
2. **Login**: Una vez autenticados, los usuarios reciben un **JWT**.
3. **Protección de rutas**: Las rutas que requieren autenticación están protegidas y deben incluir el **JWT** en el encabezado de la solicitud para acceder a ellas.

Para más detalles sobre cómo interactuar con la autenticación, consulta los endpoints en Swagger.

## Requisitos

Antes de ejecutar el proyecto, asegúrate de tener lo siguiente:

- **Java 23**: Asegúrate de tener la versión correcta de Java instalada. Puedes verificar la versión ejecutando el siguiente comando:

   ```bash
   java -version
  

PostgreSQL: Instala y configura PostgreSQL en tu máquina. Asegúrate de tener la base de datos configurada correctamente.

Swagger: Para la visualización de los métodos HTTP expuestos por la API, puedes acceder a la interfaz Swagger una vez que el proyecto esté en ejecución.

Postman (Opcional, pero recomendado): Para la validación de rutas HTTP durante el desarrollo.


## Instalación y Configuración

1. Clonar el repositorio

Clona el repositorio o descarga el archivo .zip del proyecto.

2. Configurar la base de datos PostgreSQL

Antes de ejecutar el proyecto, asegúrate de crear la base de datos en PostgreSQL y ajustar las credenciales en el archivo application.properties:

```bash
spring.datasource.url=jdbc:postgresql://localhost:5432/insalud
spring.datasource.username=tu_usuario 
spring.datasource.password=tu_contraseña 
spring.jpa.hibernate.ddl-auto=update
```

3. Acceder a la API

Una vez que el backend esté en ejecución, puedes acceder a la API a través de http://localhost:8093. Si todo está configurado correctamente, podrás visualizar la documentación de los endpoints a través de Swagger en http://localhost:8093/swagger-ui/index.html#/.


# VIDEO DE EJECUCION

En el siguiente enlace se puede ver una demo ejecutada por mí para que se pude validar el uso del proyecto y se tenga de referencia al momento de clonarlo y correrlo.

https://youtu.be/VNSyaN5dVPs