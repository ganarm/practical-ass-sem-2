// 1. Water Jug using BFS:

import java.util.*;

class State {
    int jug1;
    int jug2;

    public State(int jug1, int jug2) {
        this.jug1 = jug1;
        this.jug2 = jug2;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        State state = (State) o;
        return jug1 == state.jug1 && jug2 == state.jug2;
    }

    public int hashCode() {
        return Objects.hash(jug1, jug2);
    }

    public String toString() {
        return "(" + jug1 + ", " + jug2 + ")";
    }
}

public class jug {

    public static boolean solveWaterJugProblem(int capacity1, int capacity2, int target) {
        Queue<State> queue = new LinkedList<>();
        Set<State> visited = new HashSet<>();

        State start = new State(0, 0);
        queue.add(start);
        visited.add(start);

        while (!queue.isEmpty()) {
            State current = queue.poll();
            System.out.println(current);
            // If we reach the target
            if (current.jug1 == target || current.jug2 == target) {
                System.out.println("Solution found!");
                printSolution(current);
                return true;
            }

            // Generate all possible states
            List<State> nextStates = getNextStates(current, capacity1, capacity2);

            for (State nextState : nextStates) {
                if (!visited.contains(nextState)) {
                    System.out.print(nextState);
                    visited.add(nextState);
                    queue.add(nextState);
                }
            }
        }

        System.out.println("No solution found.");
        return false;
    }

    private static List<State> getNextStates(State current, int capacity1, int capacity2) {
        List<State> states = new ArrayList<>();

        int jug1 = current.jug1;
        int jug2 = current.jug2;

        // Fill jug1
        states.add(new State(capacity1, jug2));

        // Fill jug2
        states.add(new State(jug1, capacity2));

        // Empty jug1
        states.add(new State(0, jug2));

        // Empty jug2
        states.add(new State(jug1, 0));

        // Pour jug1 -> jug2
        int pourToJug2 = Math.min(jug1, capacity2 - jug2);
        states.add(new State(jug1 - pourToJug2, jug2 + pourToJug2));

        // Pour jug2 -> jug1
        int pourToJug1 = Math.min(jug2, capacity1 - jug1);
        states.add(new State(jug1 + pourToJug1, jug2 - pourToJug1));

        return states;
    }

    private static void printSolution(State state) {
        System.out.println("Final State: " + state);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter capacity of Jug1: ");
        int capacity1 = scanner.nextInt();

        System.out.print("Enter capacity of Jug2: ");
        int capacity2 = scanner.nextInt();

        System.out.print("Enter target amount: ");
        int target = scanner.nextInt();

        solveWaterJugProblem(capacity1, capacity2, target);
    }
}
