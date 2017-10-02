package org.avaje.docker.maven;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.avaje.docker.container.ContainerFactory;

/**
 * Start all the container(s).
 */
@Mojo(name = "start", defaultPhase = LifecyclePhase.PROCESS_TEST_CLASSES)
public class StartMojo extends BaseContainerMojo {

  public void execute() throws MojoExecutionException {

    ContainerFactory factory = containerFactory();
    factory.startContainers(msg -> getLog().info(msg));
  }


}