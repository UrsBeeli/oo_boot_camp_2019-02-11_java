package measures;

public class VolumeUnit extends AbstractUnit<VolumeUnit> {
  public static final VolumeUnit TEASPOON = new VolumeUnit(0, null);
  public static final VolumeUnit TABLESPOON = new VolumeUnit(3, TEASPOON);
  public static final VolumeUnit OUNCE = new VolumeUnit(2, TABLESPOON);
  public static final VolumeUnit CUP = new VolumeUnit(8, OUNCE);
  public static final VolumeUnit PINT = new VolumeUnit(2, CUP);
  public static final VolumeUnit QUART = new VolumeUnit(2, PINT);
  public static final VolumeUnit GALLON = new VolumeUnit(4, QUART);

  private VolumeUnit(final int amountInLowerUnit, final VolumeUnit lowerUnit) {
    super(amountInLowerUnit, lowerUnit);
  }

  @Override
  protected VolumeUnit getBaseUnit() {
    return TEASPOON;
  }
}
