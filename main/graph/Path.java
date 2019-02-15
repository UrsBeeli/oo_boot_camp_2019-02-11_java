package graph;

public abstract class Path {
  Path prepend(final Link link) {
    return this;
  }

  public abstract int hops();

  public abstract double cost();
}
