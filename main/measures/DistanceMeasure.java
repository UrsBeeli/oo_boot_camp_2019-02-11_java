package measures;

public class DistanceMeasure extends AbstractMeasure<DistanceUnit> {
  public DistanceMeasure(final double amount, final DistanceUnit unit) {
    super(amount, unit);
  }

  @Override
  protected AbstractMeasure buildMeasure(final double amount, final DistanceUnit unit) {
    return new DistanceMeasure(amount, unit);
  }

  @Override
  protected DistanceUnit getBaseUnit() {
    return DistanceUnit.INCH;
  }

//  public DistanceMeasure add(DistanceMeasure other) {
//    return (DistanceMeasure)super.add(other);
//  }

  public AreaMeasure multiply(DistanceMeasure other) {
    double otherAmountInOurUnit = this.unit.convertedAmount(other.amount, other.unit);
    return new AreaMeasure(this.amount * otherAmountInOurUnit, AreaUnit.SQUARE_INCH);
    // TODO: how to return SQUARE_UNIT of our unit?
  }
}
