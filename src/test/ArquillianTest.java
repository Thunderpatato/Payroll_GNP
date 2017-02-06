package test;

import java.io.File;

import org.apache.log4j.Logger;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.OverProtocol;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ArchivePaths;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.exporter.ZipExporter;
import org.jboss.shrinkwrap.api.spec.WebArchive;

/**
 * the basic arquillian test
 */
public class ArquillianTest {

	Logger log = Logger.getLogger(ArquillianTest.class);
	
	@Deployment(name = "PayrollTest")
	@OverProtocol("Servlet 3.0") // Evita il timeout sui test lunghi eseguiti da Eclipse
	public static Archive<?> createDeployment() {
	
		WebArchive archive = ShrinkWrap.create(WebArchive.class, "PayrollTest.war")
        .addPackages(true, "controller")
        .addPackages(true, "dao")
        .addPackages(true, "model")
        .addPackages(true, "payrollSystem")
        .addPackages(true, "utilities")
        .addPackages(true, "view")
        .addPackages(true, "test")
        .addAsResource("META-INF/persistence.xml")
        .addAsWebInfResource(EmptyAsset.INSTANCE, ArchivePaths.create("beans.xml"));
		
		// Esportazione di prova per controllo
		  archive.as(ZipExporter.class).exportTo(
				    new File("target/arquillianPackage.war"), true);
		
		return archive;
		
	}
	
	
}
	
