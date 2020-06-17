import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String shape = scanner.nextLine();

        switch (shape) {
            case "triangle":
                calculateAreaOfTriangle(scanner.nextDouble(), scanner.nextDouble(), scanner.nextDouble());
                break;
            case "rectangle":
                calculateAreaOfRectangle(scanner.nextDouble(), scanner.nextDouble());
                break;
            case "circle":
                calculateAreaOfCircle(scanner.nextDouble());
                break;
            default:
                System.out.println("Wrong shape");
                break;
        }
    }

    public static void calculateAreaOfTriangle(double a, double b, double c) {
        double p = (a + b + c) / 2;
        System.out.println(Math.sqrt(p * (p - a) * (p - b) * (p - c)));
    }

    public static void calculateAreaOfRectangle(double a, double b) {
        System.out.println(a * b);
    }

    public static void calculateAreaOfCircle(double r) {
        System.out.println(3.14 * r * r);
    }
}