# Sistema de Gestión de Reservas y Canchas

## Descripción del Proyecto

Este proyecto consiste en el desarrollo de una arquitectura basada en microservicios para la gestión de reservas en un club deportivo. El sistema permite administrar canchas y generar reservas, validando la existencia de una cancha antes de registrar una reserva.

El sistema está compuesto por dos microservicios principales:

* Microservicio de Canchas
* Microservicio de Reservas

Se implementa comunicación entre microservicios mediante Spring Cloud OpenFeign.

---

## Arquitectura

El sistema sigue una arquitectura de microservicios, caracterizada por:

* Servicios independientes
* Comunicación mediante HTTP REST
* Persistencia desacoplada por servicio

### Comunicación entre servicios

El microservicio de Reservas consume el microservicio de Canchas mediante un cliente Feign:

* Reservas consulta Canchas para validar existencia
* Si la cancha no existe, no se permite crear la reserva

---

## Tecnologías Utilizadas

* Java 21
* Spring Boot 3.2.5
* Spring Data JPA
* Spring Web
* Spring Cloud OpenFeign
* MySQL
* Maven

---

## Estructura del Proyecto

### Microservicio Canchas

* Controller
* Service
* Repository
* Entity

### Microservicio Reservas

* Controller
* Service
* Repository
* Entity
* Cliente Feign para comunicación con Canchas

---

## Configuración

### application.properties (Reservas)

```properties
spring.application.name=servicioreservas
server.port=8082

spring.datasource.url=jdbc:mysql://localhost:3306/club_deportivo2
spring.datasource.username=root
spring.datasource.password=

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

---

## Ejecución del Proyecto

1. Iniciar el servicio de MySQL
2. Crear la base de datos:

```sql
CREATE DATABASE club_deportivo2;
```

3. Ejecutar el microservicio de Canchas (puerto 8081)
4. Ejecutar el microservicio de Reservas (puerto 8082)

---

## Endpoints

### Microservicio Canchas

#### Obtener cancha por ID

```
GET /canchas/{id}
```

---

### Microservicio Reservas

#### Crear reserva

```
POST /api/reservas
```

Ejemplo de cuerpo JSON:

```json
{
  "canchaId": 1,
  "fecha": "2026-03-20",
  "hora": "18:00"
}
```

---

#### Listar reservas

```
GET /api/reservas
```

---

#### Buscar reservas por cancha

```
GET /api/reservas/cancha/{canchaId}
```

---

## Validación entre Microservicios

Antes de crear una reserva, el sistema valida la existencia de la cancha:

* Si la cancha existe, la reserva se registra correctamente
* Si la cancha no existe, se retorna un error

Ejemplo de respuesta de error:

```json
{
  "status": 404,
  "error": "Not Found",
  "message": "La cancha no existe"
}
```

---

## Pruebas con Postman

### Caso válido

* Se envía un canchaId existente
* Resultado: la reserva se crea correctamente

### Caso inválido

* Se envía un canchaId inexistente
* Resultado: se retorna error 404 y no se registra la reserva

---

## Consideraciones Técnicas

* Uso de FeignClient para comunicación entre microservicios
* Manejo de errores mediante ResponseStatusException
* Separación de responsabilidades por servicio
* Persistencia independiente para cada microservicio

---

## Conclusión

El sistema implementa correctamente una arquitectura de microservicios, permitiendo:

* Comunicación eficiente entre servicios
* Validación distribuida de datos
* Manejo adecuado de errores

Esto asegura una solución escalable, mantenible y alineada con buenas prácticas de desarrollo backend.

---

## Autor

Proyecto desarrollado en el contexto de la asignatura de desarrollo backend en Ingeniería en Informática.
