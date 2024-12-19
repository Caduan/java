import java.util.Scanner;

public class TriangleTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("Введите 1 сторону треугольника: ");
            double a = scanner.nextDouble();

            System.out.print("Введите 2 сторону треугольника: ");
            double b = scanner.nextDouble();

            System.out.print("Введите 3 сторону треугольника: ");
            double c = scanner.nextDouble();

            System.out.print("Введите цвет: ");
            String color = scanner.next();

            System.out.print("Введите заливку треугольника: ");
            boolean feel = scanner.nextBoolean();

            Triangle triangle = new Triangle(a, b, c);
            triangle.setColor(color);
            triangle.setFilled(feel);

            System.out.println("\n"+triangle);
            System.out.println("Цвет: " + triangle.getColor() + "\nЗаливка: " + triangle.isFilled());
            System.out.println("Площадь: " + triangle.getArea() + "\nПериметр: " + triangle.getPerimeter());

        } catch (IllegalTriangleException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }


    }
}
