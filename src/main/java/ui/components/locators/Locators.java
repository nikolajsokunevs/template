package ui.components.locators;

import org.openqa.selenium.By;
import java.util.function.Function;
import static java.lang.String.format;

public class Locators {

    public enum MainPage {

        LNK_LANGUAGE(By::cssSelector, ".menu_lang>a.a_menu"),
        THN_META_LANGUAGE(By::xpath, "//meta[@http-equiv='set-cookie']"),
        LNK_ELECTRONICS(By::cssSelector, "h2>a[href='/%s/electronics/']"),
        LNK_SEARCH(By::cssSelector, ".page_header_menu>:nth-child(3)"),
        LNK_BOOKMARKS(By::cssSelector, "a[href='/%s/favorites/']");

        private String id;
        private Function<String, By> function;

        MainPage(Function<String, By> function, String id) {
            this.function = function;
            this.id = id;
        }

        public By get() {
            return function.apply(id);
        }

        public By get(String value) {
            return function.apply(format(id, value));
        }
    }

    public enum SearchPage {

        TXT_MAIN_INPUT(By::cssSelector, "#ptxt"),
        TXT_PRICE_MIN(By::xpath, "//td[text()='Min ']/input[1]"),
        TXT_PRICE_MAX(By::xpath, "//td[text()='Min ']/input[2]"),
        CMB_TRANSACTION_TYPE(By::name, "sid"),
        CMB_SEARCH_REGION(By::name, "search_region"),
        CMB_PERIOD(By::name, "pr"),
        CMB_SORT(By::name, "sort"),
        BTN_SEARCH(By::cssSelector, "#sbtn"),

        THN_PRELOAD_BLOCK(By::cssSelector, "#preload_txt"),
        LBL_PRELOAD_FIRST_ITEM(By::cssSelector, "#cmp_1"),;
        private String id;
        private Function<String, By> function;

        SearchPage(Function<String, By> function, String id) {
            this.function = function;
            this.id = id;
        }

        public By get() {
            return function.apply(id);
        }

        public By get(String value) {
            return function.apply(format(id, value));
        }
    }

    public enum SearchResultPage {

        CMB_TRANSACTION_TYPE(By::name, "sid"),
        LNK_PRICE(By::xpath, "//tr[@id='head_line']/td[2]//a"),
        LNK_EXTENDED_SEARCH(By::xpath, "//td[input[@name='btn']]/following-sibling::td/a[@class='a9a']"),
        LNK_ADD_TO_BOOKMARKS(By::cssSelector, "#a_fav_sel"),
        BTN_ALERT_OK(By::cssSelector, "#alert_ok"),
        NOT_SELECTED_SEARCH_RESULT_ITEMS(By::cssSelector, "#filter_frm ~ tr:not([style='display:none;']) td:nth-child(1) input:not(:checked)[type='checkbox']"),

        LBL_SEARCH_RESULT_ITEM_IMG_SRC_REL(By::xpath, "./../../td[2]/a/img"),
        LBL_SEARCH_RESULT_ITEM_IMG_ID_REL(By::xpath, "./../../td[2]/a"),
        LBL_SEARCH_RESULT_ITEM_CATEGORY_REL(By::xpath, "./../../td[3]/div[1]"),
        LBL_SEARCH_RESULT_ITEM_DESCRIPTION_REL(By::xpath, "./../../td[3]/div[2]/a"),
        LBL_SEARCH_RESULT_ITEM_PRICE_REL(By::xpath, "./../../td[4]/a"),
        BTN_PAGE_NUMBERS(By::cssSelector, "td[valign='top']>div.td2 a[name='nav_id'][rel='next']");
        private String id;
        private Function<String, By> function;

        SearchResultPage(Function<String, By> function, String id) {
            this.function = function;
            this.id = id;
        }

        public By get() {
            return function.apply(id);
        }

        public By get(String value) {
            return function.apply(format(id, value));
        }
    }

    public enum BookmarkPage {

        LBL_TABLE_HEADER(By::cssSelector, "#head_line"),
        ALL_ITEMS(By::cssSelector, "#filter_frm table tr:not([id='head_line']"),

        LBL_BOOKMARK_ITEM_IMG_SRC_REL(By::xpath, "./td[2]/a/img"),
        LBL_BOOKMARK_ITEM_IMG_ID_REL(By::xpath, "./td[2]/a"),
        LBL_BOOKMARK_ITEM_CATEGORY_REL(By::xpath, "./../tr[1]/td[1]"),
        LBL_BOOKMARK_ITEM_DESCRIPTION_REL(By::xpath, "./td[3]/div/a"),
        LBL_BOOKMARK_ITEM_PRICE_REL(By::xpath, "./td[last()]/a"),;
        private String id;
        private Function<String, By> function;

        BookmarkPage(Function<String, By> function, String id) {
            this.function = function;
            this.id = id;
        }

        public By get() {
            return function.apply(id);
        }

        public By get(String value) {
            return function.apply(format(id, value));
        }
    }
}
