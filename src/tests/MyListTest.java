package tests;

import lib.CoreTestCase;
import lib.Platform;
import lib.ui.*;
import lib.ui.factories.*;
import org.junit.Test;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MyListTest extends CoreTestCase
{
    @Test
    public void testEx5() throws InterruptedException {
        // сохраняем 2 статьи в одну папку, 1 удаляем и проверяем что осталась вторая по названию
        MainPageObject MainPageObject = new MainPageObject(driver);
        StartPageObject StartPageObject = StartPageObjectFactory.get(driver);
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        SavedArticlePageObject SavedArticlePageObject = SavedArticlePageObjectFactory.get(driver);
        AuthorizationPageObject AuthorizationPageObject = AuthorizationPageObjectFactory.get(driver);


        StartPageObject.skipOnboardingButton();

        //Авторизация для сайта
        if (Platform.getInstance().isMW()) {
            driver.get("https://en.m.wikipedia.org/w/index.php?title=Special:UserLogin&returnto=Main+Page");
            AuthorizationPageObject.enterLoginData("Shalanova", "Mem6rana1");
            AuthorizationPageObject.submitForm();

        } else {
            System.out.println("Skip authorization for mobile");
        }

        // 1 статья
        SearchPageObject.initSearchInput();

        SearchPageObject.typeSearchLine("Java");
        if (Platform.getInstance().isIOS()) {
            SearchPageObject.clickByArticleWithSubstring("зык программирования");
        } else if (Platform.getInstance().isAndroid()) {
            SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");
        } else {
            SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");
        }
        if (Platform.getInstance().isAndroid()){ ArticlePageObject.changeMenu();}
        else {System.out.println("Skip changeMenu for iOS and web");}
        if (Platform.getInstance().isMW()) {
            ArticlePageObject.saveArticle();



            ArticlePageObject.waitForTitleElement();
            String article_title = ArticlePageObject.getArticleTitle();
            assertEquals("We are not on the same page after login.",
                    article_title,
                    ArticlePageObject.getArticleTitle()
                    );

           ArticlePageObject.saveArticle();

        }
        else ArticlePageObject.saveArticle();
        // driver.navigate().back();
        // SearchPageObject.tapBycoordinate();


        // 2 статья
        if (Platform.getInstance().isAndroid()){ SearchPageObject.initSearchInput();}
        else {   driver.navigate().back();
            SearchPageObject.initSearchInput();}
        SearchPageObject.clearSearchText();
        if (Platform.getInstance().isAndroid()){
        SearchPageObject.typeSearchLine("Appium");
        SearchPageObject.clickByArticleWithSubstring("Automation for Apps");}
        else {
            SearchPageObject.typeSearchLine("Appium");
            SearchPageObject.clickByArticleWithSubstring("Вид растений");
        }
        ArticlePageObject.saveArticle();

        //удаляем одну из списка
        if (Platform.getInstance().isAndroid()) {
        driver.navigate().back();
        driver.navigate().back();
        driver.navigate().back();
        driver.navigate().back();}
        else if (Platform.getInstance().isIOS()) {
            driver.navigate().back();
            SearchPageObject.cancelSearch();
            driver.navigate().back();
            driver.navigate().back();
            driver.navigate().back();
        } else {


        }
        SavedArticlePageObject.getSavedAriclePage();

        //SavedArticlePageObject.clickByArticleInListWithSubstring("Saved");
        if (Platform.getInstance().isAndroid()) {
            SavedArticlePageObject.clickByArticleInList();

            MainPageObject.swipeElementToLeft("xpath://*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']", "Cannot find saved article");
            MainPageObject.assertElementHasNotText("id:org.wikipedia:id/page_list_item_description", "Object-oriented programming language", "Cannot  delete article", 5);
            MainPageObject.assertElementHasText("id:org.wikipedia:id/page_list_item_description", "Automation for Apps", "Second article is still in list", 5);
        } else {
            SavedArticlePageObject.closeSinchXButton();
            MainPageObject.swipeElementToLeftv3("xpath://XCUIElementTypeStaticText[@name='Вид растений']","Cannot find saved article");
            SavedArticlePageObject.deleteArticleOnSavedPage();
            MainPageObject.assertElementHasNotText("xpath://XCUIElementTypeStaticText","Вид растений","Cannot  delete article",5);
            MainPageObject.assertElementHasText("xpath://XCUIElementTypeStaticText[contains(@name,'Язык программирования')]","Язык программирования","Second article is still in list",5);

        }

    }


    // создания списка
    @Test
    public void testCreateList() {
        StartPageObject StartPageObject = StartPageObjectFactory.get(driver);
        SavedArticlePageObject SavedArticlePageObject = SavedArticlePageObjectFactory.get(driver);

        StartPageObject.skipOnboardingButton();
        SavedArticlePageObject.getSavedAriclePage();
        SavedArticlePageObject.createNewList("First");
    }

}
