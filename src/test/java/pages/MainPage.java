package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class MainPage {
    private static final By MANUAL_BLOCKER = By.xpath("//a[span[text()='Manual product blocker']]");
    private static final By IMAGE_BLOCKER = By.xpath("//a[span[text()='Image blocker']]");
    private static final By MY_ACCOUNT = By.className("Footer_button__pavpi");
    private static final By SIGN_OUT = By.xpath("//button[span[text()='Sign out']]");
    private static final By IMAGE_PICTURE = By.cssSelector("img[alt='Woman cataloguing pictures.']");
    private static final By MANUAL_IMAGE = By.cssSelector("img[alt='Man with magnifying glass against a background of question marks.']");

    private final WebDriver driver;

    public MainPage(final WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public MainPage waitForPageToBeLoaded() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        return this;
    }

    public boolean isLoggedIn() {
        return driver.findElement(MY_ACCOUNT).isDisplayed();
    }


    public ManualBlockerPage navigateToManualBlocker() {
        driver.findElement(MANUAL_BLOCKER).click();
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(MANUAL_IMAGE));
        return new ManualBlockerPage(driver);
    }

    public ImageBlockerPage navigateToImageBlocker() {
        driver.findElement(IMAGE_BLOCKER).click();
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(IMAGE_PICTURE));
        return new ImageBlockerPage(driver);
    }
    public HomePage signOut() {
        driver.findElement(MY_ACCOUNT).click();
        driver.findElement(SIGN_OUT).click();
        return new HomePage(driver);
    }
}