package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class SearchPageObject extends MainPageObject {
    private static final String
            SEARCH_INIT_ELEMENT = "//*[contains(@text, 'Search Wikipedia')]",
            SEARCH_INPUT = "//*[contains(@text, 'Search Wikipedia')]",
            SEARCH_RESULT_BY_SUBSTRING_TPL = "//*[@resource-id='org.wikipedia:id/page_list_item_description' and @text='{SUBSTRING}']",
            SEARCH_CANCEL_BUTTON = "org.wikipedia:id/search_close_btn",
            SEARCH_RESULT_ELEMENT = "//*[@resource-id='org.wikipedia:id/search_results_list']/android.view.ViewGroup",
            SEARCH_EMPTY_RESULT_ELEMENT = "//*[@text='No results']",
            LIST_OF_INPUT_SEARCH_RESULT = "org.wikipedia:id/search_results_list",
            LIST_OF_ITEMS_TITLE = "//*[@resource-id='org.wikipedia:id/page_list_item_title']",
            INPUTTED_SEARCH_ITEM = "org.wikipedia:id/search_src_text",
            NAME_INPUTTED_SEARCH_ITEM = "//*[contains(@text, 'Java')]",
            ARTICLES_IN_SEARCH_LIST = "//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@resource-id='org.wikipedia:id/page_list_item_title']",
            ARTICLE_TITLE_AND_DESCRIPTION_TPL = "//android.view.ViewGroup[android.widget.TextView[@resource-id='org.wikipedia:id/page_list_item_title' and @text=\"{TITLE}\"] and android.widget.TextView[@resource-id='org.wikipedia:id/page_list_item_description' and @text=\"{DESCRIPTION}\"]]";

    public SearchPageObject(AppiumDriver driver) {
        super(driver);
    }

    /* TEMPLATES METHODS */
    private static String getResultSearchElement(String substring) {
        return SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}", substring);
    }

    private static String getResultSearchElements(String title, String description) {
        String article = ARTICLE_TITLE_AND_DESCRIPTION_TPL.replace("{TITLE}", title);
        return article.replace("{DESCRIPTION}", description);

    }
    /*TEMPLATES METHODS*/

    public void initSeachInput() {
        this.waitForElementPresent(By.xpath(SEARCH_INIT_ELEMENT), "Cannot find search input after clicking search init element");
        this.waitForElementAndClick(By.xpath(SEARCH_INIT_ELEMENT), "Cannot find and click search init element", 5);
    }

    public void waitForCancelButtonToAppear() {
        this.waitForElementPresent(By.id(SEARCH_CANCEL_BUTTON), "Cant find search cancel btn", 5);
    }
    public void waitForCancelButtonToDisappear() {
        this.waitForElementNotPresent(By.id(SEARCH_CANCEL_BUTTON), "Search cancel btn is still present", 5);
    }
    public void clickCancelSearch() {
        this.waitForElementAndClick(By.id(SEARCH_CANCEL_BUTTON), "Cant find and click search cancel btn", 5);
    }

    public void typeSearchLine(String search_line) {
        this.waitForElementAndSendKeys(By.xpath(SEARCH_INPUT), search_line, "Cant find and type into search input", 5);
    }

    public void waitForSearchResult(String substring) {
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementPresent(By.xpath(search_result_xpath), "Cant find search result with substring " + substring);
    }
    public void clickByArticleWithSubstring(String substring) {
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementAndClick(By.xpath(search_result_xpath), "Cant find and click search result with substring " + substring, 10);
    }


    public int getAmountOfFoundArticles() {
        this.waitForElementPresent(
                By.xpath(SEARCH_RESULT_ELEMENT),
                "Cannot find anything by the request ",
                15);
        return this.getAmountOfElements(By.xpath(SEARCH_RESULT_ELEMENT));
    }

    public void waitForEmptyResultsLabel() {
        this.waitForElementPresent(By.xpath(SEARCH_EMPTY_RESULT_ELEMENT), "Cant find empty result element, 15");

    }

    public void assertThereIsNoResultOfSearch() {
        this.assertElementNotPresent(By.xpath(SEARCH_RESULT_ELEMENT), "We supposed not to find any results");
    }

    public void assertSearchFieldHasText() {
        this.assertElementHasText(By.xpath(SEARCH_INIT_ELEMENT),
                "Search Wikipedia",
                "Cant find init text",
                10);
    }

    public void assertSearchListOfItemsResults() {
        this.waitForElementPresent(By.id(LIST_OF_INPUT_SEARCH_RESULT),
                "Cant find list of input search result",
                10);
        this.assertElementsArePresent(By.xpath(LIST_OF_ITEMS_TITLE),
                "Cant find elements in list",
                5);
    }

    public void clearInputSearchItem() {
        this.waitForElementAndClear(By.id(INPUTTED_SEARCH_ITEM),
                "Cant find inputted search item",
                5);
    }

    public void waitForSearchFieldIsEmpty() {
        this.waitForElementNotPresent(By.xpath(NAME_INPUTTED_SEARCH_ITEM),
                "Search field is not empty",
                10);
    }

    public void assertSearchResultsContainInputWord() {
        this.assertElementsContainsWord(By.id(LIST_OF_INPUT_SEARCH_RESULT),
                By.xpath(ARTICLES_IN_SEARCH_LIST),
                "Java",
                "Cant find articles",
                10);
    }

    public void waitForElementByTitleAndDescription(String title, String description) {
        String finalLocator = getResultSearchElements(title, description);
        waitForElementPresent(By.xpath(finalLocator),
                "Статья с заголовком " + title + " и описанием " + description + " не найдена.",
                15);
    }
}
