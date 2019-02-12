package unit;

import measures.Unit;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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

  @Test
  void mixed() {
    assertThrows(IllegalArgumentException.class, () -> Unit.CUP.convertedAmount(20, Unit.INCH));
  }

  @Test
  void temperatures() {
    assertEquals(0, Unit.CELSIUS.convertedAmount(32, Unit.FAHRENHEIT));
    assertEquals(10, Unit.CELSIUS.convertedAmount(50, Unit.FAHRENHEIT));
    assertEquals(100, Unit.CELSIUS.convertedAmount(212, Unit.FAHRENHEIT));
    assertEquals(-40, Unit.CELSIUS.convertedAmount(-40, Unit.FAHRENHEIT));

    assertEquals(32, Unit.FAHRENHEIT.convertedAmount(0, Unit.CELSIUS));
    assertEquals(50, Unit.FAHRENHEIT.convertedAmount(10, Unit.CELSIUS));
    assertEquals(212, Unit.FAHRENHEIT.convertedAmount(100, Unit.CELSIUS));
    assertEquals(-40, Unit.FAHRENHEIT.convertedAmount(-40, Unit.CELSIUS));
  }
}
