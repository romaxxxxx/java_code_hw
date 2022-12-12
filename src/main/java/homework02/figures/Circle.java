package homework02.figures;

public class Circle extends Ellipse implements WithArea {

    Circle(double rad) {
        super(rad, rad);
    }

    @Override
    public double figureArea() {
        return Math.PI * Math.pow(2, super.rad_1);
    }

}