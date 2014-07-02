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

@Mojo(name="claudio",defaultPhase = LifecyclePhase.PACKAGE, requiresProject = true, threadSafe = true,
requiresDependencyResolution = ResolutionScope.RUNTIME )
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
