package unit;

import measures.Quantity;
import org.junit.jupiter.api.Test;

import static measures.Area.SQUARE_FOOT;
import static measures.Area.SQUARE_INCH;
import static measures.Length.FOOT;
import static measures.Volume.CUBE_FOOT;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AreaQuantityTest {
  @Test
  void add() {
    assertEquals(SQUARE_FOOT.s(20.25), SQUARE_FOOT.s(20).add(SQUARE_INCH.s(36)));
  }

  @Test
  void multiply() {
    assertEquals(CUBE_FOOT.s(8), SQUARE_FOOT.s(2).multiply(FOOT.s(4)));
  }
}
