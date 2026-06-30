Donaton - BFF Gateway

Backend For Frontend (BFF) desarrollado para el proyecto Donaton utilizando arquitectura de microservicios con Spring Boot.

Descripción

Este proyecto actúa como una capa intermedia entre el frontend React y los microservicios del sistema Donaton.

El gateway centraliza las solicitudes HTTP, aplica validaciones de seguridad mediante JWT y protege la comunicación utilizando el patrón Circuit Breaker con Resilience4j.

Arquitectura

Frontend React (5173) ↓ BFF Gateway (8080) ↓ ↓ ms-usuarios (8081) ms-donaciones (8082)

Tecnologías utilizadas

Java 21
Spring Boot 3.5.14
Spring Web
Spring Security
Lombok
Resilience4j
Maven
JWT
REST API
Patrones implementados

Backend For Frontend (BFF)

El gateway centraliza todas las solicitudes provenientes del frontend, desacoplando la interfaz de usuario de los microservicios internos.

Circuit Breaker

Implementado mediante Resilience4j para evitar cascadas de fallos cuando un microservicio no responde correctamente.

Fallback implementado:

Servicio usuarios no disponible → HTTP 503
Servicio donaciones no disponible → HTTP 503
Seguridad

Se implementó validación básica JWT mediante filtro personalizado:

rutas públicas:

/gateway/usuarios/login
/gateway/usuarios/registro
rutas protegidas:

todas las demás
Las solicitudes protegidas requieren:

Authorization: Bearer token

Configuración de puertos

Servicio	Puerto
BFF Gateway	8080
ms-usuarios	8081
ms-donaciones	8082
Frontend React	5173
Endpoints principales

Usuarios

Método	Endpoint
POST	/gateway/usuarios/login
POST	/gateway/usuarios/registro
GET	/gateway/usuarios/{id}
PUT	/gateway/usuarios/{id}
Donaciones

Método	Endpoint
POST	/gateway/donaciones
GET	/gateway/donaciones
GET	/gateway/donaciones/{id}
GET	/gateway/centros
Cómo ejecutar el proyecto

1. Clonar repositorio

git clone https://github.com/Alexdevnanobytes/bff-gateway.git
2. Entrar al proyecto

cd bff-gateway
3. Ejecutar compilación

./mvnw clean package
4. Ejecutar aplicación

./mvnw spring-boot:run
Estructura del proyecto

src/main/java/com/donaton/bff_gateway
├── client
├── config
├── controller
├── dto
├── facade
├── security
Pruebas unitarias

El proyecto incluye pruebas unitarias para validar la lógica principal del BFF Gateway. Tests run: 21 Failures: 0 Errors: 0 Skipped: 0 BUILD SUCCESS

Componentes probados: JwtValidatorFilter RestTemplateConfig UsuarioClient DonacionClient DonacionFacade UsuarioGatewayController DonacionGatewayController

Integrantes

Alexis Armijo → BFF Gateway + Circuit Breaker
Daniela → ms-usuarios
Yesenia → ms-donaciones
Matías → Frontend React
Asignatura

DSY1106 - Desarrollo Fullstack III Duoc UC
