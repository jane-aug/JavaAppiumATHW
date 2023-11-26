package lib;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import junit.framework.TestCase;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.net.URL;

public class CoreTestCase extends TestCase {
    private static final String PLATFORM_IOS = "ios";
    private static final String PLATFORM_ANDROID = "android";

    protected AppiumDriver driver;
    private static String AppiumURL = "http://127.0.0.1:4723/";

    @Override
    protected void setUp() throws Exception
    {
        super.setUp();

        DesiredCapabilities capabilities = this.getCapabilitiesByPlatformEnv();
//        driver = new AndroidDriver(new URL(AppiumURL),capabilities);
        driver = getDriverByPlatormenv(capabilities);
        driver.rotate(ScreenOrientation.PORTRAIT);
    }

    @Override
    protected void tearDown() throws Exception
    {
        driver.quit();
        super.tearDown();
    }

    private DesiredCapabilities getCapabilitiesByPlatformEnv() throws Exception {
        String platform = System.getenv("PLATFORM");
        DesiredCapabilities capabilities = new DesiredCapabilities();
        if(platform.equals(PLATFORM_ANDROID)){
            capabilities.setCapability("platformName","Android");
            capabilities.setCapability("appium:deviceName","Pixel5");
            capabilities.setCapability("appium:platformVersion","9");
            capabilities.setCapability("appium:appPackage","org.wikipedia");
            capabilities.setCapability("appium:appActivity",".main.MainActivity");
            capabilities.setCapability("appium:automationName","UiAutomator2");
            capabilities.setCapability("appium:app","/Users/sep/Desktop/JavaAppiumATHWCOPY/JavaAppiumATHW/apks/org.wikipedia.apk");

        } else if (platform.equals(PLATFORM_IOS)) {
            capabilities.setCapability("platformName", "iOS");
            capabilities.setCapability("appium:deviceName", "iPhone 11");
            capabilities.setCapability("appium:platformVersion", "15.0");
            capabilities.setCapability("appium:automationName", "XCUITest");
            capabilities.setCapability("appium:app", "/Users/sep/Desktop/JavaAppiumATHWCOPY/JavaAppiumATHW/apks/Wikipedia.app");
        } else {
            throw  new Exception("Cannot get run platform from env varible. Platform value " + platform);
        }
        return capabilities;
    }
    private AppiumDriver getDriverByPlatormenv(DesiredCapabilities capabilities) throws Exception {
        String platform = System.getenv("PLATFORM");
        if(platform.equals(PLATFORM_ANDROID)){
            driver = new AndroidDriver(new URL(AppiumURL),capabilities);
        } else if (platform.equals(PLATFORM_IOS)) {
            driver = new IOSDriver(new URL(AppiumURL),capabilities);;
        } else{
            throw  new Exception("Cannot get run platform and driver from env varible. Platform value " + platform);
        }
        return driver;
    }
}
