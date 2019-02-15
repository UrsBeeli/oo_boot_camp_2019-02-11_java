package measures;

import static measures.Volume.BASE_VOLUME;
import static measures.Volume.CUBE_INCH;
import static measures.Volume.CUBE_MILLIMETER;

public class Area extends BaseUnit {

  public static final Area BASE_AREA = new Area();

  public static final Area SQUARE_MILLIMETER = new Area(1, BASE_AREA);
  public static final Area SQUARE_CENTIMETER = new Area(Math.pow(10, 2), SQUARE_MILLIMETER);
  public static final Area SQUARE_METER = new Area(Math.pow(100, 2), SQUARE_CENTIMETER);
  public static final Area SQUARE_KILOMETER = new Area(Math.pow(1000, 2), SQUARE_METER);

  public static final Area SQUARE_INCH = new Area(Math.pow(254, 2), SQUARE_MILLIMETER);
  public static final Area SQUARE_FOOT = new Area(Math.pow(12, 2), SQUARE_INCH);
  public static final Area SQUARE_YARD = new Area(Math.pow(3, 2), SQUARE_FOOT);
  public static final Area SQUARE_CHAIN = new Area(Math.pow(22, 2), SQUARE_YARD);
  public static final Area SQUARE_FURLONG = new Area(Math.pow(10, 2), SQUARE_CHAIN);
  public static final Area SQUARE_MILE = new Area(Math.pow(8, 2), SQUARE_FURLONG);

  public static final Area ACRE = new Area(1 * 10, SQUARE_CHAIN); // ACRE = CHAIN * FURLONG

  private Area() {
    super();
  }

  private Area(double amountInBaseUnit, BaseUnit lowerUnit) {
    super(amountInBaseUnit, lowerUnit);
  }

  public MultiplyableQuantity<Area, Length, Volume> s(double amount) {
    return new MultiplyableQuantity<>(amount, this, BASE_VOLUME);
  }
}
