public class openHash {
    private class Entry {
        String key;
        String value;
        Entry(String k, String v) { key = k; value = v; }
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
        
        
        while (table[i] != null) {
            if (table[i].key.equals(key)) {
                table[i].value = value; 
                return;
            }
            i = (i % m) + 1; 
            if (i == start) return; 
        }
        
        
        table[i] = new Entry(key, value);
    }
    
    public String lookup(String key) {
        int i = hash(key);
        int start = i;
        
        while (table[i] != null) {
            if (table[i].key.equals(key)) {
                return table[i].value;
            }
            i = (i % m) + 1;
            if (i == start) break;
        }
        return null;
    }
}
