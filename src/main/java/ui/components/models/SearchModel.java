package ui.components.models;

import io.qameta.allure.Step;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import static ui.components.locators.Locators.SearchPage.*;
import utils.DataProvider;

import static support.web.WebElementHelper.*;

public class SearchModel extends MainModel {

    public SearchModel(String languagePrefix) {
        super(languagePrefix);
    }

    private static final Logger logger = LoggerFactory.getLogger(SearchModel.class);

    @Step
    public SearchModel setSearchByTextValue(String value) {
        sendKeys(TXT_MAIN_INPUT.get(), value);
        //Close preload with JS
        waitForElement(THN_PRELOAD_BLOCK_DISPLAYED.get());
        sleep(300);
        executeJS("show_s_dv('none',1);");
        waitForInivsibilityOfElement(LBL_PRELOAD_FIRST_ITEM.get());
        return this;
    }

    public SearchModel setSearchByTextValue(DataProvider data) {
        return setSearchByTextValue(data.getData(languagePrefix, "searchText"));
    }

    @Step
    public SearchModel setLocation(String value) {
        selectByVisiableText(CMB_SEARCH_REGION.get(), value);
        return this;
    }

    public SearchModel setLocation(DataProvider data) {
        setLocation(data.getData(languagePrefix, "location"));
        return this;
    }

    @Step
    public SearchModel setMinAndMaxPrice(String min, String max) {
        sendKeys(TXT_PRICE_MIN.get(), min);
        sendKeys(TXT_PRICE_MAX.get(), max);
        return this;
    }

    public SearchModel setMinAndMaxPrice(int min, int max) {
        return setMinAndMaxPrice(String.valueOf(min), String.valueOf(max));
    }

    @Step
    public SearchResultModel clickSearchButton() {
        click(BTN_SEARCH.get());
        return new SearchResultModel(languagePrefix);
    }


}
