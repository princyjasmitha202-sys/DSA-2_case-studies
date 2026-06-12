import java.util.Arrays; 

 

public class OrderFulfillment { 

 

    // --- Heap Sort --- 

    static void heapify(int[] arr, int n, int i) { 

        int largest = i, l = 2*i+1, r = 2*i+2; 

        if (l < n && arr[l] > arr[largest]) largest = l; 

        if (r < n && arr[r] > arr[largest]) largest = r; 

        if (largest != i) { 

            int tmp = arr[i]; arr[i] = arr[largest]; arr[largest] = tmp; 

            heapify(arr, n, largest); 

        } 

    } 

    static void heapSort(int[] arr) { 

        int n = arr.length; 

        for (int i = n/2-1; i >= 0; i--) heapify(arr, n, i); 

        for (int i = n-1; i > 0; i--) { 

            int tmp = arr[0]; arr[0] = arr[i]; arr[i] = tmp; 

            heapify(arr, i, 0); 

        } 

    } 

 

    // --- Radix Sort (for order IDs) --- 

    static void countingSort(int[] arr, int exp) { 

        int n = arr.length; 

        int[] output = new int[n], count = new int[10]; 

        for (int x : arr) count[(x / exp) % 10]++; 

        for (int i = 1; i < 10; i++) count[i] += count[i-1]; 

        for (int i = n-1; i >= 0; i--) { 

            output[--count[(arr[i] / exp) % 10]] = arr[i]; 

        } 

        System.arraycopy(output, 0, arr, 0, n); 

    } 

    static void radixSort(int[] arr) { 

        int max = Arrays.stream(arr).max().getAsInt(); 

        for (int exp = 1; max / exp > 0; exp *= 10) 

            countingSort(arr, exp); 

    } 

 

    // --- 0/1 Knapsack (Dynamic Programming) --- 

    static int knapsack(int W, int[] wt, int[] val, int n) { 

        int[][] dp = new int[n+1][W+1]; 

        for (int i = 1; i <= n; i++) 

            for (int w = 0; w <= W; w++) 

                dp[i][w] = (wt[i-1] <= w) 

                    ? Math.max(dp[i-1][w], val[i-1] + dp[i-1][w-wt[i-1]]) 

                    : dp[i-1][w]; 

        return dp[n][W]; 

    } 

 

    public static void main(String[] args) { 

        int[] priorities = {34, 7, 23, 32, 5, 62}; 

        heapSort(priorities); 

        System.out.println("Heap Sorted: " + Arrays.toString(priorities)); 

 

        int[] orderIds = {170, 45, 75, 90, 802, 24, 2, 66}; 

        radixSort(orderIds); 

        System.out.println("Radix Sorted: " + Arrays.toString(orderIds)); 

 

        int[] weights = {2, 3, 4, 5}; 

        int[] values  = {3, 4, 5, 6}; 

        System.out.println("Max Knapsack Value: " + knapsack(5, weights, values, 4)); 

    } 

} 