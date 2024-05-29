package TestRunner;
import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;


@RunWith(Cucumber.class)
@CucumberOptions(features="src\\test\\resource\\Features\\AddUser.feature",
glue="Steps",
monochrome=false,
plugin={"pretty", "html:target/regresReport/UserReport.html",
		"json:target/JsonReort/UserReport.json",
		"junit:target/JunitReport/UserReport.xml",
		"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
})
public class AddUserTestRunner extends AbstractTestNGCucumberTests{

}
