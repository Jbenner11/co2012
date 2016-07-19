
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = {"pretty","html:build/cucumber-html-report"}, 
    features = "src/test/resources")
public class MoveTest {
}
