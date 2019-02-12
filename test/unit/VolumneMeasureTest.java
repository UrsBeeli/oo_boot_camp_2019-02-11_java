package unit;

import measures.VolumeMeasure;
import org.junit.jupiter.api.Test;
import probability.Probability;

import java.util.Collections;
import java.util.HashSet;

import static measures.VolumeUnit.CUP;
import static measures.VolumeUnit.GALLON;
import static measures.VolumeUnit.OUNCE;
import static measures.VolumeUnit.PINT;
import static measures.VolumeUnit.QUART;
import static measures.VolumeUnit.TABLESPOON;
import static measures.VolumeUnit.TEASPOON;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class VolumneMeasureTest {

  @Test
  void constructor() {
    assertThrows(NullPointerException.class, () -> new VolumeMeasure(15, null));
  }

  @Test
  void compare() {
    assertEquals(new VolumeMeasure(1, TABLESPOON), new VolumeMeasure(3, TEASPOON));
    assertEquals(new VolumeMeasure(1, OUNCE), new VolumeMeasure(2, TABLESPOON));
    assertEquals(new VolumeMeasure(1, CUP), new VolumeMeasure(8, OUNCE));
    assertEquals(new VolumeMeasure(1, PINT), new VolumeMeasure(2, CUP));
    assertEquals(new VolumeMeasure(1, QUART), new VolumeMeasure(2, PINT));
    assertEquals(new VolumeMeasure(1, GALLON), new VolumeMeasure(4, QUART));

    assertEquals(new VolumeMeasure(1, GALLON), new VolumeMeasure(768, TEASPOON));
    assertEquals(new VolumeMeasure(1, GALLON), new VolumeMeasure(256, TABLESPOON));
    assertEquals(new VolumeMeasure(1, GALLON), new VolumeMeasure(128, OUNCE));
    assertEquals(new VolumeMeasure(1, GALLON), new VolumeMeasure(16, CUP));
    assertEquals(new VolumeMeasure(1, GALLON), new VolumeMeasure(8, PINT));
    assertEquals(new VolumeMeasure(1, GALLON), new VolumeMeasure(4, QUART));
  }

  @Test
  void convert() {
    assertEquals(new VolumeMeasure(1, TABLESPOON), new VolumeMeasure(3, TEASPOON).convertTo(TABLESPOON));
    assertEquals(new VolumeMeasure(3, TEASPOON), new VolumeMeasure(1, TABLESPOON).convertTo(TEASPOON));

    assertEquals(new VolumeMeasure(1, GALLON), new VolumeMeasure(768, TEASPOON).convertTo(GALLON));
    assertEquals(new VolumeMeasure(768, TEASPOON), new VolumeMeasure(1, GALLON).convertTo(TEASPOON));

    assertEquals(new VolumeMeasure(0.5, CUP), new VolumeMeasure(4, OUNCE).convertTo(CUP));

  }

  @Test
  void testHashCode() {
    assertEquals(new VolumeMeasure(4, TEASPOON).hashCode(), new VolumeMeasure(4, TEASPOON).hashCode());

    assertEquals(new VolumeMeasure(1, TABLESPOON).hashCode(), new VolumeMeasure(3, TEASPOON).hashCode());

    assertTrue(new HashSet<>(Collections.singleton(new VolumeMeasure(1, TABLESPOON))).contains(new VolumeMeasure(1, TABLESPOON)));
    assertTrue(new HashSet<>(Collections.singleton(new VolumeMeasure(1, TABLESPOON))).contains(new VolumeMeasure(3, TEASPOON)));
  }

  @Test
  void hashSet() {
    final HashSet<VolumeMeasure> hashSet = new HashSet<>();
    hashSet.add(new VolumeMeasure(16, OUNCE));
    hashSet.add(new VolumeMeasure(16, OUNCE));
    hashSet.add(new VolumeMeasure(2, CUP));

    assertEquals(1, hashSet.size());
  }

  @Test
  void add() {
    assertEquals(new VolumeMeasure(2, TABLESPOON), new VolumeMeasure(1, TABLESPOON).add(new VolumeMeasure(3, TEASPOON)));
  }

  @Test
  void subtract() {
    assertEquals(new VolumeMeasure(3, QUART), new VolumeMeasure(4, QUART).subtract(new VolumeMeasure(2, PINT)));
  }
}
