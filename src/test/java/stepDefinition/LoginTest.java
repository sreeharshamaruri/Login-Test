package stepDefinition;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import utilities.UtilitiesCucumber;

public class LoginTest extends UtilitiesCucumber {

//You can implement missing steps with the snippet below:

	@Given("^I Open Chrome Browser$")
	public void I_Open_Chrome_Browser() {
//		System.setProperty("webdriver.chrome.driver",
//				System.getProperty("user.dir") + "/Drivers/chromedriver.exe");
//		driver = new ChromeDriver();
//		
		DesiredCapabilities caps = new DesiredCapabilities();
        caps.setJavascriptEnabled(true); // not really needed: JS enabled by default
        caps.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY, System.getProperty("user.dir") + "/Drivers/phantomjs.exe");
        driver = new PhantomJSDriver(caps);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}

	@When("^I go to Salesforce application$")
	public void I_go_to_Salesforce_application() {
		driver.get("https://login.salesforce.com/");
		driver.manage().window().maximize();

	}

	@Then("^I should see the Salesforce logo$")
	public void I_should_see_the_Salesforce_logo() {
		WebElement logo = driver.findElement(By.id("logo"));
		if (logo.isDisplayed()) {
			System.out.println("Logo is present");
		} else {
			System.out.println("The logo is not present");
		}

	}

	@Then("^I enter \"([^\"]*)\" username$")
	public void I_enter_username(String username) {
		driver.findElement(By.id("username")).sendKeys(username);
	}

	@Then("^I enter \"([^\"]*)\" password$")
	public void I_enter_password(String password) {
		driver.findElement(By.id("password")).sendKeys(password);
	}

	@When("^I click the login button$")
	public void I_click_the_login_button() {
		driver.findElement(By.id("Login")).click();

	}

	@Then("^I should see the logout link$")
	public void I_should_see_the_logout_link() {
		boolean loggedin = driver.findElement(By.id("setupLink")).isDisplayed();
		Assert.assertTrue(loggedin);
	}

}
