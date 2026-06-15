import java.util.*;

public class DijkstraDemo {

    static final int V = 6;

    int minDistance(int dist[], boolean visited[]) {
        int min = Integer.MAX_VALUE, minIndex = -1;

        for (int v = 0; v < V; v++) {
            if (!visited[v] && dist[v] <= min) {
                min = dist[v];
                minIndex = v;
            }
        }
        return minIndex;
    }

    void dijkstra(int graph[][], int src) {

        int dist[] = new int[V];
        boolean visited[] = new boolean[V];

        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;

        for (int count = 0; count < V - 1; count++) {

            int u = minDistance(dist, visited);
            visited[u] = true;

            for (int v = 0; v < V; v++) {

                if (!visited[v] &&
                    graph[u][v] != 0 &&
                    dist[u] != Integer.MAX_VALUE &&
                    dist[u] + graph[u][v] < dist[v]) {

                    dist[v] = dist[u] + graph[u][v];
                }
            }
        }

        System.out.println("Node\tDistance");
        for (int i = 0; i < V; i++)
            System.out.println(i + "\t" + dist[i]);
    }

    public static void main(String[] args) {

        int graph[][] = {
            {0,4,6,0,0,0},
            {4,0,5,7,3,0},
            {6,5,0,6,0,0},
            {0,7,6,0,0,5},
            {0,3,0,0,0,4},
            {0,0,0,5,4,0}
        };

        DijkstraDemo obj = new DijkstraDemo();
        obj.dijkstra(graph, 0);
    }
}