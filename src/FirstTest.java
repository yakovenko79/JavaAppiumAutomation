import lib.CoreTestCase;

import lib.ui.*;

import org.junit.Test;
import org.openqa.selenium.By;


public class FirstTest extends CoreTestCase {
    private MainPageObject MainPageObject;
    protected void setUp() throws Exception {
        super.setUp();
        MainPageObject = new MainPageObject(driver);
    }
    private String SEARCH = "Java";
    private String APPIUM_SEARCH = "Appium";





    @Test
    public void testSearchFieldHasText() {
//        MainPageObject.skipOnboarding();
        MainPageObject.assertElementHasText(By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Search Wikipedia",
                "Cant find this text",
                15
                );
    }

    @Test
    public void testSearchArticlesAndClearWord() {
//        MainPageObject.skipOnboarding();
        MainPageObject.waitForElementAndClick(By.id("org.wikipedia:id/search_container"),
                "Cant find search input",
                5);
        MainPageObject.waitForElementAndSendKeys(By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Java",
                "Cant find search input",
                5);
        MainPageObject.waitForElementPresent(By.id("org.wikipedia:id/search_results_list"),
                "Cant find list of articles",
                10);
        MainPageObject.assertElementsArePresent(By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_title']"),
                "Cant find articles",
                10);
        MainPageObject.waitForElementAndClear(By.id("org.wikipedia:id/search_src_text"),
                "Cant find search field",
                5);
        MainPageObject.waitForElementNotPresent(By.xpath("//*[contains(@text, 'Java')]"),
                "The ",
                5);
    }

    @Test
    public void testWordsInSearch() {
        MainPageObject.skipOnboarding();
        MainPageObject.waitForElementAndClick(By.id("org.wikipedia:id/search_container"),
                "Cant find search input",
                5);
        MainPageObject.waitForElementAndSendKeys(By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Java",
                "Cant find search input",
                5);
        MainPageObject.waitForElementPresent(By.id("org.wikipedia:id/search_results_list"),
                "Cant find list of articles",
                10);
        MainPageObject.assertElementsContainsWord(By.id("org.wikipedia:id/search_results_list"),
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@resource-id='org.wikipedia:id/page_list_item_title']"),
                SEARCH,
                "Cant find articles",
                10);

    }






    @Test
    public void testSaveTwoArticlesToTheFolderAndRemoveOneOfThem() {
        MainPageObject.skipOnboarding();
        MainPageObject.waitForElementAndClick(By.id("org.wikipedia:id/search_container"),
                "Cant find search input",
                5);
        MainPageObject.waitForElementAndSendKeys(By.id("org.wikipedia:id/search_src_text"),
                "Java",
                "Cant find search input",
                5);
        MainPageObject.waitForElementAndClick(By.xpath("//*[@text='Java (programming language)']"),
                "Cant find search input",
                5);
        MainPageObject.waitForElementPresent(By.xpath("//android.widget.TextView[@text=\"Java (programming language)\"]"),
                "Cant find article title",
                15);
        MainPageObject.waitForElementAndClick(By.id("org.wikipedia:id/page_save"),
                "Cant find Save button",
                5);
        MainPageObject.waitForElementAndClick(By.id("org.wikipedia:id/snackbar_action"),
                "Catn find Add to list btn",
                3);
        String name_of_folder = "TestList";
        MainPageObject.waitForElementAndSendKeys(By.id("org.wikipedia:id/text_input"),
                name_of_folder,
                "Cant find field for input name of list",
                3);
        MainPageObject.waitForElementAndClick(By.id("android:id/button1"),
                "Cant find OK btn",
                3);
        MainPageObject.waitForElementAndClick(By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                "Cant find left arrow btn",
                5);
        MainPageObject.waitForElementAndClick(By.xpath("//*[@resource-id=\"org.wikipedia:id/page_list_item_description\"][@text='Island in Indonesia']"),
                "Cant find article about Java Island",
                5);
        MainPageObject.waitForElementPresent(By.xpath("//android.widget.TextView[@text=\"Island in Indonesia\"]"),
                "Cant find article description",
                15);
        MainPageObject.waitForElementAndClick(By.id("org.wikipedia:id/page_save"),
                "Cant find Save btn",
                5);
        MainPageObject.waitForElementAndClick(By.id("org.wikipedia:id/snackbar_action"),
                "Catn find Add to list btn",
                3);
        MainPageObject.waitForElementAndClick(By.id("org.wikipedia:id/item_title_container"),
                "Cant find " + name_of_folder + " folder",
                5);
        MainPageObject.waitForElementAndClick(By.id("org.wikipedia:id/snackbar_action"),
                "Catn find View list btn",
                3);

        String locator = "//*[@class='android.widget.FrameLayout']/*[@class='android.view.ViewGroup'][@resource-id='org.wikipedia:id/page_list_item_container']";
        String locator_article_about_java_programming_language = "//*[@text='Java (programming language)']";
        String locator_article_about_java_island_in_list = "//*[@text='Island in Indonesia']";
        int amount_of_expected_articles = 2;
        assertTrue("Amount of articles are not " + amount_of_expected_articles + " but equals to " + MainPageObject.getAmountOfElements(By.xpath(locator)),
                MainPageObject.getAmountOfElements(By.xpath(locator)) == amount_of_expected_articles);


        MainPageObject.swipeElementToLeft(By.xpath(locator_article_about_java_programming_language),
                "Cant find this article");
        assertTrue("Amount of articles are not " + amount_of_expected_articles + " but equals to " + MainPageObject.getAmountOfElements(By.xpath(locator)),
                MainPageObject.getAmountOfElements(By.xpath(locator)) == amount_of_expected_articles - 1);
        MainPageObject.waitForElementNotPresent(By.xpath(locator_article_about_java_programming_language),
                "Removed article is found",
                5);
        MainPageObject.waitForElementPresent(By.xpath(locator_article_about_java_island_in_list),
                "Cant find article about Java island",
                5);
        String article_text_in_folder = MainPageObject.getElementDescription(By.xpath(locator_article_about_java_island_in_list));
        MainPageObject.waitForElementAndClick(By.xpath(locator_article_about_java_island_in_list),
                "Cant find article about Java island",
                3);
        String article_text_on_title = MainPageObject.getElementDescription(By.xpath("//android.webkit.WebView[@text=\"Java\"]/android.view.View/android.view.View[1]/*[@text='Island in Indonesia']"));
        assertEquals("Text about Java island isnt equal", article_text_in_folder, article_text_on_title);
    }

    @Test
    public void testOpenArticleWithoutDelay() {
        MainPageObject.skipOnboarding();
        MainPageObject.waitForElementAndClick(By.id("org.wikipedia:id/search_container"),
                "Cant find search input",
                5);
        MainPageObject.waitForElementAndSendKeys(By.id("org.wikipedia:id/search_src_text"),
                SEARCH,
                "Cant find search input",
                5);
        MainPageObject.waitForElementAndClick(By.xpath("//*[@text='Java (programming language)']"),
                "Cant find search input",
                5);
        MainPageObject.assertElementPresent(By.xpath("//android.widget.TextView[@text=\"Java (programming language)\"]"),
                "Cant find the title of article");
    }







}
