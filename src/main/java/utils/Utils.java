package utils;

public class Utils {

    public static boolean lessThan10Seconds(long startTime){
        return (System.currentTimeMillis()-startTime)<10000;
    }

}
