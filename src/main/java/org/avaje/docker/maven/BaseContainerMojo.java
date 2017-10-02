package org.avaje.docker.maven;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.Parameter;
import org.avaje.docker.container.ContainerFactory;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

/**
 */
abstract class BaseContainerMojo extends AbstractMojo {

  /**
   * The directory holding the class files we want to transform.
   */
  @Parameter(property = "project.build.testOutputDirectory")
  String testOutputDir;

  public abstract void execute() throws MojoExecutionException;

  ContainerFactory containerFactory() {
    Properties properties = loadProperties();
    return new ContainerFactory(properties);
  }

  Properties loadProperties() {

    Properties properties = new Properties();

    File testOut = new File(testOutputDir);
    loadFile(properties, testOut);

    return properties;
  }

  private void loadFile(Properties properties, File dir) {

    File propsFile = new File(dir, "docker-run.properties");
    if (!propsFile.exists()) {
      getLog().info("Could not find docker-run.properties at " + propsFile.getAbsolutePath());

    } else {
      try {
        FileReader reader = new FileReader(propsFile);
        properties.load(reader);

        getLog().debug("loaded docker-run.properties: " + properties);

      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    }
  }

}