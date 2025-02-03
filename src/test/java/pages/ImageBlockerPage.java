package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class ImageBlockerPage {
    private static final By LOGO = By.className("Header_logo__17nja");
    private static final By IMAGE_BLOCKER_TITLE = By.xpath("//div[p[text()='Image Blocker']]");
    private static final By UPLOAD_IMAGE_BUTTON = By.xpath("//button//span[text()='Upload Image']");
    private static final By VIEW_FILTERS_BUTTON = By.xpath("//button//span[text()='View Filters']");
    private static final By IMAGE_TABLE_HEADERS = By.xpath("//table/thead/tr/th");
    private static final By IMAGE_PICTURE = By.cssSelector("img[alt='Woman cataloguing pictures.']");
    private final WebDriver driver;

    public ImageBlockerPage(final WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public ImageBlockerPage verifyLogo(){
        WebElement logo = driver.findElement(LOGO);
        assertThat(logo.isDisplayed()).as("Logo is displayed").isTrue();
        return this;
    }

    public ImageBlockerPage verifyImageBlockerTitle() {
        WebElement title = driver.findElement(IMAGE_BLOCKER_TITLE);
        assertThat(title.isDisplayed()).as("Image blocker title is displayed").isTrue();
        return this;
    }

    public ImageBlockerPage clickUploadImageButton() {
        driver.findElement(UPLOAD_IMAGE_BUTTON).click();
        return this;
    }

    public ImageBlockerPage verifyImagePicture() {
        WebElement picture = driver.findElement(IMAGE_PICTURE);
        assertThat(picture.isDisplayed()).as("Image picture is displayed").isTrue();
        return this;
    }

    public ImageBlockerPage verifyUploadButton() {
        WebElement uploadButton = driver.findElement(UPLOAD_IMAGE_BUTTON);
        assertThat(uploadButton.isDisplayed()).as("Upload button is displayed").isTrue();
        return this;
    }

    public ImageBlockerPage verifyViewFiltersButton() {
        WebElement viewFiltersButton = driver.findElement(VIEW_FILTERS_BUTTON);
        assertThat(viewFiltersButton.isDisplayed()).as("View filters button is displayed").isTrue();
        return this;
    }

    public ImageBlockerPage verifyImageTableHeaders(final List<String> expectedHeaders) {
        List<WebElement> menuItems = driver.findElements(IMAGE_TABLE_HEADERS);
        assertThat(menuItems).as("Table header list is not empty").isNotEmpty();

        List<String> actualHeaders = menuItems.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());

        assertThat(actualHeaders).as("Table headers contain expected texts")
                .containsAll(expectedHeaders);
        return this;
    }
}
