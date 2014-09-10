package it.taxone.plugins.classpath;

import java.util.jar.Manifest;

import org.apache.maven.archiver.ManifestConfiguration;
import org.apache.maven.archiver.MavenArchiver;
import org.apache.maven.artifact.DependencyResolutionRequiredException;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Component;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.plugins.annotations.ResolutionScope;
import org.apache.maven.project.MavenProject;
import org.codehaus.plexus.archiver.jar.ManifestException;

/**
 * Set a property which contains the runtime dependencies (the list of jars) of the artifact.
 * This property can be referred in the POM.xml.
 * 
 * @author Claudio Tasso
 *
 */
@Mojo(name="setClassPath",
requiresProject = true, 
threadSafe = true,
requiresDependencyResolution = ResolutionScope.RUNTIME 
)
public class ClassPathMojo extends AbstractMojo {

	@Component
	private MavenProject project;
	
	/**
	 * The name of the property which contains the runtime classpath and that
	 * is available in the project POM. 
	 */
	@Parameter(
		property="taxone.classpath.propertyName",
		defaultValue="taxone.classpath.propertyName"
	)
	private String classpathPropertyName;
	
	/**
	 * A text that will be prefixed to all your Class-Path entries. The default value is "".
	 */
	@Parameter(
			property="taxone.classpath.prefix"
	)
	private String classpathPrefix;
	
	public void execute() throws MojoExecutionException, MojoFailureException {
		getLog().info("ClassPath Plugin starting...");
		
		getLog().debug("ClassPath variable name is "+classpathPropertyName);
		
		try {
			MavenArchiver mavenArchiver = new MavenArchiver();
			
			ManifestConfiguration config = new ManifestConfiguration();
			config.setAddClasspath(true);
			config.setClasspathPrefix(classpathPrefix);
			Manifest manifest = mavenArchiver.getManifest(project, config);
			
			
			String classPath = manifest.getMainAttributes().getValue("Class-Path");
			
			getLog().debug(String.format("Setting the classpath property %s to %s",classpathPropertyName,classPath));
			
			project.getProperties().put(classpathPropertyName, classPath);
			
		} catch (DependencyResolutionRequiredException e) {
			throw new MojoFailureException(e.getMessage());
		} catch (ManifestException e) {
			throw new MojoFailureException(e.getMessage());
		}
		
		getLog().info("ClassPath Plugin completed.");
	}

}
