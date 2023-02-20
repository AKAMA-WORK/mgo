# Start services
Copy and paste .env.exemple to .env

```shell
docker-compose up
```

## Importing Realm

```shell
cd api/migrations
docker cp ./mgo.json $(docker ps | grep keycloak | awk '{print $1}'):/opt/keycloak/
docker exec $(docker ps | grep keycloak | awk '{print $1}') /opt/keycloak/bin/kc.sh import --file /opt/keycloak/mgo.json
```

## Restore database

Open http://localhost:8081 and type : 
- mysql as host
- devops as user
- Root123$ as password
- evoyage as database

Then import these files
- api/migrations/structure.sql
- api/migrations/data.sql

## Starting API

```shell script
./gradlew api:quarkusDev
```
Then open http://localhost:8090/docs
## Starting web
Copy and paste .env.exemple to .env
```shell
cd web
yarn
yarn dev
```

Then open http://localhost:3000
## Starting payment-service
Copy and paste .env.exemple to .env
```shell
cd web
yarn
yarn dev
```

Then open http://localhost:8091/docs
## Maquette

https://www.figma.com/file/FTuyy5t80zbkUnTdjrAkUw/M-Go-1.1?node-id=203%3A1866

### Some notes

Easily start your Reactive RESTful Web Services

[Related guide section...](https://quarkus.io/guides/getting-started-reactive#reactive-jax-rs-resources)


http://localhost:8082/realms/mgo/protocol/openid-connect/auth?client_id=webapp&redirect_uri=http%3A%2F%2Flocalhost%3A8040%2Fadmin%2Fmaster%2Fconsole%2F%23%2F&state=60ab0f74-9479-4433-81da-82274272f890&response_mode=fragment&response_type=code&scope=openid&nonce=d6cff048-7617-4746-80ea-2a1b8b6d7bb1&code_challenge=nBuiuu-gHmIGjcAouaX8J2QTmyBNvAvNR7dSnfFOl5o&code_challenge_method=S256

## Exporting Realm
docker exec $(docker ps | grep keycloak | awk '{print $1}') /opt/keycloak/bin/kc.sh export --file /opt/keycloak/mgo.json --realm mgo
docker cp $(docker ps | grep keycloak | awk '{print $1}'):/opt/keycloak/mgo.json ./

## Some notes for production with pm2

````shell
pm2 start "yarn start" --name payment-service --cwd $(pwd)/payment-service
pm2 start "yarn start" --name web --cwd $(pwd)/web
````


