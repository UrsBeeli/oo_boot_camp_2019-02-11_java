package unit;

import measures.Quantity;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.HashSet;

import static measures.Unit.CELSIUS;
import static measures.Unit.CUP;
import static measures.Unit.FAHRENHEIT;
import static measures.Unit.GALLON;
import static measures.Unit.INCH;
import static measures.Unit.OUNCE;
import static measures.Unit.PINT;
import static measures.Unit.QUART;
import static measures.Unit.TABLESPOON;
import static measures.Unit.TEASPOON;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TemperatureMeasureTest {

  @Test
  void constructor() {
    assertThrows(NullPointerException.class, () -> new Quantity(15, null));
  }

  @Test
  void compare() {
    assertEquals(new Quantity(0, CELSIUS), new Quantity(32, FAHRENHEIT));
    assertEquals(new Quantity(10, CELSIUS), new Quantity(50, FAHRENHEIT));
    assertEquals(new Quantity(100, CELSIUS), new Quantity(212, FAHRENHEIT));
    assertEquals(new Quantity(-40, CELSIUS), new Quantity(-40, FAHRENHEIT));
    assertNotEquals(new Quantity(-40, CELSIUS), null);
  }

  @Test
  void convert() {
    assertEquals(new Quantity(32, FAHRENHEIT), new Quantity(0, CELSIUS).convertTo(FAHRENHEIT));
    assertEquals(new Quantity(0, CELSIUS), new Quantity(32, FAHRENHEIT).convertTo(CELSIUS));
  }

  @Test
  void testHashCode() {
    assertEquals(new Quantity(0, CELSIUS).hashCode(), new Quantity(32, FAHRENHEIT).hashCode());
    assertEquals(new Quantity(-40, CELSIUS).hashCode(), new Quantity(-40, FAHRENHEIT).hashCode());

    assertTrue(new HashSet<>(Collections.singleton(new Quantity(13, CELSIUS))).contains(new Quantity(13, CELSIUS)));
    assertTrue(new HashSet<>(Collections.singleton(new Quantity(451, FAHRENHEIT))).contains(new Quantity(451, FAHRENHEIT)));
  }

  @Test
  void hashSet() {
    final HashSet<Quantity> hashSet = new HashSet<>();
    hashSet.add(new Quantity(32, FAHRENHEIT));
    hashSet.add(new Quantity(32, FAHRENHEIT));
    hashSet.add(new Quantity(0, CELSIUS));

    assertEquals(1, hashSet.size());
  }

  @Test
  void mixedHashSet() {
    final HashSet<Quantity> hashSet = new HashSet<>();
    hashSet.add(new Quantity(3, INCH));
    hashSet.add(new Quantity(3, TEASPOON));
    hashSet.add(new Quantity(3, CELSIUS));

    // even with equal hashValues, equals() is still used, so this works. phew!
    assertEquals(3, hashSet.size());
  }

  @Test
  void add() {
    assertEquals(new Quantity(10, FAHRENHEIT), new Quantity(-40, FAHRENHEIT).add(new Quantity(10, CELSIUS)));
  }

  @Test
  void subtract() {
    assertEquals(new Quantity(162, FAHRENHEIT), new Quantity(212, FAHRENHEIT).subtract(new Quantity(10, CELSIUS)));
  }

  @Test
  void mixedAddition() {
    assertThrows(IllegalArgumentException.class, () -> new Quantity(451, FAHRENHEIT).add(new Quantity(9, INCH)));
  }
}
