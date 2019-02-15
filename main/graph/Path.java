package graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static java.util.stream.Collectors.joining;

public class Path {
  private final Link.WeightStrategy weightStrategy;
  private List<Link> links = new ArrayList<>();

  public Path(Link.WeightStrategy weightStrategy) {
    this.weightStrategy = weightStrategy;
  }

  public void prepend(final Link link) {
    links.add(0, link);
  }

  int compareTo(Path other) {
    return Double.compare(weightStrategy.weight(this.links), weightStrategy.weight(other.links));
  }

  public int hops() {
    return links.size();
  }

  public double cost() {
    return Link.totalPathLength(links);
  }

  @Override
  public boolean equals(final Object other) {
    if (this == other) return true;
    if (other == null || getClass() != other.getClass()) return false;
    final Path otherPath = (Path) other;
    return Objects.equals(links, otherPath.links);
  }

  @Override
  public int hashCode() {
    return Objects.hash(links);
  }

  @Override
  public String toString() {
    return "Path: ["+links.stream().map(Link::toString).collect(joining("]["))+"]";
  }
}
