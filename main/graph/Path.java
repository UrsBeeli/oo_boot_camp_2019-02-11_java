package graph;

import java.util.Comparator;

public abstract class Path {
  static final Comparator<Path> COST_COMPARATOR = Comparator.comparingDouble(Path::cost);
  static final Comparator<Path> HOP_COUNT_COMPARATOR = Comparator.comparingInt(Path::hops);

  Path prepend(final Link link) {
    return this;
  }

  public abstract int hops();

  public abstract double cost();
}
