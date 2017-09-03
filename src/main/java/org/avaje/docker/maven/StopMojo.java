package org.avaje.docker.maven;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.avaje.docker.commands.postgres.PostgresCommands;
import org.avaje.docker.commands.postgres.PostgresConfig;

/**
 *
 */
@Mojo(name = "stopRemove", defaultPhase = LifecyclePhase.PREPARE_PACKAGE)//, requiresDependencyResolution = ResolutionScope.COMPILE_PLUS_RUNTIME)
public class StopMojo extends BaseContainerMojo {


  /**
   * Stop the container(s).
   * <p>
   * Stop with remove means the containers are removed.
   * </p>
   */
  public void execute() throws MojoExecutionException {

    PostgresConfig config =postgresBaseConfig();
    PostgresCommands pg = new PostgresCommands(config);


    getLog().info("stopping " + dbContainer + " mode:" + stopMode);

    if ("remove".equalsIgnoreCase(stopMode)) {
      getLog().info("stop with remove");
      pg.stopRemove();

    } else if ("none".equalsIgnoreCase(stopMode)) {
      getLog().info("leaving container running");

    } else {
      getLog().info("stop container (no remove)");
      pg.stop();
    }
  }

}