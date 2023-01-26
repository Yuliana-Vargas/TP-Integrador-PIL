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
