package unit;

import measures.Quantity;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.HashSet;

import static measures.Unit.CUP;
import static measures.Unit.GALLON;
import static measures.Unit.INCH;
import static measures.Unit.OUNCE;
import static measures.Unit.PINT;
import static measures.Unit.QUART;
import static measures.Unit.TABLESPOON;
import static measures.Unit.TEASPOON;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class VolumeMeasureTest {

  @Test
  void constructor() {
    assertThrows(NullPointerException.class, () -> new Quantity(15, null));
  }

  @Test
  void compare() {
    assertEquals(new Quantity(1, TABLESPOON), new Quantity(3, TEASPOON));
    assertEquals(new Quantity(1, OUNCE), new Quantity(2, TABLESPOON));
    assertEquals(new Quantity(1, CUP), new Quantity(8, OUNCE));
    assertEquals(new Quantity(1, PINT), new Quantity(2, CUP));
    assertEquals(new Quantity(1, QUART), new Quantity(2, PINT));
    assertEquals(new Quantity(1, GALLON), new Quantity(4, QUART));

    assertEquals(new Quantity(1, GALLON), new Quantity(768, TEASPOON));
    assertEquals(new Quantity(1, GALLON), new Quantity(256, TABLESPOON));
    assertEquals(new Quantity(1, GALLON), new Quantity(128, OUNCE));
    assertEquals(new Quantity(1, GALLON), new Quantity(16, CUP));
    assertEquals(new Quantity(1, GALLON), new Quantity(8, PINT));
    assertEquals(new Quantity(1, GALLON), new Quantity(4, QUART));
  }

  @Test
  void convert() {
    assertEquals(new Quantity(1, TABLESPOON), new Quantity(3, TEASPOON).convertTo(TABLESPOON));
    assertEquals(new Quantity(3, TEASPOON), new Quantity(1, TABLESPOON).convertTo(TEASPOON));

    assertEquals(new Quantity(1, GALLON), new Quantity(768, TEASPOON).convertTo(GALLON));
    assertEquals(new Quantity(768, TEASPOON), new Quantity(1, GALLON).convertTo(TEASPOON));

    assertEquals(new Quantity(0.5, CUP), new Quantity(4, OUNCE).convertTo(CUP));

  }

  @Test
  void testHashCode() {
    assertEquals(new Quantity(4, TEASPOON).hashCode(), new Quantity(4, TEASPOON).hashCode());

    assertEquals(new Quantity(1, TABLESPOON).hashCode(), new Quantity(3, TEASPOON).hashCode());

    assertTrue(new HashSet<>(Collections.singleton(new Quantity(1, TABLESPOON))).contains(new Quantity(1, TABLESPOON)));
    assertTrue(new HashSet<>(Collections.singleton(new Quantity(1, TABLESPOON))).contains(new Quantity(3, TEASPOON)));
  }

  @Test
  void hashSet() {
    final HashSet<Quantity> hashSet = new HashSet<>();
    hashSet.add(new Quantity(16, OUNCE));
    hashSet.add(new Quantity(16, OUNCE));
    hashSet.add(new Quantity(2, CUP));

    assertEquals(1, hashSet.size());
  }

  @Test
  void mixedHashSet() {
    final HashSet<Quantity> hashSet = new HashSet<>();
    hashSet.add(new Quantity(3, INCH));
    hashSet.add(new Quantity(3, TEASPOON));

    // even with equal hashValues, equals() is still used, so this works. phew!
    assertEquals(2, hashSet.size());
  }

  @Test
  void add() {
    assertEquals(new Quantity(2, TABLESPOON), new Quantity(1, TABLESPOON).add(new Quantity(3, TEASPOON)));
  }

  @Test
  void subtract() {
    assertEquals(new Quantity(3, QUART), new Quantity(4, QUART).subtract(new Quantity(2, PINT)));
  }
}
