package tests.manualBlockerPage;

import config.CredentialsManager;
import org.junit.jupiter.api.Test;
import pages.HomePage;
import tests.TestDriver;

import java.util.Arrays;

public class UserCanViewManualBlockerElementsTests extends TestDriver {

    @Override
    public String getInitialUrl() {
        return "http://docker-host-test-1.test.fruugo:8110/";
    }

    @Test
    public void verifyManualBlockerPageElements() {
        new HomePage(driver).logInToLegalHub(CredentialsManager.getUsername(), CredentialsManager.getPassword())
                .navigateToManualBlocker()
                .verifyLogo()
                .verifyManualImage()
                .verifyManualBlockerTitle()
                .verifySearchProductsButton();
    }

    @Test
    public void verifyManualBlockerTableHeaders() {
        new HomePage(driver).logInToLegalHub(CredentialsManager.getUsername(), CredentialsManager.getPassword())
                .navigateToManualBlocker()
                .verifyManualTableHeaders(Arrays.asList("Product Name", "Retailer", "Creation Time", "Marketplace Countries", "Product Category"));
    }
}
