package config.webdriver;

import org.openqa.selenium.WebDriver;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DriverBase {

    private static List<DriverFactory> webDriverThreadPool = Collections.synchronizedList(new ArrayList<DriverFactory>());
    private static ThreadLocal<DriverFactory> driverFactory;

    static {
        driverFactory=ThreadLocal.withInitial(DriverFactory::new);
        webDriverThreadPool.add(driverFactory.get());
    }

    public static WebDriver getDriver() {
        return driverFactory.get().getDriver();
    }

    public static void clearCookies() {
        getDriver().manage().deleteAllCookies();
    }

    public static void closeDriverObjects() {
        for (DriverFactory driverFactory : webDriverThreadPool) {
            driverFactory.quitDriver();
        }
    }
}