package ui.components.models;

import dto.SearchResultItem;
import exception.IncorrectTestDataException;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.DataProvider;
import java.util.List;
import java.util.Random;

import static support.web.WebElementHelper.*;
import static ui.components.locators.Locators.SearchResultPage.*;

public class SearchResultModel extends MainModel {

    public SearchResultModel(String languagePrefix) {
        super(languagePrefix);
    }

    private static final Logger logger = LoggerFactory.getLogger(SearchResultModel.class);

    @Step
    public SearchResultModel setTransactionType(String value) {
        selectByVisiableText(CMB_TRANSACTION_TYPE.get(), value);
        return this;
    }

    public SearchResultModel setTransactionType(DataProvider data) {
        setTransactionType(data.getData(languagePrefix, "transactionType"));
        return this;
    }

    @Step
    public SearchResultModel sortByPrice() {
        click(LNK_PRICE.get());
        return this;
    }

    @Step
    public SearchModel clickAtExtendedSearchLink() {
        click(LNK_EXTENDED_SEARCH.get());
        return new SearchModel(languagePrefix);
    }

    @Step
    public SearchResultModel selectRandomSearchResultsItemsFromRandomScreens(int iterationNumber, List<SearchResultItem> selectedItems) {
        for (int i = 0; i < iterationNumber; i++) {
            navigateToRandomPage();
            selectRandomItemOnCurrentPage(selectedItems);
        }
        return this;
    }

    @Step
    public SearchResultModel clickAddToBookmark(){
        click(LNK_ADD_TO_BOOKMARKS.get());
        click(BTN_ALERT_OK.get());
        return this;
    }

    private void navigateToRandomPage() {
        List<WebElement> pages = waitForElements(BTN_PAGE_NUMBERS.get());
        if (pages.size() > 0) {
            pages.get(new Random().nextInt(pages.size())).click();
        }
    }

    private void selectRandomItemOnCurrentPage(List<SearchResultItem> selectedItems) {
        List<WebElement> items = waitForElements(NOT_SELECTED_SEARCH_RESULT_ITEMS.get());
        if (items.size() > 0) {
            int randomNumberFromList = new Random().nextInt(items.size());
            WebElement randomItem = items.get(randomNumberFromList);
            selectItem(randomItem);
            String imageSrc = randomItem.findElement(LBL_SEARCH_RESULT_ITEM_IMG_SRC_REL.get()).getAttribute("src");
            String imageId = randomItem.findElement(LBL_SEARCH_RESULT_ITEM_IMG_ID_REL.get()).getAttribute("id");
            String category = randomItem.findElement(LBL_SEARCH_RESULT_ITEM_CATEGORY_REL.get()).getText();
            String description = randomItem.findElement(LBL_SEARCH_RESULT_ITEM_DESCRIPTION_REL.get()).getText();
            String price = randomItem.findElement(LBL_SEARCH_RESULT_ITEM_PRICE_REL.get()).getText();
            SearchResultItem searchResultRandomItemDTO = new SearchResultItem(imageSrc, imageId, category, description, price);
            selectedItems.add(searchResultRandomItemDTO);
        }else {
            throw new IncorrectTestDataException("Page does not contain unchecked items");
        }
    }

    private void selectItem(WebElement item) {
        item.click();
    }


}
