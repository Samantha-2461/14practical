public class DataGenerator {
    public static class Pair {
        String key;
        String value;
        Pair(String k, String v) { key = k; value = v; }
    }
    
    public static Pair[] generateData() {
        int N = 1 << 20; 
        
        
        Integer[] keys = new Integer[N];
        for (int i = 0; i < N; i++) keys[i] = i + 1;
        
        
        Collections.shuffle(Arrays.asList(keys));
        
        
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
