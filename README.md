# Frameworkless Http Server Java Application


## HELLO WORLD
This is my implementation of the ```Frameworkless Basic Web Application Kata With Enhancements``` kata:

- [Kata on github](https://github.com/MYOB-Technology/General_Developer/blob/master/katas/kata-frameworkless-basic-web-application/kata-frameworkless-basic-web-application-enhancements.md)

According to the kata, when hitting the server from a web browser it should return a greeting message view with the world owners name and the current time on the server. 

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

- *Expected Response:* ```Get all users, or create a new user```
##
- *Endpoint:*  ```/users/:id```
    
- *Supports:*   ```GET, PUT, DELETE```

- *Expected Response:* ```Get specific user, delete specific user and update a users name```