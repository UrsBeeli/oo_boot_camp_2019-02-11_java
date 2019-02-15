package unit;

import finder.Comparable;
import finder.FlexibleDoubleFinder;
import org.junit.jupiter.api.Test;
import probability.Probability;
import rectangle.Rectangle;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static measures.Length.CHAIN;
import static measures.Length.FOOT;
import static measures.Length.FURLONG;
import static measures.Volume.CUP;
import static measures.Volume.OUNCE;
import static measures.Volume.PINT;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SortingTest {
  @Test
  void findLargestRectangleByComparable() {
    final List<Rectangle> collection = Collections.emptyList();
    assertNull(Comparable.findBest(collection));

    assertEquals(100,
        Comparable.findBest(Arrays.asList(
            new Rectangle(1, 1),
            new Rectangle(10, 10),
            new Rectangle(10, 9)
        )).area());
  }

  @Test
  void findExtremesOfRectangle() {
    assertNull(FlexibleDoubleFinder.findLargestByDouble(Collections.emptyList(), Rectangle::area));

    assertEquals(49,
        FlexibleDoubleFinder.findLargestByDouble(Arrays.asList(
            new Rectangle(2, 2),
            new Rectangle(1, 40),
            new Rectangle(7, 7),
            new Rectangle(1, 3)
        ), Rectangle::area).area());

    assertEquals(82,
        FlexibleDoubleFinder.findLargestByDouble(Arrays.asList(
            new Rectangle(2, 2),
            new Rectangle(1, 40),
            new Rectangle(7, 7),
            new Rectangle(1, 3)
        ), Rectangle::circumference).circumference());

    assertEquals(3,
        FlexibleDoubleFinder.findSmallestByDouble(Arrays.asList(
            new Rectangle(2, 2),
            new Rectangle(1, 40),
            new Rectangle(7, 7),
            new Rectangle(1, 3)
        ), Rectangle::area).area());

    assertEquals(8,
        FlexibleDoubleFinder.findSmallestByDouble(Arrays.asList(
            new Rectangle(2, 2),
            new Rectangle(1, 40),
            new Rectangle(7, 7),
            new Rectangle(1, 3)
        ), Rectangle::circumference).circumference());
  }

  @Test
  void findMostProbable() {
    assertEquals(new Probability(0.94),
        Comparable.findBest(Arrays.asList(
            new Probability(0.4),
            new Probability(0),
            new Probability(0.72),
            new Probability(0.94),
            new Probability(0.86)
        )));
  }

  @Test
  void findLargestQuantity() {
    assertEquals(CUP.s(5),
        Comparable.findBest(Arrays.asList(
            CUP.s(5),
            PINT.s(1),
            OUNCE.s(11)
        )));

    assertEquals(CHAIN.s(2),
        Comparable.findBest(Arrays.asList(
            FOOT.s(5),
            CHAIN.s(2),
            FURLONG.s(0.1)
        )));
  }
}
