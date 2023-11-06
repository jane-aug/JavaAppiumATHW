package lib.ui;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;

public class MainPageObject {
    protected AppiumDriver driver;

    public MainPageObject(AppiumDriver driver) {
        this.driver = driver;
    }

    //Общий метод для поиска элемента с передачей времени для таймаута
    public WebElement waitForElementPresent(By by, String error_massage, long timeoutInSeconds)
    {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_massage + "\n");
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );
    }

    //Общий метод для поиска элемента  с таймаутом 5 секунд
    public WebElement waitForElementPresent(By by, String error_massage)
    {
        return this.waitForElementPresent(by, error_massage, 5);
    }

    //Общий метод для поиска элемента с передачей времени для таймаута и клика
    public WebElement waitForElementAndClick(By by, String error_massage, long timeoutInSeconds){
        WebElement element = waitForElementPresent(by,error_massage, timeoutInSeconds);
        element.click();
        return element;
    }


    //Общий метод для поиска элемента с передачей времени для таймаута и отправки ключей
    public WebElement waitForElementAndSendKeys(By by, String value, String error_massage, long timeoutInSeconds){
        WebElement element = waitForElementPresent(by,error_massage, timeoutInSeconds);
        element.sendKeys(value);
        return element;
    };


    //Общий метод проверяющий отсутствие элемента на странице
    public boolean waitForElementNotPresent(By by, String error_massage, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_massage + "\n");
        return wait.until(
                ExpectedConditions.invisibilityOfElementLocated(by)
        );
    }
    //  Метод ожидания элемента и его очистка
    public WebElement waitForElementAndClear(By by, String error_massage, long timeoutInSeconds)
    {
        WebElement element =  waitForElementPresent(by,error_massage, timeoutInSeconds);
        element.clear();
        return element;
    }

    //Ex 2
    public WebElement assertElementHasText(By by, String value, String error_massage, long timeoutInSeconds){
        WebElement element =  waitForElementPresent(by,error_massage, timeoutInSeconds);
        String element_name = element.getAttribute("text");
        Assert.assertEquals(
                error_massage,
                value,
                element_name

        );
        return element;
    }


    // ex 3
    public List quantityElements(By by) {
        List elements_search = driver.findElements(by);
        return elements_search;
    }

    // метод свайп по относительным координатам
    public void swipeUp(int timeOfSwipe){
        TouchAction action = new TouchAction(driver);
        Dimension size = driver.manage().window().getSize();
        int x = size.width / 2;
        int start_y = (int) (size.height * 0.8);
        int end_y = (int) (size.height * 0.2);

        action.press(x, start_y).waitAction(timeOfSwipe).moveTo(x, end_y).release().perform();
    }

    public void swipeUpQuick(){
        swipeUp(200);
    }

    public void swipeUpToFindElement (By by, String error_massage, int max_swipes){
        int already_swiped = 0;
        while (driver.findElements(by).size() == 0) {
            if(already_swiped > max_swipes){
                waitForElementPresent(by, "Cannot find element by swiping up. \n"+ error_massage, 0);
                return;
            }
            swipeUpQuick();
            ++already_swiped;
        }
    }

    public void tuochByCoordinate(int x,int y){
        TouchAction action = new TouchAction(driver);
        action.press(x,y).release().perform();
    }

        /*
    //метод для поиска по ByXpath с передачей времени для таймаута
    private WebElement waitForElementPresentByXpath(String xpath,String error_massage,long timeoutInSeconds)
    {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_massage + "\n");
        By by = By.xpath(xpath);
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );
    }


    //метод для поиска по ByXpath с таймаутом 5 секунд
    private WebElement waitForElementPresentByXpath(String xpath,String error_massage)
    {
       return waitForElementPresentByXpath(xpath, error_massage, 5);
    }


    //метод для поиска по ByID с передачей времени для таймаута
    private WebElement waitForElementPresentByID(String id,String error_massage,long timeoutInSeconds)
    {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_massage + "\n");
        By by = By.id(id);
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );
    }


    //метод для поиска по ByID с таймаутом 5 секунд
    private WebElement waitForElementPresentByID(String id,String error_massage)
    {
        return waitForElementPresentByID(id, error_massage, 5);
    }

    //метод для поиска по ByXpath с передачей времени для таймаута и клика
    private WebElement waitForElementByXpathAndClick(String xpath,String error_massage,long timeoutInSeconds){
        WebElement element = waitForElementPresentByXpath(xpath,error_massage, timeoutInSeconds);
        element.click();
        return element;
    }

    //метод для поиска по ByID с передачей времени для таймаута и клика
    private WebElement waitForElementByIDAndClick(String id,String error_massage,long timeoutInSeconds){
        WebElement element = waitForElementPresentByID(id,error_massage, timeoutInSeconds);
        element.click();
        return element;
    }


    //метод для поиска по ByXpath с передачей времени для таймаута и отправки ключей
    private WebElement waitForElementByXpathAndSendKeys(String xpath,String value, String error_massage,long timeoutInSeconds){
        WebElement element = waitForElementPresentByXpath(xpath,error_massage, timeoutInSeconds);
        element.sendKeys(value);
        return element;
    }


    //метод для поиска по ByID с передачей времени для таймаута и отправки ключей
    private WebElement waitForElementByIDAndSendKeys(String id,String value, String error_massage,long timeoutInSeconds){
        WebElement element = waitForElementPresentByID(id,error_massage, timeoutInSeconds);
        element.sendKeys(value);
        return element;
    }


    // метод проверяющий отсутствие элемента на странице ByXpath
    private boolean waitForElementNotPresent(String xpath, String error_massage,long timeoutInSeconds){
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_massage + "\n");
        By by = By.xpath(xpath);
        return wait.until(
                ExpectedConditions.invisibilityOfElementLocated(by)
        );
    };


    // метод проверяющий отсутствие элемента на странице ByID
    private boolean waitForElementByIDNotPresent(String id, String error_massage,long timeoutInSeconds){
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_massage + "\n");
        By by = By.id(id);
        return wait.until(
                ExpectedConditions.invisibilityOfElementLocated(by)
        ); */

}
