PAX-JDBC and Camel mybatis Blueprint (OSGi)
=========================================

To build this project use

    mvn clean install

## Steps to deploy PAX JDBC

Create a feature profile in `fabric` with camel-feature and add the following features repo and features,

### Feature Repositories

* mvn:org.ops4j.pax.jdbc/pax-jdbc-features/0.8.0/xml/features
* mvn:org.workspace7.fuse/paxjdbc.demo/1.0.0

### Features

* transaction
* jndi
* pax-jdbc-mariadb
* pax-jdbc-config
* pax-jdbc-pool-dbcp2
* paxjdbc.demo.mybatis.provider
* paxjdbc.demo.mybatis.mappers
* paxjdbc.demo.camel


** WIP add fabric profile comamnds **


## Testing Application


References
=========

[Camel](http://camel.apache.org/)

[PAX-JDBC](https://ops4j1.jira.com/wiki/display/PAXJDBC/Documentation?src=contextnavchildmode)


