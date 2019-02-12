package unit;

import measures.AreaMeasure;
import measures.AreaUnit;
import measures.DistanceMeasure;
import measures.VolumeMeasure;
import measures.VolumeUnit;
import org.junit.jupiter.api.Test;
import probability.Probability;

import java.util.Collections;
import java.util.HashSet;

import static measures.DistanceUnit.CHAIN;
import static measures.DistanceUnit.YARD;
import static measures.DistanceUnit.FURLONG;
import static measures.DistanceUnit.MILE;
import static measures.DistanceUnit.FOOT;
import static measures.DistanceUnit.INCH;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DistanceMeasureTest {

  @Test
  void constructor() {
    assertThrows(NullPointerException.class, () -> new DistanceMeasure(15, null));
  }

  @Test
  void compare() {
    assertEquals(new DistanceMeasure(1, FOOT), new DistanceMeasure(12, INCH));
    assertEquals(new DistanceMeasure(1, YARD), new DistanceMeasure(3, FOOT));
    assertEquals(new DistanceMeasure(1, CHAIN), new DistanceMeasure(22, YARD));
    assertEquals(new DistanceMeasure(1, FURLONG), new DistanceMeasure(10, CHAIN));
    assertEquals(new DistanceMeasure(1, MILE), new DistanceMeasure(8, FURLONG));

    assertEquals(new DistanceMeasure(1, MILE), new DistanceMeasure(63360, INCH));
    assertEquals(new DistanceMeasure(1, MILE), new DistanceMeasure(5280, FOOT));
    assertEquals(new DistanceMeasure(1, MILE), new DistanceMeasure(1760, YARD));
    assertEquals(new DistanceMeasure(1, MILE), new DistanceMeasure(80, CHAIN));
    assertEquals(new DistanceMeasure(1, MILE), new DistanceMeasure(8, FURLONG));
  }

  @Test
  void convert() {
    assertEquals(new DistanceMeasure(1, FOOT), new DistanceMeasure(12, INCH).convertTo(FOOT));
    assertEquals(new DistanceMeasure(12, INCH), new DistanceMeasure(1, FOOT).convertTo(INCH));

    assertEquals(new DistanceMeasure(1, MILE), new DistanceMeasure(63360, INCH).convertTo(MILE));
    assertEquals(new DistanceMeasure(63360, INCH), new DistanceMeasure(1, MILE).convertTo(INCH));

    assertEquals(new DistanceMeasure(0.5, CHAIN), new DistanceMeasure(11, YARD).convertTo(CHAIN));

  }

  @Test
  void testHashCode() {
    assertEquals(new DistanceMeasure(4, INCH).hashCode(), new DistanceMeasure(4, INCH).hashCode());

    assertEquals(new DistanceMeasure(1, FOOT).hashCode(), new DistanceMeasure(12, INCH).hashCode());

    assertTrue(new HashSet<>(Collections.singleton(new DistanceMeasure(1, FOOT))).contains(new DistanceMeasure(1, FOOT)));
    assertTrue(new HashSet<>(Collections.singleton(new DistanceMeasure(1, FOOT))).contains(new DistanceMeasure(12, INCH)));
  }

  @Test
  void hashSet() {
    final HashSet<DistanceMeasure> hashSet = new HashSet<>();
    hashSet.add(new DistanceMeasure(44, YARD));
    hashSet.add(new DistanceMeasure(44, YARD));
    hashSet.add(new DistanceMeasure(2, CHAIN));

    assertEquals(1, hashSet.size());
  }

  @Test
  void add() {
    assertEquals(new DistanceMeasure(1.5, FOOT), new DistanceMeasure(1, FOOT).add(new DistanceMeasure(6, INCH)));
    assertEquals(new DistanceMeasure(1, YARD), new DistanceMeasure(1, FOOT).add(new DistanceMeasure(24, INCH)));
  }

  @Test
  void subtract() {
    assertEquals(new DistanceMeasure(2, MILE), new DistanceMeasure(4, MILE).subtract(new DistanceMeasure(16, FURLONG)));
  }

  @Test
  void mixing() {
    // TODO: how do we stop allowing this?
    new DistanceMeasure(2, FOOT).add(new VolumeMeasure(3, VolumeUnit.CUP));
  }

  @Test
  void multiply() {
    assertEquals(new AreaMeasure(4, AreaUnit.SQUARE_INCH), new DistanceMeasure(2, INCH).multiply(new DistanceMeasure(2, INCH)));
  }
}
