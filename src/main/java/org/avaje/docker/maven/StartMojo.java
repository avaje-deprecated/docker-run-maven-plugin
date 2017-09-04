package org.avaje.docker.maven;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.avaje.docker.commands.DbCommands;
import org.avaje.docker.commands.DbConfig;
import org.avaje.docker.commands.DbConfigFactory;

import java.util.Properties;

/**
 * Start all the container(s).
 */
@Mojo(name = "start", defaultPhase = LifecyclePhase.PROCESS_TEST_CLASSES)
public class StartMojo extends BaseContainerMojo {

  public void execute() throws MojoExecutionException {

    Properties properties = loadProperties();
    DbConfig dbConfig = dbConfig(properties);

    // could also start other containers like elasticsearch, redis etc

    if (dbConfig.hasPlatform()) {

      DbCommands db = DbConfigFactory.createCommands(dbConfig);
      getLog().info(db.getStartDescription());

      db.start();
    }

  }


}