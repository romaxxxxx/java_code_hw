package homework02.figures;

public class Ellipse extends Figure {
    double rad_1, rad_2;

    public Ellipse(double r_1, double r_2) {
        rad_1 = r_1;
        rad_2 = r_2;
    }

    @Override
    double getPerimeter() {
        return 2 * Math.PI * Math.sqrt((Math.pow(2, rad_1) + Math.pow(2, rad_2)) / 2);
    }
}
