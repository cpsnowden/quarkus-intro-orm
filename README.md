## Modules

### Artist

- Uses Plain Old Java Object
- Access MSQL DB via JDBC/DataSource

- Data Source is configured in
- For tests, setup SQL can be configured via 
- quarkus.datasource.jdbc.new-connection-sql
- Quarkus configures the data source automatically

## Customer

- Uses JPA
  - Use Entity Manager

## Vintage-Store

- Uses Panache
- Build on-top of JPA
- Simplifies JPA entities and repositories
  - Public attributes
  - Optional getter/setters
  - Generated ID
  - Inherit all crud

Quarkus Course on Accessing Databases

## Structure
- Understand ORM / JPA / Panache
- Check development environment
- Boostrap 3 Quarkus applications
- Several databases
- Develop POJOs and entities
- Map and query entities
- Visualize data with Qute
- Expose REST endpoints

## What you will build in this course?
- Boostrap 3 quarkus applications
  - MySQL - Using Pojo
  - MariaDB - Using Entity JPA Entity
  - Postgress - Using Panache Entity
- Entrich data model with relationships
- Create a set of resources for REST apis
- Add Qute templates for HTML

- H2 for Test
- Postgress using Test Containers
- Postgress using Docker for Production

## What is an ORM

- Map between Java Objects and Tables in DBS
- Not new
  - Toplink 1996
  - Hibernate 2001
  - JPA - 2009
  - Panache ORM 2019

In testing can use docker to run databases
- Quarkus uses test containers in dev services architecture
  - Quarkus automatically uses test containers under the hood

## Boostrapping Application

1. MSQL
2. MariaDB
3. PostgresSQL

```bash
quarkus create app --package-name=org.cps.quarkus.jdbc --extension=jdbc-mysql,quarkus-agroal,resteasy org.cps.course.quarkus.orm:artist
quarkus create app --package-name=org.cps.quarkus.jdbc --extension=jdbc-mariadb,hibernate-orm,resteasy org.cps.course.quarkus.orm:customer
quarkus create app --package-name=org.cps.quarkus.jdbc --extension=jdbc-postgresql,hibernate-orm-panache,resteasy org.cps.course.quarkus.orm:vintage-store
```


## Inheritance
- Single table per class hierarchy
- Join subclass strategy
- Table per concrete entity class

`@Inheritance(InheritanceType.SINGLE_TABLE|JOINED|TABLE_PER_CLASS)`

