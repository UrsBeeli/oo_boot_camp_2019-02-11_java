package measures;


import java.util.Objects;

public class Measure {

  private static final int TEA_SPOONS_PER_TABLE_SPOON = 3;
  private static final int TEA_SPOONS_PER_OUNCE = 2 * TEA_SPOONS_PER_TABLE_SPOON;
  private static final int TEA_SPOONS_PER_CUP = 8 * TEA_SPOONS_PER_OUNCE;
  private static final int TEA_SPOONS_PER_PINT = 2 * TEA_SPOONS_PER_CUP;
  private static final int TEA_SPOONS_PER_QUART = 2 * TEA_SPOONS_PER_PINT;
  private static final int TEA_SPOONS_PER_GALLON = 4 * TEA_SPOONS_PER_QUART;

  public enum Unit {
    TEA_SPOON,
    TABLE_SPOON,
    OUNCE,
    CUP,
    PINT,
    QUART,
    GALLON;
  }

  private final double value;
  private final Unit unit;

  public Measure(final double value, final Unit unit) {
    Objects.requireNonNull(unit, "Unit must not be null");

    this.value = value;
    this.unit = unit;
  }

  private Measure convertToLowest() {
    switch (unit) {
      case TEA_SPOON:
        return this;
      case TABLE_SPOON:
        return new Measure(this.value * TEA_SPOONS_PER_TABLE_SPOON, Unit.TEA_SPOON);
      case OUNCE:
        return new Measure(this.value * TEA_SPOONS_PER_OUNCE, Unit.TEA_SPOON);
      case CUP:
        return new Measure(this.value * TEA_SPOONS_PER_CUP, Unit.TEA_SPOON);
      case PINT:
        return new Measure(this.value * TEA_SPOONS_PER_PINT, Unit.TEA_SPOON);
      case QUART:
        return new Measure(this.value * TEA_SPOONS_PER_QUART, Unit.TEA_SPOON);
      case GALLON:
        return new Measure(this.value * TEA_SPOONS_PER_GALLON, Unit.TEA_SPOON);
    }
    throw new RuntimeException("Unexpected source unit " + unit);
  }

  private Measure convertFromLowest(Unit targetUnit) {
    if (unit == Unit.TEA_SPOON) {
      switch (targetUnit) {
        case TEA_SPOON:
          return this;
        case TABLE_SPOON:
          return new Measure(this.value / TEA_SPOONS_PER_TABLE_SPOON, targetUnit);
        case OUNCE:
          return new Measure(this.value / TEA_SPOONS_PER_OUNCE, targetUnit);
        case CUP:
          return new Measure(this.value / TEA_SPOONS_PER_CUP, targetUnit);
        case PINT:
          return new Measure(this.value / TEA_SPOONS_PER_PINT, targetUnit);
        case QUART:
          return new Measure(this.value / TEA_SPOONS_PER_QUART, targetUnit);
        case GALLON:
          return new Measure(this.value / TEA_SPOONS_PER_GALLON, targetUnit);
      }
    }
    throw new RuntimeException("Cannot convertFromLowest for units other than TEA_SPOON. Current unit is " + unit);
  }

  public Measure convertTo(Unit targetUnit) {
    return this.convertToLowest().convertFromLowest(targetUnit);
  }

  public Measure add(final Measure other, final Unit targetUnit) {
    return new Measure(
        this.convertToLowest().value + other.convertToLowest().value,
        Unit.TEA_SPOON
    ).convertTo(targetUnit);
  }

  @Override
  public boolean equals(final Object other) {
    if (this == other) {
      return true;
    }
    if (other == null || getClass() != other.getClass()) {
      return false;
    }
    final Measure that = (Measure) other;
    Measure left = this.convertToLowest();
    Measure right = that.convertToLowest();
    return left.value == right.value;
  }

  @Override
  public int hashCode() {
    return Objects.hash(value, unit);
  }

  public String toString() {
    return String.format("%f %s", value, unit);
  }
}
