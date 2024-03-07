
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
* Java 11
	https://www.oracle.com/in/java/technologies/javase/jdk11-archive-downloads.html

### Installation

_Below is an example of how you can instruct your audience on installing and setting up your app. This template doesn't rely on any external dependencies or services._

1. Clone the repo
   ```sh
    git clone https://github.com/vertica/vertica-geoserver-datasource.git
   ```
2. Build Vertica connector
   ```sh
    mvn clean install
   ```
3. Download the Vertica JDBC driver from Vertica website. (Ref: https://www.vertica.com/download/vertica/client-drivers/)
4. Copy jar file generated in target folder and JDBC drivers to '<GEOSERVER_ROOT>/webapps/geoserver/WEB-INF/lib'.
5. Restart GeoServer.



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
