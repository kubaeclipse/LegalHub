package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class ManualBlockerPage {

    private static final By LOGO = By.className("Header_logo__17nja");
    private static final By MANUAL_BLOCKER_TITLE = By.xpath("//div[p[text()='Manual Product Blocker']]");
    private static final By SEARCH_PRODUCTS_BUTTON = By.xpath("//div[@class='hWYKUw4yiu2Tw89+mPigJw==']//button[span[text()='Search Products']]");
    private static final By MANUAL_TABLE_HEADERS = By.xpath("//table/thead/tr/th");
    private static final By MANUAL_IMAGE = By.cssSelector("img[alt='Man with magnifying glass against a background of question marks.']");
    private static final By SEARCH_MODAL = By.cssSelector("div.Modal_content__1bth6");


    private final WebDriver driver;

    public ManualBlockerPage(final WebDriver driver) {
        this.driver = driver;
    }


    public ManualBlockerPage verifyManualTableHeaders(final List<String> expectedHeaders) {
        List<WebElement> menuItems = driver.findElements(MANUAL_TABLE_HEADERS);
        assertThat(menuItems).as("Table header list is not empty").isNotEmpty();

        List<String> actualHeaders = menuItems.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());

        assertThat(actualHeaders).as("Table headers contain expected texts")
                .containsAll(expectedHeaders);
        return this;
    }

    public ManualBlockerPage verifyManualImage() {
        WebElement manualImage = driver.findElement(MANUAL_IMAGE);
        assertThat(manualImage.isDisplayed()).as("Manual image is displayed properly").isTrue();
        return this;
    }

    public SearchModalPage clickSearchProductsButton() {
        WebElement searchButton = driver.findElement(SEARCH_PRODUCTS_BUTTON);
        searchButton.click();

        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(SEARCH_MODAL));
        return new SearchModalPage(driver);
    }

    public ManualBlockerPage verifyLogo() {
        WebElement logo = driver.findElement(LOGO);
        assertThat(logo.isDisplayed()).as("Logo is displayed properly").isTrue();
        return this;
    }

    public ManualBlockerPage verifyManualBlockerTitle() {
        WebElement title = driver.findElement(MANUAL_BLOCKER_TITLE);
        assertThat(title.isDisplayed()).as("Manual blocker title is displayed properly").isTrue();
        return this;
    }

    public ManualBlockerPage verifySearchProductsButton() {
        WebElement searchButton = driver.findElement(SEARCH_PRODUCTS_BUTTON);
        assertThat(searchButton.isDisplayed()).as("Search products button is displayed properly").isTrue();
        return this;
    }

}
