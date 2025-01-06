package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


public class SearchModalPage {

    private static final By BLOCK_MODAL = By.cssSelector("div.Modal_content__1bth6");
    private static final By FIND_PRODUCTS = By.xpath("//button[span[text()='Find Products']]");
    private static final By SEARCH_PRODUCTS = By.xpath("//div[@class='Dialog_body__1md2z']//button[span[text()='Search Products']]");
    private static final By CANCEL = By.xpath("//button[span[text()='Cancel']]");
    private static final By PRODUCT_FIELD = By.id("textArea");
    private static final By SEARCH_TERM_FIELD = By.id("query");
    private static final By EXACT_MATCH_CHECKBOX = By.className("CustomCheckbox_checkbox__11ho3");

    private final WebDriver driver;

    public SearchModalPage(final WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    public SearchModalPage waitForPageToBeLoaded() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        return this;
    }

    public SearchModalPage verifySearchModalElements() {
        assertThat(driver.findElement(BLOCK_MODAL).isDisplayed()).as("Modal is displayed").isTrue();
        assertThat(driver.findElement(FIND_PRODUCTS).isDisplayed()).as("Find Products button is displayed").isTrue();
        assertThat(driver.findElement(SEARCH_PRODUCTS).isDisplayed()).as("Search Products button is displayed").isTrue();
        assertThat(driver.findElement(CANCEL).isDisplayed()).as("Cancel button is displayed").isTrue();
        assertThat(driver.findElement(PRODUCT_FIELD).isDisplayed()).as("Product field is displayed").isTrue();

        return this;
    }

    public SearchModalPage enterProductId(final String productId) {
        driver.findElement(PRODUCT_FIELD).sendKeys(productId);
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

    public SearchResultsPage clickSearchProductsButton() {
        driver.findElement(SEARCH_PRODUCTS).click();
        return new SearchResultsPage(driver);
    }

    public SearchResultsPage clickFindProductsButton() {
        driver.findElement(FIND_PRODUCTS).click();
        return new SearchResultsPage(driver);
    }

    public ManualBlockerPage clickCancelButton() {
        driver.findElement(CANCEL).click();
        return new ManualBlockerPage(driver);
    }


}
