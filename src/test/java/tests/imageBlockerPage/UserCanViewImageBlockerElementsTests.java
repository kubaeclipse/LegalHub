package tests.imageBlockerPage;

import config.CredentialsManager;
import org.junit.jupiter.api.Test;
import pages.HomePage;
import tests.TestDriver;

import java.util.Arrays;

public class UserCanViewImageBlockerElementsTests extends TestDriver {

    @Override
    public String getInitialUrl() {
        return "http://docker-host-test-1.test.fruugo:8110/";
    }


    @Test
    public void verifyImageBlockerElements() {
        new HomePage(driver).logInToLegalHub(CredentialsManager.getUsername(), CredentialsManager.getPassword())
                .navigateToImageBlocker()
                .verifyImageBlockerTitle()
                .verifyUploadButton()
                .verifyImagePicture()
                .verifyLogo()
                .verifyViewFiltersButton();
    }
    @Test
    public void verifyManualImageTableElements() {
        new HomePage(driver).logInToLegalHub(CredentialsManager.getUsername(), CredentialsManager.getPassword())
                .navigateToImageBlocker()
                .verifyImageTableHeaders(Arrays.asList("Product Name", "Retailer", "Creation Time", "Marketplace Countries", "Product Category", "Similarity Rating"));
    }
}
