package graph;

public abstract class Path {
  Path prepend(final Link link) {
    return this;
  }

  abstract int hops();

  abstract double cost();
}
