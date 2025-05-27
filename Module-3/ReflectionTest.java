import java.lang.reflect.*;

public class ReflectionTest {
    public static void main(String[] args) throws Exception {
        // Load ReflectionTarget class
        Class<?> clazz = Class.forName("ReflectionTarget");

        // Create an instance of the class
        Object instance = clazz.getDeclaredConstructor().newInstance();

        // List all declared methods with parameter types
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            System.out.println("Method: " + method.getName());

            System.out.print("Parameters: ");
            for (Class<?> param : method.getParameterTypes()) {
                System.out.print(param.getSimpleName() + " ");
            }
            System.out.println("\n");
        }

        // Call sayHello()
        Method sayHello = clazz.getDeclaredMethod("sayHello");
        sayHello.invoke(instance);

        // Call greet(String)
        Method greet = clazz.getDeclaredMethod("greet", String.class);
        greet.invoke(instance, "Shain");
    }
}
