package tests.forgotPasswordPage;

import org.junit.jupiter.api.Test;
import pages.HomePage;
import tests.TestDriver;

public class UserCanRecoverPasswordTests extends TestDriver {


    @Override
    public String getInitialUrl() {
        return "http://docker-host-test-1.test.fruugo:8110/";
    }

    @Test
    public void navigateBackToHomepageTest() {
        new HomePage(driver).verifyLogoElement()
                .waitForPageToBeLoaded()
                .clickForgotPassword()
                .waitForPageToBeLoaded()
                .clickBackToHomeLink()
                .verifyLogoElement()
                .isOnLoginPage();
    }

    @Test
    public void verifyForgotPasswordTest() {
        new HomePage(driver).verifyLogoElement()
                .clickForgotPassword()
                .waitForPageToBeLoaded()
                .enterEmailAddress("aaa@example.com")
                .clickRecoveryLinkButtonAndVerifyMessage();
    }
}
