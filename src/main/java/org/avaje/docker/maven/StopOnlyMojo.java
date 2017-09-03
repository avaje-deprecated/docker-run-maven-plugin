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
@Mojo(name = "stopOnly", defaultPhase = LifecyclePhase.PREPARE_PACKAGE)//, requiresDependencyResolution = ResolutionScope.COMPILE_PLUS_RUNTIME)
public class StopOnlyMojo extends BaseContainerMojo {

  public void execute() throws MojoExecutionException {

    PostgresConfig config =postgresBaseConfig();
    PostgresCommands pg = new PostgresCommands(config);

    getLog().info("stop container (no remove)");
    pg.stop();
  }

}