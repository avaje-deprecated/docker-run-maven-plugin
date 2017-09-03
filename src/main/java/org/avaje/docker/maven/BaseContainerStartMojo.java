package org.avaje.docker.maven;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.Parameter;
import org.avaje.docker.commands.postgres.PostgresCommands;
import org.avaje.docker.commands.postgres.PostgresConfig;

/**
 *
 */
abstract class BaseContainerStartMojo extends BaseContainerMojo {


  @Parameter(name = "dbPort")
  String dbPort;

  @Parameter(name = "dbName")
  String dbName;

  @Parameter(name = "dbUser")
  String dbUser;

  @Parameter(name = "dbPassword")
  String dbPassword;

  @Parameter(name = "dbExtensions")
  String dbExtensions;

  PostgresConfig postgresConfig() {

    PostgresConfig config = new PostgresConfig();
    if (defined(dbContainer)) {
      config.withName(dbContainer);
    }
    if (defined(dbName)) {
      config.withDbName(dbName);
    }
    if (defined(dbUser)) {
      config.withDbUser(dbUser);
    }
    if (defined(dbPassword)) {
      config.withDbName(dbPassword);
    }
    if (defined(dbExtensions)) {
      config.withDbExtensions(dbExtensions);
    }
    if (defined(dbPort)) {
      config.withHostPort(dbPort);
    }

    return config;
  }

  public void startContainers(String startMode) throws MojoExecutionException {

    // todo check dbType for postgres, mysql etc
    // could also start other containers like elasticsearch, redis etc

    PostgresConfig config = postgresConfig();
    PostgresCommands pg = new PostgresCommands(config);

    getLog().info("starting " + config.name + " port:" + config.hostPort + " extensions:" + config.dbExtensions + " startMode:" + startMode);

    pg.start(startMode);

  }

}