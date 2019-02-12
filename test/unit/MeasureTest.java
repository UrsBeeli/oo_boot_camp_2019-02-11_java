package unit;

import measures.Measure;
import org.junit.jupiter.api.Test;
import probability.Probability;

import java.util.Collections;
import java.util.HashSet;

import static measures.Unit.CUP;
import static measures.Unit.GALLON;
import static measures.Unit.OUNCE;
import static measures.Unit.PINT;
import static measures.Unit.QUART;
import static measures.Unit.TABLESPOON;
import static measures.Unit.TEASPOON;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MeasureTest {

  @Test
  void constructor() {
    assertThrows(NullPointerException.class, () -> new Measure(15, null));
  }

  @Test
  void compare() {
    assertEquals(new Measure(1, TABLESPOON), new Measure(3, TEASPOON));
    assertEquals(new Measure(1, OUNCE), new Measure(2, TABLESPOON));
    assertEquals(new Measure(1, CUP), new Measure(8, OUNCE));
    assertEquals(new Measure(1, PINT), new Measure(2, CUP));
    assertEquals(new Measure(1, QUART), new Measure(2, PINT));
    assertEquals(new Measure(1, GALLON), new Measure(4, QUART));

    assertEquals(new Measure(1, GALLON), new Measure(768, TEASPOON));
    assertEquals(new Measure(1, GALLON), new Measure(256, TABLESPOON));
    assertEquals(new Measure(1, GALLON), new Measure(128, OUNCE));
    assertEquals(new Measure(1, GALLON), new Measure(16, CUP));
    assertEquals(new Measure(1, GALLON), new Measure(8, PINT));
    assertEquals(new Measure(1, GALLON), new Measure(4, QUART));
  }

  @Test
  void convert(){
    assertEquals(new Measure(1, TABLESPOON), new Measure(3, TEASPOON).convertTo(TABLESPOON));
    assertEquals(new Measure(3, TEASPOON), new Measure(1, TABLESPOON).convertTo(TEASPOON));

    assertEquals(new Measure(1, GALLON), new Measure(768, TEASPOON).convertTo(GALLON));
    assertEquals(new Measure(768, TEASPOON), new Measure(1, GALLON).convertTo(TEASPOON));

    assertEquals(new Measure(0.5, CUP), new Measure(4, OUNCE).convertTo(CUP));

  }

  @Test
  void testHashCode() {
    assertEquals(new Measure(4, TEASPOON).hashCode(), new Measure(4, TEASPOON).hashCode());

    assertEquals(new Measure(1, TABLESPOON).hashCode(), new Measure(3, TEASPOON).hashCode());

    assertTrue(new HashSet<>(Collections.singleton(new Measure(1, TABLESPOON))).contains(new Measure(1, TABLESPOON)));
    assertTrue(new HashSet<>(Collections.singleton(new Measure(1, TABLESPOON))).contains(new Measure(3, TEASPOON)));
  }

  @Test
  void hashSet() {
    final HashSet<Probability> hashSet = new HashSet<>();
    hashSet.add(new Probability(0.82));
    hashSet.add(new Probability(0.82));

    assertEquals(1, hashSet.size());
  }


  @Test
  void add() {
    assertEquals(new Measure(1, TABLESPOON), new Measure(1, TEASPOON).add(new Measure(2, TEASPOON), TABLESPOON));
  }

  @Test
  void subtract() {
    assertEquals(new Measure(192, TABLESPOON), new Measure(1, GALLON).subtract(new Measure(2, PINT), TABLESPOON));
  }
}
