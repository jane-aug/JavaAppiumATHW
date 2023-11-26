package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class StartPageObject extends MainPageObject{
    private static final String
    ANDROID_SKIP_BUTTON = "id:org.wikipedia:id/fragment_onboarding_skip_button",
    IOS_SKIP_BUTTON = "xpath://XCUIElementTypeButton[@name=\"Пропустить\"]";
    public StartPageObject (AppiumDriver driver){
        super(driver);
    }
    public void ANDROIDskipOnboardingButton() {
        this.waitForElementAndClick(ANDROID_SKIP_BUTTON, "Cannot find and click skip button",5);
    }

    public void iOSskipOnboardingButton() {
        this.waitForElementAndClick(IOS_SKIP_BUTTON, "Cannot find and click skip button",5);
    }

}
