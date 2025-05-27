import java.util.*;

public class LambdaSortExample {
    public static void main(String[] args) {
        List<String> names = new ArrayList<>();
        names.add("Zara");
        names.add("Mike");
        names.add("Anna");
        names.add("John");

        Collections.sort(names, (a, b) -> a.compareTo(b));

        System.out.println("Sorted list:");
        for (String name : names) {
            System.out.println(name);
        }
    }
}
