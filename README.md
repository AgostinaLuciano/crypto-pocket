# CRYPTO-POCKET

Crypto-pocket is an app thought to manage your holdings,  send, recive, adquire o sell your cryptocurrencies. All quotes you will see are updated thanks to the interaction with [CoinMarketCap API](https://coinmarketcap.com/ "CoinMarketCap")

## Tech stack
- Java 8
- Spring Boot
- Postgres
- Docker

## Architecture

![Alt text](diagrams/architecture.png?raw=true "Architecture")

## Data Model

![Alt text](diagrams/data-model.png?raw=true "Title")

## API

* Healthcheck

```
curl -i -H "Accept: application/json" -H "Content-Type: application/json" -X GET http://localhost:8080/healthcheck
```

* Get user by id

```
curl -i -H "Accept: application/json" -H "Content-Type: application/json" -X GET http://localhost:8080/users/:userId
```

* Get all users

```
curl -i -H "Accept: application/json" -H "Content-Type: application/json" -X GET http://localhost:8080/users
```

* Create user

```
curl -X POST -H "Content-Type: application/json" \
    -d '{"username": "pepe", "password": "pass1234", "email": "pepe@gmail.com"}' \
    http://localhost:8080/users
```

* Update user

```
curl -X PUT -H "Content-Type: application/json" \
    -d '{"username": "pepe", "password": "pass1234", "email": "pepe@gmail.com"}' \
    http://localhost:8080/users/:userId
```

* Delete user

```
curl -X "DELETE" http://localhost:8080/users/:userId
```

* Get quotes

```
curl -i -H "Accept: application/json" -H "Content-Type: application/json" -X GET http://localhost:8080/cryptocurrencies/quotes
```

* Get portfolio (in progress, not full implemented yet)

```
curl -i -H "Accept: application/json" -H "Content-Type: application/json" -X GET http://localhost:8080/portfolios/:userId
```

## Run the app

Create Crypto-Pocket's executable and image:

```
bash build.sh
```


Starts containers using:

```
docker-compose up
```

Consult the app in [http://localhost:8080/](http://localhost:8080/ "http://localhost:8080/")

## Important

To run the application you need an Api key from CoinMarketCap. You can obtain one [here](https://coinmarketcap.com/api/)
Then, modify the Docker-compose.yml file to set the variable:

```
COINMARKETCAP_API_KEY=<<you have to get one from coinmarketcap>>
 
```
```
PASSWORD=root
 
``````
USERNAME=root
 
``````
DB=cryptodb
 
``````
IP=127.0.0.1
 
```
```
PUERTO=45432
 
```