/*
 * Copyright (c) 2019 by Fred George
 * May be used freely except for training; license required for training.
 */

package unit;

import org.junit.jupiter.api.Test;
import rectangle.Rectangle;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

// Ensures Rectangle operates correctly
class RectangleTest {

    @Test void area() {
        assertEquals(24.0, new Rectangle(4.0, 6.0).area());
    }

    @Test()
    void notNullNegative() {
        assertThrows(IllegalArgumentException.class, () -> new Rectangle(-1, 5));
        assertThrows(IllegalArgumentException.class, () -> new Rectangle(1, -5));

        assertThrows(IllegalArgumentException.class, () -> Rectangle.square(-5));

        assertThrows(IllegalArgumentException.class, () -> new Rectangle(3, 0));
        assertThrows(IllegalArgumentException.class, () -> new Rectangle(0, 5));
        assertThrows(IllegalArgumentException.class, () -> Rectangle.square(0));

    }

    @Test
    void circumference() {
        assertEquals(30, new Rectangle(10, 5).circumference());
    }

    @Test
    void square() {
        assertEquals(16, Rectangle.square(4).area());
        assertEquals(25, Rectangle.square(5).area());

        assertEquals(16, Rectangle.square(4).circumference());
        assertEquals(20, Rectangle.square(5).circumference());
    }


}
