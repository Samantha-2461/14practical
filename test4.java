public class Timer {
    
    public static long timeLookups(openHash hashTable, DataGenerator.Pair[] data, int numLookups) {
        long start = System.currentTimeMillis();
        
        for (int i = 0; i < numLookups; i++) {
            hashTable.lookup(data[i % data.length].key);
        }
        
        long end = System.currentTimeMillis();
        return end - start;
    }
    
    
    public static long timeLookups(chainedHash hashTable, DataGenerator.Pair[] data, int numLookups) {
        long start = System.currentTimeMillis();
        
        for (int i = 0; i < numLookups; i++) {
            hashTable.lookup(data[i % data.length].key);
        }
        
        long end = System.currentTimeMillis();
        return end - start;
    }
    
    public static double averageTime(openHash hashTable, DataGenerator.Pair[] data,
                                     int numLookups, int repetitions) {
        long total = 0;
        for (int r = 0; r < repetitions; r++) {
            total += timeLookups(hashTable, data, numLookups);
        }
        return total / (double) repetitions;
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
