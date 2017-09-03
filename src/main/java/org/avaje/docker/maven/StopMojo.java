package org.avaje.docker.maven;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.avaje.docker.commands.postgres.PostgresCommands;
import org.avaje.docker.commands.postgres.PostgresConfig;

/**
 * Stop the containers.
 */
@Mojo(name = "stop", defaultPhase = LifecyclePhase.PREPARE_PACKAGE)
public class StopMojo extends BaseContainerMojo {


  /**
   * Stop the container(s).
   * <p>
   * By default it will stop and remove the containers - a mode of 'stop' or 'none' can be used to either only stop
   * the container(s) or do nothing respectively.
   * </p>
   */
  public void execute() throws MojoExecutionException {

    PostgresConfig config = postgresBaseConfig();
    PostgresCommands pg = new PostgresCommands(config);

    getLog().info("stopping " + dbContainer + " mode:" + stopMode);

    if ("stop".equalsIgnoreCase(stopMode)) {
      getLog().info("stop container (no remove)");
      pg.stopRemove();

    } else if ("none".equalsIgnoreCase(stopMode)) {
      getLog().info("leaving container running");

    } else {
      getLog().info("stop with remove");
      pg.stop();
    }
  }

}