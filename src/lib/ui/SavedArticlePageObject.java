package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class SavedArticlePageObject extends MainPageObject{
    private static final String
            SAVED = "org.wikipedia:id/nav_tab_reading_lists",
            EXPLORE = "org.wikipedia:id/nav_tab_explore",
            MENU_BUTTON = "org.wikipedia:id/menu_overflow_button",
            CRATE_NEW_LIST_BUTTON = "org.wikipedia:id/reading_lists_overflow_create_new_list",
            INPUT_FIELD_FOR_NEW_LIST = "org.wikipedia:id/text_input",
            FIELD_NAME_FOR_LIST = "org.wikipedia:id/item_title",
            OK_BUTTON = "android:id/button1",
            ARTICLE_IN_LUST_RESULT_BY_SUBSTRING_TPL = "//android.widget.TextView[@text='{SUBSTRING}']";




    public SavedArticlePageObject(AppiumDriver driver){
        super(driver);
    }

    private static String getResultArticleElement(String subString){
        return ARTICLE_IN_LUST_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}", subString);
    }

    public void clickByArticleInListWithSubstring(String subString){
        String search_result_id = getResultArticleElement(subString);
        this.waitForElementAndClick(By.id(search_result_id),"Cannot find and click in article list result with substring " + subString,10);
    }

    public  void clickByArticleInList(){
        this.waitForElementAndClick(By.id("org.wikipedia:id/item_title"),"Cannot find and click article on list result!",5);
       // this.waitForElementAndClick(By.xpath("//*[@resource-id='org.wikipedia:id/item_title']"),"Cannot find and click article on list result!",5);

    }

    public void getSavedAriclePage(){
        this.waitForElementAndClick(By.id(SAVED), "Cannot find nav_tab_reading_lists",5);
    }

    public void openMenu(){
        this.waitForElementAndClick(By.id(MENU_BUTTON),"Cannot find menu_overflow_button", 5);
    }

    public void createNewList(String nameForList ){
        openMenu();
        this.waitForElementAndClick(By.id(CRATE_NEW_LIST_BUTTON),"Cannot find create_new_list", 5);
        this.waitForElementAndSendKeys(By.id(INPUT_FIELD_FOR_NEW_LIST), nameForList ,"Cannot find text input",5 );
        this.waitForElementAndClick(By.id(OK_BUTTON),"Cannot find OK button",5);
        this.assertElementHasText(By.id(FIELD_NAME_FOR_LIST), nameForList, "Cannot find new list" + nameForList,5);
        this.waitForElementAndClick(By.id(EXPLORE), "Cannot find explore", 5);

    }


}
