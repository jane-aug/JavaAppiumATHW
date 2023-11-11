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

        MainPageObject MainPageObject = new MainPageObject(driver);
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


    // создания списка
    @Test
    public void testCreateList() {
        StartPageObject StartPageObject = new StartPageObject(driver);
        SavedArticlePageObject SavedArticlePageObject = new SavedArticlePageObject(driver);

        StartPageObject.skipOnboardingButton();
        SavedArticlePageObject.getSavedAriclePage();
        SavedArticlePageObject.createNewList("First");
    }

    @Test
    public void testEx5() throws InterruptedException {
        // сохраняем 2 статьи в одну папку, 1 удаляем и проверяем что осталась вторая по названию
        MainPageObject MainPageObject = new MainPageObject(driver);
        StartPageObject StartPageObject = new StartPageObject(driver);
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        SavedArticlePageObject SavedArticlePageObject = new SavedArticlePageObject(driver);


        StartPageObject.skipOnboardingButton();

        // 1 статья
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");
        ArticlePageObject.changeMenu();
        ArticlePageObject.saveArticle();
       // driver.navigate().back();
       // SearchPageObject.tapBycoordinate();


        // 2 статья
        SearchPageObject.initSearchInput();
        SearchPageObject.clearSearchText();
        SearchPageObject.typeSearchLine("Appium");
        SearchPageObject.clickByArticleWithSubstring("Automation for Apps");
        ArticlePageObject.saveArticle();

        //удаляем одну из списка
        driver.navigate().back();
        driver.navigate().back();
        driver.navigate().back();
        driver.navigate().back();

        SavedArticlePageObject.getSavedAriclePage();
        //SavedArticlePageObject.clickByArticleInListWithSubstring("Saved");
        SavedArticlePageObject.clickByArticleInList();

        MainPageObject.swipeElementToLeft(By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"), "Cannot find saved article");
        MainPageObject.assertElementHasNotText(By.id("org.wikipedia:id/page_list_item_description"), "Object-oriented programming language","Cannot  delete article", 5);
        MainPageObject.assertElementHasText(By.id("org.wikipedia:id/page_list_item_description"), "Automation for Apps","Second article is still in list", 5);

        // MainPageObject.waitForElementNotPresent(By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_description']//*[@text='Object-oriented programming language']"), "Cannot  delete article ", 5);
        //  MainPageObject.waitForElementPresent(By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_description']//*[@text='Automation for Apps]"), "Second article is still in list", 10);
        // By.xpath("//*[@resource-id='org.wikipedia:id/search_container']//*[@text='Object-oriented programming language']")

    }

    @Test
    public void testAmountOfNotEmtySearch(){
        StartPageObject StartPageObject = new StartPageObject(driver);
        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        StartPageObject.skipOnboardingButton();

        String searh_line = "Linkin Park Discography";
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(searh_line);
        SearchPageObject.assertSearchHasResult("We did not found any results",10);

    }

    @Test
    public void testAmountOfEemptySerch(){
        StartPageObject StartPageObject = new StartPageObject(driver);
        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        StartPageObject.skipOnboardingButton();

        String searh_line = "jhvghjhhgjghjg";
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(searh_line);
        SearchPageObject.assertSearchNotResult("We see results",5);


    }

    @Test
    public void testEx6() {
        StartPageObject StartPageObject = new StartPageObject(driver);
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);

        StartPageObject.skipOnboardingButton();

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");
        ArticlePageObject.assertArticleTitlePresent();


    }

}

