package dto;

import java.util.HashMap;
import java.util.Map;

public class TestData {

    private Map<String,Map<String,String>> data = new HashMap<String,Map<String,String>>();

    public TestData(Map<String,Map<String,String>> data){
        this.data=data;
    }
}
