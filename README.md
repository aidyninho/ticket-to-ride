
# Ticket To Ride Web Application

Final project for "Java From Scratch" course by Andersen.



## About The Application

An application to build city map with routes between cities and have users that can buy tickets for shortest routes.
## Built With

Java 21,
SpringBoot 3.3.2,
JUnit 5,
PostgreSQL,
JgraphT.
## Getting Started

Build and Run

```bash
./gradlew bootRun build.gradle
```

## Usage

#### Public API
Saves user to DB:
```
HOST/api/v1/users/save
```
Finds shortest path between cities using Dijkstra's SPA:
```
HOST/api/v1/routes/find
```
#### Private API (HttpBasic secured)
Saves city to DB:
```
HOST/api/v1/cities/save
```
Saves route which links cities to DB:
```
HOST/api/v1/routes/save
```
Saves ticket with shortest route and user to DB:
```
HOST/api/v1/tickets/save
```