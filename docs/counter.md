# Counter API Spec (Admin Role)

## Add Counter

Endpoint : POST /api/v1/counters

Request Header :

- X-API-TOKEN : Token (Mandatory)

Request Body :

```json
{
  "name": "Loket xxx",
  "serviceTypeId": "random-string"
}
```

Response Body (Success) :

```json
{
  "data": {
    "id": "random-string",
    "name": "Loket xxx",
    "serviceType": {
      "name": "Layanan xxx",
      "description": "Deskripsi layanan xxx"
    }
  }
}
```

Response Body (Failed) :

```json
{
  "errors": "Name must not blank, ???"
}
```

## Get Counters

Endpoint : GET /api/v1/counters

Request Header :

- X-API-TOKEN : Token (Mandatory)

Response Body (Success) :

```json
{
  "data": [
    {
      "id": "random-string",
      "name": "Loket xxx",
      "serviceType": {
        "name": "Layanan xxx",
        "description": "Deskripsi layanan xxx"
      }
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

## Update Counter

Endpoint : PATCH /api/v1/counters/{counterId}

Request Header :

- X-API-TOKEN : Token (Mandatory)

Request Body :

```json
{
  "name": "Loket xxx",
  "serviceTypeId": "random-string"
}
```

Response Body (Success) :

```json
{
  "data": {
    "id": "random-string",
    "name": "Loket xxx",
    "serviceType": {
      "name": "Layanan xxx",
      "description": "Deskripsi layanan xxx"
    }
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

Endpoint : DELETE /api/v1/counters/{counterId}

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