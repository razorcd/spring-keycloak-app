# Spring REST API secured with Keycloak and Spring Security

### Start Keycloak manually with Docker Compose
- run `docker-compose up`

### Start Keycloak manually with Docker

- create docker network:
` docker network create keycloak-network`
- start separate mysql server:
`docker run --name mysql -d --net keycloak-network -e MYSQL_DATABASE=keycloak -e MYSQL_USER=keycloak -e MYSQL_PASSWORD=password -e MYSQL_ROOT_PASSWORD=root_password mysql`
- start keycloak server in same network:
`docker run --name mysql -d --net keycloak-network -e MYSQL_DATABASE=keycloak -e MYSQL_USER=keycloak -e MYSQL_PASSWORD=password -e MYSQL_ROOT_PASSWORD=root_password mysql`

### Setup Keycloak with client and users

- open Keycloak in browser at `localhost:8080`
- login with `admin`:`admin` credentials.
- first add a client by clicking `Clients` and importing the file: `keycloak-configs/springdemoapi.json`
- go to `Users` and create at least one user

### Start application Spring application and authenticate
- run `mvn clean install` to install dependencies
- start spring app by running `mvn spring-boot:run`


### Keycloak API


- authenticate with `password` grant and `credentials`:
```
curl -X POST \
  http://localhost:8080/auth/realms/master/protocol/openid-connect/token \
  -H 'content-type: application/x-www-form-urlencoded' \
  -d 'grant_type=password&username=user1&password=password&client_id=springdemoapi&client_secret=2ff43352-5479-41a0-845c-683ed9343493'
```

- authenticate with `refresh_token` grant
```
curl -X POST \
  http://localhost:8080/auth/realms/master/protocol/openid-connect/token \
  -H 'content-type: application/x-www-form-urlencoded' \
  -d 'grant_type=refresh_token&client_id=springdemoapi&client_secret=2ff43352-5479-41a0-845c-683ed9343493&refresh_token=eyJhbGci....'
```

- get userinfo from Keycloak
```
curl -X GET \
  http://localhost:8080/auth/realms/master/protocol/openid-connect/userinfo \
  -H 'authorization: Bearer eyJhbGciO....'
```

- get `Principal` from Spring API
```
curl -X GET \
  http://localhost:8888/api/principal \
  -H 'authorization: Bearer eyJhbGciO.....' \
  -H 'content-type: application/json'
``` 
  
  
  