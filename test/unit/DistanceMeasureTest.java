package unit;

import measures.Quantity;
import measures.Unit;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.HashSet;

import static measures.Unit.CHAIN;
import static measures.Unit.CUP;
import static measures.Unit.FOOT;
import static measures.Unit.FURLONG;
import static measures.Unit.INCH;
import static measures.Unit.MILE;
import static measures.Unit.SQUARE_INCH;
import static measures.Unit.YARD;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DistanceMeasureTest {

  @Test
  void constructor() {
    assertThrows(NullPointerException.class, () -> new Quantity(15, null));
  }

  @Test
  void compare() {
    assertEquals(new Quantity(1, FOOT), new Quantity(12, INCH));
    assertEquals(new Quantity(1, YARD), new Quantity(3, FOOT));
    assertEquals(new Quantity(1, CHAIN), new Quantity(22, YARD));
    assertEquals(new Quantity(1, FURLONG), new Quantity(10, CHAIN));
    assertEquals(new Quantity(1, MILE), new Quantity(8, FURLONG));

    assertEquals(new Quantity(1, MILE), new Quantity(63360, INCH));
    assertEquals(new Quantity(1, MILE), new Quantity(5280, FOOT));
    assertEquals(new Quantity(1, MILE), new Quantity(1760, YARD));
    assertEquals(new Quantity(1, MILE), new Quantity(80, CHAIN));
    assertEquals(new Quantity(1, MILE), new Quantity(8, FURLONG));
  }

  @Test
  void convert() {
    assertEquals(new Quantity(1, FOOT), new Quantity(12, INCH).convertTo(FOOT));
    assertEquals(new Quantity(12, INCH), new Quantity(1, FOOT).convertTo(INCH));

    assertEquals(new Quantity(1, MILE), new Quantity(63360, INCH).convertTo(MILE));
    assertEquals(new Quantity(63360, INCH), new Quantity(1, MILE).convertTo(INCH));

    assertEquals(new Quantity(0.5, CHAIN), new Quantity(11, YARD).convertTo(CHAIN));

  }

  @Test
  void testHashCode() {
    assertEquals(new Quantity(4, INCH).hashCode(), new Quantity(4, INCH).hashCode());

    assertEquals(new Quantity(1, FOOT).hashCode(), new Quantity(12, INCH).hashCode());

    assertTrue(new HashSet<>(Collections.singleton(new Quantity(1, FOOT))).contains(new Quantity(1, FOOT)));
    assertTrue(new HashSet<>(Collections.singleton(new Quantity(1, FOOT))).contains(new Quantity(12, INCH)));
  }

  @Test
  void hashSet() {
    final HashSet<Quantity> hashSet = new HashSet<>();
    hashSet.add(new Quantity(44, YARD));
    hashSet.add(new Quantity(44, YARD));
    hashSet.add(new Quantity(2, CHAIN));

    assertEquals(1, hashSet.size());
  }

  @Test
  void add() {
    assertEquals(new Quantity(1.5, FOOT), new Quantity(1, FOOT).add(new Quantity(6, INCH)));
    assertEquals(new Quantity(1, YARD), new Quantity(1, FOOT).add(new Quantity(24, INCH)));
  }

  @Test
  void subtract() {
    assertEquals(new Quantity(2, MILE), new Quantity(4, MILE).subtract(new Quantity(16, FURLONG)));
  }

  @Test
  void mixing() {
    assertThrows(IllegalArgumentException.class, () -> new Quantity(2, FOOT).add(new Quantity(3, CUP)));
  }
}
