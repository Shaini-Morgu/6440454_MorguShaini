public class BytecodeDemo {
    public int add(int a, int b) {
        return a + b;
    }

    public static void main(String[] args) {
        BytecodeDemo obj = new BytecodeDemo();
        System.out.println(obj.add(10, 20));
    }
}
