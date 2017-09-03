package org.avaje.docker.maven;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.avaje.docker.commands.postgres.PostgresCommands;
import org.avaje.docker.commands.postgres.PostgresConfig;

/**
 * Only stop (not remove) the container(s).
 *
 * Potentially not useful.
 */
@Mojo(name = "stopOnly", defaultPhase = LifecyclePhase.PREPARE_PACKAGE)
public class StopOnlyMojo extends BaseContainerMojo {

  public void execute() throws MojoExecutionException {

    PostgresConfig config =postgresBaseConfig();
    PostgresCommands pg = new PostgresCommands(config);

    getLog().info("stop container (no remove)");
    pg.stop();
  }

}