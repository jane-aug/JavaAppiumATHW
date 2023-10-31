package HW1.exercise3;

import HW1.exercise1.FirstTest;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;


public class ex3 {
    // подключаем приватные методы из первого урока ( и пока что Appium)
    FirstTest FirstTest = new FirstTest();



    @Test
    public void exercise3() throws Exception {
        FirstTest.setUp();
        FirstTest.waitForElementAndClick(
                By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
                "Cannot find skip button",
                5
        );
        FirstTest.waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find Search Wikipedia",
                5
        );
        FirstTest.waitForElementAndSendKeys(
                By.id("org.wikipedia:id/search_src_text"),
                "Java",
                "Cannot find search input",
                5
        );

        FirstTest.waitForElementPresent(
                By.xpath("//*[contains(@text, 'Java')]"),
                "Cannot find specific search results",
                10
        );

        Assert.assertTrue("Cannot find specific search results on search page",
                FirstTest.quantityElements(By.xpath("//*[contains(@text, 'Java')]")).size() > 1);



        FirstTest.tearDown();
    }
}