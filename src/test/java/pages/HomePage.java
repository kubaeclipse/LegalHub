package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.assertj.core.api.Assertions.assertThat;
public class HomePage {

    private static final By LOGO = By.className("OE78qEQMDj7l-w1dsy+GGg==");
    private static final By USERNAME = By.id("username");
    private static final By PASSWORD = By.id("password");
    private static final By FORGOT_PASSWORD = By.xpath("//a[span[text()='Forgot your password?']]");
    private static final By LOGIN = By.className("Button_text__wi7ei");
    private static final By INVALID_PASSWORD_MESSAGE = By.className("Alert_message__8m4bi");



    private final WebDriver driver;

    public HomePage(final WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public HomePage waitForPageToBeLoaded() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        return this;
    }

    public HomePage verifyLogo() {
        final var header = driver.findElement(LOGO);
        assertThat(header.isDisplayed()).as("Logo is displayed properly").isTrue();
        return this;
    }

    public HomePage verifyUsernameField() {
        assertThat(driver.findElement(USERNAME).isDisplayed()).as("Logo is displayed properly").isTrue();
        return this;
    }

    public HomePage verifyPasswordField() {
        assertThat(driver.findElement(PASSWORD).isDisplayed()).as("Logo is displayed properly").isTrue();
        return this;
    }

    public HomePage verifyLoginButton() {
        assertThat(driver.findElement(LOGIN).isDisplayed()).as("Logo is displayed properly").isTrue();
        return this;
    }

    public HomePage verifyInvalidLoginDataMessage() {
        driver.findElement(LOGIN).click();
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(INVALID_PASSWORD_MESSAGE));
        assertThat(driver.findElement(INVALID_PASSWORD_MESSAGE).isDisplayed()).as("Message is displayed properly").isTrue();
        assertThat(driver.findElement(INVALID_PASSWORD_MESSAGE).getText()).isEqualTo("Error: Invalid username or password.");
        return this;
    }


    public HomePage enterUsername(final String username) {
        driver.findElement(USERNAME).sendKeys(username);
        return this;
    }

    public HomePage enterPassword(final String password) {
        driver.findElement(PASSWORD).sendKeys(password);
        return this;
    }

    public MainPage clickLogin() {
        driver.findElement(LOGIN).click();
        return new MainPage(driver);
    }

    public ForgotPasswordPage clickForgotPassword() {
        driver.findElement(FORGOT_PASSWORD).click();
        return new ForgotPasswordPage(driver);
    }

}
