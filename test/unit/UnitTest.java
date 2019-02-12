package unit;

import measures.Unit;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UnitTest {
  @Test
  void conversionToTeaspoons() {
    assertEquals(3, Unit.TEASPOON.convertedAmount(1, Unit.TABLESPOON));
    assertEquals(768, Unit.TEASPOON.convertedAmount(1, Unit.GALLON));
  }

  @Test
  void conversionFromTeaspoons() {
    assertEquals(1, Unit.TABLESPOON.convertedAmount(3, Unit.TEASPOON));
    assertEquals(1, Unit.GALLON.convertedAmount(768, Unit.TEASPOON));
  }

  @Test
  void conversions() {
    assertEquals(2.5, Unit.CUP.convertedAmount(20, Unit.OUNCE));
    assertEquals(13, Unit.CUP.convertedAmount(3.25, Unit.QUART));
  }
}
