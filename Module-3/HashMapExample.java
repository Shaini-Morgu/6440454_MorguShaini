import java.util.HashMap;
import java.util.Scanner;

public class HashMapExample {
    public static void main(String[] args) {
        HashMap<Integer, String> studentMap = new HashMap<>();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter student ID and name (type 'done' to stop):");
        while (true) {
            System.out.print("Enter student ID: ");
            String idInput = scanner.nextLine();
            if (idInput.equalsIgnoreCase("done")) break;

            int id = Integer.parseInt(idInput);
            System.out.print("Enter student name: ");
            String name = scanner.nextLine();
            studentMap.put(id, name);
        }

        System.out.print("Enter an ID to search: ");
        int searchId = scanner.nextInt();
        if (studentMap.containsKey(searchId)) {
            System.out.println("Student Name: " + studentMap.get(searchId));
        } else {
            System.out.println("No student found with ID: " + searchId);
        }

        scanner.close();
    }
}
