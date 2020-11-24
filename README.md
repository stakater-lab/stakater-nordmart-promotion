# Promotion API

This project uses Quarkus, the Supersonic Subatomic Java Framework.

If you want to learn more about Quarkus, please visit its website: https://quarkus.io/

## Dependencies

Promotion API has following dependencies:

- Quarkas
- MySQL

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:
```
./mvnw quarkus:dev
```

## Packaging and running the application

The application can be packaged using `./mvnw package`.
It produces the `promotion-1.0-SNAPSHOT-runner.jar` file in the `/target` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `target/lib` directory.

The application is now runnable using `java -jar target/promotion-1.0-SNAPSHOT-runner.jar`.

## Creating a native executable

You can create a native executable using: `./mvnw package -Pnative`.

Or, if you don't have GraalVM installed, you can run the native executable build in a container using: `./mvnw package -Pnative -Dquarkus.native.container-build=true`.

You can then execute your native executable with: `./target/promotion-1.0-SNAPSHOT-runner`

If you want to learn more about building native executables, please consult https://quarkus.io/guides/building-native-image.

## Build a container with native executable

You can create a native container using: `docker/podman image build -t nordman-promotion:native .`.

## Using Promotion api

### List all promotions

```
curl http://HOST:PORT/promotions/
```

```
[
  {
    "id": 1,
    "active": true,
    "code": "prom_100",
    "description": "Promotion from item 100",
    "itemId": "100",
    "percentOff": 20
  },
  {
    "id": 2,
    "active": true,
    "code": "prom_200",
    "description": "Promotion from item 200",
    "itemId": "200",
    "percentOff": 20
  }
]
```

### List active promotions as map

```
curl http://HOST:PORT/promotions/active
```

```
{
  "100": {
    "id": 1,
    "active": true,
    "code": "prom_100",
    "description": "Promotion from item 100",
    "itemId": "100",
    "percentOff": 20
  },
  "200": {
    "id": 2,
    "active": true,
    "code": "prom_200",
    "description": "Promotion from item 200",
    "itemId": "200",
    "percentOff": 20
  }
}
```

### Get promotion for single item

```
curl http://HOST:PORT/promotions/100
```
```
[
  {
    "id": 1,
    "active": true,
    "code": "prom_100",
    "description": "Promotion from item 100",
    "itemId": "100",
    "percentOff": 20
  }
]
```

### Add new promotions

```
curl -H "Content-Type: application/json" -X POST -d  '{"active": true,"code": "prom_100","description": "Promotion from item 100","itemId": "100","percentOff": 20}' http://HOST:PORT/promotions
```

```
...
> POST /promotions HTTP/1.1
> Host: localhost:8080
> User-Agent: curl/7.64.1
> Accept: */*
> Content-Type: application/json
> Content-Length: 109
> 
* upload completely sent off: 109 out of 109 bytes
< HTTP/1.1 201 Created
< Content-Length: 0
< Location: http://127.0.0.1:8080/promotions/7
...
```
