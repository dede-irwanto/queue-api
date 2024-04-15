# Application Setting API Spec (Admin Role)

## Add Application Setting

Endpoint : POST /api/v1/application-setting

Request Header :

- X-API-TOKEN : Token (Mandatory)

Request Body :

```json
{
  "image": "xxx.jpg",
  "instituteName": "xxx",
  "footer": "string"
}
```

Response Body (Success) :

```json
{
  "data": {
    "image": "xxx.jpg",
    "instituteName": "xxx",
    "footer": "string"
  }
}
```

Response Body (Failed) :

```json
{
  "errors": "Name must not blank, ???"
}
```

## Get Application Setting

Endpoint : GET /api/v1/application-setting

Request Header :

- X-API-TOKEN : Token (Mandatory)

Response Body (Success) :

```json
{
  "data": {
    "image": "xxx.jpg",
    "instituteName": "xxx",
    "footer": "string"
  }
}
```

Response Body (Failed, 401) :

```json
{
  "errors": "Unauthorized"
}
```

## Update Application Setting

Endpoint : PATCH /api/v1/application-setting

Request Header :

- X-API-TOKEN : Token (Mandatory)

Request Body :

```json
{
  "image": "xxx.jpg",
  "instituteName": "xxx",
  "footer": "string"
}
```

Response Body (Success) :

```json
{
  "data": {
    "image": "xxx.jpg",
    "instituteName": "xxx",
    "footer": "string"
  }
}
```

Response Body (Failed, 401) :

```json
{
  "errors": "Unauthorized"
}
```