package unit;

import measures.Measure;
import org.junit.jupiter.api.Test;

import static measures.Measure.Unit.CUP;
import static measures.Measure.Unit.GALLON;
import static measures.Measure.Unit.OUNCE;
import static measures.Measure.Unit.PINT;
import static measures.Measure.Unit.QUART;
import static measures.Measure.Unit.TABLE_SPOON;
import static measures.Measure.Unit.TEA_SPOON;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MeasureTest {

  @Test
  void constructor() {
    assertThrows(NullPointerException.class, () -> new Measure(15, null));
  }

  @Test
  void compare() {
    assertEquals(new Measure(1, TABLE_SPOON), new Measure(3, Measure.Unit.TEA_SPOON));
    assertEquals(new Measure(1, OUNCE), new Measure(2, TABLE_SPOON));
    assertEquals(new Measure(1, CUP), new Measure(8, OUNCE));
    assertEquals(new Measure(1, PINT), new Measure(2, CUP));
    assertEquals(new Measure(1, QUART), new Measure(2, PINT));
    assertEquals(new Measure(1, GALLON), new Measure(4, QUART));
  }

  @Test
  void convert(){
    assertEquals(new Measure(1, TABLE_SPOON), new Measure(3, TEA_SPOON).convertTo(TABLE_SPOON));
    assertEquals(new Measure(3, TEA_SPOON), new Measure(1, TABLE_SPOON).convertTo(TEA_SPOON));

    assertEquals(new Measure(1, GALLON), new Measure(768, TEA_SPOON).convertTo(GALLON));
    assertEquals(new Measure(768, TEA_SPOON), new Measure(1, GALLON).convertTo(TEA_SPOON));
  }

  @Test
  void add() {
    assertEquals(new Measure(1, TABLE_SPOON), new Measure(1, TEA_SPOON).add(new Measure(2, TEA_SPOON), TABLE_SPOON));
  }
}
