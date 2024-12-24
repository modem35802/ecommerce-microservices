# Ecommerce Microservices Project
A microservices-based e-commerce application built with Java, Spring Boot, and MySQL. Demonstrates key microservices principles like separate databases, RESTful APIs, and API gateway with circuit breaker.

## Services

![image](https://github.com/user-attachments/assets/eb4cef6a-249a-466a-8a02-c177930abda9)

## API Endpoints

### Product Service:

![image](https://github.com/user-attachments/assets/f044812d-abbe-4a0c-85e7-acfa452002f9)

### Inventory Service:

![image](https://github.com/user-attachments/assets/bdd29f67-6eb8-4d08-ade7-05b889dd632b)

### User Service:

![image](https://github.com/user-attachments/assets/27f07200-df84-44c3-a018-677d00befe1e)

### Order Service:

![image](https://github.com/user-attachments/assets/014e1686-7c7a-440c-a57f-28b0d02bf631)


### API Gateway Route Configuration:

<ul><b>Route for Product Service</b></ul>
<li>spring.cloud.gateway.routes[0].id=product-service</li>
<li>spring.cloud.gateway.routes[0].uri=lb://product-service </li>   
<li>spring.cloud.gateway.routes[0].predicates[0]=Path=/api/product/** </li> </br>

<ul><b>Route for Order Service </b></ul>
<li>spring.cloud.gateway.routes[1].id=order-service </li>
<li>spring.cloud.gateway.routes[1].uri=lb://order-service </li>
<li>spring.cloud.gateway.routes[1].predicates[0]=Path=/api/order/** </li> </br>

<ul><b>Route for Inventory Service </b></ul>
<li>spring.cloud.gateway.routes[2].id=inventory-service </li>
<li>spring.cloud.gateway.routes[2].uri=lb://inventory-service </li>
<li>spring.cloud.gateway.routes[2].predicates[0]=Path=/api/inventory/** </li> </br>

<ul><b>Route for User Service </b></ul>
<li>spring.cloud.gateway.routes[3].id=user-service </li>
<li>spring.cloud.gateway.routes[3].uri=lb://user-service </li>
<li>spring.cloud.gateway.routes[3].predicates[0]=Path=/api/user/** </li> </br>


### Open API(Swagger) Configuration:

![image](https://github.com/user-attachments/assets/f38697dd-cd92-4106-90b4-d48fc8231977)


### Zipkin Configuration:

![image](https://github.com/user-attachments/assets/22978675-5709-4c03-bf02-c315d23bcfb4)


## Implemented Concepts
<li><b>Feign Client:</b> Used for inter service communication</li>
<li><b>Resilience4j:</b> Used for circuit breaker </li>
<li><b>Zipkin:</b> Used for request tracing </li>
<li><b>Open API (Swagger)</b> Used for API documentation </li>


## MySql Databases and Tables:

<b>User Table:</b> </br>
![image](https://github.com/user-attachments/assets/ea7d7d77-6ca8-4acf-9da2-62f9f7542538)

<b>Product Table:<b> </br>
![image](https://github.com/user-attachments/assets/d838e264-f978-4071-a837-8542690dc72b)

<b>Inventory Table:</b> </br>
![image](https://github.com/user-attachments/assets/71387ff4-8a14-4877-a9c2-6e2b379e5692)

<b>Order Table:</b> </br>
![image](https://github.com/user-attachments/assets/8a0a933e-4c5f-463f-965b-c6e47c97734d)











