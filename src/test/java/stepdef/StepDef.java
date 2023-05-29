package stepdef;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;



import core.WebDriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.CommonPageObjects;

public class StepDef {
	
	private static final Logger logger = LogManager.getLogger(StepDef.class);
	WebDriver driver;
	Scenario scn;
	String base_url = "https://www.candere.com/  ";

//============== Declare object reference of pageObjects classes =============================//
	
	CommonPageObjects cmnPageObject;
//	FooterSectionObject footerObject;
//	SignInPageObject signInObject;
//	HeaderSectionObject headerObject;
//====================== Before Hook =========================================================//	
	@Before
	public void setUp(Scenario scn)
	{
		this.scn=scn;

		//Get the desired browser to be invoked
		String browserName= WebDriverFactory.getBrowserName();
		driver=WebDriverFactory.getWebDriverForBrowser(browserName); 
		scn.log("Browser get invoked");

		//Initialize object of page objects classes 
		 cmnPageObject= new CommonPageObjects(driver, scn);
//		 footerObject= new FooterSectionObject(driver, scn);
//		 signInObject= new SignInPageObject(driver, scn);
//		 headerObject= new HeaderSectionObject(driver, scn);
	}

//====================== After Hook =========================================================//
	
	@After(order=2)
	//Capture screenshot if test case get failed
	public void captureScreenshot(Scenario scn)
	{
		if(scn.isFailed())
		{
			TakesScreenshot srnshot= ((TakesScreenshot)driver);
			byte [] data =srnshot.getScreenshotAs(OutputType.BYTES);
			scn.attach(data, "image/png", "Name of failed step is: "+ scn.getName());
			scn.log("Attach a screenshot as step get failed");
		}
		else
		{
			scn.log("Test case get passed, no screenshot is captured");
		}
	}
	
	@After(order=1)
	//Quit the browser
	public void tearDown(Scenario scn)
	{
		WebDriverFactory.quitTheBrowser();
		scn.log("Browser is quit");
	}
	
//====================== Background ================================================//
	@Given("User navigate to URL and open the landing page")
	public void user_navigate_to_url_and_open_the_landing_page() {
		WebDriverFactory.navigateToURL(base_url);
	    }

//===================== Scenarios =================================================//

	@When("User is on landing page")
	public void user_is_on_landing_page() {
		logger.info("User is on landing page after navigating to base URL");
	    scn.log("User is on landing page after navigating to base URL");
	}
	@Then("Validate current URL of landing page with expected URL")
	public void validate_current_url_of_landing_page_with_expected_url() {
		 cmnPageObject.validatePageURL();
		 scn.log("Validate current URL is: "+ driver.getCurrentUrl());
	}
	
	@Then("Validate title of landing page with expected title as {string}")
	public void validate_title_of_landing_page_with_expected_title_as(String titleOfPage) {
		cmnPageObject.validatePageTitle(titleOfPage);
		scn.log("Validate page title is: "+ driver.getTitle());
	}
		
	@Then("User see the logo of application")
	public void user_see_the_logo_of_application() {
		cmnPageObject.displayLogo();
		scn.log("Display the application logo on landing page");
	}
	
	@When("fetch the height of logo")
	public void fetch_the_height_of_logo() {
		cmnPageObject.fetchLogoHeight();
	}

	@Then("Height of logo should be {string}")
	public void height_of_logo_should_be(String height) {
		cmnPageObject.logoHeightValid(height);
	}
		
	@When("fetch the width of logo")
	public void fetch_the_width_of_logo() {
		 
		cmnPageObject.fetchLogoWidth();
		
	}

	@Then("Width of logo should be {string}")
	public void width_of_logo_should_be(String width) {
		cmnPageObject.logoWidthValid(width);
	}
	
	//------------------------- @ProductCategory----------------------------------------------------------------------------

	@When("User see the product category")
	public void user_see_the_product_category() {
	   cmnPageObject.setProdCategory();
	}

	@Then("Validate product category as per expected product category listed below")
	public void validate_product_category_as_per_expected_product_category_listed_below(List<String> prodList1) {
	    cmnPageObject.validateProdCategory(prodList1);
	    scn.log("Validate the product category with expected datatable");
	}

	@Then("Size of product category should be {int}")
	public void size_of_product_category_should_be(Integer prodCatCount) {
	    cmnPageObject.sizeOfProdCategory(prodCatCount);
	}
	
	
	@When("User navigated to the home application url")
	public void user_navigated_to_the_home_application_url() {
		cmnPageObject.vaildateLandingPageTitle(base_url);;
		
		scn.log("User navigated to the landing page of the application");

	}
	
	@When("User Search for product on search bar {string}")
	public void user_search_for_product_on_search_bar(String prodName) {


		cmnPageObject.searchProduct(prodName);
	

	}
	@Then("Search Result page is displayed with title contains {string}")
	public void search_result_page_is_displayed_with_title_contains(String prodName1) {
	    cmnPageObject.validateSearchResult(prodName1);
	    
	    

	}

}
