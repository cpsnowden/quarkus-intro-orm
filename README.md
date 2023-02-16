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

## Vintage-Store

- Uses Panache