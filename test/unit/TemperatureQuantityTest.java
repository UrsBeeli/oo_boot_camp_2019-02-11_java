package unit;

import measures.Area;
import measures.Length;
import measures.MultiplyableQuantity;
import measures.Quantity;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.HashSet;

import static measures.Temperature.CELSIUS;
import static measures.Temperature.FAHRENHEIT;
import static measures.Length.INCH;
import static measures.Temperature.KELVIN;
import static measures.Volume.TEASPOON;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TemperatureQuantityTest {

  @Test
  void compare() {
    assertEquals(CELSIUS.s(0), FAHRENHEIT.s(32));
    assertEquals(CELSIUS.s(10), FAHRENHEIT.s(50));
    assertEquals(CELSIUS.s(100), FAHRENHEIT.s(212));
    assertEquals(CELSIUS.s(-40), FAHRENHEIT.s(-40));
    assertNotEquals(CELSIUS.s(-40), null);

    assertEquals(CELSIUS.s(-273), KELVIN.s(0));
    assertEquals(CELSIUS.s(0), KELVIN.s(273));
  }

  @Test
  void convert() {
    assertEquals(FAHRENHEIT.s(32), CELSIUS.s(0).convertTo(FAHRENHEIT));
    assertEquals(CELSIUS.s(0), FAHRENHEIT.s(32).convertTo(CELSIUS));
  }

  @Test
  void testHashCode() {
    assertEquals(CELSIUS.s(0).hashCode(), FAHRENHEIT.s(32).hashCode());
    assertEquals(CELSIUS.s(-40).hashCode(), FAHRENHEIT.s(-40).hashCode());

    assertTrue(new HashSet<>(Collections.singleton(CELSIUS.s(13))).contains(CELSIUS.s(13)));
    assertTrue(new HashSet<>(Collections.singleton(FAHRENHEIT.s(451))).contains(FAHRENHEIT.s(451)));
  }

  @Test
  void hashSet() {
    final HashSet<Quantity> hashSet = new HashSet<>();
    hashSet.add(FAHRENHEIT.s(32));
    hashSet.add(FAHRENHEIT.s(32));
    hashSet.add(CELSIUS.s(0));

    assertEquals(1, hashSet.size());
  }

  @Test
  void mixedHashSet() {
    final HashSet<Quantity> hashSet = new HashSet<>();
    hashSet.add(INCH.s(3));
    hashSet.add(TEASPOON.s(3));
    hashSet.add(CELSIUS.s(3));

    // even with equal hashValues, equals() is still used, so this works. phew!
    assertEquals(3, hashSet.size());
  }
}
