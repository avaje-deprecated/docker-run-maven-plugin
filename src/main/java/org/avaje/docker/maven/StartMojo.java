package org.avaje.docker.maven;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;

/**
 * Start all the container(s).
 */
@Mojo(name = "start", defaultPhase = LifecyclePhase.PROCESS_TEST_CLASSES)
public class StartMojo extends BaseContainerStartMojo {

  public void execute() throws MojoExecutionException {

    startContainers(dbStartMode);
  }


}