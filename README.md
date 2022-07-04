## Second phase of cloud project



Read Readme.md file in branch phase1\_final.

### Micro-service application :

#### Development plan :

*   Developing each micro-service
*   Dockerizing the application

#### Micro-service development :

First, we used [http://start.spring.io/](http://start.spring.io/) to create the micro-service as an application, then we used the reusable codes from the monolith app to develop it.

We specified needed changes and their dependencies due to the need for internal calls. For example, a service registry is needed to discover other services to call them and a gateway is needed to implement JWT and changes are needed in entities as the database of each micro-service is only accessible by itself.

Like other micro-services, we used [http://start.spring.io/](http://start.spring.io/) to create gateway and service registry services. For service registry, we used Eureka Server and Eureka Client is used in other micro-services which are defined in their application.yml.

JWT is implemented in the gateway which routes the incoming request to the needed micro-service.

#### Dockerizing :

Now that all micro-services are developed, we can use docker to create images. These images will then be used to be deployed in containers.

A docker-compose.yml is created to use the images and deploy them in containers. All micro-services and their databases should be declared here with ports and configs.

For databases, we use MySQL.

### How to run :

*   Create images using two possible methods :

1.  Using Dockerfile in each micro-service
2.  Using Gradle task BootBuildImage 

*   Run the following command in the directory of the application where docker-compose.yml exists

```plaintext
docker-compose up
```

*   Use Eureka to check micro-services at [localhost:8761/eureka](localhost:8761/eureka)
*   Use gateway service to make requests at [localhost:9191/](localhost:9191/)
*   A postman collection is available at the root of the project.
*   Note that an image of oracle mysql is needed if not downloaded automatically 

A simple use of an application as an example :

1.  Register a user
2.  Retrieve JWT token
3.  Use JWT token and create an article
4.  Use JWT token to create a comment
5.  Use JWT token to get user's articles and comments

Swagger is enabled for all micro-services and is reachable at [http://localhost:\[service-port\]/swagger-ui/index.html#/](http://localhost:[service-port]/swagger-ui/index.html#/) , replace \[service-port\] to the desired service port.

Micro-services ports :

*   service registry : 8761
*   gateway : 9191
*   user-service : 9002
*   article-service : 9003
*   comment-service : 9001
