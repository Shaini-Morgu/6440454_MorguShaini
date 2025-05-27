import java.util.*;
import java.util.stream.Collectors;

record Person(String name, int age) {}

public class RecordExample {
    public static void main(String[] args) {
        List<Person> people = List.of(
            new Person("Alice", 22),
            new Person("Bob", 17),
            new Person("Charlie", 30)
        );

        System.out.println("All People:");
        for (Person p : people) {
            System.out.println(p);
        }

        List<Person> adults = people.stream()
                                    .filter(p -> p.age() >= 18)
                                    .collect(Collectors.toList());

        System.out.println("\nAdults (age 18+):");
        for (Person p : adults) {
            System.out.println(p);
        }
    }
}
