package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.junit.Assert;
import org.openqa.selenium.By;

public class MyListsPageObject extends MainPageObject{
    private static final String
            FOLDER_BY_NAME_TPL = "//android.widget.TextView[@resource-id='org.wikipedia:id/item_title' and @text='{FOLDER_NAME}']",
            ARTICLE_BY_TITLE_TPL = "//*[@text='{TITLE}']",
            ARTICLES_LIST_IN_FOLDER = "//*[@class='android.widget.FrameLayout']/*[@class='android.view.ViewGroup'][@resource-id='org.wikipedia:id/page_list_item_container']",
            ARTICLE_JAVA_PROGRAMMING_LANGUAGE = "//*[@text='Java (programming language)']",
            ARTICLE_JAVA_ISLAND = "//*[@text='Island in Indonesia']",
            ARTICLE_JAVA_DESCRIPTION = "//android.webkit.WebView[@text=\"Java\"]/android.view.View/android.view.View[1]/*[@text='Island in Indonesia']",
            ARTICLE_JAVA_TITLE = "org.wikipedia:id/page_list_item_title",
            ARTICLE_JAVA_TITLE_ON_ARTICLE_PAGE = "//android.widget.TextView[@text = 'Java']";
    int amount_of_articles = 2;

    private static String getFolderXpathByName(String name_of_folder) {
        return FOLDER_BY_NAME_TPL.replace("{FOLDER_NAME}", name_of_folder);
    }


    private static String getSavedArticleXpathByTitle(String article_title) {
        return ARTICLE_BY_TITLE_TPL.replace("{TITLE}", article_title);
    }
    public MyListsPageObject(AppiumDriver driver) {
        super(driver);
    }

    public void openFolderByName(String name_of_folder) {
        String folder_name_xpath = getFolderXpathByName(name_of_folder);
        this.waitForElementAndClick(By.xpath(folder_name_xpath),
                "Cant find folder by name " + name_of_folder,
                10);
    }

    public void waitForArticleToAppearByTitle(String article_title) {
        String article_xpath = getSavedArticleXpathByTitle(article_title);
        this.waitForElementPresent(By.xpath(article_xpath), "Cant find saved article by title " + article_title, 15);
    }

    public void waitForArticleToDisappearByTitle(String article_title) {
        String article_xpath = getFolderXpathByName(article_title);
        this.waitForElementNotPresent(By.xpath(article_xpath), "Saved article still present with title " + article_title, 15);
    }

    public void swipeByArticleToDelete(String article_title) {
        this.waitForArticleToAppearByTitle(article_title);
        String article_xpath = getSavedArticleXpathByTitle(article_title);
        this.swipeElementToLeft(By.xpath(article_xpath),
                "Cant find article in the list");
        this.waitForArticleToDisappearByTitle(article_title);
    }

    public void assertAmountOfArticlesInFolder() {
        Assert.assertTrue("Amount of articles are not " + amount_of_articles + " but equals to " + this.getAmountOfElements(By.xpath(ARTICLES_LIST_IN_FOLDER)),
                this.getAmountOfElements(By.xpath(ARTICLES_LIST_IN_FOLDER)) == amount_of_articles);
    }

    public void assertAmountOfArticlesInFolderAfterDecreaseByOne() {
        Assert.assertTrue("Amount of articles are not " + amount_of_articles + " but equals to " + this.getAmountOfElements(By.xpath(ARTICLES_LIST_IN_FOLDER)),
                this.getAmountOfElements(By.xpath(ARTICLES_LIST_IN_FOLDER)) == amount_of_articles - 1);
    }

    public String getArticleTitleInFolderMyList() {
        String article_text_in_folder = this.getElementDescription(By.id(ARTICLE_JAVA_TITLE));
        return article_text_in_folder;
    }

    public String getArticleDescriptionInFolderMyList() {
        String article_text_in_folder = this.getElementDescription(By.xpath(ARTICLE_JAVA_ISLAND));
        return article_text_in_folder;
    }

    public void goToTheArticleFromList() {
        this.waitForElementAndClick(By.xpath(ARTICLE_JAVA_ISLAND),
                "Cant find article about Java island",
                5);
    }

    public void assertDescriptionsInArticleEqual(String article_description_from_folder) {
        String article_text_on_description = this.getElementDescription(By.xpath(ARTICLE_JAVA_DESCRIPTION));
        Assert.assertEquals("Text about Java island isnt equal", article_description_from_folder, article_text_on_description);

}

    public void assertTitlesInArticleEqual(String articleTitleInFolder) {
        String article_text_on_title = this.getElementDescription(By.xpath(ARTICLE_JAVA_TITLE_ON_ARTICLE_PAGE));
        Assert.assertEquals("Titles are not equal", articleTitleInFolder, article_text_on_title);
    }
    }
