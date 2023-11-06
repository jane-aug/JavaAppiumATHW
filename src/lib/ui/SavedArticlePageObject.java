package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class SavedArticlePageObject extends MainPageObject{
    private static final String
            SAVED = "org.wikipedia:id/nav_tab_reading_lists",
            MENU_BUTTON = "org.wikipedia:id/menu_overflow_button",
            CRATE_NEW_LIST_BUTTON = "org.wikipedia:id/reading_lists_overflow_create_new_list",
            INPUT_FIELD_FOR_NEW_LIST = "org.wikipedia:id/text_input",
            FIELD_NAME_FOR_LIST = "org.wikipedia:id/item_title",
            OK_BUTTON = "android:id/button1";



    public SavedArticlePageObject(AppiumDriver driver){
        super(driver);
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

    }
}
