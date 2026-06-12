import java.util.*; 

 

public class LogisticsRouter { 

 

    // --- Dijkstra's Algorithm (non-negative weights) --- 

    static int[] dijkstra(int[][] graph, int src, int n) { 

        int[] dist = new int[n]; 

        boolean[] visited = new boolean[n]; 

        Arrays.fill(dist, Integer.MAX_VALUE); 

        dist[src] = 0; 

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0])); 

        pq.offer(new int[]{0, src}); 

        while (!pq.isEmpty()) { 

            int[] curr = pq.poll(); 

            int u = curr[1]; 

            if (visited[u]) continue; 

            visited[u] = true; 

            for (int v = 0; v < n; v++) { 

                if (graph[u][v] != 0 && dist[u] != Integer.MAX_VALUE 

                        && dist[u] + graph[u][v] < dist[v]) { 

                    dist[v] = dist[u] + graph[u][v]; 

                    pq.offer(new int[]{dist[v], v}); 

                } 

            } 

        } 

        return dist; 

    } 

 

    // --- Bellman-Ford (handles negative weights) --- 

    static int[] bellmanFord(int[][] edges, int src, int n, int E) { 

        int[] dist = new int[n]; 

        Arrays.fill(dist, Integer.MAX_VALUE); 

        dist[src] = 0; 

        for (int i = 0; i < n - 1; i++) 

            for (int[] e : edges) 

                if (dist[e[0]] != Integer.MAX_VALUE && dist[e[0]] + e[2] < dist[e[1]]) 

                    dist[e[1]] = dist[e[0]] + e[2]; 

        // Check negative cycle 

        for (int[] e : edges) 

            if (dist[e[0]] != Integer.MAX_VALUE && dist[e[0]] + e[2] < dist[e[1]]) 

                System.out.println("Negative cycle detected!"); 

        return dist; 

    } 

 

    // --- Floyd-Warshall (all-pairs shortest paths) --- 

    static int[][] floydWarshall(int[][] graph, int n) { 

        int[][] dist = new int[n][n]; 

        for (int[] row : dist) Arrays.fill(row, Integer.MAX_VALUE / 2); 

        for (int i = 0; i < n; i++) dist[i][i] = 0; 

        for (int i = 0; i < n; i++) 

            for (int j = 0; j < n; j++) 

                if (graph[i][j] != 0) dist[i][j] = graph[i][j]; 

        for (int k = 0; k < n; k++) 

            for (int i = 0; i < n; i++) 

                for (int j = 0; j < n; j++) 

                    dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]); 

        return dist; 

    } 

 

    public static void main(String[] args) { 

        int n = 4; 

        int[][] graph = {{0,10,0,5},{0,0,1,2},{0,0,0,0},{0,3,9,0}}; 

        System.out.println("Dijkstra from 0: " + Arrays.toString(dijkstra(graph, 0, n))); 

        int[][] edges = {{0,1,10},{0,3,5},{3,1,3},{1,2,1},{3,2,9}}; 

        System.out.println("Bellman-Ford: " + Arrays.toString(bellmanFord(edges, 0, n, 5))); 

        int[][] fw = floydWarshall(graph, n); 

        System.out.println("Floyd-Warshall 0->2: " + fw[0][2]); 

    } 

} 
