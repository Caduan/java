public class Triangle extends GeometricObject {
    double side1 = 1.0, side2 = 1.0, side3 = 1.0;

    // Constructors
    public Triangle() {
    }

    public Triangle(double side1, double side2, double side3) throws IllegalTriangleException {
        if (side1 <= 0 || side2 <= 0 || side3 <= 0 || side1 + side2 <= side3 || side1 + side3 <= side2 || side3 + side2 <= side1) {
            throw new IllegalTriangleException("This triangle doesn't exist!");
        }
        this.side1 = side1;
        this.side2 = side2;
        this.side3 = side3;
    }

    public double getPerimeter() {
        return side1 + side2 + side3;
    }

    public double getArea() {
        double s = getPerimeter() / 2;
        return Math.sqrt(s * (s - side1) * (s - side2) * (s - side3));
    }

    @Override
    public String toString() {
        return "Треугольник: сторона_1 = " + side1 + ", сторона_2 = " + side2 + ", сторона_3 = " + side3;
    }
}

