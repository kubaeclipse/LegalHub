package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FiltersPage {

    Logger logger = LogManager.getRootLogger();

    private static final By APPLY_BUTTON = By.xpath("//button[span[text()=\"Apply\"]]");
    private static final By BRANDS_FIELD = By.id("brands");
    private static final By CATEGORY_PATH_FIELD = By.id("categoryPath");
    private static final By CATEGORY_TREE = By.className("gJFRVMVYSMBQ8I-lrzTryQ==");
    private static final By CLOSE_BUTTON = By.cssSelector(".Button_button__wi7ei.Button_outline__wi7ei.Button_secondary__wi7ei Dialog_button__1md2z");
    private static final By COUNTRIES_FIELD = By.className("SearchInput_input__v67os");
    private static final By CREATION_TIME_END = By.xpath("(//input[@class='BaseDatePicker_input__322rz react-datepicker-ignore-onclickoutside'])[2]");
    private static final By CREATION_TIME_START = By.xpath("(//input[@class='BaseDatePicker_input__322rz react-datepicker-ignore-onclickoutside'])[1]");
    private static final By EMPTY_SPACE = By.cssSelector("[class=Modal_backdrop__gcq5h]");
    private static final By FILTERS_HEADER = By.xpath("//h5[@class='Dialog_title__1md2z' and contains(text(),'Manual Search Filters')]");
    private static final By MARKETPLACE_COUNTRIES_CLEAR_BUTTON = By.cssSelector("button.SearchInput_clear-icon__v67os");
    private static final By MARKETPLACE_COUNTRIES_DROPDOWN = By.xpath("//div[contains(@class, 'MultiSelect_select__')]//p[contains(text(), 'Select Marketplace Countries')]");
    private static final By MONTH_DROPDOWN = By.cssSelector(".CustomHeader_select__hkex8:nth-of-type(1)");
    private static final By RESET_BUTTON = By.cssSelector(".Button_button__wi7ei.Button_solid__wi7ei.Button_danger__wi7ei Dialog_button__1md2z");
    private static final By RETAILER_COUNTRIES_CLEAR_BUTTON = By.cssSelector("button.SearchInput_clear-icon__v67os");
    private static final By RETAILER_COUNTRIES_DROPDOWN = By.xpath("//div[contains(@class, 'MultiSelect_select__')]//p[contains(text(), 'Select Retailer Countries')]");
    private static final By RETAILER_FIELD = By.id("Retailer name or ID");
    private static final By YEAR_DROPDOWN = By.cssSelector(".CustomHeader_select__hkex8:nth-of-type(2)");

    private final WebDriver driver;

    public FiltersPage(final WebDriver driver) {
        this.driver = driver;
    }

    public FiltersPage waitForPageToBeLoaded() {
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(EMPTY_SPACE));
        return this;
    }

    public FiltersPage verifyFilterLabels() {

        WebElement filterElement = new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions
                .visibilityOfElementLocated(By.className("bB1ki0mb9WKVCcDQc63JSA==")));

        String filtersText = filterElement.getText();
        List<String> filterLabels = Arrays.asList(filtersText.split("\\n")); // Splits text based on a new line


        List<String> expectedLabels = Arrays.asList(
                "Retailer Name or ID",
                "Brands",
                "Marketplace Countries",
                "Retailer Countries",
                "Creation Time",
                "Category Path"
        );

        assertThat(filterLabels)
                .as("Filters should match expected labels")
                .containsAll(expectedLabels);

        return this;
    }

    public FiltersPage selectRetailerFilter(String retailerName) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        int retries = 3;

        for (int attempt = 1; attempt <= retries; attempt++) {
            try {
                WebElement retailerField = driver.findElement(RETAILER_FIELD);
                retailerField.clear();
                retailerField.sendKeys(retailerName);

                wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//ul[text()='Loading...']")));

                List<WebElement> options = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("SelectMenu_options__lzvya")));

                Optional<WebElement> matchingOption = options.stream()
                        .filter(option -> option.getText().trim().startsWith(retailerName))
                        .findFirst();

                if (matchingOption.isPresent()) {
                    WebElement option = matchingOption.get();
                    wait.until(ExpectedConditions.elementToBeClickable(option)).click();
                    return this;
                } else {
                    throw new NoSuchElementException("Retailer '" + retailerName + "' not found.");
                }
            } catch (NoSuchElementException | TimeoutException e) {
                logger.info("Attempt " + attempt + " failed. Retrying...");
                if (attempt == retries) {
                    throw new RuntimeException("Failed to select retailer after " + retries + " attempts", e);
                }
            }
        }
        return this;
    }

    public FiltersPage selectBrandFilter(String brandName) {
        WebElement brandField = driver.findElement(BRANDS_FIELD);
        brandField.clear();
        brandField.click();
        brandField.sendKeys(brandName);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//ul[text()='Loading...']")));

        int index = 1;

        while (true) {
            try {
                WebElement option = wait.until(ExpectedConditions.presenceOfElementLocated(
                        By.xpath("(//ul[@class='SelectMenu_options__lzvya']//li)[" + index + "]")
                ));

                String optionText = option.getText();
                logger.info("Checking: " + optionText);

                if (optionText.equals(brandName)) {
                    option.click();
                    break;
                }

                index++;

            } catch (TimeoutException e) {
                break;
            }
        }

        return this;
    }

    public FiltersPage selectMarketplaceCountriesFromList(List<String> countries) {
        WebElement countriesDropdown = driver.findElement(MARKETPLACE_COUNTRIES_DROPDOWN);

        countriesDropdown.click();

        for (String country : countries) {
            driver.findElement(By.xpath("//div[@role='listbox']//div[@class='OptionRow_option__le3cf' and @value='" + country + "']")).click();
        }
        WebElement emptySpace = driver.findElement(EMPTY_SPACE);
        emptySpace.click();

        return this;
    }

    private FiltersPage selectCountriesByEnteringName(List<String> countries, By dropdownLocator, By clearButtonLocator) {
        driver.findElement(dropdownLocator).click();
        for (String country : countries) {
            WebElement countriesField = driver.findElement(COUNTRIES_FIELD);
            countriesField.sendKeys(country);
            driver.findElement(By.xpath("//div[@role='listbox']//div[contains(@class, 'OptionRow_option__le3cf')]//span[text()='" + country + "']")).click();
            driver.findElement(clearButtonLocator).click();
        }
        driver.findElement(EMPTY_SPACE).click();
        return this;
    }

    public FiltersPage selectMarketplaceCountriesByEnteringName(List<String> countries) {
        return selectCountriesByEnteringName(countries, MARKETPLACE_COUNTRIES_DROPDOWN, MARKETPLACE_COUNTRIES_CLEAR_BUTTON);
    }

    public FiltersPage selectRetailerCountriesByEnteringName(List<String> countries) {
        return selectCountriesByEnteringName(countries, RETAILER_COUNTRIES_DROPDOWN, RETAILER_COUNTRIES_CLEAR_BUTTON);
    }

    public FiltersPage selectRetailerCountriesFromList(List<String> countries) {
        WebElement retailerCountries = driver.findElement(RETAILER_COUNTRIES_DROPDOWN);
        WebElement emptySpace = driver.findElement(EMPTY_SPACE);

        retailerCountries.click();

        for (String country : countries) {
            driver.findElement(By.xpath("//div[@role='listbox']//div[@class='OptionRow_option__le3cf' and @value='" + country + "']")).click();
        }
        emptySpace.click();

        return this;
    }

    public FiltersPage selectCreationTime(String day, String month, String year) {
        driver.findElement(CREATION_TIME_START).click();
        new Select(driver.findElement(MONTH_DROPDOWN)).selectByValue(month);
        new Select(driver.findElement(YEAR_DROPDOWN)).selectByValue(year);
        return this;
    }

    public FiltersPage enterCategoryPath(String categoryPath) {
        WebElement categoryField = driver.findElement(CATEGORY_PATH_FIELD);
        categoryField.clear();
        categoryField.click();
        categoryField.sendKeys(categoryPath);

        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(CATEGORY_TREE));

        return this;
    }

    public FiltersPage selectSubCategory(String subcategory) {
        List<WebElement> suggestions = driver.findElements(By.xpath("//div[@class='_8MaHoyZq6ZjR6D-elV2RiA==']//button"));
        for (WebElement suggestion : suggestions) {
            logger.info(suggestion.getText());
            if (suggestion.getText().equals(subcategory)) {
                logger.info("Selected category: " + suggestion.getText());
                suggestion.click();
                break;
            }
        }
        WebElement emptySpace = driver.findElement(EMPTY_SPACE);
        emptySpace.click();

        return this;
    }


    public FiltersPage verifyCategoryPath(String categoryPath) {
        WebElement categoryElement = driver.findElement(By.className("lgEytPD9hW430jloEMg82A=="));

        Actions actions = new Actions(driver);
        actions.moveToElement(categoryElement).perform();

        WebElement tooltip = new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(By.className("Tooltip_tooltip__z2r95")));

        String tooltipText = tooltip.getText();
        logger.info(tooltipText);

        assertEquals(categoryPath, tooltipText);

        return this;
    }


    public SearchResultsPage clickApplyButton() {
        driver.findElement(APPLY_BUTTON).click();
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.invisibilityOfElementLocated(FILTERS_HEADER));
        return new SearchResultsPage(driver);
    }

    public SearchResultsPage clickResetButton() {
        driver.findElement(RESET_BUTTON).click();
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.invisibilityOfElementLocated(FILTERS_HEADER));
        return new SearchResultsPage(driver);
    }

    public SearchResultsPage clickCloseButton() {
        driver.findElement(CLOSE_BUTTON).click();
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.invisibilityOfElementLocated(FILTERS_HEADER));
        return new SearchResultsPage(driver);
    }


}
