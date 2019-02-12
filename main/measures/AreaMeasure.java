package measures;

public class AreaMeasure extends AbstractMeasure<AreaUnit> {
  public AreaMeasure(final double amount, final AreaUnit unit) {
    super(amount, unit);
  }

  @Override
  protected AbstractMeasure buildMeasure(final double amount, final AreaUnit unit) {
    return new AreaMeasure(amount, unit);
  }

  @Override
  protected AreaUnit getBaseUnit() {
    return AreaUnit.SQUARE_INCH;
  }
}
