package ui.components.models;

import dto.SearchResultItem;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ui.components.locators.Locators;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import static support.web.WebElementHelper.*;
import static ui.components.locators.Locators.BookmarkPage.*;
import static ui.components.locators.Locators.SearchResultPage.*;
import static ui.components.locators.Locators.SearchResultPage.LBL_SEARCH_RESULT_ITEM_PRICE_REL;

public class BookmarksModel extends MainModel{

    public BookmarksModel(String languagePrefix) {
        super(languagePrefix);
    }

    private static final Logger logger = LoggerFactory.getLogger(BookmarksModel.class);


    @Step
    public BookmarksModel verifyAllExpectedItemsAreDisplayed(List<SearchResultItem> selectedItems) {
        VerifyTotalCountEqualsToExpected(selectedItems);
        List<SearchResultItem> actualList=GetAllItemsFromTheScreen();
        Predicate<SearchResultItem> condition = e -> selectedItems.contains(e);
        Assertions.assertTrue(actualList.stream().allMatch(condition));
        return this;
    }

    private void VerifyTotalCountEqualsToExpected(List<SearchResultItem> selectedItems){
        Assertions.assertEquals(selectedItems.size(), waitForElements(ALL_ITEMS.get()).size());
    }

    private List<SearchResultItem> GetAllItemsFromTheScreen(){
        List<SearchResultItem> actualList=new ArrayList<>();
        List<WebElement> tables=waitForElements(LBL_TABLE_HEADER.get());
        for (WebElement table:tables){
            actualList.addAll(GetAllItemsFromTable(table));
        }
        return actualList;
    }

    private List<SearchResultItem> GetAllItemsFromTable(WebElement table){
        List<SearchResultItem> actualList=new ArrayList<>();
        List<WebElement> itemsInTable=table.findElements(By.xpath("./following-sibling::tr"));
        for (WebElement item:itemsInTable){
            actualList.add(ConvertWebElementItemToDto(item));
        }
        return actualList;
    }

    private SearchResultItem ConvertWebElementItemToDto(WebElement item){
        String imageSrc = item.findElement(LBL_BOOKMARK_ITEM_IMG_SRC_REL.get()).getAttribute("src");
        String imageId = item.findElement(LBL_BOOKMARK_ITEM_IMG_ID_REL.get()).getAttribute("id");
        String category = item.findElement(LBL_BOOKMARK_ITEM_CATEGORY_REL.get()).getText();
        String description = item.findElement(LBL_BOOKMARK_ITEM_DESCRIPTION_REL.get()).getText();
        String price = item.findElement(LBL_BOOKMARK_ITEM_PRICE_REL.get()).getText();
        return new SearchResultItem(imageSrc, imageId, category, description, price);
    }
}
