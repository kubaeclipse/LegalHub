package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;

import static org.assertj.core.api.Assertions.assertThat;

public class ForgotPasswordPage {

    private static final By LOGO = By.className("OE78qEQMDj7l-w1dsy+GGg==");
    private static final By EMAIL_FIELD = By.id("email-address");
    private static final By RECOVERY_BUTTON = By.xpath("//span[@class='Button_text__wi7ei' and text()='Email Recovery Link']");
    private static final By BACK_TO_HOME = By.xpath("//span[@class='Button_text__wi7ei' and text()='Back to Home']");
    private static final By EMAIL_MESSAGE = By.cssSelector("#app-mount > div >div > div > p:nth-child(3)");


    private final WebDriver driver;

    public ForgotPasswordPage(final WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public ForgotPasswordPage waitForPageToBeLoaded() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        return this;
    }
    public ForgotPasswordPage verifyLogo() {
        assertThat(driver.findElement(LOGO).isDisplayed()).as("Logo is displayed properly").isTrue();
        return this;
    }

    public ForgotPasswordPage enterEmailAddress(final String username) {
        driver.findElement(EMAIL_FIELD).sendKeys(username);
        return this;
    }

    public ForgotPasswordPage clickRecoveryLinkButton () {
        driver.findElement(RECOVERY_BUTTON).click();
        assertThat(driver.findElement(EMAIL_MESSAGE).isDisplayed()).isTrue();
        assertThat(driver.findElement(EMAIL_MESSAGE).getText()).isEqualTo("If an account with that email address exists, an email will be sent with further instructions");
        return this;
    }

    public HomePage clickBackToHomeLink() {
        driver.findElement(BACK_TO_HOME).click();
        return new HomePage(driver);
    }
}

