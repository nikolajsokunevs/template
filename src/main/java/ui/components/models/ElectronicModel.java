package ui.components.models;

import io.qameta.allure.Step;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ui.components.locators.Locators;

import static support.web.WebElementHelper.click;

public class ElectronicModel extends MainModel{

    public ElectronicModel(String languagePrefix) {
        super(languagePrefix);
    }

    private static final Logger logger = LoggerFactory.getLogger(ElectronicModel.class);

}
