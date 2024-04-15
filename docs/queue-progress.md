# Print Queue API Spec

## Print Queue

Endpoint : GET /api/v1/queue-progress

Response Body (Success) :

```json
{
  "data": {
    "number": "A001",
    "counter": "Counter xxx"
  }
}
```

Response Body (Failed, 400) :

```json
{
  "errors": "Bad request"
}
```