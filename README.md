# TP-Integrador-PIL

## REST API

The REST API to  app is described below.

---

## CRUD of Recycling Zone

    'http://localhost:8080/recycling-zone'
    
## ***Get list of Recycling zones***

### Request

`GET /recycling-zone`

Bring all the recycling zones

### Response

```json
[
    {
        "id": 1,
        "name": "Zone1",
        "occupationCapacity": "EMPTY",
        "stateOfTheZone": "AVAILABLE",
        "classificationType": "BATTERY_DISPOSAL",
        "needsReclassification": true 
    },
    {
        "id": 2,
        "name": "Zone2",
        "occupationCapacity": "EXCEEDED",
        "stateOfTheZone": "DAMAGED",
        "classificationType": "PAPER_DISPOSAL",
        "needsReclassification": true,
        "supervisor": {
            "id": 3,
            "supervisorName": "Romina"
        },
        "location": {
            "id": 1,
            "department": "Capital",
            "neighborhood": "Alberdi",
            "address": "Sta Fe",
            "number": 159,
            "coordinates": {
                "x": -31.0,
                "y": -64.0
            }
        },
        "complaints": [
            {
                "id": 1,
                "typeOfComplaint": "FOR_MISUSE",
                "description": "improper recycling"
            }
        ]
    }
```

## ***Create a new Recycling Zone***

### Request

`POST /recycling-zone`

### Body

```json
{
    "name": "Zone3",
    "occupationCapacity": 3,
    "stateOfTheZone": 0,
    "classificationType": 1,
    "needsReclassification": false,
    "typeOfComplaint": 3,
    "supervisor": {
        "supervisorName": "Paula"
    },
    "location": {
        "department": "a",
        "neighborhood": "b",
        "address": "c",
        "number": 10,
        "coordinates": {
            "x": 13424.1231,
            "y": -231.22
        }
    },
    "complaints": [
            {
                "typeOfComplaint": "ANOTHER_REASON",
                "description": "usually excceded"
            }
        ]
}
```

### Response

```json
{
    "id": 3,
    "name": "Zone3",
    "occupationCapacity": "EXCEEDED",
    "stateOfTheZone": "AVAILABLE",
    "classificationType": "NON_RECYCLABLE_GARBAGE_DISPOSAL",
    "needsReclassification": false,
    "supervisor": {
        "id": 8,
        "supervisorName": "Paula"
    },
    "location": {
        "id": 8,
        "department": "a",
        "neighborhood": "b",
        "address": "c",
        "number": 10,
        "coordinates": {
            "x": 13424.0,
            "y": -231.0
        }
    },
    "complaints": [
        {
            "id": 9,
            "typeOfComplaint": "ANOTHER_REASON",
            "description": "usually excceded"
        }
    ]
}
```

## ***Get a Recycling Zone***

### Request

`GET /recycling-zone/{id}`
`GET /recycling-zone/1`

### Response

```json
{
    "id": 1,
    "name": "Zone1",
    "occupationCapacity": "EMPTY",
    "stateOfTheZone": "AVAILABLE",
    "classificationType": "BATTERY_DISPOSAL",
    "needsReclassification": true 
}
```

## ***Update a Recycling Zone***

### Request

`PUT /recycling-zone/{id}`
`PUT /recycling-zone/1`

### Body

```json
{
    "name": "zona3 edited"
}
```

### Response

```json
{
    "id": 3,
    "name": "zona3 edited",
    "occupationCapacity": "EXCEEDED",
    "stateOfTheZone": "AVAILABLE",
    "classificationType": "NON_RECYCLABLE_GARBAGE_DISPOSAL",
    "needsReclassification": false,
    "supervisor": {
        "id": 8,
        "supervisorName": "Paula"
    },
    "location": {
        "id": 8,
        "department": "a",
        "neighborhood": "b",
        "address": "c",
        "number": 10,
        "coordinates": {
            "x": 13424.0,
            "y": -231.0
        }
    },
    "complaints": [
        {
            "id": 9,
            "typeOfComplaint": "ANOTHER_REASON",
            "description": "usually excceded"
        }
    ]
}
```

## ***Delete a Recycling Zone***

### Request

`DELETE /recycling-zone/{id}`
`DELETE /recycling-zone/1`

### Response

```json
"The Recycling Zone with id: 1, was removed"
```

---

## CRUD of Supervisor

    'http://localhost:8080/supervisor'

## ***Get list of Supervisors***

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
## ***Create a new Supervisor***

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

## ***Get a Supervisor***

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

## ***Update a Supervisor***

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

## ***Delete a Supervisor***

### Request

`DELETE /supervisor/{id}`
`DELETE /supervisor/1`

### Response

```json
"The Supervisor with the id: 1, was removed"
```

---

## CRUD of Location

    'http://localhost:8080/location'

## ***Get list of Locations***
    
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

## ***Create a new Location***

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

## ***Get a Location***

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

## ***Update a Location***

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

## ***Delete a Location***

### Request

`DELETE /location/{id}`
`DELETE /location/1`

### Response

```json
"The Location with the id: 1, was removed"    
```

---

## CRUD of Complaints

    'http://localhost:8080/complaint'

## ***Get list of Complaints***

### Request

`GET /complaint`

Bring all the complaints

### Response

```json
[
    {
        "id": 1,
        "typeOfComplaint": "FOR_MISUSE",
        "description": "improper recycling"
    },
    {
        "id": 2,
        "typeOfComplaint": "FOR_VANDALISM",
        "description": "broken"
    }
]
```
## ***Create a new Complaint***

### Request

`POST /complaint`

### Body

```json
{
    "typeOfComplaint": "ANOTHER_REASON",
    "description": "always full"
}
```

### Response

```json
{
    "id": 3,
    "typeOfComplaint": "ANOTHER_REASON",
    "description": "always full"
}
```

## ***Get a Complaint***

### Request

`GET /complaint/{id}`
`GET /complaint/1`

### Response

```json
{
    "id": 1,
    "typeOfComplaint": "FOR_MISUSE",
    "description": "improper recycling"
}
```

## ***Update a Complaint***

### Request

`PUT /complaint/{id}`
`PUT /complaint/2`

### Body

```json
{
    "typeOfComplaint": "FOR_VANDALISM",
    "description": "broken container"
}
```


### Response

```json
{
    "id": 2,
    "typeOfComplaint": "FOR_VANDALISM",
    "description": "broken container"
}
```

## ***Delete a Complaint***

### Request

`DELETE /complaint/{id}`
`DELETE /complaint/1`

### Response

```json
"The Complaint with id: 1, was removed"
```

---

## Authors

[<img src="https://avatars.githubusercontent.com/u/81372862?v=4" width=115><br><sub>Fernando José Noceti</sub>](https://github.com/FerNoceti) |  [<img src="https://avatars.githubusercontent.com/u/101755881?v=4" width=115><br><sub>Milena Muñoz</sub>](https://github.com/milemunoz10) |  [<img src="https://avatars.githubusercontent.com/u/101777026?v=4" width=115><br><sub>Martin Maciel</sub>](https://github.com/MartinMaciel110916) |  [<img src="https://avatars.githubusercontent.com/u/72422271?v=4" width=115><br><sub>Yuliana Vargas</sub>](https://github.com/Yuliana-Vargas) |
| :---: | :---: | :---: | :---: |
