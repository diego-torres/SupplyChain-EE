# SupplyChain-EE
Spring Boot implementation of Supply chain software example
## Spring Boot Hibernate Configuration
### 1. Add Hibernate dependencies to the pom.xml
```xml
<dependency>
	<groupId>org.springframework</groupId>
	<artifactId>spring-orm</artifactId>
</dependency>
<dependency>
	<groupId>org.hibernate</groupId>
	<artifactId>hibernate-entitymanager</artifactId>
</dependency>
<dependency>
	<groupId>mysql</groupId>
	<artifactId>mysql-connector-java</artifactId>
</dependency>
```
### 2. Create PersistenceConfig class with HibernateJpaSessionFactoryBean and a DataSource bean.
```java
...
public class PersistenceConfig {
...
  @Bean
	public HibernateJpaSessionFactoryBean sessionFactory() {
		return new HibernateJpaSessionFactoryBean();
	}
...
  @Bean
  public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(dbDriver);
		dataSource.setUrl(dbUrl);
		dataSource.setUsername(dbUser);
		dataSource.setPassword(dbPwd);
		return dataSource;
	}
```
Note that the data source properties are being loaded from the application.yml file.
### 3. Add Hibernate properties to application.yml
```YAML
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/nowgroup_scs
    username: root
    password: root
    driver-class-name: com.mysql.jdbc.Driver
  jpa:
    database-platform: org.hibernate.dialect.MySQL5Dialect
    show-sql: true
    properties:
      hibernate:
        current_session_context_class: org.springframework.orm.hibernate4.SpringSessionContext
        hbm2ddl.auto: update
```
Take special care about ```spirng.jpa.properties.hibernate.current_session_context_class```. Check that it is a ```hibernate4```. That is the reason why in ```com.nowgroup.scsee.repository.HibernateReadOnlyRepository``` I am importing ```org.springframework.orm.hibernate4.support.HibernateDaoSupport```, so I can tie this compatibility together.
