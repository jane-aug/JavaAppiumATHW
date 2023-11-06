package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class StartPageObject extends MainPageObject{
    private static final String
    SKIP_BUTTON = "org.wikipedia:id/fragment_onboarding_skip_button";
    public StartPageObject (AppiumDriver driver){
        super(driver);
    }
    public void skipOnboardingButton() {
        this.waitForElementAndClick(By.id(SKIP_BUTTON), "Cannot find and click skip button",5);


    }
}
