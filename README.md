# Spring application secured with Keycloak and Spring Security

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
- import configuration by clicking `Import` and selecting the `realm-export.json` file.
This will create a `springdemo` client and 2 users:
    - `user1`:`password`
    - `admin1`:`password`

### Start application Spring application
- run `mvn clean install` to install dependencies
- start spring app by running `mvn spring-boot:run`
- load the public page in browser at `localhost:8888/pets`
Clicking `login` will redirect to keycloak login page. Keycloak will work with Spring Security to hold the session.


