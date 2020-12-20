package amazon.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
/**
 * This class acts as a base call. It has the method to initialize the driver.
 * 
 * @author Suganya Kannan
 * @version 1.0
 * @since 2020-12-20
 */

public class PageObject {
    protected WebDriver driver;

    public PageObject(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
}
