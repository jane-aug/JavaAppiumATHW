package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class SearchPageObject  extends  MainPageObject
{
    private static final String
    SEARCH_INIT_ELEMENT = "//*[contains(@text,'Search Wikipedia')]",
    SEARCH_INPUT = "org.wikipedia:id/search_src_text",
    SEARCH_RESULT_BY_SUBSTRING_TPL = "//*[@resource-id='org.wikipedia:id/search_container']//*[@text='{SUBSTRING}']",
    SEARCH_UNDO_BUTTON = "//android.widget.ImageButton[@content-desc=\"Navigate up\"]",
    PLACEHOLDER_SEARCH_LINE = "Search Wikipedia";


    public SearchPageObject (AppiumDriver driver){
        super(driver);
    }
    private static String getResultSearchElement(String subString){
        return SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}", subString);
    }
    //TEMPLATE METHODS
    public void initSearchInput(){
        this.waitForElementPresent(By.xpath(SEARCH_INIT_ELEMENT),"Cannot find search input after clicking search init element",5);
        this.waitForElementAndClick(By.xpath(SEARCH_INIT_ELEMENT), "Cannot find and click search element",5);
    }
    //TEMPLATE METHODS

    public void typeSearchLine(String search_line){
        this.waitForElementAndSendKeys(By.id(SEARCH_INPUT), search_line,"Cannot find and type into search input",5);
    }

    public void waitForSearchResult(String subString){
        String search_result_xpath = getResultSearchElement(subString);
        this.waitForElementPresent(By.xpath(search_result_xpath),"Cannot find search result with substring" + subString,5);
    }

    public void waitForUndoButtonToAppear(){
        this.waitForElementPresent(By.xpath(SEARCH_UNDO_BUTTON),"Cannot find Navigate <-",5);
    }

    public void waitForUndoButtonToDisappear(){
        this.waitForElementNotPresent(By.xpath(SEARCH_UNDO_BUTTON),"Navigate <- still present on page",5);
    }

    public void clickUndoButton(){
        this.waitForElementAndClick(By.xpath(SEARCH_UNDO_BUTTON),"Cannot find and click to Navigate <-",5);
    }

    public void clickByArticleWithSubstring(String subString){
        String search_result_xpath = getResultSearchElement(subString);
        this.waitForElementAndClick(By.xpath(search_result_xpath),"Cannot find and click search result with substring" + subString,10);
    }

    public void clearSearchText(){
        this.waitForElementAndClear(By.id(SEARCH_INPUT),"Cannot find search field",5);
    }

    public void checkPlaceholderInSearchLine(){
        this.assertElementHasText(By.id(SEARCH_INPUT),PLACEHOLDER_SEARCH_LINE,"We see unexpected placeholder",5);
    }
}
