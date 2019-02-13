package unit;

import graph.Node;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GraphTest {
  Node a;
  Node b;
  Node c;
  Node d;
  Node e;
  Node f;
  Node g;

  @BeforeEach
  void setup() {
    a = new Node();
    b = new Node();
    c = new Node();
    d = new Node();
    e = new Node();
    f = new Node();
    g = new Node();

    b.addPathTo(a);
    b.addPathTo(c);
    b.addPathTo(f);

    c.addPathTo(d);
    c.addPathTo(d);
    c.addPathTo(e);

    d.addPathTo(e);

    e.addPathTo(b);
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
}
