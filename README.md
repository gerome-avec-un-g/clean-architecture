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
```

## Architecture
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
    useCases(use cases)
    boundedContext1(bounded context 1)
    boundedContext2(bounded context 2)
    restEndpoint(rest endpoint)
    persistence[(persistence)]
    restClient(rest client)
    subgraph infrastructure
        restEndpoint
        persistence
        restClient
        subgraph application
            restEndpoint
        end
        subgraph 3rd-party
            persistence
            restClient
        end
    end
    subgraph domain
        useCases
        boundedContext1
        boundedContext2
    end
    restEndpoint --> useCases
    useCases --> boundedContext1
    useCases --> boundedContext2
    persistence --> boundedContext1
    persistence --> boundedContext2
    restClient --> boundedContext2
```