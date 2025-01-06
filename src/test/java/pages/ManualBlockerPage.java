package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class ManualBlockerPage {

    private static final By LOGO = By.className("Header_logo__17nja");
    private static final By MANUAL_BLOCKER_TITLE = By.xpath("//div[p[text()='Manual Product Blocker']]");
    private static final By SEARCH_PRODUCTS_BUTTON = By.xpath("//div[@class='hWYKUw4yiu2Tw89+mPigJw==']//button[span[text()='Search Products']]");
    private static final By MANUAL_TABLE_HEADERS = By.xpath("//table/thead/tr/th");
    private static final By MANUAL_IMAGE = By.cssSelector("img[alt='Man with magnifying glass against a background of question marks']");




    private final WebDriver driver;

    public ManualBlockerPage(final WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    };


    public ManualBlockerPage waitForPageToBeLoaded() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        return this;
    }

    public ManualBlockerPage verifyManualTableHeaders(final String menuItemText) {
        final var menuItems = driver.findElements(MANUAL_TABLE_HEADERS);
        assertThat(menuItems.size()).as("Table header list is not empty").isNotZero();
        final var menuItemsText = menuItems.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
        System.out.println("Headers found: " + menuItemsText);
        assertThat(menuItemsText).as("Table headers contain proper text").contains(menuItemText);
        return this;
    }

    public ManualBlockerPage verifyManualImage() {
        assertThat(driver.findElement(MANUAL_IMAGE).isDisplayed()).as("Manual image is displayed properly").isTrue();
        return this;
    }

    public SearchModalPage clickSearchProductsButton() {
        driver.findElement(SEARCH_PRODUCTS_BUTTON).click();
        return new SearchModalPage(driver);
    }

    public ManualBlockerPage verifyLogo() {
        assertThat(driver.findElement(LOGO).isDisplayed()).as("Logo is displayed properly").isTrue();
        return this;
    }

    public ManualBlockerPage verifyManualBlockerTitle() {
        assertThat(driver.findElement(MANUAL_BLOCKER_TITLE).isDisplayed()).as("Manual blocker title is displayed properly").isTrue();
        return this;
    }

    public ManualBlockerPage verifySearchProductsButton() {
        assertThat(driver.findElement(SEARCH_PRODUCTS_BUTTON).isDisplayed()).as("Search products button is displayed properly").isTrue();
        return this;
    }



}
