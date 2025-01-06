package tests.searchResultsPage;

import org.junit.jupiter.api.Test;
import pages.HomePage;
import tests.TestDriver;

import java.util.Arrays;

public class UserCanSearchForProductsTests extends TestDriver {
    @Override
    public String getInitialUrl() {
        return "http://docker-host-test-1.test.fruugo:8110/";
    }

    @Test
    public void searchForProductsByProductID() {
        new HomePage(driver).verifyLogo()
                .enterUsername("TECHOPS_ADMIN2@fruugo.com")
                .enterPassword("Techops1111")
                .clickLogin()
                .waitForPageToBeLoaded()
                .navigateToManualBlocker()
                .verifyManualTableHeaders("Product Name")
                .verifyManualTableHeaders("Retailer")
                .verifyManualTableHeaders("Creation Time")
                .verifyManualTableHeaders("Marketplace Countries")
                .verifyManualTableHeaders("Product Category")
                .clickSearchProductsButton()
                .verifySearchModalElements()
                .enterProductId("48781763")
                .clickFindProductsButton()
                .waitForPageToBeLoaded()
                .verifyProductIdList("48781763");
    }

    @Test
    public void searchForProductsByMultipleProductIDs() {
        new HomePage(driver).verifyLogo()
                .enterUsername("TECHOPS_ADMIN2@fruugo.com")
                .enterPassword("Techops1111")
                .clickLogin()
                .waitForPageToBeLoaded()
                .navigateToManualBlocker()
                .verifyManualTableHeaders("Product Name")
                .verifyManualTableHeaders("Retailer")
                .verifyManualTableHeaders("Creation Time")
                .verifyManualTableHeaders("Marketplace Countries")
                .verifyManualTableHeaders("Product Category")
                .clickSearchProductsButton()
                .verifySearchModalElements()
                .enterMultipleProductIds(Arrays.asList("50824868", "3635621", "38125790"))
                .clickFindProductsButton()
                .waitForPageToBeLoaded()
                .verifyProductIdList(Arrays.asList("50824868", "3635621"));
    }
    @Test
    public void searchForProductsBySearchTerm() {
        new HomePage(driver).verifyLogo()
                .enterUsername("TECHOPS_ADMIN2@fruugo.com")
                .enterPassword("Techops1111")
                .clickLogin()
                .waitForPageToBeLoaded()
                .navigateToManualBlocker()
                .verifyManualTableHeaders("Product Name")
                .verifyManualTableHeaders("Retailer")
                .verifyManualTableHeaders("Creation Time")
                .verifyManualTableHeaders("Marketplace Countries")
                .verifyManualTableHeaders("Product Category")
                .clickSearchProductsButton()
                .verifySearchModalElements()
                .enterSearchTerm("Folding Camping Chair Padded Potable")
                .selectExactMatchCheckbox()
                .clickSearchProductsButton()
                .waitForPageToBeLoaded()
                .verifyProductTitleList("Folding Camping Chair Padded Potable");
    }

    @Test
    public void searchForProductsByProductImageUrl() {
        new HomePage(driver).verifyLogo()
                .enterUsername("TECHOPS_ADMIN2@fruugo.com")
                .enterPassword("Techops1111")
                .clickLogin()
                .waitForPageToBeLoaded()
                .navigateToManualBlocker()
                .verifyManualTableHeaders("Product Name")
                .verifyManualTableHeaders("Retailer")
                .verifyManualTableHeaders("Creation Time")
                .verifyManualTableHeaders("Marketplace Countries")
                .verifyManualTableHeaders("Product Category")
                .clickSearchProductsButton()
                .verifySearchModalElements()
                .enterProductImageUrl("https://img.fruugo.com/product/8/00/147950008_0100_0100.jpg")
                .clickFindProductsButton()
                .waitForPageToBeLoaded()
                .verifyProductImageUrlList("https://img.fruugo.com/product/8/00/147950008_0100_0100.jpg");
    }

}
