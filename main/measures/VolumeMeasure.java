package measures;

public class VolumeMeasure extends AbstractMeasure<VolumeUnit> {
  public VolumeMeasure(final double amount, final VolumeUnit unit) {
    super(amount, unit);
  }

  @Override
  protected AbstractMeasure buildMeasure(final double amount, final VolumeUnit unit) {
    return new VolumeMeasure(amount, unit);
  }

  @Override
  protected VolumeUnit getBaseUnit() {
    return VolumeUnit.TEASPOON;
  }
}
