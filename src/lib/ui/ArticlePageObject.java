package lib.ui;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.util.List;

public class ArticlePageObject extends MainPageObject{

    private static final String
            TITLE = "xpath://android.view.View[@resource-id=\"pcs-edit-section-title-description\"]",
            FOOTER_ELEMENT = "xpath://android.view.View[@resource-id=\"pcs-footer-container-menu\"]",
            MENU_BUTTON = "id:org.wikipedia:id/page_toolbar_button_show_overflow_menu",
            CUSTOMIZE_BUTTON = "id:org.wikipedia:id/customize_toolbar",
            SAVE_MENU_IN_SETTINGS = "xpath://android.widget.TextView[@text='Save']",
            UNDO_BUTTON = "xpath://android.widget.ImageButton[@content-desc='Navigate up]",
            SAVE_MENU = "id:org.wikipedia:id/page_save",
            SNACKBAR_ACTION = "id:org.wikipedia:id/snackbar_action",
            FIRST_LIST = "id:org.wikipedia:id/item_title_container",
            ARTICLE_RESULT_BY_SUBSTRING_TPL = "xpath://*[@resource-id='org.wikipedia:id/reading_list_recycler_view']//*[@text='{SUBSTRING}']";

    public ArticlePageObject(AppiumDriver driver){
        super(driver);
    }
    private static String getResultListElement(String subString){
        return ARTICLE_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}", subString);
    }
    /*public void waitForSearchResult(String subString){
        String list_result_xpath = getResultListElement(subString);
        this.waitForElementPresent(By.xpath(list_result_xpath),"Cannot find search result with substring" + subString,5);
    }*/
    public WebElement waitForTitleElement() {
        return this.waitForElementPresent(TITLE,"Cannot find article title", 10);
    }

    public String getArticleTitle() {
        WebElement title_element = waitForTitleElement();
        return  title_element.getAttribute("text");
    }

    public void assertArticleTitlePresent(){
        List<WebElement> elements = driver.findElements(By.id(TITLE));
        if (elements.isEmpty()) {
            throw new AssertionError("Article name is did not loaded");
       }
    }

    public void swipeToFooter(){
        this.swipeUpToFindElement(FOOTER_ELEMENT,"Did not find footer", 20);
    }

    public void openMenu(){
        this.waitForElementAndClick(MENU_BUTTON, "Cannot find menu_overflow_button", 5);
    }


    public void dragAndDrop(){
       TouchAction action = new TouchAction(driver);
       // action.longPress(999, 627).moveTo(9, 1640).release().perform(); // поднятие версии
        action.longPress(PointOption.point(999, 627)).moveTo(PointOption.point(999, 1640)).release().perform();

    }


    public void changeMenu(){
        openMenu();
        this.waitForElementAndClick(CUSTOMIZE_BUTTON,"Cannot find customize_toolbar",5);
        this.waitForElementPresent(SAVE_MENU_IN_SETTINGS,"Cannot find save menu", 5);
        dragAndDrop();
        driver.navigate().back();
    }
    public void saveArticle(){
        openMenu();
        this.waitForElementAndClick(SAVE_MENU,"Cannot find save button in list",5);
        //this.waitForElementAndClick(By.id(SNACKBAR_ACTION),"Cannot find add to list",5);
        //this.waitForElementAndClick(By.id(FIRST_LIST),"Cannot find list",5);
        //this.waitForElementAndClick(By.id(SNACKBAR_ACTION),"Cannot find view list",5);
       // this.waitForSearchResult("Object-oriented programming language");
       // this.waitForElementAndClick(By.xpath(UNDO_BUTTON),"Cannot find undo",5);

    }
}
