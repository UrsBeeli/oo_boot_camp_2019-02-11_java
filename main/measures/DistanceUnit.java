package measures;

public class DistanceUnit extends AbstractUnit<DistanceUnit> {
  public static final DistanceUnit INCH = new DistanceUnit(0, null);
  public static final DistanceUnit FOOT = new DistanceUnit(12, INCH);
  public static final DistanceUnit YARD = new DistanceUnit(3, FOOT);
  public static final DistanceUnit CHAIN = new DistanceUnit(22, YARD);
  public static final DistanceUnit FURLONG = new DistanceUnit(10, CHAIN);
  public static final DistanceUnit MILE = new DistanceUnit(8, FURLONG);

  private DistanceUnit(final int amountInLowerUnit, final DistanceUnit lowerUnit) {
    super(amountInLowerUnit, lowerUnit);
  }

  @Override
  protected DistanceUnit getBaseUnit() {
    return INCH;
  }
}
