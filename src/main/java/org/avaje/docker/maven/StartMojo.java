package org.avaje.docker.maven;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.ResolutionScope;

/**
 *
 */
@Mojo(name = "start", defaultPhase = LifecyclePhase.PROCESS_TEST_CLASSES)//, requiresDependencyResolution = ResolutionScope.COMPILE_PLUS_RUNTIME)
public class StartMojo extends BaseContainerStartMojo {

  public void execute() throws MojoExecutionException {

    startContainers(dbStartMode);
  }


}