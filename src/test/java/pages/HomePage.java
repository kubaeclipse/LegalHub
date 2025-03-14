package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.assertj.core.api.Assertions.assertThat;

public class HomePage {

    private static final By FORGOT_PASSWORD_LINK = By.xpath("//a[span[text()='Forgot your password?']]");
    private static final By INVALID_PASSWORD_MESSAGE = By.className("Alert_message__8m4bi");
    private static final By LOGIN_BUTTON = By.className("Button_text__wi7ei");
    private static final By LOGO = By.className("OE78qEQMDj7l-w1dsy+GGg==");
    private static final By PASSWORD_FIELD = By.id("password");
    private static final By USERNAME_FIELD = By.id("username");

    private final WebDriver driver;

    public HomePage(final WebDriver driver) {
        this.driver = driver;
    }

    public HomePage waitForPageToBeLoaded() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        return this;
    }

    public HomePage isOnLoginPage() {
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(USERNAME_FIELD));
        return this;
    }

    public MainPage logInToLegalHub(String login, String password) {
        verifyLogoElement();
        enterUsername(login);
        enterPassword(password);
        clickLogin();
        waitForPageToBeLoaded();

        return new MainPage(driver);
    }

    private void verifyElementIsDisplayed(By locator, String elementName) {
        assertThat(driver.findElement(locator).isDisplayed())
                .as(elementName + " is displayed properly")
                .isTrue();
    }

    public HomePage verifyLogoElement() {
        verifyElementIsDisplayed(LOGO, "Logo");
        return this;
    }

    public HomePage verifyUsernameFieldElement() {
        verifyElementIsDisplayed(USERNAME_FIELD, "Username field");
        return this;
    }

    public HomePage verifyPasswordFieldElement() {
        verifyElementIsDisplayed(PASSWORD_FIELD, "Password field");
        return this;
    }

    public HomePage verifyLoginButtonElement() {
        verifyElementIsDisplayed(LOGIN_BUTTON, "Login button");
        return this;
    }

    public HomePage verifyInvalidLoginDataMessage() {
        clickLogin();
        WebElement errorMessage = new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(INVALID_PASSWORD_MESSAGE));
        assertThat(errorMessage.getText()).containsIgnoringCase("Invalid username or password");
        return this;
    }

    public HomePage enterUsername(final String username) {
        driver.findElement(USERNAME_FIELD).sendKeys(username);
        return this;
    }

    public HomePage enterPassword(final String password) {
        driver.findElement(PASSWORD_FIELD).sendKeys(password);
        return this;
    }

    public MainPage clickLogin() {
        driver.findElement(LOGIN_BUTTON).click();
        return new MainPage(driver);
    }

    public ForgotPasswordPage clickForgotPassword() {
        driver.findElement(FORGOT_PASSWORD_LINK).click();
        return new ForgotPasswordPage(driver);
    }

}
