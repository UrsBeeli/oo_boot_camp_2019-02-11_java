package graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.joining;

public class Path {
  private List<Link> links = new ArrayList<>();

  public Path() {
  }

  public void addLink(final Link link) {
    links.add(0, link);
  }

  int compareTo(Path other) {
    return Double.compare(Link.totalPathLength(this.links), Link.totalPathLength(other.links));
  }

  int hops() {
    return links.size();
  }

  double cost() {
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
