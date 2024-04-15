# User API Spec

## Create Administrator

Endpoint : GET /api/v1/administrator

Response Body (Success) :

```json
{
  "data": {
    "username": "admin",
    "role": "admin",
    "name": "Administrator"
  },
  "errors": null
}
```

Response Body (Failed) :

```json
{
  "data": null,
  "errors": "Administrator is already exists"
}
```

# (Admin Role)

## Add User

Endpoint : POST /api/v1/users

Request Header :

- X-API-TOKEN : Token (Mandatory)

Request Body :

```json
{
  "username": "dee",
  "password": "rahasia",
  "name": "Dede Irwanto"
}
```

Response Body (Success) :

```json
{
  "data": {
    "username": "dee",
    "password": "rahasia",
    "name": "Dede Irwanto"
  }
}
```

Response Body (Failed) :

```json
{
  "errors": "Username must not blank, ???"
}
```

## Get Users

Endpoint : GET /api/v1/users

Request Header :

- X-API-TOKEN : Token (Mandatory)

Response Body (Success) :

```json
{
  "data": [
    {
      "username": "dee",
      "name": "Dede Irwanto"
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

## Remove User

Endpoint : DELETE /api/v1/users/{idUser}

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

# (User Role)

## Update User

Endpoint : PATCH /api/v1/users/current

Request Header :

- X-API-TOKEN : Token (Mandatory)

Request Body :

```json
{
  // put if only want to update name
  "name": "New name",
  // put if only want to update password
  "password": "New password"
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