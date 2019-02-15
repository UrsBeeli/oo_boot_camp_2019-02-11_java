package measures;

import static measures.Area.BASE_AREA;
import static measures.Area.SQUARE_INCH;
import static measures.Area.SQUARE_MILLIMETER;

public class Length extends BaseUnit {

  public static final Length BASE_LENGHT = new Length();

  public static final Length MILLIMETER = new Length(1, BASE_LENGHT);
  public static final Length CENTIMETER = new Length(10, MILLIMETER);
  public static final Length METER = new Length(100, CENTIMETER);
  public static final Length KILOMETER = new Length(1000, METER);

  public static final Length INCH = new Length(254, MILLIMETER);
  public static final Length FOOT = new Length(12, INCH);
  public static final Length YARD = new Length(3, FOOT);
  public static final Length CHAIN = new Length(22, YARD);
  public static final Length FURLONG = new Length(10, CHAIN);
  public static final Length MILE = new Length(8, FURLONG);

  private Length() {
    super();
  }

  private Length(double amountInBaseUnit, BaseUnit lowerUnit) {
    super(amountInBaseUnit, lowerUnit);
  }

  public MultiplyableQuantity<Length, Length, Area> s(double amount) {
    return new MultiplyableQuantity<>(amount, this, BASE_AREA);
  }
}
