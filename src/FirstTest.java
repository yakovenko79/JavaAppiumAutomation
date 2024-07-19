import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class FirstTest {
    private AppiumDriver driver;
    private String SEARCH = "Java";

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
        skipOnboarding();
        waitForElementAndClick(By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cant find search wikipedia input",
                5);
        waitForElementAndSendKeys(By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
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
        skipOnboarding();
        waitForElementAndClick(By.id("org.wikipedia:id/search_container"),
                "Cant find search input",
                5);
        waitForElementAndSendKeys(By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Java",
                "Cant find search input",
                5);
        waitForElementAndClear(By.id("org.wikipedia:id/search_src_text"),
                "Cant find search field",
                5);
//        waitForElementAndClick(By.id("org.wikipedia:id/search_close_btn"),
//                "Btn X is absent",
//                5);
        waitForElementNotPresent(By.id("org.wikipedia:id/search_close_btn"),
                "This is not a previous page",
                5);
    }

    @Test
    public void testCompareArticleTitle() {
        skipOnboarding();
        waitForElementAndClick(By.id("org.wikipedia:id/search_container"),
                "Cant find search input",
                5);
        waitForElementAndSendKeys(By.id("org.wikipedia:id/search_src_text"),
                "Java",
                "Cant find search input",
                5);
        waitForElementAndClick(By.xpath("//*[@text='Java (programming language)']"),
                "Cant find search input",
                5);
        WebElement title_element = waitForElementPresent(By.xpath("//android.widget.TextView[@text=\"Java (programming language)\"]"),
                "Cant find article title",
                15);
        String article_title = title_element.getAttribute("text");

        Assert.assertEquals("We see unexpected title",
                "Java (programming language)",
                article_title);
    }

    @Test
    public void testSearchFieldHasText() {
        skipOnboarding();
        assertElementHasText(By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Search Wikipedia",
                "Cant find this text",
                15
                );
    }

    @Test
    public void testSearchArticlesAndClearWord() {
        skipOnboarding();
        waitForElementAndClick(By.id("org.wikipedia:id/search_container"),
                "Cant find search input",
                5);
        waitForElementAndSendKeys(By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Java",
                "Cant find search input",
                5);
        waitForElementPresent(By.id("org.wikipedia:id/search_results_list"),
                "Cant find list of articles",
                10);
        assertElementsArePresent(By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_title']"),
                "Cant find articles",
                10);
        waitForElementAndClear(By.id("org.wikipedia:id/search_src_text"),
                "Cant find search field",
                5);
        waitForElementNotPresent(By.xpath("//*[contains(@text, 'Java')]"),
                "The ",
                5);
    }

    @Test
    public void testWordsInSearch() {
        skipOnboarding();
        waitForElementAndClick(By.id("org.wikipedia:id/search_container"),
                "Cant find search input",
                5);
        waitForElementAndSendKeys(By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Java",
                "Cant find search input",
                5);
        waitForElementPresent(By.id("org.wikipedia:id/search_results_list"),
                "Cant find list of articles",
                10);
        assertElementsContainsWord(By.id("org.wikipedia:id/search_results_list"),
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@resource-id='org.wikipedia:id/page_list_item_title']"),
                SEARCH,
                "Cant find articles",
                10);

    }

    @Test
    public void testSwipeArticle() {
        skipOnboarding();
        waitForElementAndClick(By.id("org.wikipedia:id/search_container"),
                "Cant find search input",
                5);
        waitForElementAndSendKeys(By.id("org.wikipedia:id/search_src_text"),
                "Appium",
                "Cant find search input",
                5);
        waitForElementAndClick(By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_title'][@text='Appium']"),
                "Cant find search 'Appium' in search",
                5);
        waitForElementPresent(By.xpath("//*[@text='Automation for Apps']"),
                "Cant find article title",
                15);
        swipeUpToFindElement(
                By.xpath("//*[@text='View article in browser']"),
                "Cant find the end of article",
                20);

    }

    @Test
    public void saveFirstArticleToMyList() {
        skipOnboarding();
        waitForElementAndClick(By.id("org.wikipedia:id/search_container"),
                "Cant find search input",
                5);
        waitForElementAndSendKeys(By.id("org.wikipedia:id/search_src_text"),
                "Java",
                "Cant find search input",
                5);
        waitForElementAndClick(By.xpath("//*[@text='Java (programming language)']"),
                "Cant find search input",
                5);
        waitForElementPresent(By.xpath("//android.widget.TextView[@text=\"Java (programming language)\"]"),
                "Cant find article title",
                15);
        waitForElementAndClick(By.id("org.wikipedia:id/page_save"),
                "Cant find Save button",
                5);
        waitForElementAndClick(By.id("org.wikipedia:id/snackbar_action"),
                "Catn find Add to list btn",
        3);
        String name_of_folder = "TestList";
        waitForElementAndSendKeys(By.id("org.wikipedia:id/text_input"),
                name_of_folder,
                "Cant find field for input name of list",
                3);
        waitForElementAndClick(By.id("android:id/button1"),
                "Cant find OK btn",
                3);
        waitForElementAndClick(By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                "Cant find left arrow btn",
                5);
        waitForElementAndClick(By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                "Cant find left arrow btn",
                5);
        waitForElementAndClick(By.id("org.wikipedia:id/nav_tab_reading_lists"),
                "Cant find Saved btn",
                5);
        waitForElementAndClick(By.xpath("//android.widget.TextView[@resource-id=\"org.wikipedia:id/item_title\" and @text='" + name_of_folder + "']"),
                "Cant find created list",
                5);
        swipeElementToLeft(By.xpath("//*[@text='Java (programming language)']"),
                "Cant find article in the list");
        waitForElementNotPresent(By.xpath("//*[@text='Java (programming language)']"),
                "Article is presented",
                3);

    }

    @Test
    public void testAmountOfNotEmptySearch() {
        skipOnboarding();
        waitForElementAndClick(By.id("org.wikipedia:id/search_container"),
                "Cant find search input",
                5);

        String search_line = "Linkin Park discography";
        waitForElementAndSendKeys(By.id("org.wikipedia:id/search_src_text"),
                search_line,
                "Cant find search input",
                5);

        String search_result_locator = "//*[@resource-id='org.wikipedia:id/search_results_list']/android.view.ViewGroup";
        waitForElementPresent(
                By.xpath(search_result_locator),
                "Cannot find anything by the request " + search_line,
                15);

        int amount_of_search_results = getAmountOfElements(
                By.xpath(search_result_locator));

        Assert.assertTrue(
                "We found too few results!",
                amount_of_search_results > 0);
    }

    @Test
    public void testAmountOfEmptySearch() {
        skipOnboarding();
        waitForElementAndClick(By.id("org.wikipedia:id/search_container"),
                "Cant find search input",
                5);

        String search_line = "xfdfxfxfxefxe";
        waitForElementAndSendKeys(By.id("org.wikipedia:id/search_src_text"),
                search_line,
                "Cant find search input",
                5);

        String search_result_locator = "//*[@resource-id='org.wikipedia:id/search_results_list']/android.view.ViewGroup";
        String empty_result_label = "//*[@text='No results']";
        waitForElementPresent(By.xpath(empty_result_label),
               "Cant find empty result label by the request" + search_line,
                15);

        assertElementNotPresent(
                By.xpath(search_result_locator),
                "We've found some results by request " + search_line);
    }

    @Test
    public void testChangeScreenOrientationOnSearchResults() {
        skipOnboarding();
        waitForElementAndClick(By.id("org.wikipedia:id/search_container"),
                "Cant find search input",
                5);

        String search_line = "Java";
        waitForElementAndSendKeys(By.id("org.wikipedia:id/search_src_text"),
                search_line,
                "Cant find search input",
                5);

        waitForElementAndClick(By.xpath("//*[@text='Java (programming language)']"),
                "Cant find " + search_line + " text",
                5);
        String tite_before_rotation = waitForElementAndGetAttribute(
                By.xpath("//*[@text=\"Java (programming language)\"]"),
                "text",
                "Cant find title of article",
                15);
        driver.rotate(ScreenOrientation.LANDSCAPE);
        String tite_after_rotation = waitForElementAndGetAttribute(
                By.xpath("//*[@text=\"Java (programming language)\"]"),
                "text",
                "Cant find title of article",
                15);
        driver.rotate(ScreenOrientation.LANDSCAPE);

        Assert.assertEquals(
                "Article title have been changed after rotation",
                tite_before_rotation,
                tite_after_rotation);

        driver.rotate(ScreenOrientation.PORTRAIT);
        String tite_after_second_rotation = waitForElementAndGetAttribute(
                By.xpath("//*[@text=\"Java (programming language)\"]"),
                "text",
                "Cant find title of article",
                15);
        Assert.assertEquals(
                "Article title have been changed after rotation",
                tite_before_rotation,
                tite_after_second_rotation);
    }

    @Test
    public void testCheckSearchArticleInBackground() {
        skipOnboarding();
        waitForElementAndClick(By.id("org.wikipedia:id/search_container"),
                "Cant find search input",
                5);
        waitForElementAndSendKeys(By.id("org.wikipedia:id/search_src_text"),
                "Java",
                "Cant find search input",
                5);
        waitForElementPresent(By.xpath("//*[@resource-id=\"org.wikipedia:id/page_list_item_title\"][@text='Java (programming language)']"),
                "Cant find search input",
                5);
        driver.runAppInBackground(2);
        waitForElementPresent(By.xpath("//*[@resource-id=\"org.wikipedia:id/list_of_lists\"]"),
                "Cant find article after returning from background",
                5);
    }

    @Test
    public void saveTwoArticlesToTheFolderAndRemoveOneOfThem() {
        skipOnboarding();
        waitForElementAndClick(By.id("org.wikipedia:id/search_container"),
                "Cant find search input",
                5);
        waitForElementAndSendKeys(By.id("org.wikipedia:id/search_src_text"),
                "Java",
                "Cant find search input",
                5);
        waitForElementAndClick(By.xpath("//*[@text='Java (programming language)']"),
                "Cant find search input",
                5);
        waitForElementPresent(By.xpath("//android.widget.TextView[@text=\"Java (programming language)\"]"),
                "Cant find article title",
                15);
        waitForElementAndClick(By.id("org.wikipedia:id/page_save"),
                "Cant find Save button",
                5);
        waitForElementAndClick(By.id("org.wikipedia:id/snackbar_action"),
                "Catn find Add to list btn",
                3);
        String name_of_folder = "TestList";
        waitForElementAndSendKeys(By.id("org.wikipedia:id/text_input"),
                name_of_folder,
                "Cant find field for input name of list",
                3);
        waitForElementAndClick(By.id("android:id/button1"),
                "Cant find OK btn",
                3);
        waitForElementAndClick(By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                "Cant find left arrow btn",
                5);
        waitForElementAndClick(By.xpath("//*[@resource-id=\"org.wikipedia:id/page_list_item_description\"][@text='Island in Indonesia']"),
                "Cant find article about Java Island",
                5);
        waitForElementPresent(By.xpath("//android.widget.TextView[@text=\"Island in Indonesia\"]"),
                "Cant find article description",
                15);
        waitForElementAndClick(By.id("org.wikipedia:id/page_save"),
                "Cant find Save btn",
                5);
        waitForElementAndClick(By.id("org.wikipedia:id/snackbar_action"),
                "Catn find Add to list btn",
                3);
        waitForElementAndClick(By.id("org.wikipedia:id/item_title_container"),
                "Cant find " + name_of_folder + " folder",
                5);
        waitForElementAndClick(By.id("org.wikipedia:id/snackbar_action"),
                "Catn find View list btn",
                3);

        String locator = "//*[@class='android.widget.FrameLayout']/*[@class='android.view.ViewGroup'][@resource-id='org.wikipedia:id/page_list_item_container']";
        String locator_article_about_java_programming_language = "//*[@text='Java (programming language)']";
        String locator_article_about_java_island_in_list = "//*[@text='Island in Indonesia']";
        int amount_of_expected_articles = 2;
        Assert.assertTrue("Amount of articles are not " + amount_of_expected_articles + " but equals to " + getAmountOfElements(By.xpath(locator)),
                getAmountOfElements(By.xpath(locator)) == amount_of_expected_articles);


        swipeElementToLeft(By.xpath(locator_article_about_java_programming_language),
                "Cant find this article");
        Assert.assertTrue("Amount of articles are not " + amount_of_expected_articles + " but equals to " + getAmountOfElements(By.xpath(locator)),
                getAmountOfElements(By.xpath(locator)) == amount_of_expected_articles - 1);
        waitForElementNotPresent(By.xpath(locator_article_about_java_programming_language),
                "Removed article is found",
                5);
        waitForElementPresent(By.xpath(locator_article_about_java_island_in_list),
                "Cant find article about Java island",
                5);
        String article_text_in_folder = getElementDescription(By.xpath(locator_article_about_java_island_in_list));
        waitForElementAndClick(By.xpath(locator_article_about_java_island_in_list),
                "Cant find article about Java island",
                3);
        String article_text_on_title = getElementDescription(By.xpath("//android.webkit.WebView[@text=\"Java\"]/android.view.View/android.view.View[1]/*[@text='Island in Indonesia']"));
        Assert.assertEquals("Text about Java island isnt equal", article_text_in_folder, article_text_on_title);
    }

    @Test
    public void openArticleWithoutDelay() {
        skipOnboarding();
        waitForElementAndClick(By.id("org.wikipedia:id/search_container"),
                "Cant find search input",
                5);
        waitForElementAndSendKeys(By.id("org.wikipedia:id/search_src_text"),
                "Java",
                "Cant find search input",
                5);
        waitForElementAndClick(By.xpath("//*[@text='Java (programming language)']"),
                "Cant find search input",
                5);
        assertElementPresent(By.xpath("//android.widget.TextView[@text=\"Java (programming language)\"]"),
                "Cant find the title of article");
    }






    public void skipOnboarding() {
        waitForElementAndClick(By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
        "Cant find skip button",
        10);
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

    private WebElement waitForElementAndSkip(By by, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.click();
        return element;
    }

    private void assertElementHasText(By by, String expectedText, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        String actualText = element.getAttribute("text");
        Assert.assertEquals(expectedText, actualText);
    }

    private void assertElementsArePresent(By by, String error_message, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        List<WebElement> elements = driver.findElements(by);
        System.out.println(elements);
        Assert.assertTrue(elements.size() > 1);
    }

    private void assertElementsContainsWord(By byLst, By byElm, String word, String error_message, long timeoutInSeconds) {
        WebElement elements = waitForElementPresent(byLst, error_message, timeoutInSeconds);
        for (WebElement element: elements.findElements(byElm)) {
            String text = element.getText();
            Assert.assertTrue("Article doesnt contain word", text.contains(word));
        }
    }

    protected void swipeUp(int timeOfSwipe) {
        TouchAction action = new TouchAction(driver);
        Dimension size = driver.manage().window().getSize();
        int x = size.width / 2;
        int start_y = (int) (size.height * 0.7);
        int end_y = (int) (size.height * 0.2);
        action
                .press(x, start_y)
                .waitAction(timeOfSwipe)
                .moveTo(x, end_y)
                .release()
                .perform();
    }

    protected void swipeUpQuick() {
        swipeUp(200);
    }

    protected void swipeUpToFindElement(By by, String error_message, int max_swipes) {
        int already_swiped = 0;
        while (driver.findElements(by).size() == 0) {
            if (already_swiped > max_swipes) {
                waitForElementPresent(by, "Cant find element by swiping up. \n" + error_message, 0);
                return;
            }
            swipeUpQuick();
            ++already_swiped;
        }
    }

    protected void swipeElementToLeft(By by, String error_message) {
        WebElement element = waitForElementPresent(
                by,
                error_message,
                10);
        int left_x = element.getLocation().getX();
        int right_x = element.getSize().getWidth();
        int upper_y = element.getLocation().getY();
        int lower_y = upper_y + element.getSize().getHeight();
        int middle_y = (upper_y + lower_y) / 2;

        TouchAction action = new TouchAction(driver);
        action
                .press(right_x, middle_y)
                .waitAction(350)
                .moveTo(left_x, middle_y)
                .release()
                .perform();
    }

    private int getAmountOfElements(By by) {
        List elements = driver.findElements(by);
        return elements.size();
    }


    private void assertElementNotPresent(By by, String error_message) {
        int amount_of_elements = getAmountOfElements(by);
        if (amount_of_elements > 1) {
            String default_message = "An element '" + by.toString() + "' supposed to be not present";
            throw new AssertionError(default_message + " " + error_message);
        }
    }

    private String waitForElementAndGetAttribute(By by, String attribute, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        return element.getAttribute(attribute);
    }

    private String getElementDescription(By by) {
        WebElement element = driver.findElement(by);
        String element_text = element.getText();
        return element_text;
    }

    private WebElement assertElementPresent(By by, String error_message) {
        return waitForElementPresent(by, error_message, 0);
    }
}
