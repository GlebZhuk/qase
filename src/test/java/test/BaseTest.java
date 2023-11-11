package test;

import driver.DriverSingleton;
import driver.TestListener;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.Listeners;

@Listeners(TestListener.class)
public class BaseTest {
    @AfterSuite
    public void stopBrowser() {
        DriverSingleton.getInstance().closeDriver();
    }
}
