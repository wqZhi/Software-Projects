public class Example {

    public static double someMethod(double g) {
        g = g + 1;
        return g;
    }

    public static void main(String[] args) {
        double grade = 89.5;
        someMethod(grade);
        System.out.println(grade);

    }
}
