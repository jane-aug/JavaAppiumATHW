package tests;

import lib.CoreTestCase;
import lib.ui.*;
import org.junit.Test;
import org.openqa.selenium.By;

public class MyListTest extends CoreTestCase
{
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

    // создания списка
    @Test
    public void testCreateList() {
        StartPageObject StartPageObject = new StartPageObject(driver);
        SavedArticlePageObject SavedArticlePageObject = new SavedArticlePageObject(driver);

        StartPageObject.skipOnboardingButton();
        SavedArticlePageObject.getSavedAriclePage();
        SavedArticlePageObject.createNewList("First");
    }

}
