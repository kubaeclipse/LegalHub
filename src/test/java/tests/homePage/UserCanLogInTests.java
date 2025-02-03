package tests.homePage;

import config.CredentialsManager;
import org.junit.jupiter.api.Test;
import pages.HomePage;
import pages.MainPage;
import tests.TestDriver;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class UserCanLogInTests extends TestDriver {
    @Override
    public String getInitialUrl() {
        return "http://docker-host-test-1.test.fruugo:8110/";
    }

    @Test
    public void verifyElementsOfTheHomePageTest() {
        new HomePage(driver).verifyLogoElement()
                .verifyUsernameFieldElement()
                .verifyPasswordFieldElement()
                .verifyLoginButtonElement();
    }

    @Test
    public void verifySuccessfulLoginToLegalHubTest() {
        new HomePage(driver)
                .logInToLegalHub(CredentialsManager.getUsername(), CredentialsManager.getPassword())
                .isLoggedIn();
    }

    @Test
    public void verifyUnsuccessfulLoginToLegalHubTest() {
        new HomePage(driver).verifyLogoElement()
                .enterUsername("TEST")
                .enterPassword("Techops")
                .verifyInvalidLoginDataMessage();
    }

    @Test
    public void verifySigningOutOfLegalHubTest() {
        MainPage mainPage = new HomePage(driver).logInToLegalHub(CredentialsManager.getUsername(), CredentialsManager.getPassword());

        assertThat(mainPage.isLoggedIn())
                .as("User should be logged in successfully")
                .isTrue();

        mainPage.signOut()
                .verifyUsernameFieldElement()
                .verifyPasswordFieldElement();
    }
}
