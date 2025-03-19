// 1. Missionaries and Cannibals using BFS

import java.util.*;

class State {
    int leftM, leftC, rightM, rightC, boatPosition;
    List<String> path;

    public State(int leftM, int leftC, int rightM, int rightC, int boatPosition, List<String> path) {
        this.leftM = leftM;
        this.leftC = leftC;
        this.rightM = rightM;
        this.rightC = rightC;
        this.boatPosition = boatPosition;
        this.path = new ArrayList<>(path); // Make a copy of the path
    }
}

public class MissionariesAndCannibalsBFS {

    public static void main(String[] args) {
        // Initialize BFS
        bfs();
    }

    public static void bfs() {
        // Initial state
        int leftM = 3, leftC = 3, rightM = 0, rightC = 0, boatPosition = 0; // Boat on the left
        State startState = new State(leftM, leftC, rightM, rightC, boatPosition, new ArrayList<>());
        
        // BFS Queue
        Queue<State> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        queue.add(startState);
        visited.add(stateToString(startState));

        // BFS loop
        while (!queue.isEmpty()) {
            State current = queue.poll();

            // Check if the solution is found
            if (current.leftM == 0 && current.leftC == 0) {
                System.out.println("Solution found!");
                printSolution(current);
                return;
            }

            // Generate possible next states based on boat position
            List<State> nextStates = generateNextStates(current);

            for (State nextState : nextStates) {
                String stateString = stateToString(nextState);
                if (!visited.contains(stateString)) {
                    visited.add(stateString);
                    queue.add(nextState);
                }
            }
        }
        
        System.out.println("No solution found.");
    }

    // Generate next possible states
    public static List<State> generateNextStates(State current) {
        List<State> nextStates = new ArrayList<>();
        int boatPosition = current.boatPosition;
        
        if (boatPosition == 0) {
            // Boat is on the left side
            for (int i = 0; i <= 2; i++) { // i: number of missionaries to move
                for (int j = 0; j <= 2; j++) { // j: number of cannibals to move
                    if ((i + j) >= 1 && (i + j) <= 2 && i <= current.leftM && j <= current.leftC) {
                        // Move missionaries and cannibals from left to right
                        int newLeftM = current.leftM - i;
                        int newLeftC = current.leftC - j;
                        int newRightM = current.rightM + i;
                        int newRightC = current.rightC + j;
                        
                        if ((newLeftM == 0 || newLeftM >= newLeftC) && (newRightM == 0 || newRightM >= newRightC)) {
                            List<String> newPath = new ArrayList<>(current.path);
                            newPath.add("Move " + i + " missionaries and " + j + " cannibals from left to right");
                            nextStates.add(new State(newLeftM, newLeftC, newRightM, newRightC, 1, newPath));
                        }
                    }
                }
            }
        } else {
            // Boat is on the right side
            for (int i = 0; i <= 2; i++) { // i: number of missionaries to move
                for (int j = 0; j <= 2; j++) { // j: number of cannibals to move
                    if ((i + j) >= 1 && (i + j) <= 2 && i <= current.rightM && j <= current.rightC) {
                        // Move missionaries and cannibals from right to left
                        int newLeftM = current.leftM + i;
                        int newLeftC = current.leftC + j;
                        int newRightM = current.rightM - i;
                        int newRightC = current.rightC - j;
                        
                        if ((newLeftM == 0 || newLeftM >= newLeftC) && (newRightM == 0 || newRightM >= newRightC)) {
                            List<String> newPath = new ArrayList<>(current.path);
                            newPath.add("Move " + i + " missionaries and " + j + " cannibals from right to left");
                            nextStates.add(new State(newLeftM, newLeftC, newRightM, newRightC, 0, newPath));
                        }
                    }
                }
            }
        }

        return nextStates;
    }

    // Convert state to string for visited set
    public static String stateToString(State state) {
        return state.leftM + "," + state.leftC + "," + state.rightM + "," + state.rightC + "," + state.boatPosition;
    }

    // Print the solution path
    public static void printSolution(State solution) {
        for (String step : solution.path) {
            System.out.println(step);
        }
    }
}
