package amazon.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
/**
 * This class contains page objects for the product page. It has the method to add the product to the cart.
 * 
 * @author Suganya Kannan
 * @version 1.0
 * @since 2020-12-20
 */

public class ProductPage extends PageObject {
	  
		public ProductPage(WebDriver driver){
	        super(driver);
	    }
		  @FindBy(xpath = "//*[@id=\"add-to-cart-button\"]")
		    private WebElement addToCartButton;
		  
		  public void addToCart() throws InterruptedException {
			  addToCartButton.click();
		        Thread.sleep(1000);  		      
}
}
