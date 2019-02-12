package measures;

public class IntervalQuantity<T extends BaseUnit> extends Quantity<T> {

  protected IntervalQuantity(final double amount, final T unit) {
    super(amount, unit);
  }

  @Override
  public Quantity<T> add(final Quantity<T> other) {
    throw new UnsupportedOperationException("Cannot add these types");
  }

  @Override
  public Quantity<T> subtract(final Quantity<T> other) {
    throw new UnsupportedOperationException("Cannot subtract these types");
  }
}
