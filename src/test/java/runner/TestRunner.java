package runner;



import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;


@RunWith(Cucumber.class)
@CucumberOptions(
        features={"src/test/resources/Feature/Candere.feature"},           //Path of Feature folder which hold feature file
        glue={"stepdef","StepDef" }, // Path of StepDefinition file
        tags= "@Searchprod1", 
        plugin = {"pretty",                      
            "html:target/html/htmlReport.html",
            "json:target/json/jsonReport.json",
            },
        monochrome=true,
        publish= true,
        dryRun=false
        
		)

public class TestRunner {

	
	
}
