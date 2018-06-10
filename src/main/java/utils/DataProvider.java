package utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import exception.IncorrectTestDataException;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class DataProvider {

    private Map<String, Map<String, String>> testData = new HashMap<String, Map<String, String>>();

    public DataProvider(String name) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            testData = mapper.readValue(new File("./src/test/resources/" + name + ".json"), new TypeReference<Map<String, Map<String, String>>>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getData(String dataset, String field) {
        try {
            return testData.get(dataset).get(field);
        } catch (NullPointerException er) {
            throw new IncorrectTestDataException(String.format("Test data does not contains field '%s' in dataset '%s'", field, dataset));
        }
    }

}
