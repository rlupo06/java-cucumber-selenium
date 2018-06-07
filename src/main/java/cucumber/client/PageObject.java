package cucumber.client;


import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public abstract class PageObject {

  private final RemoteWebDriver driver;

  public PageObject(RemoteWebDriver driver) {
    this.driver = driver;
    AjaxElementLocatorFactory decorator = new AjaxElementLocatorFactory(this.driver, 15);
    PageFactory.initElements(decorator, this);
  }

  public abstract void trait();


}
