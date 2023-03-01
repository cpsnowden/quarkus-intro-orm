## Project Structure

### Notes
- Boostrap 3 quarkus applications
  - MySQL - Using Pojo
  - MariaDB - Using Entity JPA Entity
  - Postgres - Using Panache Entity
- Enrich data model with relationships
- Create a set of resources for REST apis
- Add Qute templates for HTML
- H2 for Test
- Postgres using Test Containers
- Postgres using Docker for Production

### Artist Module

- Uses Plain Old Java Object
- Access MSQL DB via JDBC/DataSource
- Data Source is configured in
- For tests, setup SQL can be configured via 
- quarkus.datasource.jdbc.new-connection-sql
- Quarkus configures the data source automatically

## Customer Module

- Uses JPA and Entity Manager

## Vintage-Store Module

- Uses Panache
- Build on-top of JPA
- Simplifies JPA entities and repositories
  - Public attributes
  - Optional getter/setters
  - Generated ID
  - Inherit all crud

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

## Bootstrapping Application

1. MSQL
2. MariaDB
3. PostgresSQL

```bash
quarkus create app --package-name=org.cps.quarkus.jdbc --extension=jdbc-mysql,quarkus-agroal,resteasy org.cps.course.quarkus.orm:artist
quarkus create app --package-name=org.cps.quarkus.jdbc --extension=jdbc-mariadb,hibernate-orm,resteasy org.cps.course.quarkus.orm:customer
quarkus create app --package-name=org.cps.quarkus.jdbc --extension=jdbc-postgresql,hibernate-orm-panache,resteasy org.cps.course.quarkus.orm:vintage-store
```

## Modelling Entities

### Pojos

Consider a simple Pojo [Artist](./artist/src/main/java/org/cps/quarkus/jdbc/Artist.java). In order to map this to a relational table via JDBC takes tedious 
code as shown in [ArtistRepository](./artist/src/test/java/org/cps/quarkus/jdbc/ArtistRepository.java).

Panache ORM can take an existing pojo with mappings defined via an [orm.xml](./vintage-store/src/main/resources/META-INF/orm.xml) (if the pojo itself cannot be edited)
and automate the tedious code required to manually map a JDBC response.

### JPA Entities

Panache ORM can use JPA Entities e.g. [Customer](./customer/src/main/java/org/cps/quarkus/jdbc/Customer.java) with queries made via an entity manager e.g.
[CustomerRepository](./customer/src/test/java/org/cps/quarkus/jdbc/CustomerRepository.java) or via a PanacheRepository.

```java 
@Entity
public class Customer {

    @Id
    @GeneratedValue
    private Long id;
    private String firstName;
    ...
}
```

If the entity is in a different jar to the Panache repository, then you need to ensure a `panache-archive.marker` file exists in the META-INF

### Panache Entities

Panache entities extend the `PanacheEntity` abstract class which:
- Automatically provides the `id` field
- Auto-generates accessors for all public fields
- Auto-generates static query and persistence methods e.g. `findById` via an ActiveRecord pattern

## Querying Entities

### JPA Entity Manager

Panache is built on-top of Hibernate / JPA and therefore the JPA `EntityManager` can be used for
query and persistence. See [CustomerRepository](./customer/src/test/java/org/cps/quarkus/jdbc/CustomerRepository.java).

### Panache Active Record

Once an entity extends `PanacheEntity` the active record pattern can be used for query/persistence. Using
[Publisher](./vintage-store/src/main/java/org/cps/quarkus/jdbc/model/Publisher.java) as an example the following
two static methods exist: `Publisher.persist(...)` and `Publisher.findById(...)`.

### Panache Repository

If you want to use the repository pattern an separate the persistence layer from your domain objects, a 
repository can be generated by extending `PanacheRepository<DomainClass>` which gives you access to the 
same methods as the active record pattern. Note that the given `DomainClass` can either be a Pojo (with an XML mapping 
see [CustomerRepository](./vintage-store/src/main/java/org/cps/quarkus/jdbc/repository/ArtistRepository.java)) or a 
JPA Entity (see [CustomerRepository](./vintage-store/src/main/java/org/cps/quarkus/jdbc/repository/CustomerRepository.java).

## Mapping Entities

Standard JPA annotations are supported e.g.
- `@Column(name = "music_company")`: Control the name, nullability, max length etc of a column
- `@Table`: Control the name of the table for an entity
- `@Enumerated(EnumType.STRING)`: Control how enumerations are persisted (ordinal vs name)

### Generating DDL Generation

You can automatically generate a DDL script by including:

```properties
quarkus.hibernate-orm.scripts.generation=create
quarkus.hibernate-orm.scripts.generation.create-target=create.sql
```

in your `application.properties`. This will create a SQL script containing all your entity table definitions
including primary key and foreign key constraints.

### Mapping Inheritance

Class hierarchies can be mapped to table structures via different strategies controlled by the:

```
@Inheritance(InheritanceType.SINGLE_TABLE|JOINED|TABLE_PER_CLASS)
```

annotation (`@MappedSuperclass` also exists which enabled inheritance in the classes but does not appear in the entity model).

#### Single table per class hierarchy

All entities in the class hierarchy are stored in a single table with differentiation done via a
`@DiscriminatorColumn`

- Pros: 
  - Polymorphic query performance is good as only one table needs to be accessed
- Cons: 
  - NOT NULL constraints cannot be used on subclass attributes
  - Sparse tables
  
#### Join subclass strategy

Super class is an entity and has its own table. Each concrete class also has its own table with its specific columns.

- Pros:
  - Strong data consistency as constraints can be enforced
  - Can model relationships between the parent class and other entities
- Cons:
  - Retrieving entities requires joins between tables, particularly when querying the base entity as it requires a join with every child

#### Table per concrete entity class

Super class is an entity (but does not have a table). All concrete classes are in their own tables. 

- Pros:
  - Strong data consistency
- Cons:
  - Performance impact of union for polymorphic queries

### Mapping Relationships

You can model relationships using the `OneToMany`, `ManyToOne` etc annotations and use the `@JoinColumn` to 
define the join itself. 

For bi-directional relationships, use the `mappedBy` attribute to define which field owns the mapping.

For dependent objects, you can also add `cascade` operations, e.g. to ensure the dependency is deleted when the parent is deleted.

## Queries

Beyond the autogenerated queries, Panache supports JPQL, a simplified SQL that runs against objects and selects attributes (rather than tables and columns)

```
SELECT c
FROM Customer c
WHERE c.firstName = 'Dan'
ORDER BY c.lastName DESC
```

JPQL can be run against the entity manager, a panache entity or a panache repository

```java
public static Optional<Publisher> findByName(String name) {
    return Publisher.find("name", name).firstResultOptional();
}
```