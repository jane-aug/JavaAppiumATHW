package lib.ui;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.PointOption;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class SearchPageObject  extends  MainPageObject
{
    private static final String
    SEARCH_INIT_ELEMENT = "xpath://*[contains(@text,'Search Wikipedia')]",
    SEARCH_INPUT = "id:org.wikipedia:id/search_src_text",
    SEARCH_INPUT_ON_PAGE = "id:org.wikipedia:id/page_toolbar_button_search",
    SEARCH_RESULT_BY_SUBSTRING_TPL = "xpath://*[@resource-id='org.wikipedia:id/search_container']//*[@text='{SUBSTRING}']",
    SEARCH_UNDO_BUTTON = "xpath://android.widget.ImageButton[@content-desc=\"Navigate up\"]",
    PLACEHOLDER_SEARCH_LINE = "Search Wikipedia",
    SEARCH_RESULT_LOCATOR = "xpath://*[@resource-id='org.wikipedia:id/fragment_search_results']//*[@resource-id='org.wikipedia:id/search_results_list']",
    EMPTY_RESULT_LABEL = "xpath://*[@text='No results']";


    public SearchPageObject (AppiumDriver driver){
        super(driver);
    }
    private static String getResultSearchElement(String subString){
        return SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}", subString);
    }
    //TEMPLATE METHODS
    public void initSearchInput(){
        this.waitForElementPresent(SEARCH_INIT_ELEMENT,"Cannot find search input after clicking search init element",5);
        this.waitForElementAndClick(SEARCH_INIT_ELEMENT, "Cannot find and click search element",5);
    }
    //TEMPLATE METHODS

    public void typeSearchLine(String search_line){
        this.waitForElementAndSendKeys(SEARCH_INPUT, search_line,"Cannot find and type into search input",5);
    }

    public void waitForSearchResult(String subString){
        String search_result_xpath = getResultSearchElement(subString);
        this.waitForElementPresent(search_result_xpath,"Cannot find search result with substring" + subString,5);
    }

    public void waitForUndoButtonToAppear(){
        this.waitForElementPresent(SEARCH_UNDO_BUTTON,"Cannot find Navigate <-",5);
    }

    public void waitForUndoButtonToDisappear(){
        this.waitForElementNotPresent(SEARCH_UNDO_BUTTON,"Navigate <- still present on page",5);
    }

    public void clickUndoButton(){
        this.waitForElementAndClick(SEARCH_UNDO_BUTTON,"Cannot find and click to Navigate <-",5);
    }

    public void clickByArticleWithSubstring(String subString){
        String search_result_xpath = getResultSearchElement(subString);
        this.waitForElementAndClick(search_result_xpath,"Cannot find and click search result with substring" + subString,10);
    }

    public void clearSearchText(){
        this.waitForElementAndClear(SEARCH_INPUT,"Cannot find search field",5);
    }

    public void checkPlaceholderInSearchLine(){
        this.assertElementHasText(SEARCH_INPUT,PLACEHOLDER_SEARCH_LINE,"We see unexpected placeholder",5);
    }

    public void tapBycoordinate(){
        TouchAction action = new TouchAction(driver);
        // action.press(369,1460); // поднятие версии
        action.press(PointOption.point(369,1460));
    }

    public int getAmountOfElements(String loccator){
        By by = getLocatorByString(loccator); //определение локатора
        List elements = driver.findElements(by);
        return elements.size();
    }

    public WebElement assertSearchHasResult( String error_massage, long timeoutInSeconds){
        WebElement element =  waitForElementPresent(SEARCH_RESULT_LOCATOR,error_massage, timeoutInSeconds);
        int element_count = getAmountOfElements(SEARCH_RESULT_LOCATOR);
        Assert.assertTrue(
                "We did not find any results",
                element_count > 1

        );
        return element;
    }

    public void assertSearchNotResult( String error_massage, long timeoutInSeconds){
       waitForElementPresent(EMPTY_RESULT_LABEL,error_massage, timeoutInSeconds);
    }
}
