package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class SearchResultsPage {

    private static final By SEARCH_RESULTS_TABLE = By.cssSelector("div > div:nth-child(2) > div.iZUfdKCcCIFrCZRMoEi3aQ\\=\\= > div > table > tbody");
    private static final By PRODUCT_ID = By.xpath("(//p[contains(text(), 'Product ID:')]//span[contains(@class, '_73AZDR8zhd9GTrDghbn6PQ==')])");
    private static final By PRODUCT_TITLE = By.xpath("(//div/p/a)");
    private static final By PRODUCT_IMAGE_URL = By.xpath("(//div/div/img)");


    private final WebDriver driver;

    public SearchResultsPage(final WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public SearchResultsPage waitForPageToBeLoaded() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        return this;
    }

    public SearchResultsPage verifySearchResults() {
        assertThat(driver.findElement(SEARCH_RESULTS_TABLE).isDisplayed());
        return this;
    }


    public SearchResultsPage verifyProductIdList(String productId) {

        List<WebElement> productIdElements = driver.findElements(PRODUCT_ID);
        List<String> productIdList = new ArrayList<>();

        for (WebElement element : productIdElements) {
            String productText = element.getText();
            productIdList.add(productText);
        }

        System.out.println("Product IDs found: " + productIdList);
        assertThat(productIdList).as("List of product IDs contains a proper value").contains(productId);
        return this;
    }

    public SearchResultsPage verifyProductIdList(List<String> productIds) {

        List<WebElement> productIdElements = driver.findElements(PRODUCT_ID);
        List<String> productIdList = new ArrayList<>();

        for (WebElement element : productIdElements) {
            String productText = element.getText();
            productIdList.add(productText);
        }

        System.out.println("Product IDs found: " + productIdList);
        assertThat(productIdList).as("List of product IDs contains a proper value").containsAll(productIds);
        return this;
    }


    public SearchResultsPage verifyProductTitleList(String productTitle) {

        List<WebElement> productTitleElements = driver.findElements(PRODUCT_TITLE);


        for (WebElement element : productTitleElements) {
            String productText = element.getText();
            System.out.println("Product titles found: " + productText);
            assertThat(productText).contains(productTitle);
        }
        return this;

    }    public SearchResultsPage verifyProductImageUrlList(String productImageUrl) {

        List<WebElement> productTitleElements = driver.findElements(PRODUCT_IMAGE_URL);


        for (WebElement element : productTitleElements) {
            String imageUrl = element.getAttribute("src");
            System.out.println("Product titles found: " + imageUrl);
            assertThat(imageUrl).isEqualTo(productImageUrl);
        }
        return this;

    }

}
