package graph;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Path {
  static final Comparator<Path> COST_COMPARATOR = Comparator.comparingDouble(Path::cost);
  static final Comparator<Path> HOP_COUNT_COMPARATOR = Comparator.comparingInt(Path::hopCount);

  private List<Link> links = new ArrayList<>();

  Path() {
  }

  Path prepend(final Link link) {
    links.add(0, link);
    return this;
  }

  public int hopCount() {
    return links.size();
  }

  public double cost() {
    return Link.totalPathLength(links);
  }
}
