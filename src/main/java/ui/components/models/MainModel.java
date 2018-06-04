package ui.components.models;

import static config.webdriver.DriverBase.getDriver;
import static support.web.WebElementHelper.*;

import io.qameta.allure.Step;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ui.components.locators.Locators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainModel {

    private static final Logger logger = LoggerFactory.getLogger(MainModel.class);
    protected String languagePrefix="";

    public MainModel(String languagePrefix){
        this.languagePrefix=languagePrefix.toLowerCase();
    }

    @Step
    public MainModel changeLanguage(){
            Pattern pattern = Pattern.compile("LG=([a-z]+)");

            boolean lenguageIsNotSelected = true;
            while (lenguageIsNotSelected) {
                Matcher matcher = pattern.matcher(getAttribute(Locators.MainPage.THN_META_LANGUAGE.get(), "content"));
                if (matcher.find() && !languagePrefix.equalsIgnoreCase(matcher.group(1))) {
                    click(Locators.MainPage.LNK_LANGUAGE.get());
                } else {
                    lenguageIsNotSelected = false;
                }

            }
        return this;
    }

    @Step
    public ElectronicModel goToElectronicPage(){
        click(Locators.MainPage.LNK_ELECTRONICS.get(languagePrefix));
        return new ElectronicModel(languagePrefix);
    }

    @Step
    public  SearchModel clickAtSearchLink(){
        click(Locators.MainPage.LNK_SEARCH.get());
        return new SearchModel(languagePrefix);
    }

    @Step
    public  BookmarksModel clickAtBookmarksLink(){
        jsClick(Locators.MainPage.LNK_BOOKMARKS.get(languagePrefix));
        return new BookmarksModel(languagePrefix);
    }
}
