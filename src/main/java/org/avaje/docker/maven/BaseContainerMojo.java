package org.avaje.docker.maven;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.Parameter;
import org.avaje.docker.commands.postgres.PostgresConfig;

/**
 */
abstract class BaseContainerMojo extends AbstractMojo {

  /**
   * Name of the database container.
   */
  @Parameter(name = "dbContainer")
  String dbContainer;

  /**
   * Database type (postgres, mysql etc).
   */
  @Parameter(name = "dbType")
  String dbType;

  /**
   * Database start mode (dropCreate, create or container [only]).
   */
  @Parameter(name = "dbStartMode")
  String dbStartMode;


  /**
   * Stop mode (remove, none or stop only).
   */
  @Parameter(name = "stopMode")
  String stopMode;


  public abstract void execute() throws MojoExecutionException;


  PostgresConfig postgresBaseConfig() {

    PostgresConfig config = new PostgresConfig();
    if (defined(dbContainer)) {
      config.withName(dbContainer);
    }
    return config;
  }

  boolean defined(String value) {
    return value != null && !value.trim().isEmpty();
  }
}