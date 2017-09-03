# docker run plugin

This starts and stops docker containers as part of the maven lifecycle.

For example:
- Start Postgres (at phase process-test-classes)
- Stop Postgres (at phase prepare-package)

For Postgres container it handles the drop and create of the database, user and
extensions including the waiting checks for the postgres container to be ready
for use.


## Example

Start Postgres container (with the following defaults):
- Port defaults to 6432
- DB name is 'test_db'
- DB user is 'test_user'
- DB password is 'test'
- Docker container name is 'ut_postgres'
- Postgres docker image is 'postgres:9.6.4'

```xml

  <plugin>
    <groupId>org.avaje.docker</groupId>
    <artifactId>docker-run-maven-plugin</artifactId>
    <version>0.9.1</version>
    <configuration>
      <!--<dbStartMode>dropCreate</dbStartMode>-->
      <!--<dbContainer>ex_pg_test</dbContainer>-->
      <!--<dbPort>9765</dbPort>-->
      <!--<dbExtensions>hstore</dbExtensions>-->
    </configuration>
    <executions>
      <execution>
        <id>start</id>
        <phase>process-test-classes</phase>
        <goals>
          <goal>start</goal>
        </goals>
      </execution>
      <execution>
        <id>stop</id>
        <phase>prepare-package</phase>
        <goals>
          <goal>stop</goal>
        </goals>
      </execution>
    </executions>
  </plugin>

```