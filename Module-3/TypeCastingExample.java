public class TypeCastingExample {
    public static void main(String[] args) {
        double myDouble = 9.78;
        int castedInt = (int) myDouble;
        System.out.println("Double value: " + myDouble);
        System.out.println("Casted to int: " + castedInt);

        int myInt = 10;
        double castedDouble = (double) myInt;
        System.out.println("Int value: " + myInt);
        System.out.println("Casted to double: " + castedDouble);
    }
}
