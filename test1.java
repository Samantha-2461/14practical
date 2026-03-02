import java.util.*;

public class DataGenerator {
    public static class Pair {
        public String key;  // Make public for access in Timer
        public String value;
        public Pair(String k, String v) { key = k; value = v; }
    }

    public static Pair[] generateData() {
        int N = 1 << 20; // 2^20 = 1,048,576
        
        // Create array of keys
        Integer[] keys = new Integer[N];
        for (int i = 0; i < N; i++) keys[i] = i + 1;
        
        // Shuffle keys
        List<Integer> keyList = Arrays.asList(keys);
        Collections.shuffle(keyList);
        
        // Create key-value pairs
        Pair[] data = new Pair[N];
        for (int i = 0; i < N; i++) {
            String keyStr = keys[i].toString();
            String valueStr = Integer.toString(i + 1);
            data[i] = new Pair(keyStr, valueStr);
        }
        
        return data;
    }
}
// I Samantha Siwela student number 4546203 ,acknowledge that i used Google gemini to aid me with debugging my code, understanding some of the functions, knowing how i can import some of the functions. 
// and to understand what problem i need to tackle for the practical.
