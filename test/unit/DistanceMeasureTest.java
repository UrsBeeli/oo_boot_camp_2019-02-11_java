package unit;

import measures.Area;
import measures.Quantity;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.HashSet;

import static measures.Area.SQUARE_FOOT;
import static measures.Length.CHAIN;
import static measures.Volume.CUP;
import static measures.Length.FOOT;
import static measures.Length.FURLONG;
import static measures.Length.INCH;
import static measures.Length.MILE;
import static measures.Length.YARD;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DistanceMeasureTest {

  @Test
  void compare() {
    assertEquals(FOOT.s(1), INCH.s(12));
    assertEquals(YARD.s(1), FOOT.s(3));
    assertEquals(CHAIN.s(1), YARD.s(22));
    assertEquals(FURLONG.s(1), CHAIN.s(10));
    assertEquals(MILE.s(1), FURLONG.s(8));

    assertEquals(MILE.s(1), INCH.s(63360));
    assertEquals(MILE.s(1), FOOT.s(5280));
    assertEquals(MILE.s(1), YARD.s(1760));
    assertEquals(MILE.s(1), CHAIN.s(80));
    assertEquals(MILE.s(1), FURLONG.s(8));
  }

  @Test
  void convert() {
    assertEquals(FOOT.s(1), INCH.s(12).convertTo(FOOT));
    assertEquals(INCH.s(12), FOOT.s(1).convertTo(INCH));

    assertEquals(MILE.s(1), INCH.s(63360).convertTo(MILE));
    assertEquals(INCH.s(63360), MILE.s(1).convertTo(INCH));

    assertEquals(CHAIN.s(0.5), YARD.s(11).convertTo(CHAIN));

  }

  @Test
  void testHashCode() {
    assertEquals(INCH.s(4).hashCode(), INCH.s(4).hashCode());
    assertEquals(FOOT.s(1).hashCode(), INCH.s(12).hashCode());

    assertTrue(new HashSet<>(Collections.singleton(FOOT.s(1))).contains(FOOT.s(1)));
    assertTrue(new HashSet<>(Collections.singleton(FOOT.s(1))).contains(INCH.s(12)));
  }

  @Test
  void hashSet() {
    final HashSet<Quantity> hashSet = new HashSet<>();
    hashSet.add(YARD.s(44));
    hashSet.add(YARD.s(44));
    hashSet.add(CHAIN.s(2));

    assertEquals(1, hashSet.size());
  }

  @Test
  void add() {
    assertEquals(FOOT.s(1.5), FOOT.s(1).add(INCH.s(6)));
    assertEquals(YARD.s(1), FOOT.s(1).add(INCH.s(24)));
  }

  @Test
  void subtract() {
    assertEquals(MILE.s(2), MILE.s(4).subtract(FURLONG.s(16)));
  }

  @Test
  void multiply() {
    assertEquals(SQUARE_FOOT.s(20), FOOT.s(4).multiply(FOOT.s(5)).convertTo(SQUARE_FOOT));
  }

//  @Test
//  void mixing() {
//    assertThrows(IllegalArgumentException.class, () -> FOOT.s(2).add(CUP.s(3))); // caught at compile time
//  }
}
