package measures;

public class AreaUnit extends AbstractUnit<AreaUnit> {
  public static final AreaUnit SQUARE_INCH = new AreaUnit(0, null);
  public static final AreaUnit SQUARE_FOOT = new AreaUnit(144, SQUARE_INCH);
  public static final AreaUnit SQUARE_YARD = new AreaUnit(9, SQUARE_FOOT);
  public static final AreaUnit SQUARE_CHAIN = new AreaUnit(484, SQUARE_YARD);
  public static final AreaUnit SQUARE_FURLONG = new AreaUnit(100, SQUARE_CHAIN);
  public static final AreaUnit SQUARE_MILE = new AreaUnit(64, SQUARE_FURLONG);

  private AreaUnit(final int amountInLowerUnit, final AreaUnit lowerUnit) {
    super(amountInLowerUnit, lowerUnit);
  }

  @Override
  protected AreaUnit getBaseUnit() {
    return SQUARE_INCH;
  }
}
