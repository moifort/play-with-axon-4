# Cart Kata using Axon 4.1

## Requirement

* docker-compose
* java 8

## Run

* Start Redis, Redis GUI, AxonServer: `docker-composer up` 
* Build app: run `./mvnw package` 
* Start domain: `java -Dspring.profiles.active=domain -jar target/axon-0.0.1-SNAPSHOT.jar`
* Start query: `java -Dspring.profiles.active=query -jar target/axon-0.0.1-SNAPSHOT.jar`
* Start client: `java -Dspring.profiles.active=client -jar target/axon-0.0.1-SNAPSHOT.jar`
* Go to: http://localhost:8080/swagger-ui.html

![Swagger](static/swagger.png)

## How to



## Problematic

### Data synchronization

The problem with CQRS is when you write data (by sending a command) is not transactional, you don't know when your view/projection will 
be updated. When your **front-end** app send a `CreateCart` http request, **client** app will just acknowledge the command if everything went
well or not, but you have no clue (due to CQRS architecture) if your **query** app have build the projection.

Solutions:
  1. In your **front-end** app, retry request until the data is available. PRO: simple, easy? CON: Lot of request, not a good idea to flood network until the data is available
  2. In your **client** app, you can make the command transactional by adding in the transaction the time to put the projection updated. PRO: like the old way (meh!) CON: difficult and you need to have a compatible infrastructure 
  3. In your **client** app, expose a subscriber (like realtime database from firebase) end point so **front-end** app can be advertise on every change. PRO: clean, scalable CON: difficult and you need to have a compatible infrastructure
 
I prefer the last solution, it's more elegant, clean and scalable. The good things is we can do it with the actual infrastructure: Redis and AxonServer 

### Update event source

### Rebuild projection

## Admin
* Access AxonServer UI: http://localhost:8024

![AxonServer](static/axonserver.png)

* Access to Redis UI: http://localhost:9090

![Redis](static/redis.png)
 

