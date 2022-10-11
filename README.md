# Spring Cloud Microservices Example

This project covers the following topics that are used to develop microservices with Spring Cloud:

* Service discovery with Netflix Eureka
* Request routing with API Gateway
* Service-to-service calls via OpenFeign
* Load balancing via Spring Cloud Load Balancer
* Distributed tracing via Sleuth and Zipkin
* Implementing Circuit Breaker via Resilience4j
* Retrying service-to-service calls via Resilience4j

There are 3 microservices included in the project:
* **User Service**: Used to retrieve user details along with their orders.
* **Order Service**: Used to retrieve previous orders of users.
* **Product Service**: Used to retrieve product details.