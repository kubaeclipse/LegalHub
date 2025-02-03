package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SearchResultsPage {

    private static final By PRODUCT_ID_ELEMENTS = By.xpath("//td[2]//p[contains(text(), 'Product ID:')]/span");
    private static final By BRAND_NAMES = By.xpath("//td[2]//p[contains(text(), 'Brand:')]/span");
    private static final By RETAILER_NAMES = By.xpath("//td[3]//p[contains(text(), 'Name:')]/span");
    private static final By RETAILER_COUNTRIES = By.xpath("//td[3]//p[contains(text(), 'Country:')]/span");
    private static final By PRODUCT_TITLE = By.xpath("//div/p/a");
    private static final By PRODUCT_IMAGE_URL = By.xpath("//div/div/img");
    private static final By VIEW_FILTERS = By.xpath("//button[span[text()='View Filters']]");
    private static final By CHIP_NAMES = By.xpath("//span[@class='Chip_text__1as84']");
    private static final By SELECT_ALL_BUTTON = By.xpath("//div[contains(@class, '_8-1ZzNYLIEADGmz4Td1Swg==')]/button[1]");
    private static final By SELECTED_BUTTON = By.xpath("//div[contains(@class, '_8-1ZzNYLIEADGmz4Td1Swg==')]/button[2]");
    private static final By BLOCK_BUTTON = By.xpath("//div[contains(@class, '_8-1ZzNYLIEADGmz4Td1Swg==')]/button[3]");
    private static final By PRODUCT_CHECKBOXES = By.className("Da5hrQ9o0T+YFdjvcJFvdw==");
    private static final By PER_PAGE_DROPDOWN = By.id("perPage");
    private static final By ACTIVE_PAGE = By.xpath("//li[contains(@class, 'PageNumbers_active__1t8yb')]//button");
    private static final By NO_RESULTS_MESSAGE = By.className("Alert_message__8m4bi");


    private final WebDriver driver;

    public SearchResultsPage(final WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }





    public SearchResultsPage performSearchAndVerify(String searchTerm, String expectedTitle) {
        new MainPage(driver)
                .navigateToManualBlocker()
                .clickSearchProductsButton()
                .verifySearchModalElements()
                .enterSearchTerm(searchTerm)
                .selectExactMatchCheckbox()
                .findProductsBySearchTerm()
                .verifyProductTitleList(expectedTitle);

        return this;
    }

    public FiltersPage navigateToViewFilters() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement filtersButton = wait.until(ExpectedConditions.visibilityOfElementLocated(VIEW_FILTERS));
        filtersButton.click();
        return new FiltersPage(driver);
    }

    public SearchResultsPage verifyProductId(String productId) {

        List<WebElement> productIdElements = driver.findElements(PRODUCT_ID_ELEMENTS);
        List<String> productIdList = new ArrayList<>();

        for (WebElement element : productIdElements) {
            String productText = element.getText();
            productIdList.add(productText);
        }

        System.out.println("Product IDs found: " + productIdList);
        assertThat(productIdList).as("List of product IDs contains a proper value").contains(productId);
        return this;
    }

    public SearchResultsPage verifyBrandName(String brandName) {
        List<WebElement> brandNameElements = driver.findElements(BRAND_NAMES);
        List<String> brandNames = new ArrayList<>();

        for (WebElement element : brandNameElements) {
            String brandText = element.getText();
            brandNames.add(brandText);
        }
        if (brandNames.isEmpty()) {
            System.out.println("No brand name found");
            System.out.println(driver.findElement(NO_RESULTS_MESSAGE).getText());
        } else {
            System.out.println("Brand names found: " + brandNames);
            assertThat(brandNames).as("List of brand names contains a proper value").contains(brandName);
        }
        return this;
    }

    public SearchResultsPage verifyRetailerName(String retailerName){
        List<WebElement> retailerNameElements = driver.findElements(RETAILER_NAMES);
        List<String> retailerNames = new ArrayList<>();

        for (WebElement element : retailerNameElements) {
            String retailerText = element.getText();
            retailerNames.add(retailerText);
        }
        if (retailerNames.isEmpty()) {
            System.out.println("No retailer name found");
            System.out.println(driver.findElement(NO_RESULTS_MESSAGE).getText());
        } else {
            System.out.println("Retailer names found: " + retailerNames);
            assertThat(retailerNames).as("List of retailer names contains a proper value").contains(retailerName);
        }

        return this;
    }

    public SearchResultsPage readAndVerifyRetailerCountry(List<String> retailerList){
        List<WebElement> retailerCountryElements = driver.findElements(RETAILER_COUNTRIES);
        List<String> retailerCountries = new ArrayList<>();

        for (WebElement element : retailerCountryElements) {
            String retailerText = element.getText();
            retailerCountries.add(retailerText);
        }
        if (retailerCountries.isEmpty()) {
            System.out.println("No retailer country found");
            System.out.println(driver.findElement(NO_RESULTS_MESSAGE).getText());
        } else {
            System.out.println("Retailer countries found: " + retailerCountries);
            assertThat(retailerCountries).as("List of retailer countries contains a proper value").containsAll(retailerList);
        }

        return this;
    }

    public SearchResultsPage verifyProductIdList(List<String> productIds) {

        List<WebElement> productIdElements = driver.findElements(PRODUCT_ID_ELEMENTS);
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
            //System.out.println("Product titles found: " + productText);
            assertThat(productText).containsIgnoringCase(productTitle);
        }
        return this;

    }

    public SearchResultsPage verifyProductImageUrlList(String productImageUrl) {

        List<WebElement> productTitleElements = driver.findElements(PRODUCT_IMAGE_URL);


        for (WebElement element : productTitleElements) {
            String imageUrl = element.getAttribute("src");
            //System.out.println("Product titles found: " + imageUrl);
            assertThat(imageUrl).isEqualTo(productImageUrl);
        }
        return this;

    }


    public SearchResultsPage verifyChipName(String name) {
        List<WebElement> chipElements = driver.findElements(CHIP_NAMES);

        for (WebElement element : chipElements) {
            String chipText = element.getText();
            System.out.println("Chip names found: " + chipText);

            if (chipText.contains(name)) {
                System.out.println("Chip name found: " + chipText);
                break;
            } else {
                throw new RuntimeException("Option with name '" + name + "' not found.");
            }
        }
        return this;
    }

    public SearchResultsPage readAndVerifyMarketplaceCountryList(List<String> countryList) {
        WebElement arrow = driver.findElement(By.xpath("//span[text()='View All Countries ']"));

        Actions actions = new Actions(driver);
        actions.moveToElement(arrow).perform();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement tooltip = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.Tooltip_tooltip__1hkz0")));

        String tooltipText = tooltip.getText();
        String[] countries = tooltipText.split("\n");

        for (String country : countries) {
            System.out.println(country);
            assertTrue(Arrays.asList(countries).containsAll(countryList));
        }

        return this;
    }

    public SearchResultsPage readAndVerifyProductCategoryPath(String categoryPath) {

        for (int i = 1; i < driver.findElements(PRODUCT_CHECKBOXES).size(); i++) {
            WebElement element = driver.findElement(By.xpath("//div/table/tbody/tr[" + i + "]/td[6]/span"));
            System.out.println(element.getText());
            assertTrue(element.getText().contains(categoryPath));

        } return this;
    }

    public SearchResultsPage clickSelectAllButton() {
        WebElement button = driver.findElement(SELECT_ALL_BUTTON);
        assertTrue(button.getText().contains("Select All"));
        button.click();
        assertTrue(driver.findElement(SELECT_ALL_BUTTON).getText().contains("Clear All"));
        return this;
    }

    public SearchResultsPage clickClearAllButton() {
        WebElement button = driver.findElement(SELECT_ALL_BUTTON);
        assertTrue(button.getText().contains("Clear All"));
        button.click();
        assertTrue(driver.findElement(SELECT_ALL_BUTTON).getText().contains("Select All"));
        return this;
    }


    public int getNumberOfSelectedCheckboxes() {
        return (int) driver.findElements(PRODUCT_CHECKBOXES).stream()
                .filter(WebElement::isSelected)
                .count();
    }

    public SearchResultsPage verifyNumberOfSelectedCheckboxesEqualsButtonText() {
        int selectedCheckboxes = getNumberOfSelectedCheckboxes();
        System.out.println(selectedCheckboxes);
        String buttonText = driver.findElement(SELECTED_BUTTON).getText();
        int productsToSelect = Integer.parseInt(buttonText.split("/")[0].replaceAll("\\D", ""));
        System.out.println(productsToSelect);
        assertEquals(selectedCheckboxes, productsToSelect);
        return this;
    }


    public SearchResultsPage selectRandomCheckboxes(int numberOfCheckboxesToSelect) {
        List<WebElement> checkboxes = driver.findElements(PRODUCT_CHECKBOXES);
        Random random = new Random();
        for (int i = 0; i < numberOfCheckboxesToSelect; i++) {
            int randomIndex = random.nextInt(checkboxes.size());
            WebElement randomCheckbox = checkboxes.get(randomIndex);

            // Had to use JavaScript method as standard click method did not work
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", randomCheckbox);

        }
        return this;

    }

    public SearchResultsPage verifyPerPageElements() {
        Select select = new Select(driver.findElement(PER_PAGE_DROPDOWN));
        List<WebElement> options = select.getOptions();
        List<String> pageCount = new ArrayList<>();

        for (WebElement option : options) {
            String optionText = (option.getText());
            pageCount.add(optionText);
            System.out.println(option.getText());
        }

        List<String> expectedPageCount = List.of("50", "100", "500");

        System.out.println("Actual values: " + pageCount);
        System.out.println("Expected values: " + expectedPageCount);

        assertThat(expectedPageCount).isEqualTo(pageCount);
        return this;
    }

    public SearchResultsPage selectNumberOfElementsPerPage(String value) {
        Select select = new Select(driver.findElement(PER_PAGE_DROPDOWN));
        select.selectByValue(value);
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(PRODUCT_CHECKBOXES));
        return this;
    }

    public SearchResultsPage verifyNumberOfElementsOnPage(int expectedNumberOfElements) {
        int numberOfElements = driver.findElements(PRODUCT_CHECKBOXES).size();
        System.out.println("Number of elements on page: " + numberOfElements);
        assertEquals(expectedNumberOfElements, numberOfElements);
        return this;
    }

    public boolean isOnPage(int expectedPageNumber) {
        WebElement activePage = driver.findElement(ACTIVE_PAGE);
        return activePage.getText().equals(String.valueOf(expectedPageNumber));

    }

    public SearchResultsPage navigateToPageNumber(int pageNumber) {
        driver.findElement(By.xpath("//li/button[@aria-label='Go to page " + pageNumber + "']")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(PRODUCT_CHECKBOXES));
        assertTrue(isOnPage(pageNumber));

        return this;
    }

    public ManualBlockerPage clickBlockButton() {
        WebElement button = driver.findElement(BLOCK_BUTTON);
        assertTrue(button.isEnabled());
        button.click();
        return new ManualBlockerPage(driver);
    }

}
