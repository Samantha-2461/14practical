import java.io.*;
import java.util.*;
import java.text.*;

public class TimeMethods {
    
  
    static class Node {
        int key;
        String data;
        
        Node(int key, String data) {
            this.key = key;
            this.data = data;
        }
    }
    
    public static void main(String[] args) {
      
        DecimalFormat twoD = new DecimalFormat("0.00");
        DecimalFormat fourD = new DecimalFormat("0.0000");
        
       
        Node[] array = readFile("Ulysses.numbered");
        
        
        Node[] sortedArray = array.clone();
        sortArray(sortedArray); 
        
        int[] keysToFind = generateRandomKeys(30, 1, 32654);
        
        
        System.out.println("Testing Linear Search...");
        double[] linearResults = runTests(array, keysToFind, true);
        
        
        System.out.println("Testing Binary Search...");
        double[] binaryResults = runTests(sortedArray, keysToFind, false);
        
        
        System.out.printf("\n\n\fStatistics\n");
        System.out.println("________________________________________________");
        System.out.println("  Average time: " + fourD.format(linearResults[0]) + " ms");
        System.out.println("  Std Deviation: " + fourD.format(linearResults[1]) + " ms");
        System.out.println("\nBinary Search:");
        System.out.println("  Average time: " + fourD.format(binaryResults[0]) + " ms");
        System.out.println("  Std Deviation: " + fourD.format(binaryResults[1]) + " ms");
    }
    
    
    static Node[] readFile(String filename) {
       
    }
    
   
    static void sortArray(Node[] arr) {
       
        Arrays.sort(arr, (a, b) -> a.key - b.key);
    }
    
    
    static int[] generateRandomKeys(int count, int min, int max) {
        int[] keys = new int[count];
        Random rand = new Random();
        for(int i = 0; i < count; i++) {
            keys[i] = rand.nextInt(max - min + 1) + min;
        }
        return keys;
    }
    
    
    static double[] runTests(Node[] array, int[] keys, boolean useLinear) {
        int repetitions = keys.length;
        double totalTime = 0;
        double totalTimeSquared = 0;
        
        for(int i = 0; i < repetitions; i++) {
            long start = System.currentTimeMillis();
            
            
            if(useLinear) {
                linearSearch(array, keys[i]);
            } else {
                binarySearch(array, keys[i]);
            }
            
            long end = System.currentTimeMillis();
            double time = (double)(end - start);
            
            totalTime += time;
            totalTimeSquared += (time * time);
        }
        
       
        double average = totalTime / repetitions;
        double variance = (totalTimeSquared / repetitions) - (average * average);
        double stdDev = Math.sqrt(variance);
        
        return new double[]{average, stdDev};
    }
    
  
    static Node linearSearch(Node[] array, int targetKey) {
        for(int i = 0; i < array.length; i++) {
            if(array[i].key == targetKey) {
                return array[i];
            }
        }
        return null;
    }
    
   
    static Node binarySearch(Node[] array, int targetKey) {
        int left = 0;
        int right = array.length - 1;
        
        while(left <= right) {
            int mid = left + (right - left) / 2;
            
            if(array[mid].key == targetKey) {
                return array[mid];
            }
            
            if(array[mid].key < targetKey) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return null;
    }
}

// I Samantha Siwela student number 4546203 ,acknowledge that i used Google gemini to aid me with debugging my code, understanding some of the functions, knowing how i can import some of the functions. 
// and to understand what problem i need to tackle for the practical.
