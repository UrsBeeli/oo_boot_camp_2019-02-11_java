package unit;

import measures.BaseUnit;
import measures.Length;
import measures.Temperature;
import measures.Volume;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class UnitTest {
  @Test
  void conversionToTeaspoons() {
    assertEquals(3, Volume.TEASPOON.convertedAmount(1, Volume.TABLESPOON));
    assertEquals(768, Volume.TEASPOON.convertedAmount(1, Volume.GALLON));
  }

  @Test
  void conversionFromTeaspoons() {
    assertEquals(1, Volume.TABLESPOON.convertedAmount(3, Volume.TEASPOON));
    assertEquals(1, Volume.GALLON.convertedAmount(768, Volume.TEASPOON));
  }

  @Test
  void conversions() {
    assertEquals(2.5, Volume.CUP.convertedAmount(20, Volume.OUNCE));
    assertEquals(13, Volume.CUP.convertedAmount(3.25, Volume.QUART));
  }

  @Test
  void mixed() {
    assertThrows(IllegalArgumentException.class, () -> Volume.CUP.convertedAmount(20, Length.INCH));
  }

  @Test
  void temperatures() {
    assertEquals(0, Temperature.CELSIUS.convertedAmount(32, Temperature.FAHRENHEIT));
    assertEquals(10, Temperature.CELSIUS.convertedAmount(50, Temperature.FAHRENHEIT));
    assertEquals(100, Temperature.CELSIUS.convertedAmount(212, Temperature.FAHRENHEIT));
    assertEquals(-40, Temperature.CELSIUS.convertedAmount(-40, Temperature.FAHRENHEIT));

    assertEquals(32, Temperature.FAHRENHEIT.convertedAmount(0, Temperature.CELSIUS));
    assertEquals(50, Temperature.FAHRENHEIT.convertedAmount(10, Temperature.CELSIUS));
    assertEquals(212, Temperature.FAHRENHEIT.convertedAmount(100, Temperature.CELSIUS));
    assertEquals(-40, Temperature.FAHRENHEIT.convertedAmount(-40, Temperature.CELSIUS));
  }
}
