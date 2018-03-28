# Spring Boot Liquibase Template

## Install SQL Server Docker Container

```bash
# host: localhost, port: 1433, user: sa, password Password!1, database: master
docker run -d --name sqlserver \
    -e ACCEPT_EULA=Y \
    -e MSSQL_PID=Developer \
    -e SA_PASSWORD='Password!1' \
    -p 1433:1433 \
    microsoft/mssql-server-linux

docker exec -it sqlserver /opt/mssql-tools/bin/sqlcmd -S localhost -U sa -P 'Password!1' -d master
CREATE DATABASE people;
go
USE people;
go
CREATE LOGIN family WITH PASSWORD = 'Password!1';
go
CREATE USER family FOR LOGIN family;
go
EXEC sp_addrolemember 'db_owner', 'family';
go

# host: localhost, port: 1433, user: family, password: Password!1, database: people
docker exec -it sqlserver /opt/mssql-tools/bin/sqlcmd -S localhost -U family -P 'Password!1' -d people
CREATE SCHEMA family;
go
CREATE SCHEMA liquibase;
go
CREATE TABLE family.person(id INT NOT NULL IDENTITY, first_name NVARCHAR(100) NOT NULL, last_name NVARCHAR(100) NOT NULL, PRIMARY KEY (id));
go
INSERT INTO family.person(first_name, last_name) VALUES ('Volodymyr', 'Prokopyuk');
go
SELECT * FROM family.person;
go
```

Clean database:
```sql
DROP TABLE liquibase.DATABASECHANGELOG;
DROP TABLE liquibase.DATABASECHANGELOGLOCK;
DROP TABLE family.person;
```
