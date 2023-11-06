package HW1.exercise1;
import lib.CoreTestCase;
import lib.ui.*;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;

public class FirstTest  extends CoreTestCase {
    private MainPageObject MainPageObject;

    protected  void setUp() throws Exception {
        super.setUp();
        MainPageObject = new MainPageObject(driver);
    }

    @Test
    public void testSearch()
    {
        StartPageObject StartPageObject = new StartPageObject(driver);
        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        StartPageObject.skipOnboardingButton();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForSearchResult("Object-oriented programming language");
    }
    @Test
        public void testSecondTest()
    {
            StartPageObject StartPageObject = new StartPageObject(driver);
            SearchPageObject SearchPageObject = new SearchPageObject(driver);

            StartPageObject.skipOnboardingButton();
            SearchPageObject.initSearchInput();
            SearchPageObject.typeSearchLine("Java");
            SearchPageObject.waitForUndoButtonToAppear();
            SearchPageObject.clickUndoButton();
            SearchPageObject.waitForUndoButtonToDisappear();
    }

    @Test
    public void testCompareArticleTitle(){
        StartPageObject StartPageObject = new StartPageObject(driver);
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);

        StartPageObject.skipOnboardingButton();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");
        String article_title = ArticlePageObject.getArticleTitle();
        Assert.assertEquals(
                "We see unexpected title",
                "Object-oriented programming language",
                article_title

        );
    }

@Test
public void testCancelSearchAndDelete(){
    StartPageObject StartPageObject = new StartPageObject(driver);
    SearchPageObject SearchPageObject = new SearchPageObject(driver);

    StartPageObject.skipOnboardingButton();
    SearchPageObject.initSearchInput();
    SearchPageObject.typeSearchLine("Java");
    SearchPageObject.clearSearchText();
    SearchPageObject.clickUndoButton();
    SearchPageObject.waitForUndoButtonToDisappear();
}



    @Test
    public void testex2(){
        StartPageObject StartPageObject = new StartPageObject(driver);
        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        StartPageObject.skipOnboardingButton();
        SearchPageObject.initSearchInput();
        SearchPageObject.checkPlaceholderInSearchLine();

        MainPageObject.assertElementHasText(
                By.id("org.wikipedia:id/search_src_text"),
                "Search Wikipedia",
                "We see unexpected title",
                5
        );
    }





    // Занятие 4 - продвинутые тесты

    @Test
    public void testSwipeArticle(){
        StartPageObject StartPageObject = new StartPageObject(driver);
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);


        StartPageObject.skipOnboardingButton();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Appium");
        SearchPageObject.clickByArticleWithSubstring("Automation for Apps");
        ArticlePageObject.waitForTitleElement();
        MainPageObject.tuochByCoordinate(671,441);
        ArticlePageObject.swipeToFooter();

    }


    // cохранения статьи
    @Test
    public void testCreateList() {
        StartPageObject StartPageObject = new StartPageObject(driver);
        SavedArticlePageObject SavedArticlePageObject = new SavedArticlePageObject(driver);

        StartPageObject.skipOnboardingButton();
        SavedArticlePageObject.getSavedAriclePage();
        SavedArticlePageObject.createNewList("First");
    }

    @Test
    public void testSaveFirstArticleToMyList(){
        StartPageObject StartPageObject = new StartPageObject(driver);
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);

        StartPageObject.skipOnboardingButton();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");
        ArticlePageObject.changeMenu(By.xpath("//org.wikipedia:id/dragHandle[@content-desc='Hold the drag icon to move the item']"));


    }
}

