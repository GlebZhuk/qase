package ui.test;

import driver.DriverSingleton;
import utils.TestListener;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Listeners;

@Listeners(TestListener.class)
public class BaseTest {
    @AfterClass
    public void stopBrowser() {
        DriverSingleton.getInstance().closeDriver();
    }
}
