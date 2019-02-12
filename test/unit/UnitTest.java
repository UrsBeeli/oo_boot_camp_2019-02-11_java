package unit;

import measures.Unit;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UnitTest {
  @Test
  void conversionToTeaspoons() {
    assertEquals(3, Unit.TABLESPOON.convert(1, Unit.TEASPOON));
    assertEquals(768, Unit.GALLON.convert(1, Unit.TEASPOON));
  }

  @Test
  void conversionFromTeaspoons() {
    assertEquals(1, Unit.TEASPOON.convert(3, Unit.TABLESPOON));
    assertEquals(1, Unit.TEASPOON.convert(768, Unit.GALLON));
  }
}
