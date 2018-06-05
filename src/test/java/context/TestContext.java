package context;

import config.ApplicationProperties;
import config.webdriver.DriverBase;
import io.qameta.allure.Attachment;
import io.qameta.allure.junit5.AllureJunit5AnnotationProcessor;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import static support.web.WebElementHelper.navigateToPage;

import ui.components.locators.Locators;
import ui.components.models.MainModel;

import java.io.File;
import java.io.IOException;

import static config.ApplicationProperties.ApplicationProperty.APP_URL;

@ExtendWith(AllureJunit5AnnotationProcessor.class)
@ExtendWith(TestContext.AfterTestExecution.class)
public class TestContext {

    protected WebDriver driver;

    @BeforeAll
    public static void instantiateDriverObject() {
        DriverBase.instantiateDriverObject();
    }

    @BeforeEach
    public void setUp(){
        driver = DriverBase.getDriver();
        driver.manage().window().maximize();
    }

    @AfterAll
    public static void closeDriverObjects() {
        DriverBase.closeDriverObjects();
    }

    @AfterEach
    public void clearCookies() throws Exception {
        DriverBase.clearCookies();
    }

    public MainModel open(String language){
        navigateToPage(ApplicationProperties.getString(APP_URL));
        MainModel mainModel=new MainModel(language);
        mainModel.changeLanguage();
        return mainModel;
    }



    public static class AfterTestExecution implements AfterTestExecutionCallback {

        @Override
        public void afterTestExecution(ExtensionContext context) throws Exception {
            Boolean isFailed = context.getExecutionException().isPresent();

            if (isFailed) {
                captureScreen(context.getTestMethod().get().getName() + context.getDisplayName());
            }
        }

        public String captureScreen(String testName) {
            String path;
            try {
                WebDriver driver = DriverBase.getDriver();
                byte[] source = makeScreenshotOnFailure(driver);
                path = "./target/screenshots/" + testName + System.currentTimeMillis() + ".png";
                FileUtils.writeByteArrayToFile(new File(path), source);
            } catch (IOException e) {
                path = "Failed to capture screenshot: " + e.getMessage();
            }
            return path;
        }

        @Attachment("Screenshot on failure")
        public byte[] makeScreenshotOnFailure(WebDriver driver) {
            return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        }
    }

}
