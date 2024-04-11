import java.util.Arrays;

public class Utils {
    
    public static int[] getIntArray(String str) {
        return Arrays.stream(str.split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();
    }
}
