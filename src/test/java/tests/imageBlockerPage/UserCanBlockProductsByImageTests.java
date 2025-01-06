package tests.imageBlockerPage;

import org.junit.jupiter.api.Test;
import pages.HomePage;
import tests.TestDriver;
public class UserCanBlockProductsByImageTests extends TestDriver {

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
                .navigateToImageBlocker()
                .verifyImageTableHeaders("Product Name")
                .verifyImageTableHeaders("Retailer")
                .verifyImageTableHeaders("Creation Time")
                .verifyImageTableHeaders("Marketplace Countries")
                .verifyImageTableHeaders("Product Category")
                .verifyImageTableHeaders("Similarity Rating");
    }

}
