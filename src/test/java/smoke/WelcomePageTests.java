package smoke;

import context.TestContext;
import dto.SearchResultItem;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import java.util.ArrayList;
import java.util.List;

@Feature("Smoke")
public class WelcomePageTests extends TestContext {
    
    @ParameterizedTest
    @ValueSource(strings = {"RU"})
    @Story("Welcome Page")
    void verifyApplicationIsRunning(String language) {

        List<SearchResultItem> randomSearchResultsFromRandomScreens = new ArrayList<>();

        open(language)
                .goToElectronicPage()
                .clickAtSearchLink()
                .setSearchByTextValue("Компьютер")
                .setLocation("Рига")
                .clickSearchButton()
                .setTransactionType("Продажа")
                .sortByPrice()
                .clickAtExtendedSearchLink()
                .setMinAndMaxPrice(0, 300)
                .clickSearchButton()
                .selectRandomSearchResultsItemsFromRandomScreens(5, randomSearchResultsFromRandomScreens)
                .clickAddToBookmark()
                .clickAtBookmarksLink()
                .verifyAllExpectedItemsAreDisplayed(randomSearchResultsFromRandomScreens);
    }
}
