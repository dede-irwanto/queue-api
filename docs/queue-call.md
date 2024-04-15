# Call Queue API Spec (User Role)

## Call Queue

Endpoint : PUT /api/v1/queue-call/{serviceTypeId}

Request Header :

- X-API-TOKEN : Token (Mandatory)

Request Body :

```json
{
  "counter": "Loket xxx"
}
```

Response Body (Success) :

```json
{
  "data": {
    "number": "A001",
    "counter": "Loket xxx"
  }
}
```

Response Body (Failed, 400) :

```json
{
  "errors": "Bad request"
}
```