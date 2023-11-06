package HW1.exercise3;

import lib.CoreTestCase;
import lib.ui.MainPageObject;
import lib.ui.SearchPageObject;
import lib.ui.StartPageObject;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;

public class ex3 extends CoreTestCase {
    private lib.ui.MainPageObject MainPageObject;

    protected  void setUp() throws Exception {
        super.setUp();
        MainPageObject = new MainPageObject(driver);
    }



    @Test
    public void testExercise3() throws Exception {
        StartPageObject StartPageObject = new StartPageObject(driver);
        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        StartPageObject.skipOnboardingButton();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForSearchResult("Java");
        Assert.assertTrue("Cannot find specific search results on search page",
                MainPageObject.quantityElements(By.xpath("//*[contains(@text, 'Java')]")).size() > 1);

        SearchPageObject.waitForUndoButtonToAppear();
        SearchPageObject.clickUndoButton();
        SearchPageObject.waitForUndoButtonToDisappear();
    }
}