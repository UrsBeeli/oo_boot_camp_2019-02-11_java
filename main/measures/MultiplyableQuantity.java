package measures;


public class MultiplyableQuantity<T extends BaseUnit, S extends BaseUnit, R extends BaseUnit> extends RatioQuantity<T> {

  MultiplyableQuantity(final double amount, final T unit, final R resultBaseUnit) {
    super(amount, unit);
    this.resultBaseUnit = resultBaseUnit;
  }

  private R resultBaseUnit;

  public Quantity<R> multiply(final Quantity<S> other) {
    final double ourAmount = this.unit.convertToBaseunit(this.amount);
    final double theirAmount = other.unit.convertToBaseunit(other.amount);
    return new Quantity<>(ourAmount * theirAmount, resultBaseUnit);
  }

}
