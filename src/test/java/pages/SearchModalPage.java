package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class SearchModalPage {

    private Logger logger = LogManager.getRootLogger();
    private static final By CANCEL_BUTTON = By.xpath("//button[span[text()='Cancel']]");
    private static final By EXACT_MATCH_CHECKBOX = By.className("CustomCheckbox_checkbox__1jzjs");
    private static final By FIND_PRODUCTS_BUTTON = By.xpath("//button[span[text()='Find Products']]");
    private static final By PRODUCT_FIELD = By.id("textArea");
    private static final By SEARCH_MODAL = By.cssSelector("div.Modal_content__gcq5h");
    private static final By SEARCH_PRODUCTS_BUTTON = By.xpath("(//button[span[text()=\"Search Products\"]])[2]");
    private static final By SEARCH_TERM_FIELD = By.id("query");

    private final WebDriver driver;

    public SearchModalPage(final WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    private void assertElementIsDisplayed(By locator, String elementName) {
        assertThat(driver.findElement(locator).isDisplayed())
                .as(elementName + " is displayed")
                .isTrue();
    }

    public SearchModalPage verifySearchModalElements() {
        assertElementIsDisplayed(SEARCH_MODAL, "Modal");
        assertElementIsDisplayed(FIND_PRODUCTS_BUTTON, "Find Products button");
        assertElementIsDisplayed(SEARCH_PRODUCTS_BUTTON, "Search Products button");
        assertElementIsDisplayed(CANCEL_BUTTON, "Cancel button");
        assertElementIsDisplayed(PRODUCT_FIELD, "Product field");

        return this;
    }

    public SearchModalPage enterProductId(final String productId) {
        WebElement productField = driver.findElement(PRODUCT_FIELD);
        productField.clear();
        productField.sendKeys(productId);

        return this;
    }

    public SearchModalPage enterMultipleProductIds(final List<String> productIds) {
        WebElement productField = driver.findElement(PRODUCT_FIELD);

        for (String productId : productIds) {
            productField.sendKeys(productId);
            productField.sendKeys(Keys.ENTER);
        }
        return this;
    }

    public SearchModalPage enterProductImageUrl(final String productImageUrl) {
        driver.findElement(PRODUCT_FIELD).sendKeys(productImageUrl);
        return this;
    }

    public SearchModalPage enterSearchTerm(final String searchTerm) {
        driver.findElement(SEARCH_TERM_FIELD).sendKeys(searchTerm);
        return this;
    }

    public SearchModalPage selectExactMatchCheckbox() {
        driver.findElement(EXACT_MATCH_CHECKBOX).click();
        return this;
    }

    private void handleError(String errorId, String expectedErrorMessage) {
        if (!driver.findElements(By.id(errorId)).isEmpty()) {
            String errorMessage = driver.findElement(By.id(errorId)).getText();
            logger.info(errorMessage);
            assertThat(errorMessage.contains(expectedErrorMessage));
        }
    }

    public SearchResultsPage findProductsBySearchTerm() {

        WebElement searchButton = driver.findElement(SEARCH_PRODUCTS_BUTTON);
        searchButton.click();

        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.invisibilityOfElementLocated(SEARCH_MODAL));

        return new SearchResultsPage(driver);
    }

    public SearchResultsPage findProductsByUsingFindProductsButton() {
        WebElement searchButton = driver.findElement(FIND_PRODUCTS_BUTTON);
        searchButton.click();

        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.invisibilityOfElementLocated(SEARCH_MODAL));
        return new SearchResultsPage(driver);
    }

    public SearchResultsPage findNoProductsByProductIdExpectingError() {
        WebElement searchButton = driver.findElement(FIND_PRODUCTS_BUTTON);
        searchButton.click();

        handleError("textArea_error", "Error: No products found for pasted data: ");
        return new SearchResultsPage(driver);
    }

    public SearchResultsPage findNoProductsBySearchTermExpectingError() {
        WebElement searchButton = driver.findElement(SEARCH_PRODUCTS_BUTTON);
        searchButton.click();

        handleError("query_error", "No products found for search term : ");
        return new SearchResultsPage(driver);
    }

    public ManualBlockerPage clickCancelButton() {
        driver.findElement(CANCEL_BUTTON).click();
        return new ManualBlockerPage(driver);
    }
}
