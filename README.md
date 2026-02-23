# clean-architecture

## Usage

Build
```bash
mvn clean install
```

Run
```bash
mvn spring-boot:run
```

```
http://localhost:8080/swagger-ui.html
http://localhost:8080/v3/api-docs
http://localhost:8080/actuator
http://localhost:8080/actuator/metrics/application.started.time
http://localhost:8080/1/authors
http://localhost:8080/2/authors
```

## Architecture

```mermaid
---
title: Hexagonal Architecture
---
flowchart LR
    driverAdapter(Driver Adapter)
    drivenAdapter(Driven Adapter)
    domain(Domain)
    port(Port)
    subgraph dip
        direction RL
        drivenAdapter
        port
    end
    driverAdapter --> domain --> dip
    drivenAdapter --> port
    
```

```mermaid
---
title: Clean Architecture
---
flowchart LR
    domain(domain)
    infrastructure(infrastructure)
    infrastructure --> domain
```

```mermaid
---
title: Clean Architecture
---
flowchart LR
    useCase1(use case 1)
    useCase2(use case 2)
    boundedContext1(bounded context 1)
    boundedContext2(bounded context 2)
    restEndpoint1(rest endpoint 1)
    restEndpoint2(rest endpoint 2)
    database[(database)]
    restClient(rest client)
    file[\file/]
    subgraph infrastructure
        restEndpoint1
        restEndpoint2
        database
        file
        restClient
        subgraph application
            restEndpoint1
            restEndpoint2
        end
        subgraph persistence
            database
            file
            restClient
        end
    end
    subgraph domain
        subgraph useCases
            useCase1
            useCase2
        end
        subgraph boundedContexts
            boundedContext1
            boundedContext2
            boundedContext3
        end
    end
    restEndpoint1 --> useCase1
    restEndpoint2 --> useCase2
    useCase1 --> boundedContext1
    useCase1 --> boundedContext2
    useCase2 --> boundedContext2
    useCase2 --> boundedContext3
    database --> boundedContext1
    database --> boundedContext2
    restClient --> boundedContext2
    file --> boundedContext3
```