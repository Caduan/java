public class Square extends GeometricObject implements Colorable {
    private double side = 0;

    // Конструктор
    public Square() { }

    public Square(double side) {
        this.side = side;
    }

    public void setSide(double side) { this.side = side; }
    public double getSide() { return side; }

    public double getArea() { return side * side; }
    public double getPerimeter() { return 4 * side; }

    @Override
    public String toString() {
        return "Square: side = " + side;
    }
}
