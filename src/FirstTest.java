import lib.CoreTestCase;

import lib.ui.MainPageObject;
import lib.ui.SearchPageObject;
import org.junit.Assert;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebElement;

public class FirstTest extends CoreTestCase {
    private MainPageObject MainPageObject;
    protected void setUp() throws Exception {
        super.setUp();
        MainPageObject = new MainPageObject(driver);
    }
    private String SEARCH = "Java";

    @Test
    public void testSearch() {
        MainPageObject.skipOnboarding();
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSeachInput();
        SearchPageObject.typeSearchLine(SEARCH);
        SearchPageObject.waitForSearchResult("Object-oriented programming language");

    }

    @Test
    public void testCancelSearch() {
        MainPageObject.skipOnboarding();
        MainPageObject.waitForElementAndClick(By.id("org.wikipedia:id/search_container"),
                "Cant find search input",
                5);
        MainPageObject.waitForElementAndSendKeys(By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Java",
                "Cant find search input",
                5);
        MainPageObject.waitForElementAndClear(By.id("org.wikipedia:id/search_src_text"),
                "Cant find search field",
                5);
        MainPageObject.waitForElementNotPresent(By.id("org.wikipedia:id/search_close_btn"),
                "This is not a previous page",
                5);
    }

    @Test
    public void testCompareArticleTitle() {
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
        WebElement title_element = MainPageObject.waitForElementPresent(By.xpath("//android.widget.TextView[@text=\"Java (programming language)\"]"),
                "Cant find article title",
                15);
        String article_title = title_element.getAttribute("text");

        Assert.assertEquals("We see unexpected title",
                "Java (programming language)",
                article_title);
    }

    @Test
    public void testSearchFieldHasText() {
        MainPageObject.skipOnboarding();
        MainPageObject.assertElementHasText(By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Search Wikipedia",
                "Cant find this text",
                15
                );
    }

    @Test
    public void testSearchArticlesAndClearWord() {
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
    public void testSwipeArticle() {
        MainPageObject.skipOnboarding();
        MainPageObject.waitForElementAndClick(By.id("org.wikipedia:id/search_container"),
                "Cant find search input",
                5);
        MainPageObject.waitForElementAndSendKeys(By.id("org.wikipedia:id/search_src_text"),
                "Appium",
                "Cant find search input",
                5);
        MainPageObject.waitForElementAndClick(By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_title'][@text='Appium']"),
                "Cant find search 'Appium' in search",
                5);
        MainPageObject.waitForElementPresent(By.xpath("//*[@text='Automation for Apps']"),
                "Cant find article title",
                15);
        MainPageObject.swipeUpToFindElement(
                By.xpath("//*[@text='View article in browser']"),
                "Cant find the end of article",
                20);

    }

    @Test
    public void testSaveFirstArticleToMyList() {
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
        MainPageObject.waitForElementAndClick(By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                "Cant find left arrow btn",
                5);
        MainPageObject.waitForElementAndClick(By.id("org.wikipedia:id/nav_tab_reading_lists"),
                "Cant find Saved btn",
                5);
        MainPageObject.waitForElementAndClick(By.xpath("//android.widget.TextView[@resource-id=\"org.wikipedia:id/item_title\" and @text='" + name_of_folder + "']"),
                "Cant find created list",
                5);
        MainPageObject.swipeElementToLeft(By.xpath("//*[@text='Java (programming language)']"),
                "Cant find article in the list");
        MainPageObject.waitForElementNotPresent(By.xpath("//*[@text='Java (programming language)']"),
                "Article is presented",
                3);

    }

    @Test
    public void testAmountOfNotEmptySearch() {
        MainPageObject.skipOnboarding();
        MainPageObject.waitForElementAndClick(By.id("org.wikipedia:id/search_container"),
                "Cant find search input",
                5);

        String search_line = "Linkin Park discography";
        MainPageObject.waitForElementAndSendKeys(By.id("org.wikipedia:id/search_src_text"),
                search_line,
                "Cant find search input",
                5);

        String search_result_locator = "//*[@resource-id='org.wikipedia:id/search_results_list']/android.view.ViewGroup";
        MainPageObject.waitForElementPresent(
                By.xpath(search_result_locator),
                "Cannot find anything by the request " + search_line,
                15);

        int amount_of_search_results = MainPageObject.getAmountOfElements(
                By.xpath(search_result_locator));

        Assert.assertTrue(
                "We found too few results!",
                amount_of_search_results > 0);
    }

    @Test
    public void testAmountOfEmptySearch() {
        MainPageObject.skipOnboarding();
        MainPageObject.waitForElementAndClick(By.id("org.wikipedia:id/search_container"),
                "Cant find search input",
                5);

        String search_line = "xfdfxfxfxefxe";
        MainPageObject.waitForElementAndSendKeys(By.id("org.wikipedia:id/search_src_text"),
                search_line,
                "Cant find search input",
                5);

        String search_result_locator = "//*[@resource-id='org.wikipedia:id/search_results_list']/android.view.ViewGroup";
        String empty_result_label = "//*[@text='No results']";
        MainPageObject.waitForElementPresent(By.xpath(empty_result_label),
               "Cant find empty result label by the request" + search_line,
                15);

        MainPageObject.assertElementNotPresent(
                By.xpath(search_result_locator),
                "We've found some results by request " + search_line);
    }

    @Test
    public void testChangeScreenOrientationOnSearchResults() {
        MainPageObject.skipOnboarding();
        MainPageObject.waitForElementAndClick(By.id("org.wikipedia:id/search_container"),
                "Cant find search input",
                5);

        String search_line = "Java";
        MainPageObject.waitForElementAndSendKeys(By.id("org.wikipedia:id/search_src_text"),
                search_line,
                "Cant find search input",
                5);

        MainPageObject.waitForElementAndClick(By.xpath("//*[@text='Java (programming language)']"),
                "Cant find " + search_line + " text",
                5);
        String tite_before_rotation = MainPageObject.waitForElementAndGetAttribute(
                By.xpath("//*[@text=\"Java (programming language)\"]"),
                "text",
                "Cant find title of article",
                15);
        try {
            driver.rotate(ScreenOrientation.LANDSCAPE);
            String tite_after_rotation = MainPageObject.waitForElementAndGetAttribute(
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
            String tite_after_second_rotation = MainPageObject.waitForElementAndGetAttribute(
                    By.xpath("//*[@text=\"Java (programming language)\"]"),
                    "text",
                    "Cant find title of article",
                    15);
            Assert.assertEquals(
                    "Article title have been changed after rotation",
                    tite_before_rotation,
                    tite_after_second_rotation);
        } finally {
            driver.rotate(ScreenOrientation.PORTRAIT);
        }
    }

    @Test
    public void testCheckSearchArticleInBackground() {
        MainPageObject.skipOnboarding();
        MainPageObject.waitForElementAndClick(By.id("org.wikipedia:id/search_container"),
                "Cant find search input",
                5);
        MainPageObject.waitForElementAndSendKeys(By.id("org.wikipedia:id/search_src_text"),
                "Java",
                "Cant find search input",
                5);
        MainPageObject.waitForElementPresent(By.xpath("//*[@resource-id=\"org.wikipedia:id/page_list_item_title\"][@text='Java (programming language)']"),
                "Cant find search input",
                5);
        driver.runAppInBackground(2);
        MainPageObject.waitForElementPresent(By.xpath("//*[@resource-id=\"org.wikipedia:id/list_of_lists\"]"),
                "Cant find article after returning from background",
                5);
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
        Assert.assertTrue("Amount of articles are not " + amount_of_expected_articles + " but equals to " + MainPageObject.getAmountOfElements(By.xpath(locator)),
                MainPageObject.getAmountOfElements(By.xpath(locator)) == amount_of_expected_articles);


        MainPageObject.swipeElementToLeft(By.xpath(locator_article_about_java_programming_language),
                "Cant find this article");
        Assert.assertTrue("Amount of articles are not " + amount_of_expected_articles + " but equals to " + MainPageObject.getAmountOfElements(By.xpath(locator)),
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
        Assert.assertEquals("Text about Java island isnt equal", article_text_in_folder, article_text_on_title);
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
