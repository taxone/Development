package it.claudio.mojos;

import org.apache.maven.artifact.DependencyResolutionRequiredException;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Component;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.ResolutionScope;
import org.apache.maven.project.MavenProject;

/**
 * Per eseguire questo plugin specificare:
 * 
 * mvn claudio01:claudio
 * 
 * Mettere nel settings.xml:
 * 
 *  <pluginGroups>
  		<pluginGroup>it.claudio</pluginGroup>
	</pluginGroups>
 */
@Mojo(name="claudio",
	requiresProject = true, 
	threadSafe = true,
	/*Questo valore fa sì che dentro al project ci iano effettivamente le dipendenze di runtime popolato con la lista di tutti i jar*/
	/**MOLTO IMPORTANTE**/
	requiresDependencyResolution = ResolutionScope.RUNTIME 
)
public class MyMojo01 extends AbstractMojo{

	@Component
	private MavenProject project;
	
	@Override
	public void execute() throws MojoExecutionException, MojoFailureException {
		
		getLog().info("Ciao da MyMojo01...");
		
		int artifactSize = 0;
		try {
			artifactSize = project.getRuntimeClasspathElements().size();
		} catch (DependencyResolutionRequiredException e) {
			throw new MojoExecutionException("errore claudio",e);
		}
		
		getLog().info("Artifcat size: "+artifactSize);
	}

}
