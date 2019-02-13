package finder;

import java.util.Collection;
import java.util.function.Function;

public class FlexibleDoubleFinder {

  public static <T> T findSmallestByDouble(final Collection<T> collection, Function<T, Double> doubleSupplier) {
    return findLargestByDouble(collection, t -> -1 * doubleSupplier.apply(t));
  }

  public static <T> T findLargestByDouble(final Collection<T> collection, Function<T, Double> doubleSupplier) {
    T largest = null;
    for (T t : collection) {
      if (largest == null || doubleSupplier.apply(t) > doubleSupplier.apply(largest)) {
        largest = t;
      }
    }
    return largest;
  }

}
