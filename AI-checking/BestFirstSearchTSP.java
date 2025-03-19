import java.util.*;

class Node {
    int vertex;
    int g; // Accumulated cost from start
    int f; // f = g + h
    Node parent;
    
    Node(int vertex, int g, int f, Node parent) {
        this.vertex = vertex;
        this.g = g;
        this.f = f;
        this.parent = parent;
    }
    
    public String toString() {
        return "{" + vertex + ": f=" + f + "}";
    }
}

public class BestFirstSearchTSP {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        // User Inputs
        System.out.print("Enter number of cities: ");
        int n = sc.nextInt();
         
        // Distance matrix input
        int[][] distance = new int[n][n];
        System.out.println("Enter distance matrix:");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++){
                distance[i][j] = sc.nextInt();
            }
        }
        
        // Starting city input
        System.out.print("Enter starting city (0 to " + (n-1) + "): ");
        int start = sc.nextInt();
        
        // Heuristic values for each city
        int[] heuristic = new int[n];
        System.out.println("Enter heuristic values for each city:");
        for (int i = 0; i < n; i++) {
            heuristic[i] = sc.nextInt();
        }
        
        // We assume the goal is the city with heuristic value 0.
        int goal = -1;
        for (int i = 0; i < n; i++) {
            if(heuristic[i] == 0) {
                goal = i;
                break;
            }
        }
        if(goal == -1) {
            System.out.println("No goal vertex found (a city with heuristic 0 is required).");
            return;
        }
        
        // Maintain two maps for the open and closed lists.
        // Each map stores vertex as key and its corresponding Node (with f value) as value.
        Map<Integer, Node> openList = new HashMap<>();
        Map<Integer, Node> closedList = new HashMap<>();
        
        // Put start vertex in the open list.
        Node startNode = new Node(start, 0, 0 + heuristic[start], null);
        openList.put(start, startNode);
        
        boolean goalFound = false;
        Node goalNode = null;
        
        // Best-first search loop.
        while (!openList.isEmpty()) {
            // Choose the node with the smallest f-value from open list.
            Node current = null;
            int currentVertex = -1;
            int minF = Integer.MAX_VALUE;
            for (Map.Entry<Integer, Node> entry : openList.entrySet()) {
                if (entry.getValue().f < minF) {
                    minF = entry.getValue().f;
                    current = entry.getValue();
                    currentVertex = entry.getKey();
                }
            }
            
            // Remove the selected vertex from open list and add it to closed list.
            openList.remove(currentVertex);
            closedList.put(currentVertex, current);
            
            // Display current open and closed lists.
            System.out.println("Current Node: " + current.vertex + " (f=" + current.f + ")");
            System.out.println("Open List: " + mapToString(openList));
            System.out.println("Closed List: " + mapToString(closedList));
            System.out.println("---------------------------------");
            
            // If the current vertex is the goal (heuristic 0), then finish.
            if (current.vertex == goal) {
                goalFound = true;
                goalNode = current;
                break;
            }
            
            // Explore neighbors of the current vertex.
            for (int i = 0; i < n; i++) {
                // Check if there is a connection (distance > 0) from current to neighbor.
                if (distance[current.vertex][i] > 0) {
                    // Skip neighbor if it's already in closed list.
                    if (closedList.containsKey(i))
                        continue;
                    
                    // Calculate new g and f values.
                    int newG = current.g + distance[current.vertex][i];
                    int newF = newG + heuristic[i];
                    
                    // If neighbor is already in open list with a higher f value, update it.
                    if (openList.containsKey(i)) {
                        Node existing = openList.get(i);
                        if (newF < existing.f) {
                            existing.g = newG;
                            existing.f = newF;
                            existing.parent = current;
                        }
                    } else {
                        // Add the neighbor to the open list.
                        Node neighbor = new Node(i, newG, newF, current);
                        openList.put(i, neighbor);
                    }
                }
            }
        }
        
        // Output final answer.
        if (goalFound) {
            System.out.println("Goal reached: " + goal);
            System.out.println("Total cost from start: " + goalNode.g);
            System.out.print("Path: ");
            printPath(goalNode);
            System.out.println();
        } else {
            System.out.println("Goal not reached.");
        }
        
        sc.close();
    }
    
    // Recursive helper function to print the path.
    public static void printPath(Node node) {
        if (node == null)
            return;
        printPath(node.parent);
        System.out.print(node.vertex + " ");
    }
    
    // Helper function to convert a map's contents to string.
    public static String mapToString(Map<Integer, Node> map) {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        for (Map.Entry<Integer, Node> entry : map.entrySet()) {
            sb.append(entry.getKey() + ": " + entry.getValue().f + ", ");
        }
        if (sb.length() > 1)
            sb.setLength(sb.length() - 2); // remove trailing comma and space
        sb.append("}");
        return sb.toString();
    }
}
