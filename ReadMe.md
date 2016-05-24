PAX-JDBC and Camel mybatis Blueprint (OSGi) on FUSE ESB
=======================================================

To build this project use

    mvn clean install

## Steps to deploy PAX JDBC

Create a feature profile in `fabric` with camel-feature and add the following features repo and features,

### Feature Repositories

* mvn:org.ops4j.pax.jdbc/pax-jdbc-features/0.8.0/xml/features
* mvn:org.workspace7.fuse/paxjdbc.demo/1.0.0/xml/features

### Features

* transaction
* jndi
* pax-jdbc-mariadb
* pax-jdbc-config
* pax-jdbc-pool-dbcp2 ( or any other connection pooling refer to [PAX-JDBC](https://ops4j1.jira.com/wiki/display/PAXJDBC/Documentation?src=contextnavchildmode) for more info )
* paxjdbc.demo.mybatis.mappers
* paxjdbc.demo.camel


** WIP add fabric profile comamnds **


## Testing Application
**WIP**

## PAX-JDBC Configuration and Referencing

The [etc](./etc) folder has mariadb specific PAX-JDBC sample configuration, which can be dropped into the $KARAF_HOME/etc
to create a sample data source, the sample data source create could be looked up in OSGi using any of the following methods,

* via OSGi refrence in blueprint.xml as `<refrence id="myDS" interface="javax.sql.DataSource" filter="(dataSource=demodbDS)"/>`

        (OR)

*  in mybatis SqlMapConfig.xml or any other location as JNDI datasource as
    ```
      <environments default="development2">
            <environment id="development2">
                    <transactionManager type="JDBC"/>
                    <dataSource type="JNDI">
                            <!-- PAX-JDBC registers the JNDI name with osgi.jndi.service.name as dataSourceName using Aries JNDI -->
                            <property name="data_source" value="osgi:service/javax.sql.DataSource/(osgi.jndi.service.name=demodbDS)"/>
                    </dataSource>
            </environment>
       </environments>
    ```

## SQL For testing
The [sql](./sql) has the mariadb specific load scripts, which can be used to do the test

References
=========

[Camel](http://camel.apache.org/)

[PAX-JDBC](https://ops4j1.jira.com/wiki/display/PAXJDBC/Documentation?src=contextnavchildmode)


