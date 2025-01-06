package tests.homePage;

import org.junit.jupiter.api.Test;
import pages.HomePage;
import tests.TestDriver;

public class UserCanLogInTests extends TestDriver {
    @Override
    public String getInitialUrl() {
        return "http://docker-host-test-1.test.fruugo:8110/";
    }

    @Test
    public void verifyElementsOfTheHomePage() {
        new HomePage(driver).verifyLogo()
                .verifyUsernameField()
                .verifyPasswordField()
                .verifyLoginButton();

    }

    @Test
    public void verifySuccessfulLoginToLegalHub() {
        new HomePage(driver).verifyLogo()
                .enterUsername("TECHOPS_ADMIN2@fruugo.com")
                .enterPassword("Techops1111")
                .clickLogin()
                .waitForPageToBeLoaded();
    }

    @Test
    public void verifyUnsuccessfulLoginToLegalHub() {
        new HomePage(driver).verifyLogo()
                .enterUsername("TEST")
                .enterPassword("Techops")
                .verifyInvalidLoginDataMessage();
    }
    @Test
    public void verifySigningOutOfLegalHub() {
        new HomePage(driver).verifyLogo()
                .enterUsername("TECHOPS_ADMIN2@fruugo.com")
                .enterPassword("Techops1111")
                .clickLogin()
                .waitForPageToBeLoaded()
                .signOut()
                .verifyUsernameField()
                .verifyPasswordField();

    }
};
