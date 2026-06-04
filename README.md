# Portafolio - API REST de Mascotas

API REST desarrollada con Spring Boot 3.2.7 y Java 17 que expone operaciones GET y POST para mascotas, consumiendo la API externa de Petstore (`https://petstore.swagger.io/v2`).

## Especificaciones técnicas

| Especificación | Valor |
|----------------|-------|
| Java | 17 |
| Spring Boot | 3.2.7 |
| Gradle | 8.7 (Groovy) |
| Packaging | JAR |
| Base de datos | H2 (en memoria) |
| Documentación API | Swagger UI (SpringDoc OpenAPI) |

## Estructura del proyecto

```
src/
├── main/java/com/pet/portafolio/
│   ├── PortafolioApplication.java     # Clase principal
│   ├── controller/                    # Controladores REST (API)
│   ├── service/                       # Lógica de negocio
│   ├── client/                        # Cliente para API externa (Petstore)
│   ├── repository/                    # Acceso a datos JPA
│   ├── model/                         # Entidades y DTOs
│   └── config/                        # Configuración (RestTemplate)
└── test/java/com/pet/portafolio/
    ├── controller/                    # Pruebas de controladores
    └── service/                       # Pruebas de servicios
```

## Requisitos

- JDK 17 instalado
- Conexión a internet (para consumir la API de Petstore)

## Ejecución del proyecto

### Compilar y ejecutar pruebas

```shell
gradlew.bat build
```

### Ejecutar la aplicación

```shell
gradlew.bat bootRun
```

O desde el JAR:

```shell
gradlew.bat bootJar
java -jar build\libs\portafolio-0.0.1-SNAPSHOT.jar
```

La aplicación inicia en `http://localhost:8080` (esperar ~12 segundos).

## Operaciones GET y POST

### GET /api/pet/{petId}

Obtiene una mascota desde la API externa de Petstore.

**Parámetro de entrada:**
| Nombre | Tipo | Ubicación |
|--------|------|-----------|
| idPet | Long | Path parameter |

Ejemplo: `GET http://localhost:8080/api/pet/555`

**Parámetros de salida:**
```json
{
  "id": 555,
  "name": "Prueba",
  "status": "available"
}
```

**Nota:** Si el ID no existe en Petstore, responde con `404` y `{"error":"Pet not found","id":1}`.

### POST /api/pet

Crea una nueva mascota en Petstore y la guarda localmente en H2.

**Parámetros de entrada (body JSON):**
```json
{
  "id": 100,
  "name": "Firulais",
  "status": "available"
}
```

**Parámetros de salida (201 Created):**
```json
{
  "transactionId": "uuid-generado",
  "dateCreated": "2026-06-04T10:00:00",
  "status": "available",
  "name": "Firulais"
}
```

### Flujo de uso recomendado

1. Crear una mascota con POST
2. Consultarla con GET usando el ID creado

```
POST http://localhost:8080/api/pet
Body: {"id":777,"name":"MiMascota","status":"available"}

GET http://localhost:8080/api/pet/777
```

## Documentación Swagger UI

Disponible en: `http://localhost:8080/swagger-ui/index.html`

Endpoint OpenAPI: `http://localhost:8080/v3/api-docs`

## Base de datos H2

Consola disponible en: `http://localhost:8080/h2-console`

- JDBC URL: `jdbc:h2:mem:petdb`
- Usuario: `sa`
- Contraseña: *(vacío)*

## Pruebas unitarias

### Ejecutar pruebas

```shell
gradlew.bat test
```

### Pruebas incluidas

**PortafolioApplicationTests.java** — Prueba de carga de contexto de Spring Boot.

**PetServiceTest.java** (Mockito):
- `getPetByIdShouldReturnPetFromExternalApi` — Verifica que el servicio obtiene una mascota desde Petstore
- `createPetShouldDelegateToPetstoreAndReturnResponse` — Verifica que el servicio crea una mascota vía Petstore y persiste localmente

**PetControllerTest.java** (MockMvc):
- `getPetByIdShouldReturn200` — Verifica que GET /api/pet/1 responde con id, name, status
- `createPetShouldReturn201` — Verifica que POST /api/pet responde 201 con transactionId, dateCreated, status, name
