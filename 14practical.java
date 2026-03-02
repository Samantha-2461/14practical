import java.util.LinkedList;


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
public class openHash {
    private class Entry {
        String key;
        String value;
        boolean deleted; 
        Entry(String k, String v) { key = k; value = v; deleted = false; }
    }
    
    private Entry[] table;
    private int m; 
    
    public openHash(int tableSize) {
        this.m = tableSize;
        table = new Entry[m + 1]; 
    }
    
    
    private int hash(String key) {
         
        int h = key.hashCode() & 0x7fffffff; 
        return (h % m) + 1;
    }
    
    
    public void insert(String key, String value) {
        int i = hash(key);
        int start = i;
        
        
        while (table[i] != null && !table[i].key.equals(key)) {
            i = (i % m) + 1; 
            if (i == start) return; 
        }
        
        if (table[i] == null) table[i] = new Entry(key, value);
        else table[i].value = value; 
    }
    
    public String lookup(String key) {
        int i = hash(key);
        int start = i;
        
        while (table[i] != null) {
            if (table[i].key.equals(key)) return table[i].value;
            i = (i % m) + 1;
            if (i == start) break;
        }
        return null;
    }
}


public class chainedHash {
    private class Node {
        String key;
        String value;
        Node(String k, String v) { key = k; value = v; }
    }
    
    private LinkedList<Node>[] table;
    private int m;
    
    public chainedHash(int tableSize) {
        this.m = tableSize;
        table = new LinkedList[m + 1];
        for (int i = 1; i <= m; i++) table[i] = new LinkedList<>();
    }
    
    
    private int hash(String key) {
        int h = key.hashCode() & 0x7fffffff;
        return (h % m) + 1;
    }
    
    public void insert(String key, String value) {
        int i = hash(key);
        
        
        for (Node node : table[i]) {
            if (node.key.equals(key)) {
                node.value = value; 
                return;
            }
        }
        
        
        table[i].add(new Node(key, value));
    }
    
    public String lookup(String key) {
        int i = hash(key);
        
        for (Node node : table[i]) {
            if (node.key.equals(key)) return node.value;
        }
        return null;
    }
    
    public boolean isInTable(String key) {
        return lookup(key) != null;
    }
}
public class Timer {
    public static long timeLookups(chainedHash hashTable, DataGenerator.Pair[] data, int numLookups) {
        long start = System.currentTimeMillis();
        
        
        for (int i = 0; i < numLookups; i++) {
            hashTable.lookup(data[i % data.length].key);
        }
        
        long end = System.currentTimeMillis();
        return end - start;
    }
    
    public static double averageTime(chainedHash hashTable, DataGenerator.Pair[] data, 
                                      int numLookups, int repetitions) {
        long total = 0;
        for (int r = 0; r < repetitions; r++) {
            total += timeLookups(hashTable, data, numLookups);
        }
        return total / (double) repetitions;
    }
}
public class HashExperiment {
    public static void main(String[] args) {
        
        DataGenerator.Pair[] allData = DataGenerator.generateData();
        int totalPairs = 950000; 
        DataGenerator.Pair[] data = Arrays.copyOf(allData, totalPairs);
        
        
        double[] loadFactors = {0.75, 0.80, 0.85, 0.90, 0.95};
        
        
        int[] tableSizes = {1048573, 1048571, 1048567, 1048559, 1048549}; // near 2^20
        
        System.out.println("Load Factor\tOpenHash (ms)\tChainedHash (ms)");
        
        for (int idx = 0; idx < loadFactors.length; idx++) {
            double lf = loadFactors[idx];
            int m = tableSizes[idx];
            

            int numItems = (int)(m * lf);
            DataGenerator.Pair[] testData = Arrays.copyOf(data, numItems);
            
            
            openHash openTable = new openHash(m);
            for (DataGenerator.Pair p : testData) {
                openTable.insert(p.key, p.value);
            }
            double openTime = Timer.averageTime(openTable, testData, 10000, 30);
            
            
            chainedHash chainTable = new chainedHash(m);
            for (DataGenerator.Pair p : testData) {
                chainTable.insert(p.key, p.value);
            }
            double chainTime = Timer.averageTime(chainTable, testData, 10000, 30);
            
            System.out.printf("%.2f\t\t%.2f\t\t%.2f\n", lf, openTime, chainTime);
        }
    }
}
private int hash(String key) {
    
    int h = 0;
    for (int i = 0; i < key.length(); i++) {
        h = 31 * h + key.charAt(i);
    }
    h = h & 0x7fffffff; 
    return (h % m) + 1;
}
// I Samantha Siwela student number 4546203 ,acknowledge that i used Google gemini to aid me with debugging my code, understanding some of the functions, knowing how i can import some of the functions. 
// and to understand what problem i need to tackle for the practical.
