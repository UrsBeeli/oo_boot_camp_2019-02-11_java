package unit;

import measures.Quantity;
import org.junit.jupiter.api.Test;

import static measures.Unit.INCH;
import static measures.Unit.SQUARE_FOOT;
import static measures.Unit.SQUARE_INCH;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AreaMesaureTest {
  @Test
  void add() {
    assertEquals(new Quantity(20.25, SQUARE_FOOT), new Quantity(20, SQUARE_FOOT).add(new Quantity(36, SQUARE_INCH)));
  }

//  @Test
//  void multiply() {
//    assertEquals(new Quantity(8, SQUARE_INCH), new Quantity(2, INCH).multiply(new Quantity(4, INCH)));
//  }
}
