// 2. Water Jug (user input game type)

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

public class juguser {

    private static void printSolution(List<State> steps) {
        System.out.println("Your steps:");
        for (State state : steps) {
            System.out.println(state);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter capacity of Jug1: ");
        int capacity1 = scanner.nextInt();

        System.out.print("Enter capacity of Jug2: ");
        int capacity2 = scanner.nextInt();

        System.out.print("Enter target amount: ");
        int target = scanner.nextInt();

        State current = new State(0, 0);
        List<State> steps = new ArrayList<>();
        steps.add(current);
        while (true) {
            System.out.println("\nCurrent state: " + current);
            System.out.println("Choose an action:");
            System.out.println("1. Transfer water from Jug1 to Jug2");
            System.out.println("2. Transfer water from Jug2 to Jug1");
            System.out.println("3. Empty Jug1");
            System.out.println("4. Empty Jug2");
            System.out.println("5. Fill Jug1");
            System.out.println("6. Fill Jug2");
            System.out.println("7. Quit");

            int choice = scanner.nextInt();
            State nextState = null;

            switch (choice) {
                case 1: 
                    int pourToJug2 = Math.min(current.jug1, capacity2 - current.jug2);
                    nextState = new State(current.jug1 - pourToJug2, current.jug2 + pourToJug2);
                    break;
                case 2: 
                    int pourToJug1 = Math.min(current.jug2, capacity1 - current.jug1);
                    nextState = new State(current.jug1 + pourToJug1, current.jug2 - pourToJug1);
                    break;
                case 3: 
                    nextState = new State(0, current.jug2);
                    break;
                case 4: 
                    nextState = new State(current.jug1, 0);
                    break;
                case 5:
                    nextState = new State(capacity1, current.jug2);
                    break;
                case 6: 
                    nextState = new State(current.jug1, capacity2);
                    break;
                case 7: 
                    System.out.println("Exiting the game.");
                    printSolution(steps);
                    return;
                default:
                    System.out.println("Invalid choice, please choose a valid option.");
                    continue;
            }

           
            if (!steps.contains(nextState)) {
                steps.add(nextState);
                current = nextState;

                if (current.jug1 == target || current.jug2 == target) {
                    System.out.println("Solution found!");
                    printSolution(steps);
                    return;
                }
            } else {
                System.out.println("This state has already been visited, choose a different action.");
            }
        }
    }
}

