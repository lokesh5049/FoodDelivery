                                  Food Delivery System
                                  +++++++++++++++++++++
                                  
                                  
Requierment.
------------
1)wildfly 12 server.

2)Mysql 5+
                                  
3)Java 1.8+

4)Maven

Build Application
------------------

mvn clean install -DskipTests
or
mvn clean install


Installation Guide
--------------------


:Server Configuration:
Datasource Jndi name:'java:jboss/datasources/Customer' .

Mail Jndi name:'java:jboss/mail/Mail'.

Database schema :'customer'



Database Tables Guide:
----------------------
Create Tables if Hibernate not created in database.
1)CREATE TABLE `users` (
  `firstname` varchar(255) NOT NULL,
  `lastname` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `phone` bigint(45) NOT NULL,
  `password` varchar(255) NOT NULL,
  `enabled` tinyint(1) NOT NULL DEFAULT '1',
  `address` varchar(255) DEFAULT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`email`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) 

2)CREATE TABLE `roles` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL,
  `user_role` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `username_idx` (`username`),
  CONSTRAINT `username` FOREIGN KEY (`username`) REFERENCES `users` (`email`)
) 

3)CREATE TABLE `product` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `price` double NOT NULL,
  `resturent_name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) 
4)CREATE TABLE `product_user` (
  `product_id` int(11) NOT NULL,
  `user_id` varchar(255) NOT NULL,
  PRIMARY KEY (`product_id`,`user_id`),
  KEY `user_id_idx` (`user_id`),
  CONSTRAINT `product_id` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`),
  CONSTRAINT `user_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`email`)
) 

5)CREATE TABLE `orderhistory` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `Date` datetime NOT NULL,
  `location` varchar(45) NOT NULL,
  `food_id` int(11) NOT NULL,
  `number` int(11) NOT NULL,
  `orderby_id` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `food_id_idx` (`food_id`),
  CONSTRAINT `food_id` FOREIGN KEY (`food_id`) REFERENCES `product` (`id`)
) 



Description about Project
---------------------------
This Project based on java technologies ,framework of java like Spring 5,Hibernate 5+ and Javascript f/w like Extjs,Jquery.Basically this application help in adding food ,ordering food .There are Three roles in application 
1)Admin:who can see how may user Registered in application and what is order don by user.
2)Provider:As a food provider who can add food , Restaurant  from where food will be order.
3)User:user can see his order and can order food which is added by provider
Registered can be done as admin,provider and as user.after login it will redirect to page based on login role.

Java Doc(APIS) is Available generate through maven 
---------------------------------------------------
inside /fooddelivery/src/main/webapp/doc folder.





                                 