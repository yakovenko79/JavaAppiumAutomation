import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.net.URL;

public class FirstTest {
    private AppiumDriver driver;

    @Before
    public void setUp() throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "AndroidTestDevice");
        capabilities.setCapability("platformVersion", "8.0");
        capabilities.setCapability("automationName", "Appium");
        capabilities.setCapability("appPackage", "org.wikipedia");
        capabilities.setCapability("appActivity", ".main.MainActivity");
        capabilities.setCapability("app", "C:\\Users\\MIKA\\Desktop\\JavaAppiumAutomation\\JavaAppiumAutomation\\apks\\org.wikipedia.apk");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
    }
    @After
    public void tearDown() {
        driver.quit();
    }
    @Test
    public void firstTest() {
        waitForElementAndClick(By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cant find search wikipedia input",
                5);
        waitForElementAndSendKeys(By.xpath("//*[contains(@text, 'Search…')]"),
                "Java",
                "Cant find search input",
                5);
        waitForElementPresent(By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
                "Cannot find topic about java",
                15
                );
    }

    @Test
    public void testCancelSearch() {
        waitForElementAndClick(By.id("org.wikipedia:id/search_container"),
                "Cant find search input",
                5);
        waitForElementAndSendKeys(By.xpath("//*[contains(@text, 'Search…')]"),
                "Java",
                "Cant find search input",
                5);
        waitForElementAndClear(By.id("org.wikipedia:id/search_src_text"),
                "Cant find search field",
                5);
        waitForElementAndClick(By.id("org.wikipedia:id/search_close_btn"),
                "Btn X is absent",
                5);
        waitForElementNotPresent(By.id("org.wikipedia:id/search_close_btn"),
                "This is not a previous page",
                5);
    }

    @Test
    public void testCompareArticleTitle() {
        waitForElementAndClick(By.id("org.wikipedia:id/search_container"),
                "Cant find search input",
                5);
        waitForElementAndSendKeys(By.xpath("//*[contains(@text, 'Search…')]"),
                "Java",
                "Cant find search input",
                5);
        waitForElementAndClick(By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
                "Cant find search input",
                5);
        WebElement title_element = waitForElementPresent(By.id("org.wikipedia:id/view_page_title_text"),
                "Cant find article title",
                15);
        String article_title = title_element.getAttribute("text");

        Assert.assertEquals("We see unexpected title",
                "Java (programming language)",
                article_title);
    }

    @Test
    public void testSearchFieldHasText() {
        assertElementHasText(By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Search Wikipedia",
                "Cant find this text",
                15
                );
    }

    private WebElement waitForElementPresent(By by, String error_message, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    private WebElement waitForElementPresent(By by, String error_message) {
        return waitForElementPresent(by, error_message, 5);
    }

    private WebElement waitForElementAndClick(By by, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.click();
        return element;
    }

    private WebElement waitForElementAndSendKeys(By by, String value, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.sendKeys(value);
        return element;
    }

    private boolean waitForElementNotPresent(By by, String error_message, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
    }

    private WebElement waitForElementAndClear(By by, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.clear();
        return element;
    }

    private void assertElementHasText(By by, String expectedText, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        String actualText = element.getAttribute("text");
        Assert.assertEquals(expectedText, actualText);
    }
}
