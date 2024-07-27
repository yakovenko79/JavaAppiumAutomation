package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ArticlePageObject extends MainPageObject {
    private static final String
        TITLE = "//*[@class = 'android.widget.TextView' and @text=\"Java (programming language)\"]",
        APPIUM_TITLE = "//android.widget.TextView[@text = 'Automation for Apps']",
        FOOTER_ELEMENT = "//*[@text='View article in browser']",
        SAVE_BTN = "org.wikipedia:id/page_save",
        ADD_TO_LIST_BTN = "org.wikipedia:id/snackbar_action",
        MY_LIST_NAME_INPUT = "org.wikipedia:id/text_input",
        OK_BTN = "android:id/button1",
        CLOSE_ARTICLE_BTN = "//android.widget.ImageButton[@content-desc='Navigate up']",
        ARTICLE_TITLE = "//android.widget.TextView[@text='{TITLE}']";






    public ArticlePageObject(AppiumDriver driver) {
        super(driver);
    }

    public WebElement waitForTitleElement() {
        return this.waitForElementPresent(By.xpath(TITLE),"Cant find article title on page!",15);
    }

    public WebElement waitForTitleElement(String article_title) {
        return this.waitForElementPresent(By.xpath(ARTICLE_TITLE.replace("{TITLE}", article_title)), "Cant find article title on page!", 15);
    }
    public WebElement waitForTitleElementWithoutDelay(String article_title) {
        return this.waitForElementPresent(By.xpath(ARTICLE_TITLE.replace("{TITLE}", article_title)),"Cant find article title on page!");
    }

    public WebElement waitForAppiumTitleElement() {
        return this.waitForElementPresent(By.xpath(APPIUM_TITLE),"Cant find article title on page!",15);
    }

    public String getArticleTitle() {
        WebElement title_element = waitForTitleElement();
        return title_element.getAttribute("text");
    }



    public String getArticleTitleByName(String article_title) {
        WebElement title_element = waitForTitleElement(article_title);
        return title_element.getAttribute("text");
    }

    public void swipeToFooter() {
        this.swipeUpToFindElement(By.xpath(FOOTER_ELEMENT),
                "Cant find the end of article",
                20);
    }

    public void addArticleToMyList(String name_of_folder) {
        this.waitForElementAndClick(By.id(SAVE_BTN),
                "Cant find Save button",
                5);
        this.waitForElementAndClick(By.id(ADD_TO_LIST_BTN),
                "Cant find Add to list btn",
                3);
        this.waitForElementAndSendKeys(By.id(MY_LIST_NAME_INPUT),
                name_of_folder,
                "Cant find field for input name of list",
                3);
        this.waitForElementAndClick(By.id(OK_BTN),
                "Cant find OK btn",
                3);
    }

    public void addAnotherArticleToExistingList(String name_of_folder) {
        this.waitForElementAndClick(By.id(SAVE_BTN),
                "Cant find Save button",
                5);
        this.waitForElementAndClick(By.id(ADD_TO_LIST_BTN),
                "Cant find Add to list btn",
                3);
        this.waitForElementAndClick(By.id("org.wikipedia:id/item_title_container"),
                "Cant find " + name_of_folder + " folder",
                5);
        this.waitForElementAndClick(By.id("org.wikipedia:id/snackbar_action"),
                "Catn find View list btn",
                3);
    }

    public void closeArticle() {
        this.waitForElementAndClick(By.xpath(CLOSE_ARTICLE_BTN),
                "Cant find left arrow btn",
                5);
    }
}
