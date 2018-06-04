package smoke;

import context.TestContext;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.Test;

@Feature("Smoke")
public class WelcomePageTests extends TestContext {


    @Test
    @Story("Welcome Page")
    void verifyApplicationIsRunning() {
            open();
    }
}
