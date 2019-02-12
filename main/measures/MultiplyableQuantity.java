package measures;


import java.util.Objects;

public class MultiplyableQuantity<T extends BaseUnit, S extends BaseUnit, R extends BaseUnit> extends Quantity<T> {
  protected MultiplyableQuantity(final double amount, final T unit, final R resultBaseUnit) {
    super(amount, unit);
    this.resultBaseUnit = resultBaseUnit;
  }

  private R resultBaseUnit;

  public Quantity<R> multiply(final Quantity<S> other) {
    if (!unit.supportsArithmatic() || !other.unit.supportsArithmatic()) {
      throw new UnsupportedOperationException("Cannot multiply these units");
    }
    final double ourAmount = this.unit.convertToBaseunit(this.amount);
    final double theirAmount = other.unit.convertToBaseunit(other.amount);
    return new Quantity<R>(ourAmount * theirAmount, resultBaseUnit);
  }
}
