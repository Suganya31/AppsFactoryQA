package seleniumgluecode;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.SystemUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import amazon.pages.ResultsPage;
import amazon.pages.ShoppingCartPage;
import amazon.pages.ProductPage;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

/**
 * This class contains methods to exuecte all the steps definited in the checkout.feature file.
 * 
 * @author Suganya Kannan
 * @version 1.0
 * @since 2020-12-20
 */


public class checkout {

	private static WebDriver Driver;

	@Before
	public static void SetUp() {
		if (SystemUtils.IS_OS_LINUX) {
			File f = new File("WebDriver/Linux/chromedriver");
			System.setProperty("webdriver.chrome.driver", f.getAbsolutePath());
		}
		if (SystemUtils.IS_OS_WINDOWS) {
			File f = new File("WebDriver/Windows/chromedriver.exe");
			System.setProperty("webdriver.chrome.driver", f.getAbsolutePath());
		}
		if (SystemUtils.IS_OS_MAC) {
			File f = new File("WebDriver/MacOS/chromedriver");
			System.setProperty("webdriver.chrome.driver", f.getAbsolutePath());
		}
		Driver = new ChromeDriver();
		Driver.manage().window().maximize();
		Driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

	}

	@Given("The user is on Amazon homepage")
	public void user_is_on_Amazon_homepage() throws InterruptedException {
		Driver.get("http://www.amazon.de");
		// Accept the cookies
		Driver.findElement(By.id("sp-cc-accept")).click();
		//Move to the top navigation bar and choose English as the language
		Actions actions = new Actions(Driver);
		actions.moveToElement(Driver.findElement(By.id("icp-nav-flyout"))).perform();
		Driver.findElement(By.cssSelector("#nav-flyout-icp a[href^=\"#switch-lang\"]"))
				.findElement(By.xpath("//span[text()=\"" + "English - EN" + "\"]")).click();

	}

	@Given("The user searches for {string}")
	public void the_user_searches_for(String keyword) throws InterruptedException {
		//Select Grocery in the department
		Driver.findElement(By.xpath("//*[@id='searchDropdownBox']"))
				.findElement(By.xpath("//*[@id=\"searchDropdownBox\"]/option[23]")).click();

		WebElement searchBox = Driver.findElement(By.id("twotabsearchtextbox"));
		searchBox.sendKeys(keyword, Keys.ENTER);
		Driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}

	@Given("The user sorts the search results by {string}")
	public void the_user_sorts_the_search_results_by_price(String sortOption) {
		ResultsPage page = new ResultsPage(Driver);
		page.sortResults(sortOption);
	}

	@Given("The user adds the cheapest product to the basket")
	public void the_user_adds_the_cheapest_product_to_the_basket() throws InterruptedException {
		ProductPage product = new ProductPage(Driver);
		WebElement cheapestElement = Driver.findElement(
				By.xpath("//*[@id='search']/div[1]/div[2]/div/span[3]/div[2]/div[2]/div/span/div/div/div[2]/h2/a"));
		cheapestElement.click();
		product.addToCart();
	}

	@When("The user navigates to the basket page")
	public void i_navigate_to_basket_page() {
		Driver.get("https://www.amazon.de/-/en/gp/cart/view.html?ref_=nav_cart");
	}

	@Then("The basket should have the correct total amount")
	public void i_should_see_correct_total_amount() {
		ShoppingCartPage cart = new ShoppingCartPage(Driver);
		double expectedValue = cart.calculateTotalSum();
		double actualValue = cart.getPageTotal();
		assertEquals(expectedValue, actualValue, 0D);
	}

	@Given("The user proceeds to checkout")
	public void the_user_proceeds_to_checkout() {
		new ShoppingCartPage(Driver).proceedToCheckout.click();
	}

	@Then("The user should be redirected to the registration page")
	public void the_user_should_be_redirected_to_the_registration_page() {
		String actualTitle = Driver.getTitle();
		String expectedTitle = "Amazon Sign In";
		assertEquals("Verify that user is on the Amazon Registration Page", expectedTitle, actualTitle);
	}

	@After
	public static void cleanUp() {

		Driver.close();

	}
}
