package measures;

import static measures.Volume.CUBE_INCH;

public class Area extends BaseUnit {
  public static final Area SQUARE_INCH = new Area();
  public static final Area SQUARE_FOOT = new Area(12 * 12, SQUARE_INCH);
  public static final Area SQUARE_YARD = new Area(3 * 3, SQUARE_FOOT);
  public static final Area SQUARE_CHAIN = new Area(22 * 22, SQUARE_YARD);
  public static final Area SQUARE_FURLONG = new Area(10 * 10, SQUARE_CHAIN);
  public static final Area SQUARE_MILE = new Area(8 * 8, SQUARE_FURLONG);

  private Area() {
    super();
  }

  private Area(double amountInBaseUnit, BaseUnit lowerUnit) {
    super(amountInBaseUnit, lowerUnit);
  }

  public MultiplyableQuantity<Area, Length, Volume> s(double amount) {
    return new MultiplyableQuantity<>(amount, this, CUBE_INCH);
  }
}
