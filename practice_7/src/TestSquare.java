public class TestSquare {
    public static void main(String[] args) {

        GeometricObject[] objects = new GeometricObject[5];


        objects[0] = new Square(3);
        objects[1] = new Square(5);
        objects[2] = new Square(2);
        objects[3] = new Square(4);
        objects[4] = new Square(6);


        for (GeometricObject object : objects) {
            System.out.println(object.toString());
            System.out.println("Площадь: " + object.getArea());
            if (object instanceof Colorable) {
                ((Colorable) object).howToColor();
            }
            System.out.println();
        }
    }
}
