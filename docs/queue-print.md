# Print Queue API Spec

## Print Queue

Endpoint : POST /api/queue-print/{serviceTypeId}

Response Body (Success) :

```json
{
  "data": {
    "number": "A001",
    "serviceTypeDescription": "Layanan xxxx",
    "printedAt": 2345465657
    // millisecond
  }
}
```

Response Body (Failed, 400) :

```json
{
  "errors": "Bad request"
}
```