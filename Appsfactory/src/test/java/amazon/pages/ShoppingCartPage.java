package amazon.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * This class contains page objects for the shopping cart page. It has methods
 * to calculate the total sum of the products prices and the actual total price
 * 
 * @author Suganya Kannan
 * @version 1.0
 * @since 2020-12-20
 */

public class ShoppingCartPage extends PageObject {

	public ShoppingCartPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(className = "sc-product-price")
	public List<WebElement> productPrices;

	@FindBy(css = "#sc-subtotal-amount-activecart > span")
	public WebElement subtotal;

	@FindBy(xpath = "//*[@id='sc-buy-box-ptc-button']/span/input")
	public WebElement proceedToCheckout;

	/**
	 * 
	 * This method calculates the sum of all the individual products in the basket
	 */

	public double calculateTotalSum() {

		double total = 0.00;
		for (WebElement productPrice : productPrices) {
			double value = Double.parseDouble(productPrice.getText().substring(1));
			total += Math.round(value * 100.0) / 100.0;
		}
		return Math.round(total * 100.0) / 100.0;
	}

	/**
	 * 
	 * This method returns the total amount displayed in the webpage
	 */
	public double getPageTotal() {
		double value = Double.parseDouble(subtotal.getText().substring(1));
		return Math.round(value * 100.0) / 100.0;
	}
}
