
# cathay

All requirements have been completed at CoindeskController, CurrencyController, SynchronizationExchangeRate

Beside, I also implemented additional features
- Swagger-ui: 
    ![image](https://github.com/socldo/cathay/assets/84121453/08713dba-183c-4c38-ae79-b171b3c322ab)
- Request and response body log of all API be called and call out external APIs:
  + POST: /api/currency/create
    request : {
        "name": "Viet Nam",
        "code": "VND",
        "rate_float": 15.2,
        "description": "Viet Nam Dong"
    }
    response: {
        "status": 200,
        "message": "OK",
        "data": {
            "id": 7,
            "code": "Viet Nam",
            "name": "Viet Nam",
            "rate_float": 15.2,
            "description": "Viet Nam Dong",
            "created_at": "01/05/2024 17:52:40",
            "updated_at": "01/05/2024 17:52:40"
        }
    }
    + PUT: /api/currency/7/update
    request : {
        "name": "Viet Nam",
        "code": "VND2",
        "rate_float": 15.2,
        "description": "Viet Nam Dong"
    }
    response: {
        "status": 200,
        "message": "OK",
        "data": {
            "id": 7,
            "code": "VND2",
            "name": "Viet Nam",
            "rate_float": 15.2,
            "description": "Viet Nam Dong",
            "created_at": "01/05/2024 17:52:40",
            "updated_at": "01/05/2024 17:52:40"
        }
    }
    + DELETE: /api/currency/7
    response: {
        "status": 200,
        "message": "OK",
        "data": {
            "id": 7,
            "code": "VND2",
            "name": "Viet Nam",
            "rate_float": 15.2,
            "description": "Viet Nam Dong",
            "created_at": "01/05/2024 17:52:40",
            "updated_at": "01/05/2024 17:52:40"
        }
      }
     + GET: /api/currency
        response: {
    "status": 200,
    "message": "OK",
    "data": [
        {
            "id": 4,
            "code": "VND1",
            "name": "Viet Nam 2",
            "rate_float": 1.2,
            "description": "hello",
            "created_at": "30/04/2024 14:37:32",
            "updated_at": "30/04/2024 14:48:43"
        },
        {
            "id": 2,
            "code": "VND3",
            "name": "Viet Nam ",
            "rate_float": 0.0,
            "description": "hello",
            "created_at": "30/04/2024 14:33:57",
            "updated_at": "30/04/2024 14:48:43"
        },
        {
            "id": 1,
            "code": "VND4",
            "name": "Viet Nam 1",
            "rate_float": 0.0,
            "description": "hello",
            "created_at": "30/04/2024 14:32:25",
            "updated_at": "30/04/2024 14:48:43"
        },
        {
            "id": 7,
            "code": "Viet Nam",
            "name": "Viet Nam",
            "rate_float": 15.2,
            "description": "Viet Nam Dong",
            "created_at": "01/05/2024 17:52:40",
            "updated_at": "01/05/2024 17:52:40"
        }
    ]
}
