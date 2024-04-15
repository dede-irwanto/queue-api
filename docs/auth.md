# Auth API Spec

## Login User

Endpoint : POST /api/v1/auth/login

Request Body :

```json
{
  "username": "dee",
  "password": "rahasia"
}
```

Response Body (Success) :

```json
{
  "data": {
    "token": "TOKEN",
    "role": "admin",
    "expiredAt": 234567809134
    // millisecond
  }
}
```

Response Body (Failed, 401) :

```json
{
  "errors": "Username or password wrong"
}
```

## Logout User

Endpoint : DELETE /api/v1/auth/logout

Request Header :

- X-API-TOKEN : Token (Mandatory)

Response Body (Success) :

```json
{
  "data": "OK"
}
```
