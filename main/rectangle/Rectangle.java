package rectangle;

public class Rectangle {

  private final double height;
  private final double width;

  public Rectangle(final double height, final double width) {
    if (height <= 0 || width <= 0) {
      throw new IllegalArgumentException("Parameter(s) must be positive numbers");
    }

    this.height = height;
    this.width = width;
  }

  public double area() {
    return height * width;
  }

  public double circumference() {
    return 2 * (height + width);
  }

  public static Rectangle square(double length) {
    return new Rectangle(length, length);
  }
}
