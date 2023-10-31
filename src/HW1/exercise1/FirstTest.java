package HW1.exercise1;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.net.URL;
import java.util.List;

public class FirstTest {
    public AppiumDriver driver;
    @Before
    public void setUp() throws Exception
    {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName","Android");
        capabilities.setCapability("deviceName","Pixel5");
        capabilities.setCapability("platformVersion","9");
        capabilities.setCapability("appPackage","org.wikipedia");
        capabilities.setCapability("appActivity",".main.MainActivity");
        capabilities.setCapability("automationName","Appium");
        capabilities.setCapability("app","/Users/sep/Desktop/JavaAppiumATHW/JavaAppiumATHW/apks/org.wikipedia.apk");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"),capabilities);

    }
    @After
    public void tearDown(){
        driver.quit();
    }
    @Test
    public void firstTest()
    {
        waitForElementAndClick(
                By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
                "Cannot find skip button",
                5
        );

        // Заменили этот код методом выше
        // WebElement element_skip_onboarding = driver.findElementById("org.wikipedia:id/fragment_onboarding_skip_button");
        //element_skip_onboarding.click();

        waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find Search Wikipedia",
                5
        );
        // Заменили этот код методом выше
        // WebElement element_Search_Wikipedia = driver.findElementByXPath("//*[contains(@text,'Search Wikipedia')]");
        //element_Search_Wikipedia.click();

        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/search_src_text"),
                "Java",
                "Cannot find search input",
                5
        );
        // Заменили этот код методом выше
        /* WebElement element_search_line = waitForElementPresentByID(
                "org.wikipedia:id/search_src_text",
                "Cannot find search input"
        );
        element_search_line.sendKeys("Java");*/


        waitForElementPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_container']//*[@text='Object-oriented programming language']"),
                "Cannot find Java"

        );
    }
@Test
public void SecondTest(){
    waitForElementAndClick(
            By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
            "Cannot find skip button",
            5
    );

    waitForElementAndClick(
            By.id("org.wikipedia:id/search_container"),
            "Cannot find Search Wikipedia input",
            5
    );
    waitForElementAndClick(
            By.xpath("//android.widget.ImageButton[@content-desc=\"Navigate up\"]"),
            "Cannot find Navigate <-",
            5
    );
    waitForElementNotPresent(
            By.xpath("//android.widget.ImageButton[@content-desc=\"Navigate up\"]"),
            "Navigate <- still present on page",
            5
    );
    }

    @Test
    public void testCompareArticleTitle(){
        waitForElementAndClick(
                By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
                "Cannot find skip button",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find Search Wikipedia",
                5
        );

        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/search_src_text"),
                "Java",
                "Cannot find search input",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_container']//*[@text='Object-oriented programming language']"),
                "Cannot find Java",
                5
        );

       WebElement title_element = waitForElementPresent(
                By.id("pcs-edit-section-title-description"),
                "Cannot find article title",
                10
        );
       String article_title = title_element.getAttribute("text");
        Assert.assertEquals(
                "We see unexpected title",
                "Object-oriented programming language",
                article_title

        );

    }

@Test
public void testCancelSearchAndDelete(){
    waitForElementAndClick(
            By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
            "Cannot find skip button",
            5
    );

    waitForElementAndClick(
            By.id("org.wikipedia:id/search_container"),
            "Cannot find Search Wikipedia input",
            5
    );
    waitForElementAndSendKeys(
            By.id("org.wikipedia:id/search_src_text"),
            "Java",
            "Cannot find search input",
            5
    );

    waitForElementAndClear(
            By.id("org.wikipedia:id/search_src_text"),
            "Cannot find search field",
            5
    );
    waitForElementAndClick(
            By.xpath("//android.widget.ImageButton[@content-desc=\"Navigate up\"]"),
            "Cannot find Navigate <-",
            5
    );
    waitForElementNotPresent(
            By.xpath("//android.widget.ImageButton[@content-desc=\"Navigate up\"]"),
            "Navigate <- still present on page",
            5
    );
}

// МЕТОДЫ
    //Общий метод для поиска элемента с передачей времени для таймаута
    public  WebElement waitForElementPresent(By by, String error_massage, long timeoutInSeconds)
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
    private boolean waitForElementNotPresent(By by, String error_massage,long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_massage + "\n");
        return wait.until(
                ExpectedConditions.invisibilityOfElementLocated(by)
        );
    }
    //  Метод ожидания элемента и его очистка
    private WebElement waitForElementAndClear(By by, String error_massage, long timeoutInSeconds)
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

    @Test
    public void ex2 (){
        waitForElementAndClick(
                By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
                "Cannot find skip button",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find Search Wikipedia",
                5
        );

        assertElementHasText(
                By.id("org.wikipedia:id/search_src_text"),
                "Search Wikipedia",
                "We see unexpected title",
                5
        );
    }

    // ex 3
    public List quantityElements(By by) {
        List elements_search = driver.findElements(by);
        return elements_search;
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
