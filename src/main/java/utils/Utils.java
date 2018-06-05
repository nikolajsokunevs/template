package utils;

public class Utils {

    public static boolean lessThan5Seconds(long startTime) {
        return (System.currentTimeMillis() - startTime) < 5000;
    }

}
