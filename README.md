# Applied TOMTOM Map API and CRUD

## Applying TOMTOM Map API

- Using api to pinpoint the latitude and longitude of a certain location
- Applying api to look for walking and driving itineraries respectively
- Offering to two alternatives of the driving itineraries
  1. eco
  2. normal
- Opening an api to my colleague who is in charge of communal map
- Delivering data which is selected by users
- the delivering data includes start point, destination distance, duration and way

## Connecting with municipal database

- Requiring hashcode in the request head for the sake of authentication.

![img10](https://github.com/niuniu268/azure_tomtom_h2/blob/master/img/Screenshot_azure_12.png)

## CRUD

- Applying H2 database: create a table 'favorites'
```create table favorites (id int not null auto_increment,start varchar(255),destination varchar(255),distance varchar(50),duration varchar(50),way varchar(50),primary key (id));```
- Using delivering data to set up a favorite list: url CURL -X GET ~/favorites
- Adding the delivering data: url curl -X GET ~/favorites/1
- Deleting a certain data: url curl -X DELETE ~/favorites/1

## Applying Azure spring app

- To check Api ports which this application exposes:

![img0](https://github.com/niuniu268/azure_tomtom_h2/blob/master/img/Screenshot_azure_8.png)


- To check a route between ostersund central station and malmö central station by driving way (/car): In the below 
picture, the result includes 4 options. The first option is a walking itinerary. The second option is a driving 
itinerary when driver choose an environment friendly way. The third option is a driving itinerary with a normal way. 
The fourth is municipal data from municipal database.

![img1](https://github.com/niuniu268/azure_tomtom_h2/blob/master/img/Screenshot_azure_3.png)

- To check a route between östersund central station and malmö central station by walking way (/pedestrian): In the 
below picture, the result includes 2 options. The first option is a walking itinerary. The fourth is municipal data from
 municipal database.

![img2](https://github.com/niuniu268/azure_tomtom_h2/blob/master/img/Screenshot_azure_6.png)

- To check a route between östersund central station and malmö opera by driving way. If the destination is not a train 
station, the result would not include data from the municipal database.

![img3](https://github.com/niuniu268/azure_tomtom_h2/blob/master/img/Screenshot_azure_5.png)

- To show the favorite list

![img4](https://github.com/niuniu268/azure_tomtom_h2/blob/master/img/Screenshot_azure_4.png)

## Using Azure JDBC database

- In order to establish azure mysql database, we apply another java package:
  https://github.com/niuniu268/SpringAzure_tomtom

- Setting up an azure mysql database

![img5](https://github.com/niuniu268/azure_tomtom_h2/blob/master/img/Screenshot_azure_10.png)

- Connection between Azure spring app and the azure mysql database

![img6](https://github.com/niuniu268/azure_tomtom_h2/blob/master/img/Screenshot_azure_9.png)

## Using Eureka, Openfeign and Spring Gateway to fulfill Spring Cloud service
  https://github.com/niuniu268/SpringCloud

## Using nacos and Spring Cloud Alibaba to fulfill Spring Cloud service
  https://github.com/niuniu268/nacos-gateway-sentinel