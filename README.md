# Introduction
...
# Installation
### Add the following dependency to the pom.xml file
```
<dependency>
    <groupId>nl.julianrueck</groupId>
    <artifactId>spring-boot-starter-multitenancy</artifactId>
    <version>1.0.0</version>
</dependency>
```
# Settings
### application.yml
```
spring:
   jpa:
    database: postgresql

tenant:
  dataSourceMap:
    tenantId1: "driver;org.postgresql.Driver, url;jdbc:postgresql:db1, username;postgres, password;password"
    tenantId2: "driver;org.postgresql.Driver, url;jdbc:postgresql:db2, username;postgres, password;password"
```
