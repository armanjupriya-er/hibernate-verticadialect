
## Vertica Dialect

VerticaDialect has been developed and tested using the following software and versions:

Vertica Server 24.x

Hibernate 6.4.X

JDK 17 or more

## About The Project
This is an example of how you may give instructions on setting up your project locally.
To get a local copy up and running follow these simple example steps.

### Prerequisites

Follow instruction provided in below reference to setup environment.
* Maven
	https://maven.apache.org/install.html
	https://mvnrepository.com/artifact/com.vertica.jdbc/vertica-jdbc/24.1.0-0
	https://mvnrepository.com/artifact/org.hibernate.orm/hibernate-core/6.4.1.Final
* Java 17
	https://openjdk.org/projects/jdk/17/

### Installation

Below is an example of how you can implement verticaDialect in your app.

1.clone the repo
     sh
   git clone https://github.com/vertica/hibernate-verticadialect.git
2.Build the Vertica Dialect
      sh
    mvn clean install
3.copy jar file generated in target folder and place it in your application classpath.
4.restart the application 


## supported datatypes
Below is a table for what Datatypes the current version of vertica and hibernate supports

|                Datatypes                          | Supported   |  Type Datatype returns
| ------------------------------------------------- | ----------- |-------------------------------------------------
| BINARY                                            | Yes         | this datatype returns bytea.
| VARBINARY                                         | Yes         | this datatype returns bytea.
| LONG VARBINARY                                    | Yes         | this datatype returns bytea.
| BOOLEAN                                           | Yes         | this datatype returns boolean.
| CHAR                                              | Yes         | this datatype returns char(1).
| VARCHAR                                           | Yes         | this datatype returns varchar255.
| LONG VARCHAR                                      | Yes         | this datatype returns LongVarchar.
| Date                                              | Yes         | this datatype returns date.
| Time                                              | Yes         | this datatype returns time.
| TIME WITH TIMEZONE                                | Yes         | this datatype is stored as time,but mapped to utc.
| TIMESTAMP                                         | Yes         | this datatype is returns timestamp.
| TIMESTAMP WITH TIMEZONE                           | Yes         | this datatype is returns TimestampTz.
| INTERVAL                                          | Yes         | this datatype returns varchar.
| INTERVAL DAY TO SECOND                            | Yes         | this datatype returns varchar.
| INTERVAL YEAR TO MONTH                            | Yes         | this datatype returns varchar.
| DOUBLE PRECISION                                  | Yes         | this datatype returns float8.
| FLOAT                                             | Yes         | this datatype return float.
| FLOAT8                                            | Yes         | this datatype returns double.
| INTEGER                                           | Yes         | this datatype returns int.
| BIGINT                                            | Yes         | this datatype returns int.
| INT8                                              | No          |
| SMALLINT                                          | Yes         | this datatype returns int.
| TINYINT                                           | Yes         | this datatype returns smallint.
| DECIMAL                                           | Yes         | this datatype returns numeric.
| NUMERIC                                           | Yes         | this datatype returns numeric.
| NUMBER                                            | No          |
| GEOMETRY                                          | Yes         |
| GEOGRAPHY                                         | Yes         |
| UUID                                              | Yes         | this datatype returns uuid.

## sequence created in the vertica database
you can refer to VerticaSequenceSupport method in the verticaDialect.


## note
*the dialect supports all the constraints and db objects,refer this dialect for the latest vertica 24.1 and hibernate 6.4.1. 
