# TP-Integrador-PIL

## REST API

The REST API to  app is described below.

### CRUD of Supervisor

    'http://localhost:8080/supervisor'

## Get list of Supervisors

### Request

`GET /supervisor`

Bring all the supervisors

### Response

```json
[
{
"id": 1,
"supervisorName": "maria"
},
{
"id": 2,
"supervisorName": "juan"
},
{
"id": 3,
"supervisorName": "diego"
},
{
"id": 4,
"supervisorName": "camila"
}
]
```
## Create a new Supervisor

### Request

`POST /supervisor`

### Body

```json
{
  "supervisorName": "esteban"
}
```

### Response

```json
{
  "id": 5,
  "supervisorName": "esteban"
}
```

### Get a Supervisor

### Request

`GET /supervisor/{id}`
`GET /supervisor/1`

### Response

```json
{
"id": 1,
"supervisorName": "maria"
}
```

### Update a Supervisor

### Request

`PUT /supervisor/{id}`
`PUT /supervisor/1`

### Body

```json
{
  "supervisorName": "laura"
}
```


### Response

```json
{
  "id": 1,
  "supervisorName": "laura"
}
```

### Delete a Supervisor

### Request

`DELETE /supervisor/{id}`
`DELETE /supervisor/1`

### Response

```json
"The Supervisor with the id: 1, was removed"
```

---

### CRUD of Location

    'http://localhost:8080/location'
    
### Request

`GET /location`

Bring all the locations

### Response

```json
[
 {
    "id": 1,
    "department": "a",
    "neighborhood": "b",
    "address": "c",
    "number": 1,
    "coordinates": {
      "x": 13424,
      "y": -231
    }
  }
]
```

## Create a new Location

### Request

`POST /location`

### Body

```json
{
  "department": "Department",
  "neighborhood": "The Neighborhood",
  "address": "Address",
  "number": 120,
  "coordinates": {
    "x": 200,
    "y": 200
  }
}
```

### Response

```json
{
  "id": 1,
  "department": "Capital",
  "neighborhood": "Centro",
  "address": "Montevideo",
  "number": 123,
  "coordinates": {
    "x": -314216005,
    "y": -641907307
  }
}
```

### Get a Location

### Request

`GET /location/{id}`
`GET /location/1`

### Response

```json
{
  "id": 1,
  "department": "Capital",
  "neighborhood": "Centro",
  "address": "Montevideo",
  "number": 123,
  "coordinates": {
    "x": -314216005,
    "y": -641907307
  }
}
```

### Update a Location

### Request

`PUT /location/{id}`
`PUT /location/1`

### Body

```json
{
  "department": "New Capital",
  "neighborhood": "Alberdi",
  "address": "Arturo Orgaz",
  "number": 510,
  "coordinates": {
    "x": -314033216, 
    "y": -642089571
  }
}
```

### Response

```json
{
  "id": 1,
  "department": "New Capital",
  "neighborhood": "Alberdi",
  "address": "Arturo Orgaz",
  "number": 510,
  "coordinates": {
    "x": -314033216,
    "y": -642089571
  }
}
```

### Delete a Location

### Request

`DELETE /location/{id}`
`DELETE /location/1`

### Response

"The Location with the id: 1, was removed"    
