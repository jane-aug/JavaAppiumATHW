package lib.ui;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ArticlePageObject extends MainPageObject{

    private static final String
            TITLE = "pcs-edit-section-title-description",
            FOOTER_ELEMENT = "pcs-footer-container-menu",
            MENU_BUTTON = "org.wikipedia:id/page_toolbar_button_show_overflow_menu",
            CUSTOMIZE_BUTTON = "org.wikipedia:id/customize_toolbar",
            SAVE_MENU = "//android.widget.TextView[@text='Save']";

    ;
    public ArticlePageObject(AppiumDriver driver){
        super(driver);
    }

    public WebElement waitForTitleElement() {
        return this.waitForElementPresent(By.id(TITLE),"Cannot find article title", 10);
    }

    public String getArticleTitle() {
        WebElement title_element = waitForTitleElement();
        return  title_element.getAttribute("text");
    }

    public void swipeToFooter(){
        this.swipeUpToFindElement(By.id(FOOTER_ELEMENT),"Did not find footer", 20);
    }

    public void openMenu(){
        this.waitForElementAndClick(By.id(MENU_BUTTON), "Cannot find menu_overflow_button", 5);
    }


    public void dragAndDrop(By by){
       TouchAction action = new TouchAction(driver);
       WebElement  ele = this.waitForElementPresent(by, "Cannot find Save",5);
       action.longPress(ele).moveTo( 1000, 1460).waitAction(3000).release().perform();
    }


    public void changeMenu(By by){
        openMenu();
        this.waitForElementAndClick(By.id(CUSTOMIZE_BUTTON),"Cannot find customize_toolbar",5);
        this.waitForElementPresent(By.xpath(SAVE_MENU),"Cannot find save menu", 5);
        dragAndDrop(by);

    }
    public void saveArticle(){
        openMenu();

    }
}
