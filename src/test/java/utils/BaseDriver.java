package utils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.net.URL;

public class BaseDriver extends AbstractTestNGCucumberTests {
    protected AppiumDriver<MobileElement> driver;
    protected URL appiumUrl;
    private String deviceName;
    private String platformVersion;
    private String UDID;

    public void startApp() {
        UDID = SystemUtils.getRandomConnectedDeviceID();
        deviceName = SystemUtils.runCommandAndReturnOutput("adb -s " + UDID + " shell getprop ro.product.model ro.product.brand");
        platformVersion = SystemUtils.runCommandAndReturnOutput("adb -s " + UDID + " shell getprop ro.build.version.release");

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("newCommandTimeout", 20);
        capabilities.setCapability("udid", UDID);
        capabilities.setCapability("automationName", "UiAutomator2");
        capabilities.setCapability("platformVersion", platformVersion);
        capabilities.setCapability("deviceName", deviceName);
        capabilities.setCapability("app", "original.apk");
//        capabilities.setCapability("appActivity", "io.appium.android.apis");
        capabilities.setCapability("appPackage", "io.appium.android.apis");
        driver = new AndroidDriver<>(appiumUrl, capabilities);
    }


    @BeforeSuite
    public void startAppium() {
        AppiumServerController.getInstance().startAppiumServer();
        appiumUrl = AppiumServerController.getInstance().service.getUrl();
    }

    @BeforeMethod
    public void startAppBefore() {
        startApp();
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @AfterSuite
    public void stopAppium() {
        AppiumServerController.getInstance().stopAppiumServer();
    }

}