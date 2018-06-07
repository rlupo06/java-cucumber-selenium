package cucumber.pages;

import cucumber.client.PageObject;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;

public class SearchPage extends PageObject {

    @FindBy(css = "#lst-ib")
    private WebElement searchBar;

    public SearchPage(RemoteWebDriver driver) {
        super(driver);
        trait();
    }

    @Override
    public void trait() {
        Assert.assertTrue(searchBar.isDisplayed());
    }

    public void search(String searchValue) {
        this.searchBar.sendKeys(searchValue);
    }
}
