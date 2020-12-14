## Java SOAP Web Service w/ Spring Boot & MySQL


### pre-reqs
install mysql:

`docker pull mysql/mysql-server:latest`

start mysql:

`docker run --name=mysql-tshirts -p 3306:3306 -d mysql/mysql-server:latest`

get root password:

`docker logs mysql-tshirts 2>$1 | grep GENERATED`

enter mysql container:

`docker exec -it mysql-tshirts mysql -uroot -p`

create db and user:

`create database tshirts;`

`create user 'user' identified by 'pass';`

`GRANT ALL PRIVILEGES ON * . * TO 'user'@'localhost';`


### install

`mvn clean install`

### start web service
`mvn spring-boot:run`
