package lib.ui;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.PointOption;
import lib.Platform;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.List;

abstract public class SearchPageObject  extends  MainPageObject
{
    protected static  String
            SEARCH_INIT_ELEMENT,
            SEARCH_INPUT,
            SEARCH_INPUT_ON_PAGE,
            SEARCH_RESULT_BY_SUBSTRING_TPL,
            SEARCH_UNDO_BUTTON,
            PLACEHOLDER_SEARCH_LINE,
            SEARCH_RESULT_LOCATOR ,
            EMPTY_RESULT_LABEL,
            CANCEL_BUTTON_IN_SEARCH_LINE,
            PLACEHOLDER_SEARCH_LINE_EN,
            WELCOME_TEXT;


    public SearchPageObject (RemoteWebDriver driver){
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

    // иницилизация поиска на странице
   public  void  initSearhInputOnPage() {
       this.waitForElementPresent(SEARCH_INPUT_ON_PAGE,"Cannot find search input after clicking search init element",5);
       this.waitForElementAndClick(SEARCH_INPUT_ON_PAGE, "Cannot find and click search element",5);

   }
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
        this.waitForElementAndClick(search_result_xpath,"Cannot find and click search result with substring " + subString,10);
    }

    public void clearSearchText(){
        this.waitForElementAndClear(SEARCH_INPUT,"Cannot find search field",5);
    }

    public void checkPlaceholderInSearchLine(){
        if (Platform.getInstance().isAndroid()) {
            this.assertElementHasText(SEARCH_INPUT,PLACEHOLDER_SEARCH_LINE,"We see unexpected placeholder",5);
        }
        else if (Platform.getInstance().isIOS()) {
            this.assertElementHasText(SEARCH_INPUT,PLACEHOLDER_SEARCH_LINE,"We see unexpected placeholder",5);
        }
        else if (Platform.getInstance().isMW()) {
            this.assertElementHasText(SEARCH_INPUT,PLACEHOLDER_SEARCH_LINE_EN,"We see unexpected placeholder",5);

        }
    }

    public void tapBycoordinate(){
        if (driver instanceof AppiumDriver) {
        TouchAction action = new TouchAction((AppiumDriver)driver);
        // action.press(369,1460); // поднятие версии
        action.press(PointOption.point(369,1460));
        } else {
            System.out.println("Method  tapBycoordinate() does nothing for platform " + Platform.getInstance().getPlatformVar());
        }
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
                element_count > 0

        );
        return element;
    }

    public void assertSearchNotResult( String error_massage, long timeoutInSeconds){
       waitForElementPresent(EMPTY_RESULT_LABEL,error_massage, timeoutInSeconds);
    }

    public void cancelSearch(){
        waitForElementAndClick(CANCEL_BUTTON_IN_SEARCH_LINE, "Cannot find cancel button in search line", 5);

    }

    public void findWelcomeText(){
        waitForElementPresent(WELCOME_TEXT, "Cannot find Welcome text", 5);
    }
}
