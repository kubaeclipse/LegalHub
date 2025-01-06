package tests.manualBlockerPage;

import org.junit.jupiter.api.Test;
import pages.HomePage;
import tests.TestDriver;

public class UserCanBlockProductsManuallyTests extends TestDriver {

    @Override
    public String getInitialUrl() {
        return "http://docker-host-test-1.test.fruugo:8110/";
    }

    @Test
    public void verifyTableHeaders() {
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
                .verifyManualTableHeaders("Product Category");
    }
}
