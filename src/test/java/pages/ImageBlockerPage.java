package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class ImageBlockerPage {
    private static final By LOGO = By.className("Header_logo__17nja");
    private static final By IMAGE_BLOCKER_TITLE = By.xpath("//div[p[text()='Image Blocker']]");
    private static final By UPLOAD_IMAGE_BUTTON = By.xpath("//button[span[text()='Upload Image']]");
    private static final By IMAGE_TABLE_HEADERS = By.xpath("//table/thead/tr/th");
    private static final By IMAGE_PICTURE = By.cssSelector("img[alt='Woman cataloguing pictures.']");
    private final WebDriver driver;

    public ImageBlockerPage(final WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public ImageBlockerPage waitForPageToBeLoaded() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        return this;
    }

    public ImageBlockerPage verifyImageTableHeaders(final String menuItemText) {
        final var menuItems = driver.findElements(IMAGE_TABLE_HEADERS);
        assertThat(menuItems.size()).as("Table header list is not empty").isNotZero();
        final var menuItemsText = menuItems.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
        assertThat(menuItemsText).as("Table headers contain proper text").contains(menuItemText);
        return this;
    }
}
