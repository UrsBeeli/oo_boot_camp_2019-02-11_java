package graph;

import java.util.ArrayList;
import java.util.List;

public class ReachablePath extends Path {
  private List<Link> links = new ArrayList<>();

  ReachablePath() {
  }

  @Override
  Path prepend(final Link link) {
    links.add(0, link);
    return this;
  }

  @Override
  public int hopCount() {
    return links.size();
  }

  @Override
  public double cost() {
    return Link.totalPathLength(links);
  }
}
