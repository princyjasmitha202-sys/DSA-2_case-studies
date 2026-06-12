import java.util.*; 

 

public class MusicStreamingNetwork { 

 

    // --- BFS: Layer-by-layer update rollout --- 

    static void bfsUpdate(int[][] adjMatrix, int start, int n) { 

        boolean[] visited = new boolean[n]; 

        Queue<Integer> queue = new LinkedList<>(); 

        visited[start] = true; 

        queue.add(start); 

        System.out.print("BFS Update Order: "); 

        while (!queue.isEmpty()) { 

            int node = queue.poll(); 

            System.out.print("Server" + node + " "); 

            for (int i = 0; i < n; i++) 

                if (adjMatrix[node][i] != 0 && !visited[i]) { 

                    visited[i] = true; queue.add(i); 

                } 

        } 

        System.out.println(); 

    } 

 

    // --- Kruskal's MST --- 

    static int[] parent, rank; 

    static int find(int x) { 

        if (parent[x] != x) parent[x] = find(parent[x]); 

        return parent[x]; 

    } 

    static void union(int x, int y) { 

        int px = find(x), py = find(y); 

        if (rank[px] < rank[py]) parent[px] = py; 

        else if (rank[px] > rank[py]) parent[py] = px; 

        else { parent[py] = px; rank[px]++; } 

    } 

 

    static void kruskalMST(int[][] edges, int n) { 

        // edges[i] = {weight, u, v} 

        Arrays.sort(edges, Comparator.comparingInt(e -> e[0])); 

        parent = new int[n]; rank = new int[n]; 

        for (int i = 0; i < n; i++) parent[i] = i; 

        int totalCost = 0; 

        System.out.println("MST Edges (Kruskal):"); 

        for (int[] e : edges) { 

            int w = e[0], u = e[1], v = e[2]; 

            if (find(u) != find(v)) { 

                union(u, v); 

                System.out.println("  Server" + u + " -- Server" + v + " [cost: " + w + "]"); 

                totalCost += w; 

            } 

        } 

        System.out.println("Total Infrastructure Cost: " + totalCost); 

    } 

 

    public static void main(String[] args) { 

        int n = 5; // 5 data centers 

        int[][] adjMatrix = { 

            {0,4,0,0,8}, {4,0,8,0,11}, {0,8,0,7,0}, 

            {0,0,7,0,9}, {8,11,0,9,0} 

        }; 

        bfsUpdate(adjMatrix, 0, n); 

 

        int[][] edges = {{4,0,1},{8,0,4},{8,1,2},{11,1,4},{7,2,3},{9,3,4}}; 

        kruskalMST(edges, n); 

    } 

} 
