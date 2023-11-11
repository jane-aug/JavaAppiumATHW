package lib.ui;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.util.List;

public class ArticlePageObject extends MainPageObject{

    private static final String
            TITLE = "pcs-edit-section-title-description",
            FOOTER_ELEMENT = "pcs-footer-container-menu",
            MENU_BUTTON = "org.wikipedia:id/page_toolbar_button_show_overflow_menu",
            CUSTOMIZE_BUTTON = "org.wikipedia:id/customize_toolbar",
            SAVE_MENU_IN_SETTINGS = "//android.widget.TextView[@text='Save']",
            UNDO_BUTTON = "//android.widget.ImageButton[@content-desc='Navigate up]",
            SAVE_MENU = "org.wikipedia:id/page_save",
            SNACKBAR_ACTION = "org.wikipedia:id/snackbar_action",
            FIRST_LIST = "org.wikipedia:id/item_title_container",
            ARTICLE_RESULT_BY_SUBSTRING_TPL = "//*[@resource-id='org.wikipedia:id/reading_list_recycler_view']//*[@text='{SUBSTRING}']";

    public ArticlePageObject(AppiumDriver driver){
        super(driver);
    }
    private static String getResultListElement(String subString){
        return ARTICLE_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}", subString);
    }
    public void waitForSearchResult(String subString){
        String list_result_xpath = getResultListElement(subString);
        this.waitForElementPresent(By.xpath(list_result_xpath),"Cannot find search result with substring" + subString,5);
    }
    public WebElement waitForTitleElement() {
        return this.waitForElementPresent(By.id(TITLE),"Cannot find article title", 10);
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
        this.swipeUpToFindElement(By.id(FOOTER_ELEMENT),"Did not find footer", 20);
    }

    public void openMenu(){
        this.waitForElementAndClick(By.id(MENU_BUTTON), "Cannot find menu_overflow_button", 5);
    }


    public void dragAndDrop(){
       TouchAction action = new TouchAction(driver);
       action.longPress(999, 627).moveTo(9, 1640).release().perform();

    }


    public void changeMenu(){
        openMenu();
        this.waitForElementAndClick(By.id(CUSTOMIZE_BUTTON),"Cannot find customize_toolbar",5);
        this.waitForElementPresent(By.xpath(SAVE_MENU_IN_SETTINGS),"Cannot find save menu", 5);
        dragAndDrop();
        driver.navigate().back();
    }
    public void saveArticle(){
        openMenu();
        this.waitForElementAndClick(By.id(SAVE_MENU),"Cannot find save button in list",5);
        //this.waitForElementAndClick(By.id(SNACKBAR_ACTION),"Cannot find add to list",5);
        //this.waitForElementAndClick(By.id(FIRST_LIST),"Cannot find list",5);
        //this.waitForElementAndClick(By.id(SNACKBAR_ACTION),"Cannot find view list",5);
       // this.waitForSearchResult("Object-oriented programming language");
       // this.waitForElementAndClick(By.xpath(UNDO_BUTTON),"Cannot find undo",5);

    }
}
