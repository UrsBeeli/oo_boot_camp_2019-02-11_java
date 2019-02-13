package unit;

import graph.Node;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
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
    b.addPathTo(a);
    b.addPathTo(c).addPathTo(d).addPathTo(e).addPathTo(b);
    b.addPathTo(f);
    c.addPathTo(d);
    c.addPathTo(e);
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
    assertNull(a.hopCount(g));
    assertEquals(1, b.hopCount(f));
    assertEquals(3, d.hopCount(c));

    Integer hops = c.hopCount(b);
    assertTrue(hops == 2 || hops == 3);

    hops = c.hopCount(e);
    assertTrue(hops == 1 || hops == 2);

  }
}
