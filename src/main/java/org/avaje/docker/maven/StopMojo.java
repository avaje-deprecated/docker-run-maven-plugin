package org.avaje.docker.maven;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.avaje.docker.commands.DbCommands;
import org.avaje.docker.commands.DbConfig;
import org.avaje.docker.commands.DbConfigFactory;

import java.util.Properties;

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

    Properties properties = loadProperties();
    DbConfig dbConfig = dbConfig(properties);

    // could also start other containers like elasticsearch, redis etc
    if (dbConfig.hasPlatform()) {

      DbCommands db = DbConfigFactory.createCommands(dbConfig);
      getLog().info(db.getStopDescription());
      db.stop();
    }
  }

}