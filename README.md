# Serenity BDD + Screenplay — Demo REST y SOAP

![Java](https://img.shields.io/badge/Java-17-blue)
![Serenity BDD](https://img.shields.io/badge/Serenity%20BDD-5.4.1-green)
![Cucumber](https://img.shields.io/badge/Cucumber-8.0.4-brightgreen)
![Gradle](https://img.shields.io/badge/Gradle-9.8.2-darkgreen)
[![License](https://img.shields.io/badge/License-MIT-yellow)](LICENSE)

> [!TIP]
> ¿Vienes al taller de esta rama? Empieza por [DOJO.md](DOJO.md).

Pruebas de servicios con Serenity BDD y el patrón Screenplay: dos escenarios REST y uno SOAP, con
las respuestas simuladas por WireMock dentro de los tests. Es la
[plantilla](https://github.com/cedaniel200/serenity-screenplay-template) aplicada a APIs en lugar de
interfaces.

## Qué incluye

| Escenario | Protocolo | Qué comprueba |
|---|---|---|
| `create_user` | REST | Alta de un usuario y validación del cuerpo devuelto |
| `consult_users` | REST | Contrato de la respuesta: paginación, campos y tipos, más dos valores concretos |
| `say_hello` | SOAP | Envío de un sobre SOAP y validación del mensaje de vuelta |

Sobre esa base:

- **Screenplay sin navegador**: `tasks` que ejecutan `Get` y `Post`, `questions` que validan la
  respuesta.
- **Servicios simulados con WireMock**: los tres escenarios corren contra stubs locales, así que la
  suite no depende de ningún servicio externo y funciona sin internet.
- **Modelo con builder** y serialización del cuerpo de las peticiones.
- **Dos protocolos**: REST con `serenity-screenplay-rest` y SOAP sobre el mismo cliente.

## Requisitos

- **JDK 17**. El build fija un toolchain de Java 17.
- Nada más: no hace falta navegador, ni servicios levantados, ni conexión a internet.

## Cómo se ejecuta

```sh
./gradlew test
./gradlew aggregate
```

El informe queda en `target/site/serenity/index.html`.

## Estructura

```text
src/main/java/com/cedaniel200/screenplay/restapi
├── tasks/            # ConsultTheUsers, CreateUser, SayHello
├── questions/        # TheUsersContract, TheUserCreated, TheGreeting, LastResponseStatusCode
├── models/           # User, SummaryUsers y su builder
└── utils/            # RestService, SOAPService, ConverterJson

src/test
├── java/.../runners/RestSoapTestSuite.java
├── java/.../stepdefinitions/rest/
├── java/.../stepdefinitions/soap/
├── java/.../stepdefinitions/support/MockServices.java   # stubs de WireMock
└── resources/
    ├── features/{rest,soap}/
    └── mocks/        # respuestas simuladas
```

Los hooks están separados por etiqueta: `@rest` y `@soap`. Cada escenario configura el actor con la
API que le corresponde.

## Relación con la plantilla

Usa la estructura de [serenity-screenplay-template](https://github.com/cedaniel200/serenity-screenplay-template)
con el paquete adaptado al proyecto. No incluye `interactions` ni `userinterfaces`: son capas de las
pruebas de interfaz, y aquí no hay ninguna que automatizar.

## Docker

```sh
docker compose up --abort-on-container-exit
```

Un solo contenedor: los stubs viven dentro del propio test, así que no hace falta ni navegador ni
servicio aparte.

## Ten en cuenta

- **Los servicios están simulados.** La suite verifica contratos contra un doble controlado, no
  contra un servicio real. Para apuntar a uno de verdad, cambia la URL base en `RestService` y
  `SOAPService` y elimina los stubs.
- **Las aserciones validan forma y valores, no solo el código de estado**: campos obligatorios,
  tipos correctos, paginación y dos datos concretos.
- **`gradle.startParameter.continueOnFailure = true`** está heredado: Gradle sigue tras una tarea
  fallida.

## Licencia

[MIT](LICENSE) © Cesar Daniel
