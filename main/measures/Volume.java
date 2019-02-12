package measures;

public class Volume extends BaseUnit {
  public static final Volume TEASPOON = new Volume();
  public static final Volume TABLESPOON = new Volume(3, TEASPOON);
  public static final Volume OUNCE = new Volume(2, TABLESPOON);
  public static final Volume CUP = new Volume(8, OUNCE);
  public static final Volume PINT = new Volume(2, CUP);
  public static final Volume QUART = new Volume(2, PINT);
  public static final Volume GALLON = new Volume(4, QUART);

  public static final Volume CUBE_INCH = new Volume(12345, TEASPOON);  // correct factor to be determined
  public static final Volume CUBE_FOOT = new Volume(12 * 12 * 12, CUBE_INCH);
  public static final Volume CUBE_YARD = new Volume(3 * 3 * 3, CUBE_FOOT);
  public static final Volume CUBE_CHAIN = new Volume(22 * 22 * 22, CUBE_YARD);
  public static final Volume CUBE_FURLONG = new Volume(10 * 10 * 10, CUBE_CHAIN);
  public static final Volume CUBE_MILE = new Volume(8 * 8 * 8, CUBE_FURLONG);


  private Volume() {
    super();
  }

  private Volume(double amountInBaseUnit, BaseUnit lowerUnit) {
    super(amountInBaseUnit, lowerUnit);
  }

  public Quantity<Volume> s(double amount) {
    return new Quantity<>(amount, this);
  }
}
