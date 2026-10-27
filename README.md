# Serenity BDD + Screenplay — Demo REST y SOAP

![Java](https://img.shields.io/badge/Java-17-blue)
![Serenity BDD](https://img.shields.io/badge/Serenity%20BDD-5.4.1-green)
![Cucumber](https://img.shields.io/badge/Cucumber-8.0.4-brightgreen)
![Gradle](https://img.shields.io/badge/Gradle-9.8.2-darkgreen)
[![License](https://img.shields.io/badge/License-MIT-yellow)](LICENSE)

Pruebas de servicios con Serenity BDD y el patrón Screenplay: un flujo REST contra
[reqres.in](https://reqres.in) y un flujo SOAP contra un servicio de bancos. Es la
[plantilla](https://github.com/cedaniel200/serenity-screenplay-template) aplicada a APIs en lugar de
interfaces.

## Qué demuestra

- **Screenplay sin navegador para la API**: `tasks` que ejecutan `Get` y `Post`, `questions` sobre el
  código de estado y el cuerpo de la respuesta.
- **Modelo con builder** y serialización con `@SerializedName` para el cuerpo de las peticiones.
- **Dos protocolos**: REST con `serenity-screenplay-rest` y SOAP con un cliente propio.
- **Evidencia visual**: `ReqresPage` abre la web del servicio y hace scroll hasta el JSON, para que
  el informe muestre la respuesta además de la petición.

## Estado actual

| Escenario | Estado |
|---|---|
| `Create an user` (REST) | **Pasa** |
| `Consult users` (REST) | Falla: el XPath de `ReqresPage` ya no coincide con la web actual de reqres.in |
| `Consult bank` (SOAP) | Falla: `thomas-bayer.com` ya no existe |

Los dos fallos son de los servicios externos, no de la suite. Queda pendiente decidir si se
reparan, se etiquetan como `@manual` o se retiran.

## Requisitos

- **JDK 17**. El build fija un toolchain de Java 17.
- **Chrome**, solo para el escenario que abre la web de reqres.in como evidencia.
- **Conexión a internet**.

## Cómo se ejecuta

```sh
./gradlew test
./gradlew aggregate
```

El informe queda en `target/site/serenity/index.html`.

## Estructura

```text
src/main/java/com/cedaniel200/screenplay/restapi
├── tasks/            # ConsultTheUsers, CreateUser, ConsultBank
├── questions/        # LastResponseStatusCode, TheUsersOnTheWeb
├── userinterfaces/   # ReqresPage (evidencia visual)
├── models/           # User, SummaryUsers y su builder
├── utils/            # RestService, SOAPService, ConverterJson
└── (raíz)            # Builder

src/test
├── java/.../runners/RestSoapTestSuite.java
├── java/.../stepdefinitions/rest/
├── java/.../stepdefinitions/soap/
└── resources/features/{rest,soap}/
```

Los hooks están separados por etiqueta: `@rest` y `@soap`. Cada escenario configura el actor con la
API que le corresponde, sin pisar la del otro.

## Relación con la plantilla

Usa la estructura de [serenity-screenplay-template](https://github.com/cedaniel200/serenity-screenplay-template)
con el paquete adaptado al proyecto. No incluye `interactions`: son propias de las pruebas de
interfaz y aquí no hay ninguna que automatizar, salvo el scroll de la evidencia.

## Docker

```sh
docker compose up --abort-on-container-exit
```

El navegador corre en el servicio `chrome`; los tests le envían comandos por HTTP.

## Ten en cuenta

- **Depende de servicios de terceros.** `reqres.in` ya cambió una vez; `thomas-bayer.com`
  desapareció. Los localizadores y las URLs envejecen.
- **El SOAP no tiene reemplazo público estable**, así que ese escenario no se puede verificar hoy.
- **`gradle.startParameter.continueOnFailure = true`** está heredado: Gradle sigue tras una tarea
  fallida.

## Licencia

[MIT](LICENSE) © Cesar Daniel
