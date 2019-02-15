package unit;

import graph.Node;
import graph.Path;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GraphTest {
  private Node a = new Node();
  private Node b = new Node();
  private Node c = new Node();
  private Node d = new Node();
  private Node e = new Node();
  private Node f = new Node();
  private Node g = new Node();

  {
    b.addPathTo(a, 5);
    b.addPathTo(c, 6);
    b.addPathTo(f, 4);

    c.addPathTo(d, 7);
    c.addPathTo(d, 1);
    c.addPathTo(e, 8);

    d.addPathTo(e, 2);

    e.addPathTo(b, 3);

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

    assertEquals(2, c.hopCount(b));
    assertEquals(1, c.hopCount(e));
    assertEquals(3, c.hopCount(f));
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
    assertEquals(6, c.cost(b));
    assertEquals(10, c.cost(f));
  }

  @Test
  void path() {
    assertThrows(IllegalArgumentException.class, () -> g.path(a));
    final Path path = c.path(f);
    assertEquals(4, path.hopCount());
    assertEquals(10, path.cost());
  }

  @Test
  void paths() {
    assertEquals(0, g.paths(a).size());
    assertEquals(0, b.paths(g).size());

    List<Path> paths = g.paths(g);
    assertEquals(1, paths.size());
    assertExistsPath(0, 0, paths);

    paths = c.paths(d);
    assertEquals(2, paths.size());
    assertExistsPath(1, 1, paths);
    assertExistsPath(1, 7, paths);

    paths = c.paths(e);
    assertEquals(3, paths.size());
    assertExistsPath(1, 8, paths);
    assertExistsPath(2, 3, paths);
    assertExistsPath(2, 9, paths);

    paths = c.paths(f);
    assertEquals(3, paths.size());
    assertExistsPath(3, 15, paths);
    assertExistsPath(4, 10, paths);
    assertExistsPath(4, 16, paths);
  }

  private void assertExistsPath(final int hopCount, final double cost, final List<Path> paths) {
    assertTrue(paths.stream()
         .anyMatch(path -> path.cost() == cost && path.hopCount() == hopCount));
  }
}
