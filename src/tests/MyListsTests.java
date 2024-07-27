package tests;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.MyListsPageObject;
import lib.ui.NavigationUI;
import lib.ui.SearchPageObject;
import org.junit.Test;

public class MyListsTests extends CoreTestCase {
    @Test
    public void testSaveFirstArticleToMyList() {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSeachInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");

        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        ArticlePageObject.waitForTitleElement();
        String article_title = ArticlePageObject.getArticleTitle();
        String name_of_folder = "TestList";
        ArticlePageObject.addArticleToMyList(name_of_folder);
        ArticlePageObject.closeArticle();
        ArticlePageObject.closeArticle();

        NavigationUI NavigationUI = new NavigationUI(driver);
        NavigationUI.clickMyLists();

        MyListsPageObject MyListsPageObject = new MyListsPageObject(driver);
        MyListsPageObject.openFolderByName(name_of_folder);
        MyListsPageObject.swipeByArticleToDelete(article_title);
    }

    @Test
    public void testSaveTwoArticlesToTheFolderAndRemoveOneOfThem() {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSeachInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");

        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        ArticlePageObject.waitForTitleElement();
        String first_article_title = ArticlePageObject.getArticleTitleByName("Java (programming language)");
        String name_of_folder = "TestList";
        ArticlePageObject.addArticleToMyList(name_of_folder);
        ArticlePageObject.closeArticle();
        SearchPageObject.clickByArticleWithSubstring("Island in Indonesia");
        String second_article_title = ArticlePageObject.getArticleTitleByName("Java");
        ArticlePageObject.addAnotherArticleToExistingList(name_of_folder);
        MyListsPageObject MyListsPageObject = new MyListsPageObject(driver);
        MyListsPageObject.assertAmountOfArticlesInFolder();
        MyListsPageObject.swipeByArticleToDelete(first_article_title);
        MyListsPageObject.assertAmountOfArticlesInFolderAfterDecreaseByOne();
        MyListsPageObject.waitForArticleToAppearByTitle(second_article_title);
        String article_description_in_folder = MyListsPageObject.getArticleDescriptionInFolderMyList();
        String article_title_in_folder = MyListsPageObject.getArticleTitleInFolderMyList();
        MyListsPageObject.goToTheArticleFromList();
        MyListsPageObject.assertDescriptionsInArticleEqual(article_description_in_folder);
        MyListsPageObject.assertTitlesInArticleEqual(article_title_in_folder);







    }
}
