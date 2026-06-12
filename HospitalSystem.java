import java.util.*; 

 

public class HospitalSystem { 

 

    // --- Min-Heap for Patient Priority --- 

    static int[] heap = new int[100]; 

    static int heapSize = 0; 

 

    static void insertPatient(int severity) { 

        heap[heapSize] = severity; 

        int i = heapSize++; 

        while (i > 0 && heap[(i-1)/2] > heap[i]) { 

            int tmp = heap[i]; heap[i] = heap[(i-1)/2]; heap[(i-1)/2] = tmp; 

            i = (i-1)/2; 

        } 

    } 

    static int serveNextPatient() { 

        int min = heap[0]; 

        heap[0] = heap[--heapSize]; 

        int i = 0; 

        while (true) { 

            int l = 2*i+1, r = 2*i+2, small = i; 

            if (l < heapSize && heap[l] < heap[small]) small = l; 

            if (r < heapSize && heap[r] < heap[small]) small = r; 

            if (small == i) break; 

            int tmp = heap[i]; heap[i] = heap[small]; heap[small] = tmp; 

            i = small; 

        } 

        return min; 

    } 

 

    // --- Trie for Patient Name Autocomplete --- 

    static class TrieNode { 

        Map<Character, TrieNode> children = new HashMap<>(); 

        boolean isEnd = false; 

    } 

    static TrieNode trieRoot = new TrieNode(); 

 

    static void insertName(String name) { 

        TrieNode node = trieRoot; 

        for (char c : name.toCharArray()) { 

            node.children.putIfAbsent(c, new TrieNode()); 

            node = node.children.get(c); 

        } 

        node.isEnd = true; 

    } 

    static boolean searchPrefix(String prefix) { 

        TrieNode node = trieRoot; 

        for (char c : prefix.toCharArray()) { 

            if (!node.children.containsKey(c)) return false; 

            node = node.children.get(c); 

        } 

        return true; 

    } 

 

    public static void main(String[] args) { 

        // Min-Heap: severity 1 = most critical 

        insertPatient(5); insertPatient(2); insertPatient(8); insertPatient(1); 

        System.out.println("Serving patient severity: " + serveNextPatient()); // 1 

        System.out.println("Serving patient severity: " + serveNextPatient()); // 2 

 

        // Trie: autocomplete patient names 

        insertName("Alice"); insertName("Alvin"); insertName("Bob"); insertName("Bobby"); 

        System.out.println("Prefix 'Al' found: " + searchPrefix("Al")); // true 

        System.out.println("Prefix 'Bo' found: " + searchPrefix("Bo")); // true 

        System.out.println("Prefix 'Xy' found: " + searchPrefix("Xy")); // false 

    } 

} 