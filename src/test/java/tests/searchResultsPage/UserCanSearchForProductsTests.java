package tests.searchResultsPage;

import config.CredentialsManager;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import pages.HomePage;
import tests.TestDriver;

import java.util.Arrays;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class UserCanSearchForProductsTests extends TestDriver {
    @Override
    public String getInitialUrl() {
        return "http://docker-host-test-1.test.fruugo:8110/";
    }

    @Test
    public void searchForProductByProductIDTest() {
        new HomePage(driver).logInToLegalHub(CredentialsManager.getUsername(), CredentialsManager.getPassword())
                .navigateToManualBlocker()
                .clickSearchProductsButton()
                .verifySearchModalElements()
                .enterProductId("48781763")
                .findProductsByUsingFindProductsButton()
                .verifyProductId("48781763");

    }

    @Test
    public void searchForProductsByMultipleProductIDsTest() {
        new HomePage(driver).logInToLegalHub(CredentialsManager.getUsername(), CredentialsManager.getPassword())
                .navigateToManualBlocker()
                .clickSearchProductsButton()
                .verifySearchModalElements()
                .enterMultipleProductIds(Arrays.asList("50824868", "3635621", "38125790"))
                .findProductsByUsingFindProductsButton()
                .verifyProductIdList(Arrays.asList("50824868", "3635621", "38125790"));
    }

    @Test
    public void searchForProductsBySearchTermTest() {
        new HomePage(driver).logInToLegalHub(CredentialsManager.getUsername(), CredentialsManager.getPassword())
                .navigateToManualBlocker()
                .clickSearchProductsButton()
                .verifySearchModalElements()
                .enterSearchTerm("Yellowstone Ashford Executive Folding Camping Chair")
                .selectExactMatchCheckbox()
                .findProductsBySearchTerm()
                .verifyProductTitleList("Yellowstone Ashford Executive Folding Camping Chair");
    }

    @Test
    public void searchForProductsByProductImageUrlTest() {
        new HomePage(driver).logInToLegalHub(CredentialsManager.getUsername(), CredentialsManager.getPassword())
                .navigateToManualBlocker()
                .clickSearchProductsButton()
                .verifySearchModalElements()
                .enterProductImageUrl("https://img.fruugo.com/product/8/00/147950008_0100_0100.jpg")
                .findProductsByUsingFindProductsButton()
                .verifyProductImageUrlList("https://img.fruugo.com/product/8/00/147950008_0100_0100.jpg");
    }

    @Test
    public void searchForProductByInvalidProductIDTest() {
        new HomePage(driver).logInToLegalHub(CredentialsManager.getUsername(), CredentialsManager.getPassword())
                .navigateToManualBlocker()
                .clickSearchProductsButton()
                .verifySearchModalElements()
                .enterProductId("4878176333333")
                .findNoProductsByProductIdExpectingError();
        assertThat(driver.findElement(By.id("textArea_error")).isDisplayed()).isTrue();
    }

    @Test
    public void searchForProductsByInvalidSearchTermTest() {
        new HomePage(driver).logInToLegalHub(CredentialsManager.getUsername(), CredentialsManager.getPassword())
                .navigateToManualBlocker()
                .clickSearchProductsButton()
                .verifySearchModalElements()
                .enterSearchTerm("Product which does not exist")
                .findNoProductsBySearchTermExpectingError();
        assertThat(driver.findElement(By.id("query_error")).isDisplayed()).isTrue();

    }

    @Test
    public void enterSearchCriteriaAndCancelTest() {
        new HomePage(driver).logInToLegalHub(CredentialsManager.getUsername(), CredentialsManager.getPassword())
                .navigateToManualBlocker()
                .clickSearchProductsButton()
                .verifySearchModalElements()
                .enterSearchTerm("Any search term")
                .clickCancelButton()
                .clickSearchProductsButton()
                .verifySearchModalElements()
                .enterProductId("253235323")
                .clickCancelButton();
    }

    @Test
    public void selectAllProductsTest() {
        new HomePage(driver).logInToLegalHub(CredentialsManager.getUsername(), CredentialsManager.getPassword())
                .navigateToManualBlocker()
                .clickSearchProductsButton()
                .verifySearchModalElements()
                .enterSearchTerm("Folding Camping Chair Padded")
                .findProductsBySearchTerm()
                .clickSelectAllButton()
                .verifyNumberOfSelectedCheckboxesEqualsButtonText()
                .clickClearAllButton()
                .verifyNumberOfSelectedCheckboxesEqualsButtonText();
    }

    @Test
    public void selectRandomProductsTest() {
        new HomePage(driver).logInToLegalHub(CredentialsManager.getUsername(), CredentialsManager.getPassword())
                .navigateToManualBlocker()
                .clickSearchProductsButton()
                .verifySearchModalElements()
                .enterSearchTerm("Folding Camping Chair Padded")
                .findProductsBySearchTerm()
                .selectRandomCheckboxes(3)
                .verifyNumberOfSelectedCheckboxesEqualsButtonText();
    }

    @Test
    public void verifyPerPageValuesTest() {
        new HomePage(driver).logInToLegalHub(CredentialsManager.getUsername(), CredentialsManager.getPassword())
                .navigateToManualBlocker()
                .clickSearchProductsButton()
                .verifySearchModalElements()
                .enterSearchTerm("Folding Camping Chair Padded")
                .findProductsBySearchTerm()
                .verifyPerPageElements();
    }

    @Test
    public void selectValuesPerPageTest() {
        new HomePage(driver).logInToLegalHub(CredentialsManager.getUsername(), CredentialsManager.getPassword())
                .navigateToManualBlocker()
                .clickSearchProductsButton()
                .verifySearchModalElements()
                .enterSearchTerm("Blue shirt")
                .selectExactMatchCheckbox()
                .findProductsBySearchTerm()
                .verifyPerPageElements()
                .selectNumberOfElementsPerPage("100")
                .verifyNumberOfElementsOnPage(100)
                .selectNumberOfElementsPerPage("500")
                .verifyNumberOfElementsOnPage(500)
                .selectNumberOfElementsPerPage("50")
                .verifyNumberOfElementsOnPage(50);
    }

    @Test
    public void navigateToPageTest() {
        new HomePage(driver).logInToLegalHub(CredentialsManager.getUsername(), CredentialsManager.getPassword())
                .navigateToManualBlocker()
                .clickSearchProductsButton()
                .verifySearchModalElements()
                .enterSearchTerm("Blue shirt")
                .selectExactMatchCheckbox()
                .findProductsBySearchTerm()
                .navigateToPageNumber(3)
                .navigateToPageNumber(4)
                .navigateToPageNumber(2);

    }
}
