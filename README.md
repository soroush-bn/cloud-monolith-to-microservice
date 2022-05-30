# cloud-monolith-to-microservice

blog application which provides api to sign up and login for users.
users can create articles, add comments to articles. 

# Monolith to micro-service 

### list of micro services which are going to be implemented

1- user management (jwt)


2- article


3- user


4- comment

### reasons why each micro service is chosen 

article: as articles are more likely to be developed in future -> scalibility requirement


comment: as there might be a lot of comment being posted by users and there we be a lot of load on this service


user: for security requirements 

### write the files which are going to be used by each micro service

article: ArticleHandler.kt, ArticleRepository.kt, Article.kt, TagHandler.kt, TagRepository.kt


comment: CommentRepository.kt, Comment.kt


user: UserHandler.kt, ProfileHandler.kt, UserRepository.kt, User.kt


### diagram of entities and their relations and dependencies to other services as the data bases are apart
![photo1650656322](https://user-images.githubusercontent.com/45733433/164784145-7d1a2c65-8c8d-4020-ad45-0418f30109b1.jpeg)

### diagram of services and all the internal and external calls
![photo1650656350](https://user-images.githubusercontent.com/45733433/164784122-bf8718a1-2adf-41e6-a08f-e999385235fb.jpeg)


### specify all internal calls like this :

    userService --> ArticleService:
	    where: io/realworld/service/UserService.kt, class: UserService
	    happened: io/realworld/service/ArticleService.kt, class: ArticleService
	    code: getUsersArticles(userId):List<Article> 
	    reason: get users article from article service
	    solution: GRPC 

    userService --> CommentService:
      	 where: io/realworld/service/UserService.kt, class: UserService
	happened: io/realworld/service/CommentService.kt, class: CommentService
        code: val claims = Jwts.parser().setSigningKey(jwtSecret)
        reason: check validity of user token befor commenting
        solution: using jwt 

    ArticleService --> CommentService:
      	 where: io/realworld/service/ArticleService.kt, class: ArticleService
	happened: io/realworld/service/CommentService.kt, class: CommentService
        code: getCommentsOf(articleId):List<Comment>
        reason: get Comments of specific article via comment service
        solution: using GPRC
	
	
### list of frameworks used
-kotlin

-springframework

-jbcrypt

-jsonwebtoken

-h2

-feign-gson

-slugify

-junit


### data base schema and each entity info


![photo1650650248](https://user-images.githubusercontent.com/45733433/164769225-b8bf0f4a-440b-41fd-8242-05dc672b82f2.jpeg)

### team plan for developing each service

#### UserService -> @sadeghAbedi

#### CommentService -> @soroush.baghernezhad

#### ArticleService -> @Alireza.dehghan

### database configuration

run mysql server

run below commands to create needed databases :

    create database db_comment;
    create database db_article;
    create database db_user;

run below commands to create user springuser with password ThePassword :

    create user 'springuser'@'%' identified by 'ThePassword';

run below commands to give all privileges to newly created user:

    grant all on db_comment.* to 'springuser'@'%';
    grant all on db_article.* to 'springuser'@'%';
    grant all on db_user.* to 'springuser'@'%';

to show databases :

    show databases;

to select a database:

    use databasName


