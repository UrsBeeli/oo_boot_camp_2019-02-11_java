package unit;

import graph.Node;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GraphTest {
  Node a = new Node();
  Node b = new Node();
  Node c = new Node();
  Node d = new Node();
  Node e = new Node();
  Node f = new Node();
  Node g = new Node();

  {
    b.addPathTo(a, 5, 0.5);
    b.addPathTo(c, 6, 0.5);
    b.addPathTo(f, 4, 0.6);

    c.addPathTo(d, 7, 0.2);
    c.addPathTo(d, 1, 0.4);
    c.addPathTo(e, 8, 0.1);

    d.addPathTo(e, 2, 0.05);

    e.addPathTo(b, 3, 0.7);
  }

  @Test
  void graph() {
    assertTrue(a.canReach(a));
    assertFalse(a.canReach(b));
    assertFalse(a.canReach(c));
    assertFalse(a.canReach(d));
    assertFalse(a.canReach(e));
    assertFalse(a.canReach(f));
    assertFalse(a.canReach(g));

    assertTrue(b.canReach(a));
    assertTrue(b.canReach(b));
    assertTrue(b.canReach(c));
    assertTrue(b.canReach(d));
    assertTrue(b.canReach(e));
    assertTrue(b.canReach(f));
    assertFalse(b.canReach(g));

    assertTrue(c.canReach(a));
    assertTrue(c.canReach(b));
    assertTrue(c.canReach(c));
    assertTrue(c.canReach(d));
    assertTrue(c.canReach(e));
    assertTrue(c.canReach(f));
    assertFalse(c.canReach(g));

    assertTrue(d.canReach(a));
    assertTrue(d.canReach(b));
    assertTrue(d.canReach(c));
    assertTrue(d.canReach(d));
    assertTrue(d.canReach(e));
    assertTrue(d.canReach(f));
    assertFalse(d.canReach(g));

    assertTrue(e.canReach(b));
    assertTrue(e.canReach(a));
    assertTrue(e.canReach(c));
    assertTrue(e.canReach(d));
    assertTrue(e.canReach(e));
    assertTrue(e.canReach(f));
    assertFalse(e.canReach(g));

    assertFalse(f.canReach(a));
    assertFalse(f.canReach(b));
    assertFalse(f.canReach(c));
    assertFalse(f.canReach(d));
    assertFalse(f.canReach(e));
    assertTrue(f.canReach(f));
    assertFalse(f.canReach(g));

    assertFalse(g.canReach(a));
    assertFalse(g.canReach(b));
    assertFalse(g.canReach(c));
    assertFalse(g.canReach(d));
    assertFalse(g.canReach(e));
    assertFalse(g.canReach(f));
    assertTrue(g.canReach(g));
  }

  @Test
  void hop() {
    assertEquals(0, a.hopCount(a));
    assertThrows(IllegalArgumentException.class, () -> a.hopCount(g));
    assertEquals(1, b.hopCount(f));
    assertEquals(3, d.hopCount(c));

    // min hops
    assertEquals(2, c.hopCount(b));
    assertEquals(1, c.hopCount(e));
    assertEquals(3, c.hopCount(f));

    // max hops
    // assertEquals(3, c.hopCount(b));
    // assertEquals(2, c.hopCount(e));

  }

  @Test
  void cost() {
    assertThrows(IllegalArgumentException.class, () -> g.cost(a));
    assertEquals(0, g.cost(g));
    assertEquals(5, b.cost(a));
    assertEquals(6, b.cost(c));
    assertEquals(7, b.cost(d));
    assertEquals(9, b.cost(e));
    assertEquals(1, c.cost(d));
    assertEquals(3, c.cost(e));
    assertEquals(2, d.cost(e));
    assertEquals(6, c.cost(b));
    assertEquals(10, c.cost(f));
  }

  @Test
  void gradient() {
    assertThrows(IllegalArgumentException.class, () -> g.gradient(a));
    assertEquals(0, g.gradient(g));
    assertEquals(0.5, b.gradient(a));
    assertEquals(0.5, b.gradient(c));
    assertEquals(0.5, b.gradient(d));
    assertEquals(0.5, b.gradient(e));
    assertEquals(0.2, c.gradient(d));
    assertEquals(0.05, d.gradient(e));
    assertEquals(0.1, c.gradient(e));
    assertEquals(0.7, c.gradient(b));
    assertEquals(0.7, c.gradient(f));
  }
}
