package TestRunner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.testng.AbstractTestNGCucumberTests;

@RunWith(Cucumber.class)
@CucumberOptions(features="src\\test\\resource\\Features\\TestUser.feature",
glue="Stepdown"
)
public class TestUser extends AbstractTestNGCucumberTests {
	
	

}
