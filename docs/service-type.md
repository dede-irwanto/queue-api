# Service Type API Spec (Admin Role)

## Add Service Type

Endpoint : POST /api/v1/service-type

Request Header :

- X-API-TOKEN : Token (Mandatory)

Request Body :

```json
{
  "name": "Layanan xxx",
  "description": "Deskripsi layanan xxx"
}
```

Response Body (Success) :

```json
{
  "data": {
    "name": "Layanan xxx",
    "description": "Deskripsi layanan xxx"
  }
}
```

Response Body (Failed) :

```json
{
  "errors": "Name must not blank, ???"
}
```

## Get Service Type

Endpoint : GET /api/v1/service-type

Request Header :

- X-API-TOKEN : Token (Mandatory)

Response Body (Success) :

```json
{
  "data": [
    {
      "name": "Layanan xxx",
      "description": "Deskripsi layanan xxx"
    }
  ]
}
```

Response Body (Failed, 401) :

```json
{
  "errors": "Unauthorized"
}
```

## Update Service Type

Endpoint : PATCH /api/v1/users/current

Request Header :

- X-API-TOKEN : Token (Mandatory)

Request Body :

```json
{
  // put if only want to update name
  "name": "New name",
  // put if only want to update password
  "description": "New description"
}
```

Response Body (Success) :

```json
{
  "data": {
    "username": "dee",
    "name": "Dede Irwanto"
  }
}
```

Response Body (Failed, 401) :

```json
{
  "errors": "Unauthorized"
}
```

## Remove Service Type

Endpoint : DELETE /api/v1/users/{serviceTypeId}

Request Header :

- X-API-TOKEN : Token (Mandatory)

Response Body (Success) :

```json
{
  "data": "OK"
}
```

Response Body (Failed, 401) :

```json
{
  "errors": "Unauthorized"
}
```