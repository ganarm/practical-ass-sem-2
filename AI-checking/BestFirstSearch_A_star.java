import java.util.*;

public class BestFirstSearch_A_star {
    // Node class with more detailed properties
    static class Node {
        String name;
        int heuristic; // Estimated cost to goal
        Map<Node, Integer> neighbors; // Adjacency list with costs
        int costSoFar; // Actual cost from start to this node

        Node(String name, int heuristic) {
            this.name = name;
            this.heuristic = heuristic;
            this.neighbors = new HashMap<>();
            this.costSoFar = Integer.MAX_VALUE; // Initialize to infinity
        }
    }

    // Class to hold node and its priority for the priority queue
    static class PriorityNode {
        Node node;
        int priority; // f(n) = g(n) + h(n), where g(n) is costSoFar, h(n) is heuristic

        PriorityNode(Node node, int priority) {
            this.node = node;
            this.priority = priority;
        }
    }

    public static List<String> bestFirstSearch(Node start, Node goal) {
        // Custom comparator for priority queue based on f(n)
        PriorityQueue<PriorityNode> queue = new PriorityQueue<>(
            Comparator.comparingInt(pn -> pn.priority)
        );
        // Map to store the path and actual costs
        Map<Node, Node> cameFrom = new HashMap<>();
        // Set to track explored nodes
        Set<Node> explored = new HashSet<>();
        
        // Initialize start node
        start.costSoFar = 0;
        queue.add(new PriorityNode(start, start.heuristic));
        cameFrom.put(start, null);

        while (!queue.isEmpty()) {
            // Get node with lowest f(n) = g(n) + h(n)
            Node current = queue.poll().node;
            
            // If goal reached, return path
            if (current == goal) {
                return reconstructPath(cameFrom, goal);
            }
            
            // Mark as explored
            explored.add(current);
            
            // Explore all neighbors
            for (Map.Entry<Node, Integer> entry : current.neighbors.entrySet()) {
                Node neighbor = entry.getKey();
                int edgeCost = entry.getValue();
                
                // Skip if already explored
                if (explored.contains(neighbor)) continue;
                
                // Calculate new cost to reach neighbor
                int newCost = current.costSoFar + edgeCost;
                
                // If this path is better or neighbor is unvisited
                if (newCost < neighbor.costSoFar) {
                    neighbor.costSoFar = newCost;
                    cameFrom.put(neighbor, current);
                    // f(n) = g(n) + h(n)
                    int priority = newCost + neighbor.heuristic;
                    queue.add(new PriorityNode(neighbor, priority));
                }
            }
        }
        // No path found
        return new ArrayList<>();
    }

    // Reconstruct the path from start to goal
    private static List<String> reconstructPath(Map<Node, Node> cameFrom, Node goal) {
        List<String> path = new ArrayList<>();
        Node current = goal;
        while (current != null) {
            path.add(current.name);
            current = cameFrom.get(current);
        }
        Collections.reverse(path);
        return path;
    }

    public static void main(String[] args) {
        // Create nodes with heuristic values
        Node A = new Node("A", 10);
        Node B = new Node("B", 8);
        Node C = new Node("C", 5);
        Node D = new Node("D", 0);

        // Define bidirectional graph with costs
        A.neighbors.put(B, 4);
        A.neighbors.put(C, 2);
        B.neighbors.put(A, 4);
        B.neighbors.put(D, 5);
        C.neighbors.put(A, 2);
        C.neighbors.put(D, 3);
        D.neighbors.put(B, 5);
        D.neighbors.put(C, 3);

        // Find shortest path from A to D
        List<String> path = bestFirstSearch(A, D);
        System.out.println("Path: " + path); // Expected: [A, C, D]
        System.out.println("Total cost: " + D.costSoFar); // Expected: 5
    }
}