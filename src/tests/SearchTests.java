package tests;

import lib.CoreTestCase;
import lib.ui.SearchPageObject;
import org.junit.Test;

public class SearchTests extends CoreTestCase {

    @Test
    public void testSearchFieldHasText() {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.assertSearchFieldHasText();
    }


    @Test
    public void testSearch() {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSeachInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForSearchResult("Object-oriented programming language");

    }

    @Test
    public void testCancelSearch() {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSeachInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForCancelButtonToAppear();
        SearchPageObject.clickCancelSearch();
        SearchPageObject.waitForCancelButtonToDisappear();
    }

    @Test
    public void testAmountOfNotEmptySearch() {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSeachInput();
        String search_line = "Linkin Park Discography";
        SearchPageObject.typeSearchLine(search_line);
        int amount_of_search_results = SearchPageObject.getAmountOfFoundArticles();
        assertTrue(
                "We found too few results!",
                amount_of_search_results > 0);
    }

    @Test
    public void testAmountOfEmptySearch() {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSeachInput();
        String search_line = "xfdfxfxfxefxe";
        SearchPageObject.typeSearchLine(search_line);
        SearchPageObject.waitForEmptyResultsLabel();
        SearchPageObject.assertThereIsNoResultOfSearch();
    }

    @Test
    public void testSearchArticlesAndClearWord() {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSeachInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.assertSearchListOfItemsResults();
        SearchPageObject.clearInputSearchItem();
        SearchPageObject.waitForSearchFieldIsEmpty();
    }

    @Test
    public void testWordsInSearch() {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSeachInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.assertSearchResultsContainInputWord();
    }
}
