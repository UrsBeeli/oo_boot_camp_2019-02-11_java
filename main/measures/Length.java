package measures;

import static measures.Area.SQUARE_INCH;

public class Length extends BaseUnit {
  public static final Length INCH = new Length();
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
    return new MultiplyableQuantity<>(amount, this, SQUARE_INCH);
  }
}
