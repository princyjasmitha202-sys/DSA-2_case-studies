import java.util.*;

public class SkyRoute {

    private Map<String, List<String>> graph = new HashMap<>();

    // Add Airport
    void addAirport(String airport) {
        graph.putIfAbsent(airport, new ArrayList<>());
    }

    // Add Route
    void addRoute(String source, String destination) {
        graph.get(source).add(destination);
        graph.get(destination).add(source); // Undirected graph
    }

    // BFS Traversal
    void BFS(String start) {
        Set<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();

        visited.add(start);
        queue.add(start);

        System.out.print("BFS Traversal (from " + start + "): ");

        while (!queue.isEmpty()) {
            String airport = queue.poll();
            System.out.print(airport + " ");

            for (String neighbor : graph.get(airport)) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.add(neighbor);
                }
            }
        }
        System.out.println();
    }

    // DFS Traversal
    void DFS(String airport, Set<String> visited) {
        visited.add(airport);
        System.out.print(airport + " ");

        for (String neighbor : graph.get(airport)) {
            if (!visited.contains(neighbor)) {
                DFS(neighbor, visited);
            }
        }
    }

    public static void main(String[] args) {

        SkyRoute network = new SkyRoute();

        String[] airports = {
            "DEL", "BOM", "HYD",
            "MAA", "BLR", "CCU", "GOI"
        };

        for (String airport : airports) {
            network.addAirport(airport);
        }

        network.addRoute("DEL", "BOM");
        network.addRoute("DEL", "HYD");
        network.addRoute("BOM", "MAA");
        network.addRoute("BOM", "BLR");
        network.addRoute("HYD", "CCU");
        network.addRoute("HYD", "GOI");
        network.addRoute("BOM", "HYD");

        System.out.println("Airport Flight Network - SkyRoute\n");

        network.BFS("DEL");

        System.out.print("DFS Traversal (from DEL): ");
        network.DFS("DEL", new HashSet<>());
    }
}