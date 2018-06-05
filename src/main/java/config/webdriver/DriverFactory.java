package config.webdriver;


import config.ApplicationProperties;
import org.openqa.selenium.Platform;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;
import java.net.URL;

import static org.openqa.selenium.Proxy.ProxyType.MANUAL;

public class DriverFactory {

    private static final Logger logger = LoggerFactory.getLogger(DriverFactory.class);
    private final DriverType defaultDriverType = DriverType.valueOf(ApplicationProperties.getString(ApplicationProperties.ApplicationProperty.TARGET_BROWSER));
    private final String browser = System.getProperty("browser", defaultDriverType.toString());
    private final String operatingSystem = System.getProperty("os.name").toUpperCase();
    private final String systemArchitecture = System.getProperty("os.arch");
    private final boolean useRemoteWebDriver = ApplicationProperties.getBoolean(ApplicationProperties.ApplicationProperty.REMOTE_DRIVER);
    private final boolean proxyEnabled = ApplicationProperties.getBoolean(ApplicationProperties.ApplicationProperty.BROWSER_PROXY_ENABLED);
    private final String proxyHostname = ApplicationProperties.getString(ApplicationProperties.ApplicationProperty.PROXY_HOST);
    private final Integer proxyPort = ApplicationProperties.getInteger(ApplicationProperties.ApplicationProperty.PROXY_PORT);
    private final String proxyDetails = String.format("%s:%d", proxyHostname, proxyPort);
    private WebDriver webdriver;
    private DriverType selectedDriverType;

    public WebDriver getDriver() {
        if (null == webdriver) {
            Proxy proxy = null;
            if (proxyEnabled) {
                proxy = new Proxy();
                proxy.setProxyType(MANUAL);
                proxy.setHttpProxy(proxyDetails);
                proxy.setSslProxy(proxyDetails);
            }
            determineEffectiveDriverType();
            DesiredCapabilities desiredCapabilities = selectedDriverType.getDesiredCapabilities(proxy);
            instantiateWebDriver(desiredCapabilities);
        }

        return webdriver;
    }

    public void quitDriver() {
        if (null != webdriver) {
            webdriver.quit();
        }
    }

    private void determineEffectiveDriverType() {
        DriverType driverType = defaultDriverType;
        try {
            driverType = DriverType.valueOf(browser);
        } catch (IllegalArgumentException ignored) {
            logger.error("Unknown driver specified, defaulting to '" + driverType + "'...");
        } catch (NullPointerException ignored) {
            logger.error("No driver specified, defaulting to '" + driverType + "'...");
        }
        selectedDriverType = driverType;
    }

    private void instantiateWebDriver(DesiredCapabilities desiredCapabilities) {
        logger.info("Current Operating System: " + operatingSystem);
        logger.info("Current Architecture: " + systemArchitecture);
        try {

            if (useRemoteWebDriver) {
                String seleniumGridUrl = ApplicationProperties.getString(ApplicationProperties.ApplicationProperty.SELENIUM_GRID_URL);
                logger.info("Running remote webdriver with url - " + seleniumGridUrl);

                URL seleniumGridURL = new URL(seleniumGridUrl);
                String desiredBrowserVersion = ApplicationProperties.getString(ApplicationProperties.ApplicationProperty.DESIRED_BROWSER_VERSION);
                String desiredPlatform = ApplicationProperties.getString(ApplicationProperties.ApplicationProperty.DESIRED_PLATFORM);

                if (null != desiredPlatform && !desiredPlatform.isEmpty()) {
                    desiredCapabilities.setPlatform(Platform.valueOf(desiredPlatform.toUpperCase()));
                }

                if (null != desiredBrowserVersion && !desiredBrowserVersion.isEmpty()) {
                    desiredCapabilities.setVersion(desiredBrowserVersion);
                }
                webdriver = new RemoteWebDriver(seleniumGridURL, desiredCapabilities);

            } else {
                logger.info("Current Browser Selection: " + selectedDriverType);

                webdriver = selectedDriverType.getWebDriverObject(desiredCapabilities);
                webdriver.manage().window().maximize();
            }

        } catch (MalformedURLException e) {
            logger.error("Error parsing Grid Url + " + e.getMessage());
        }
    }
}

