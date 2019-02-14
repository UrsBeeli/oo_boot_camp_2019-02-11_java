package measures;

public class Volume extends BaseUnit {

  public static final Volume BASE_VOLUME = new Volume();

  public static final Volume CUBE_MILLIMETER = new Volume(1, BASE_VOLUME);
  public static final Volume CUBE_CENTIMETER = new Volume(Math.pow(10, 3), CUBE_MILLIMETER);
  public static final Volume CUBE_METER = new Volume(Math.pow(100, 3), CUBE_CENTIMETER);

  public static final Volume MILLILITER = new Volume(1, CUBE_MILLIMETER);
  public static final Volume DECILITER = new Volume(10, MILLILITER);
  public static final Volume LITER = new Volume(10, DECILITER);
  public static final Volume HECTOLITER = new Volume(100, LITER);

  public static final Volume CUBE_INCH = new Volume(254 * 254 * 254, CUBE_MILLIMETER);
  public static final Volume CUBE_FOOT = new Volume(12 * 12 * 12, CUBE_INCH);
  public static final Volume CUBE_YARD = new Volume(3 * 3 * 3, CUBE_FOOT);
  public static final Volume CUBE_CHAIN = new Volume(22 * 22 * 22, CUBE_YARD);
  public static final Volume CUBE_FURLONG = new Volume(10 * 10 * 10, CUBE_CHAIN);
  public static final Volume CUBE_MILE = new Volume(8 * 8 * 8, CUBE_FURLONG);

  public static final Volume TEASPOON = new Volume(1 / 3.3246763996984, CUBE_INCH);  // conversion according to google
  public static final Volume TABLESPOON = new Volume(3, TEASPOON);
  public static final Volume OUNCE = new Volume(2, TABLESPOON);
  public static final Volume CUP = new Volume(8, OUNCE);
  public static final Volume PINT = new Volume(2, CUP);
  public static final Volume QUART = new Volume(2, PINT);
  public static final Volume GALLON = new Volume(4, QUART);

  private Volume() {
    super();
  }

  private Volume(double amountInBaseUnit, BaseUnit lowerUnit) {
    super(amountInBaseUnit, lowerUnit);
  }

  public RatioQuantity<Volume> s(double amount) {
    return new RatioQuantity<>(amount, this);
  }
}
