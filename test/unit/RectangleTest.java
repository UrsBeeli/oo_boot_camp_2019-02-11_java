/*
 * Copyright (c) 2019 by Fred George
 * May be used freely except for training; license required for training.
 */

package unit;

import org.junit.jupiter.api.Test;
import rectangle.Rectangle;

import static org.junit.jupiter.api.Assertions.*;

// Ensures Rectangle operates correctly
class RectangleTest {

    @Test void area() {
        assertEquals(24.0, new Rectangle(4.0, 6.0).area());
    }

    @Test() void notNegative(){
        new Rectangle(-1, 0);
    }
}
