package ui.components.locators;

import org.openqa.selenium.By;
import java.util.function.Function;
import static java.lang.String.format;

public class Locators {

    public enum MainPage {

        LBL_HEADER(By::className, "heading");

        private String id;
        private Function<String, By> function;

        MainPage(Function<String, By> function, String id) {
            this.function=function;
            this.id=id;
        }

        public By get(){
            return function.apply(id);
        }

        public By get(String value){
            return function.apply(format(id, value));
        }
    }
}
