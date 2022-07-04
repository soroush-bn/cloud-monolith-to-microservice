## First phase of cloud project

### Monolith application :

#### Functionality :

This application provides an api that enables users to:

*   Sign up
*   Post articles
*   Post comments on articles

#### How it works :

This project uses JAVA Spring using Kotlin 

There are three different controllers :

*   UserController
*   ArticleController
*   CommentController

Each one has the needed endpoints declared in them.

For the database, h2 and JPA are used.

There is one service for each business logic which is used to call the needed repositories.

User, Article and Comment repositories are declared apart, so testing has become easier.

#### Database :

Entities :

*   UserEntity
*   ArticleEntity
*   CommentEntity

Relations :

![](https://33333.cdn.cke-cs.com/kSW7V9NHUXugvhoQeFaf/images/5f67911c5fe589a4294487ae05674a0a1765b56f14c8c129.png)

#### How to run :

Run the main function in MonolithApplication.kt.

Use [localhost:9002](localhost:9002).

### Migration  of monolith application to micro-services  :

#### Development plan :

1.  Specify micro-services
2.  Specify files which can be reused
3.  Specify the relation between micro-services
4.  Specify needed internal calls between micro-services
5.  Specify new features to be implemented

#### Development :

Three main micro-services :

1.  User 
2.  Article 
3.  comment

Reasons :

User micro-service was chosen because user management can be considered a standalone service in this application as users can sign in once and, with log-in functionality, use other services.

Article micro-service was chosen because this service has a lot of potential for future development and its functionality can be considered to have low coupling.

Comment micro-service was chosen for the same reasons as article service as there might be a lot of comments on articles and it is low coupled with article service.

Others :

    4. Service Registry

    5. Gateway

Reasons :

Service Registry is used to let each service discover other services and also, with the help of Eureka, we can monitor the services' condition.

Gateway is used so there will be a single port or main url for the api and it will re-route the request to each service needed and this is possible with functionality of the service registry.

Now that all micro-services are chosen and specified, we can check the monolith application classes and choose which class can be reused in micro-service implementation.

User :

*   UserController.kt
*   UserService.kt
*   UserRepositiry.kt
*   UserEntity.kt

Article :

*   ArticleController.kt
*   ArticleService.kt
*   ArticleRepositiry.kt
*   ArticleEntity.kt

Comment :

*   CommentController.kt
*   CommentService.kt
*   CommentRepositiry.kt
*   CommentEntity.kt

Differences to consider :

*   Entities will be changed to have fields that will draw the relation between each micro-service.
*   Repository functions will be changed as the entities change.
*   Services will be changed as internal calls should be implemented.

Now we can specify the relation between micro-services:

![](https://33333.cdn.cke-cs.com/kSW7V9NHUXugvhoQeFaf/images/7978ecb566883b690553aae9294f248d31eec671c0b3d25c.jpg)

Internal calls :

```plaintext
user-service --> article-service:
    where: article-service/ArticleController.kt, class: ArticleController
    happened: user-service/UserService.kt, class: UserService
    code: getUsersArticles(userId):List<Article> 
    reason: get user's article from article service
    solution: GRPC 

user-service --> comment-service:
    where: comment-service/CommentController.kt, class: CommentController
	happened: user-service/UserService.kt, class: UserService
    code: getUsersComments(userId):List<Comment> 
    reason: get user's article from article service
    solution: GRPC 

article-service --> comment-service:
    where: comment-service/CommentController.kt, class: CommentController
    where: article-service/ArticleService.kt, class: ArticleService
    code: getArticlesComments(articleId):List<Comment>
    reason: get articles's comments from comment service
    solution: GRPC
```

Now the new features needed to be implemented can be specified :

*   JWT

For this feature, we can use simple authorization in a gateway where the user registers( signs up ) and receives a JWT which can later be used.

