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
    public void verifyForgotPasswordForm() {
        new HomePage(driver)
                .verifyLogo()
                .waitForPageToBeLoaded()
                .clickForgotPassword()
                .waitForPageToBeLoaded()
                .clickBackToHomeLink()
                .verifyLogo()
                .waitForPageToBeLoaded()
                .clickForgotPassword()
                .waitForPageToBeLoaded()
                .enterEmailAddress("aaa@example.com")
                .clickRecoveryLinkButton();

    }
}
