import java.util.LinkedList;

public class chainedHash {
    private class Node {
        String key;
        String value;
        Node(String k, String v) { key = k; value = v; }
    }
    
    private LinkedList<Node>[] table;
    private int m;
    
    @SuppressWarnings("unchecked")
    public chainedHash(int tableSize) {
        this.m = tableSize;
        table = new LinkedList[m + 1];
        for (int i = 1; i <= m; i++) {
            table[i] = new LinkedList<>();
        }
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
            if (node.key.equals(key)) {
                return node.value;
            }
        }
        return null;
    }
    
    public boolean isInTable(String key) {
        return lookup(key) != null;
    }
}
