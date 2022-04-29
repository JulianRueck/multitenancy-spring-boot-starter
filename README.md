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
tenantList:
  - id: tenantId1
    dataSource:
      driverClassName: org.postgresql.Driver
      url: jdbc:postgresql:db1
      username: postgres
      password: password
  - id: tenantId2
    dataSource:
      driverClassName: org.postgresql.Driver
      url: jdbc:postgresql:db2
      username: postgres
      password: password
```
