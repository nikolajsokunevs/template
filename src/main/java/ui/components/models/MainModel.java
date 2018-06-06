package ui.components.models;

import static support.web.WebElementHelper.*;
import static utils.Utils.*;

import exception.IncorrectTestDataException;
import io.qameta.allure.Step;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static ui.components.locators.Locators.MainPage.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainModel {

    private static final Logger logger = LoggerFactory.getLogger(MainModel.class);
    protected String languagePrefix = "";

    public MainModel(String languagePrefix) {
        this.languagePrefix = languagePrefix.toLowerCase();
    }

    @Step
    public MainModel changeLanguage() {
        Pattern pattern = Pattern.compile("LG=([a-z]+)");

        boolean languageIsNotSelected = true;
        long startTime = System.currentTimeMillis();

        while (languageIsNotSelected && lessThan5Seconds(startTime)) {
            Matcher matcher = pattern.matcher(getAttribute(THN_META_LANGUAGE.get(), "content"));
            if (matcher.find() && !languagePrefix.equalsIgnoreCase(matcher.group(1))) {
                click(LNK_LANGUAGE.get());
            } else {
                languageIsNotSelected = false;
            }
        }
        if (languageIsNotSelected) {
            throw new IncorrectTestDataException(String.format("Language '%s' is not supported by ss.com, please select 'RU' or 'LV' language", languagePrefix));
        }
        return this;
    }

    @Step
    public ElectronicModel goToElectronicPage() {
        click(LNK_ELECTRONICS.get(languagePrefix));
        return new ElectronicModel(languagePrefix);
    }

    @Step
    public SearchModel clickAtSearchLink() {
        click(LNK_SEARCH.get());
        return new SearchModel(languagePrefix);
    }

    @Step
    public BookmarksModel clickAtBookmarksLink() {
        jsClick(LNK_BOOKMARKS.get(languagePrefix));
        return new BookmarksModel(languagePrefix);
    }
}
