package tests;

import lib.CoreTestCase;
import lib.Platform;
import lib.ui.ArticlePageObject;
import lib.ui.MainPageObject;
import lib.ui.SearchPageObject;
import lib.ui.StartPageObject;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.SearchPageObjectFactory;
import lib.ui.factories.StartPageObjectFactory;
import org.junit.Test;

public class ArticleTests extends CoreTestCase {

    @Test
    public void testCompareArticleTitle(){
        StartPageObject StartPageObject = StartPageObjectFactory.get(driver);
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);

        StartPageObject.skipOnboardingButton();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        if (Platform.getInstance().isIOS()) {
            SearchPageObject.clickByArticleWithSubstring("Язык программирования");
        } else if (Platform.getInstance().isAndroid()) {
            SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");
        } else {
            SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");
        }

        if (Platform.getInstance().isAndroid()) {
        String article_title = ArticlePageObject.getArticleTitle();
        assertEquals(
                "We see unexpected title",
                "Object-oriented programming language",
                article_title

        );}
        else if (Platform.getInstance().isIOS()) {
            String article_title = ArticlePageObject.getArticleTitle();
            assertEquals(
                    "We see unexpected title",
                    "язык программирования",
                    article_title

            );
        }
        else {
            String article_title = ArticlePageObject.getArticleTitle();
            assertEquals(
                    "We see unexpected title",
                    "Java (programming language)",
                    article_title

            );
        }
    }
    // Занятие 4 - продвинутые тесты

    @Test
    public void testSwipeArticle(){

        MainPageObject MainPageObject = new MainPageObject(driver);
        StartPageObject StartPageObject =  StartPageObjectFactory.get(driver);
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);


        StartPageObject.skipOnboardingButton();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        if (Platform.getInstance().isIOS()) {
            SearchPageObject.clickByArticleWithSubstring("Язык программирования");
        } else if (Platform.getInstance().isAndroid()) {
            SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");
        } else {
            SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");
        }
        ArticlePageObject.waitForTitleElement();
        MainPageObject.tuochByCoordinate(671,441);
        if (Platform.getInstance().isAndroid()) {ArticlePageObject.changeMenu();}
        ArticlePageObject.swipeToFooter();

    }

    @Test
    public void testSwipeArticlev2(){

        MainPageObject MainPageObject = new MainPageObject(driver);
        StartPageObject StartPageObject =  StartPageObjectFactory.get(driver);
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);


        StartPageObject.skipOnboardingButton();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        if (Platform.getInstance().isIOS()) {
            SearchPageObject.clickByArticleWithSubstring("Язык программирования");
        } else if (Platform.getInstance().isAndroid()) {
            SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");
        } else {
            SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");
        }
        ArticlePageObject.waitForTitleElement();
        MainPageObject.verticalSwipeToBottom();

    }


}
