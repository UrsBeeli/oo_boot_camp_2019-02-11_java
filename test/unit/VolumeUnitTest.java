package unit;

import measures.VolumeUnit;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class VolumeUnitTest {
  @Test
  void conversionToTeaspoons() {
    assertEquals(3, VolumeUnit.TEASPOON.convertedAmount(1, VolumeUnit.TABLESPOON));
    assertEquals(768, VolumeUnit.TEASPOON.convertedAmount(1, VolumeUnit.GALLON));
  }

  @Test
  void conversionFromTeaspoons() {
    assertEquals(1, VolumeUnit.TABLESPOON.convertedAmount(3, VolumeUnit.TEASPOON));
    assertEquals(1, VolumeUnit.GALLON.convertedAmount(768, VolumeUnit.TEASPOON));
  }

  @Test
  void conversions() {
    assertEquals(2.5, VolumeUnit.CUP.convertedAmount(20, VolumeUnit.OUNCE));
    assertEquals(13, VolumeUnit.CUP.convertedAmount(3.25, VolumeUnit.QUART));
  }
}
