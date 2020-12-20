package amazon.pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
/**
 * This class contains page objects for the search results page. It has the method to sort the search results.
 * 
 * @author Suganya Kannan
 * @version 1.0
 * @since 2020-12-20
 */

public class ResultsPage extends PageObject {
  
	public ResultsPage(WebDriver driver){
        super(driver);
    }
	
    @FindBy(id = "s-result-sort-select")
    public WebElement resultSortSelect;


    public void sortResults(String sortOption) {
        resultSortSelect.findElement(By.xpath("//option[text()=\"" + sortOption + "\"]")).click();
    }

 

}

