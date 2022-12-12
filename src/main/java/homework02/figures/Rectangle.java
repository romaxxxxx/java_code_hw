package homework02.figures;

public class Rectangle extends Figure {
    double side_1, side_2;

    public Rectangle(double s_1, double s_2) {
        side_1 = s_1;
        side_2 = s_2;
    }

    @Override
    double getPerimeter() {
        return 2 * (side_1 + side_2);
    }
}
