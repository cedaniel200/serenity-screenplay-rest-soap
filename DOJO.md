# DOJO — implementa las tareas

Los tres escenarios están escritos y los servicios simulados funcionan.
Lo que falta es el cuerpo de las tres tareas:

| Tarea | Qué implementa |
|---|---|
| `ConsultTheUsers` | Consulta paginada de usuarios (REST) |
| `CreateUser` | Alta de un usuario (REST) |
| `SayHello` | Saludo por SOAP |

Cada una tiene un `performAs` vacío. Hasta que lo completes, los escenarios fallan.

## Servicios simulados

WireMock arranca dentro del propio test en `http://localhost:8089`, con puerto fijo.
No hace falta levantar nada: `./gradlew test` es suficiente.

## Consult users — GET

Ruta: `/api/users?page=2`

La tarea recibe el número de página y construye la ruta con `RestService.CONSULT_USERS`.

> Pista: `Get.resource(...)` de `net.serenitybdd.screenplay.rest.interactions`.

## Create user — POST

Ruta: `/api/users`

| Cabecera | Valor |
|---|---|
| `Content-Type` | `application/json` |

El cuerpo es el objeto `User` que recibe la tarea.

> Pista: `Post.to(...).with(request -> request.header(...).body(...))`.

## Say hello — POST (SOAP)

Ruta: `/services/hello`

| Cabecera | Valor |
|---|---|
| `Content-Type` | `text/xml;charset=UTF-8` |
| `SOAPAction` | `""` |

```xml
<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
                  xmlns:hel="http://learnwebservices.com/services/hello">
  <soapenv:Body>
    <hel:HelloRequest>
      <hel:Name>NOMBRE</hel:Name>
    </hel:HelloRequest>
  </soapenv:Body>
</soapenv:Envelope>
```

> Pista: `Post.to(...)` con el sobre en el cuerpo.

## Cómo saber si está bien

```sh
./gradlew test
```

Los tres escenarios deben quedar en verde. Las respuestas simuladas están en
`src/test/resources/mocks/` si quieres ver qué se espera de vuelta.
