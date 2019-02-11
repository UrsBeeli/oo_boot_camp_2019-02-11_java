package unit;

import jdk.nashorn.internal.ir.annotations.Ignore;
import org.junit.jupiter.api.Test;
import probability.Probability;

import java.util.Collections;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ProbabilityTest {

  @Test
  void constructorOutOfRange() {
    assertThrows(IllegalArgumentException.class, () -> new Probability(-3));
    assertThrows(IllegalArgumentException.class, () -> new Probability(1.000005));
  }

  @Test
  void testEquals() {
    assertEquals(new Probability(0.25), new Probability(0.25));
    assertNotEquals(new Probability(0.25), new Probability(0.75));
    assertNotEquals(new Probability(0.25), new Object());
    assertNotEquals(new Probability(0.25), null);
  }

  @Test
  void testHashCode() {
    //assertEquals(new Probability(0.25).hashCode(), new Probability(0.25).hashCode());
    assertTrue(new HashSet<Probability>(Collections.singleton(new Probability(0.45))).contains(new Probability(0.45)));
  }

  @Test
  void hashSet() {
    final HashSet<Probability> hashSet = new HashSet<>();
    hashSet.add(new Probability(0.82));
    hashSet.add(new Probability(0.82));

    assertEquals(1, hashSet.size());
  }

//  @Test
//  void testLikelyhood() {
//    assertFalse(new Probability(0.567).isMoreLikely(new Probability(0.8)));
//    assertTrue(new Probability(0.567).isMoreLikely(new Probability(0.2)));
//
//    assertFalse(new Probability(0.567).isLessLikely(new Probability(0.8)));
//    assertTrue(new Probability(0.567).isLessLikely(new Probability(0.2)));
//  }
//
//  @Ignore
//  @Test
//  void testProbabilityAnd(){
//    assertEquals(new Probability(0.24), new Probability(0.4).and(new Probability(0.6)));
//    assertEquals(new Probability(0.06), new Probability(0.4).and(new Probability(0.5)).and(new Probability(0.3)));
//  }
//
//  @Ignore
//  @Test
//  void testProbabilityOr(){
//    assertEquals(new Probability(0.68), new Probability(0.6).or(new Probability(0.2)));
//  }
//
//  @Ignore
//  @Test
//  void testNot() {
//    assertEquals(new Probability(0.25), new Probability(0.75).not());
//  }
//
//
}
