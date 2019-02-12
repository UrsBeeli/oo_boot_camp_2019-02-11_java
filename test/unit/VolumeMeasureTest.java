package unit;

import measures.Quantity;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.HashSet;

import static measures.Length.INCH;
import static measures.Volume.CUP;
import static measures.Volume.GALLON;
import static measures.Volume.OUNCE;
import static measures.Volume.PINT;
import static measures.Volume.QUART;
import static measures.Volume.TABLESPOON;
import static measures.Volume.TEASPOON;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class VolumeMeasureTest {
  @Test
  void compare() {
    assertEquals(TABLESPOON.s(1), TEASPOON.s(3));
    assertEquals(OUNCE.s(1), TABLESPOON.s(2));
    assertEquals(CUP.s(1), OUNCE.s(8));
    assertEquals(PINT.s(1), CUP.s(2));
    assertEquals(QUART.s(1), PINT.s(2));
    assertEquals(GALLON.s(1), QUART.s(4));

    assertEquals(GALLON.s(1), TEASPOON.s(768));
    assertEquals(GALLON.s(1), TABLESPOON.s(256));
    assertEquals(GALLON.s(1), OUNCE.s(128));
    assertEquals(GALLON.s(1), CUP.s(16));
    assertEquals(GALLON.s(1), PINT.s(8));
    assertEquals(GALLON.s(1), QUART.s(4));
  }

  @Test
  void convert() {
    assertEquals(TABLESPOON.s(1), TEASPOON.s(3).convertTo(TABLESPOON));
    assertEquals(TEASPOON.s(3), TABLESPOON.s(1).convertTo(TEASPOON));

    assertEquals(GALLON.s(1), TEASPOON.s(768).convertTo(GALLON));
    assertEquals(TEASPOON.s(768), GALLON.s(1).convertTo(TEASPOON));

    assertEquals(CUP.s(0.5), OUNCE.s(4).convertTo(CUP));

  }

  @Test
  void testHashCode() {
    assertEquals(TEASPOON.s(4).hashCode(), TEASPOON.s(4).hashCode());
    assertEquals(TABLESPOON.s(1).hashCode(), TEASPOON.s(3).hashCode());
    assertTrue(new HashSet<>(Collections.singleton(TABLESPOON.s(1))).contains(TABLESPOON.s(1)));
    assertTrue(new HashSet<>(Collections.singleton(TABLESPOON.s(1))).contains(TEASPOON.s(3)));
  }

  @Test
  void hashSet() {
    final HashSet<Quantity> hashSet = new HashSet<>();
    hashSet.add(OUNCE.s(16));
    hashSet.add(OUNCE.s(16));
    hashSet.add(CUP.s(2));

    assertEquals(1, hashSet.size());
  }

  @Test
  void mixedHashSet() {
    final HashSet<Quantity> hashSet = new HashSet<>();
    hashSet.add(INCH.s(3));
    hashSet.add(TEASPOON.s(3));

    // even with equal hashValues, equals() is still used, so this works. phew!
    assertEquals(2, hashSet.size());
  }

  @Test
  void add() {
    assertEquals(TABLESPOON.s(2), TABLESPOON.s(1).add(TEASPOON.s(3)));
  }

  @Test
  void subtract() {
    assertEquals(QUART.s(3), QUART.s(4).subtract(PINT.s(2)));
  }
}
