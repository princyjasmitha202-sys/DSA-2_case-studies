import java.util.*; 

 

public class SocialMediaPlatform { 

 

    // --- Segment Tree for Range Max Queries --- 

    static int[] tree; 

    static void build(int[] arr, int node, int start, int end) { 

        if (start == end) { tree[node] = arr[start]; return; } 

        int mid = (start + end) / 2; 

        build(arr, 2*node, start, mid); 

        build(arr, 2*node+1, mid+1, end); 

        tree[node] = Math.max(tree[2*node], tree[2*node+1]); 

    } 

    static int queryMax(int node, int start, int end, int l, int r) { 

        if (r < start || end < l) return Integer.MIN_VALUE; 

        if (l <= start && end <= r) return tree[node]; 

        int mid = (start + end) / 2; 

        return Math.max(queryMax(2*node, start, mid, l, r), 

                        queryMax(2*node+1, mid+1, end, l, r)); 

    } 

 

    // --- Activity Selection (Greedy) --- 

    static int activitySelection(int[] start, int[] end, int n) { 

        Integer[] idx = new Integer[n]; 

        for (int i = 0; i < n; i++) idx[i] = i; 

        Arrays.sort(idx, Comparator.comparingInt(i -> end[i])); 

        int count = 1, lastEnd = end[idx[0]]; 

        for (int i = 1; i < n; i++) 

            if (start[idx[i]] >= lastEnd) { count++; lastEnd = end[idx[i]]; } 

        return count; 

    } 

 

    public static void main(String[] args) { 

        int[] scores = {18, 17, 13, 19, 15, 11, 20}; // post engagement scores 

        int n = scores.length; 

        tree = new int[4 * n]; 

        build(scores, 1, 0, n-1); 

        System.out.println("Max in posts 1-5: " + queryMax(1, 0, n-1, 1, 5)); 

        System.out.println("Max in posts 0-6: " + queryMax(1, 0, n-1, 0, 6)); 

 

        int[] s = {1, 3, 0, 5, 8, 5}; 

        int[] e = {2, 4, 6, 7, 9, 9}; 

        System.out.println("Max sessions: " + activitySelection(s, e, 6)); 

    } 

} 

 
