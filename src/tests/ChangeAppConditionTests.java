package tests;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.SearchPageObject;
import org.junit.Test;

public class ChangeAppConditionTests extends CoreTestCase {

    @Test
    public void testChangeScreenOrientationOnSearchResults() {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSeachInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");

        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        String tite_before_rotation = ArticlePageObject.getArticleTitle();
        try {
            this.rotateScreenLandscape();
            String tite_after_rotation = ArticlePageObject.getArticleTitle();
            assertEquals(
                    "Article title have been changed after rotation",
                    tite_before_rotation,
                    tite_after_rotation);

            this.rotateScreenPortrait();
            String tite_after_second_rotation = ArticlePageObject.getArticleTitle();

            assertEquals(
                    "Article title have been changed after rotation",
                    tite_before_rotation,
                    tite_after_second_rotation);
        } finally {
            this.rotateScreenPortrait();
        }
    }

    @Test
    public void testCheckSearchArticleInBackground() {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSeachInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForSearchResult("Object-oriented programming language");
        this.bachgroundApp(2);
        SearchPageObject.waitForSearchResult("Object-oriented programming language");
    }
}
