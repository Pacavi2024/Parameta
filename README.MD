# Servicio de Gestión de Empleados

## Descripción
Servicio REST para registro de empleados con validaciones e integración SOAP.

## Requisitos
- Java 17
- Maven
- MySQL

## Configuración
1. Clonar repositorio
2. Configurar base de datos en `application.properties`
3. Ejecutar `mvn clean install`

## Ejecución
`mvn spring-boot:run`

## Endpoints
- GET `/api/empleados/registro`: Registrar empleado

## Tecnologías
- Spring Boot
- Spring Data JPA
- Web Services
- MySQL