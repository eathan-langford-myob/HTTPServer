# Frameworkless Http Server Java Application


## HELLO WORLD
This is my implementation of the ```Frameworkless Basic Web Application Kata With Enhancements``` kata:

- [Kata on github](https://github.com/MYOB-Technology/General_Developer/blob/master/katas/kata-frameworkless-basic-web-application/kata-frameworkless-basic-web-application-enhancements.md)

According to the kata, when hitting the server from a web browser, it should return a greeting message view with the world owners name and the current time on the server. 

The rest of the app, should mimic RESTful API beahviour
```Create, Read, Update and Delete.```

For the CI/CD kata 
I used Docker to solve the containerisation, Jupiter platform for deployment and Buildkite for integration.



- [CI/CD Kata on github](https://github.com/MYOB-Technology/General_Developer/blob/master/katas/kata-cicd/kata-cicd.md) 
## BUILD STATUS
[![Build status](https://badge.buildkite.com/7f966a6092cad1c4c476376dcad26e2ed825fff4221bf5a2cd.svg)](https://buildkite.com/myob/eathan-hello-world)
## Kata Requirements

- Application containerised
- Managing secrets
- ```/``` root endpoint displays the greeting.
- ```/users``` will return all user names
- ```Admin``` world owner and cant be altered
- ```CRUD``` implementation
- We can assume there are ```no duplicate``` names
- CI/CD pipeline

## API CONTRACT


- *Endpoint:*  ```/```
    
- *Supports:*   ```GET```

- *Expected Response:* ```Greeting Message, all users, date time```
##
- *Endpoint:*  ```/users```
    
- *Supports:*   ```GET, POST, ```

- *Expected Response:* ```Greeting Message, all users, date time```
### Get users list
- **URI:**  `/users`

- **Method:**   `GET`

- **Success Response:**
    * Code: `200` <br/>
      Content: `{"content": ["username1", "username2"]}`
      
### Create user
- **URI:**  `/users`

- **Method:**   `POST`

- **Request body:** `{"name": "Spiderman"}`

- **Success Response:**
    * Code: `201` <br/>
      Content: `{"content": "SUCCESS"}`
      
- **Error Response:**
    * When user name already exists <br/>
      Code: `400` <br/>
      Content: `{"content": "User already exists"}`

    OR when user name is invalid
    * Code: `400` <br/>
      Content: `{"content": "Invalid user name"}`

### Update user
- **URI:**  `/users/:name`

- **Method:**   `PUT`

- **Request body:** `{"name": "Thor"}`

- **Success Response:**
    * Code: `200` <br/>
      Content: `{"content": "SUCCESS"}`
      
- **Error Response:**
    * When user name already exists <br/>
      Code: `400` <br/>
      Content: `{"content": "User already exists"}`

    OR when `:name` already exists
    * Code: `400` <br/>
      Content: `{"content": "User does not exist"}`
    
    OR when `:name` is owner's name
    * Code: `400` <br/>
      Content: `{"content": "Target user is owner. No modifications could be carried out on owner of this world"}`

### Delete user
- **URI:**  `/users`

- **Method:**   `DELETE`

- **Request body:** `{"name": "Batman"}`

- **Success Response:**
    * Code: `200` <br/>
      Content: `{"content": "SUCCESS"}`
      
- **Error Response:**
    * When user name does not exist <br/>
      Code: `400` <br/>
      Content: `{"content": "User does not exist"}`

    OR when `:name` is owner's name
    * Code: `400` <br/>
      Content: `{"content": "Target user is owner. No modifications could be carried out on owner of this world"}`
      
### Other request types
- **Error Response:**
    * Code: `405` <br/>
      Content: `{"content": "Invalid request - method not allowed"}`
      
## Pipeline

[Diagram](https://www.draw.io/?lightbox=1&highlight=0000ff&edit=_blank&layers=1&nav=1&title=Pipeline#R5VrbcuI4EP0aHklJvgGPISEzs5VUUZPdmsnTlgDFViJbHllOYL5%2BJSODhcwlWQxO5gWs1tWn%2B7S72%2B64V%2FH8C0dpdMdmmHYcMJt33OuO40DgOfJPSRZLSeANloKQk5ketBbck9%2B4nKmlOZnhzBgoGKOCpKZwypIET4UhQ5yzV3PYI6PmrikKsSW4nyJqS3%2BQmYiW0r7TW8u%2FYhJG5c4w0PcXo3KwvpMsQjP2WhG5o457xRkTy6t4foWpAq%2FEZTnvZkvv6mAcJ%2BKQCdGj988v%2BJQRTJ8i6iyym4dht1wmE4vyjvFMAqCbjIuIhSxBdLSWDjnLkxlWywLZWo%2B5ZSyVQiiFT1iIhdYmygWTokjEVPfiORE%2F1fQLX7ce9GLq%2BnpebSwqjTHmJMYC81KWCL6oLKSaD%2BUeqrFeqmgtqq3NxWw8S2xYzqd4B4ilXSIeYrEL7OU4BXBlA62tL5jJ0%2FCFHMAxRYK8mBaItCGHq3FrXcsLre43qF6v%2B4JorneyTOEFc0EkF27RBNMxy4ggLJFdEyYEi02dlmMvKQnVGKFsYYh0aypBVUgPM8HZM75ilCncE5Yog3oklJaijuMCMBzdOGpwhFJ1lHgeKu9ygX7nHF9QlC4XTxlRq45e5OKZPsZWLarz4flO3MtefzlDeywPaAK%2Frvk%2F0KKoQv0ANKSoGsV8RI52wQUAsEpTcAGhd1qqOgdS1W0VVZ2PStWQiCif%2FDuVYUFzfHVMvjrAt%2Fjaq%2BFrrym%2Buvu1ZbLzNSIC36eosNxXCZ%2BprqNABAyMBrZLq%2FVpzYEEzxt4VF3aysHVO7X3Oxv%2FQGfTa5Wz8S3zvb%2B%2Fs5VFqQzB8UmsN3D3Wi90aqy335TxBhZEo6vvrYJIRi1nxqhnYfQ9T9RGxVSBM%2FkI2O4W4UlQc6GJGqxDre7Z4TXmFs8a68E3uMV17mWGdKtEbEtA93532j%2FQnQatcqd9iwfDnNCZ2ipRv2meRfLvMk3l77dYlSPaRou6iOq0tHD6Z40WjBQIHkgLsyQBGqPF4EBaeK2ixcCixTVOKVu0z%2Fh7Zzd%2BYGHVcQIqVDbG5H1W4Qp%2B5azs6GaFYV%2FKATBI5%2BtOeRXq%2F2KVSSn4K0%2BJyv0cMJY28Mh4XA6RB59sTpOy5f6leENzEnBhqodjeSQ0KQaAMiEs4PKHHf9aJaGShMtjFxPKnJTiR7E1cc2kOZAk%2FLtgddc7jhl4fdMMPLdnm4FXlzI1ZgZ2XvkDTyLGnj8X8gPPjMmAZwFfR7%2FmcPcs3MfFM%2Fszgb5RQ3FrnvinNXY7C23I5xXR2LN0e3%2Bsq3MD09UFdhLUP6nu7dTRAjyUYUF6%2BL2v3g9qjXSqr%2BAOKZh5vu3%2Bvbp8Gg7cpmCxqw6HwrIb5veDdT4wbJ9sYfE%2Fa6y7dbCfXOfDBrYfnC2Ve9fimDvwa6BrripdU%2FzEU4531qrOiaC74adqIDxt%2Fcp2UmM22wHeu5K6I4EHe30DvIHt4wPXBq%2BxF73QrhWNcs5S1E05TrnCEeDkxUKzTdHHkVSzEY5aeqmtZTcWkUC7WnFzp2LMBMU4Kwz3D9DJfm8N4Um10rNQb3tRcOPzh7fXyndGcntLhVrle0uFsF0ldMem3zhfvUZSN148o1vNwTdnZf7Gk33FrT2fITVGt%2FINo1UHAaT2rcXHRn8jJz57Uuza1T%2FNgU%2BIfg%2BY6PvBmWuArp1S3TKJRZErx2leFM0%2FkwY2v4GsMX8IjqMB2Vx%2FBF30VT4ld0f%2FAQ%3D%3D)