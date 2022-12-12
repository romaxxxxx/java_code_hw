package homework02.figures;

public class Square extends Rectangle implements WithArea {
    Square(double side) {
        super(side, side);
    }

    @Override
    public double figureArea() {
        return Math.pow(2, side_1);
    }
}
