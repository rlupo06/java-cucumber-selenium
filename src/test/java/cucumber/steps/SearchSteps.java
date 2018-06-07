package cucumber.steps;

import cucumber.api.java.en.Given;
import cucumber.data.TestData;
import cucumber.pages.SearchPage;
import cucumber.support.Hooks;

public class SearchSteps {

  private final SearchPage page;
  private final TestData testData;

  public SearchSteps(Hooks hooks) {
    this.page = new SearchPage(hooks.getDriver());
    this.testData = hooks.getTestData();
  }

  @Given("^the user searches for \"([^\"]*)\"$")
  public void theUserLogsIn(String searchValue) throws Throwable {
    page.search(searchValue);
  }
}