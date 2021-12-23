import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.Stack;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;


/*
Author: Kyle White
Course: CS401 Algorithms
Date: 5/14/2021
 */

/*
The ConvexHullBuilderTest class has an initialization method that builds a hull using the ConvexHullBuilder
class, then extracts the resultant stack using the getter method within ConvexHullBuilder.
The two test methods then create a new hull each with a point outside the original hull, and
inside the original hull respectively. A new point outside the hull should alter the hull and their comparisons
should produce a false boolean. A new point inside the hull should not alter the hull, and their comparisons
should produce a true boolean.
 */

public class ConvexHullBuilderTest {
    static ConvexHullBuilder hull;
    static ArrayList<Point2D> list;
    static Stack<Point2D> stack;
    static Point2D a;
    static Point2D b;
    static Point2D c;
    static Point2D d;

    // Build hull with known set of points
    @BeforeAll
    static void init() {
        list = new ArrayList<Point2D>();
        list.add(a = new Point2D(10.0, 5.0));
        list.add(b = new Point2D(10.0, 10.0));
        list.add(c = new Point2D(5.0, 10.0));
        list.add(d = new Point2D(5.0, 5.0));
        hull = new ConvexHullBuilder(list);
        stack = hull.getHull();
    }

    // Build a new hull with another point outside of the hull - if it changes to include that point, then it passes
    @Test
    void testOutsideHull() {
        Point2D outside;
        ArrayList<Point2D> newList = new ArrayList<Point2D>();
        newList.add(a = new Point2D(10.0, 5.0));
        newList.add(b = new Point2D(10.0, 10.0));
        newList.add(c = new Point2D(5.0, 10.0));
        newList.add(d = new Point2D(5.0, 5.0));
        newList.add(outside = new Point2D(12.5, 7.5));
        ConvexHullBuilder newHull = new ConvexHullBuilder(newList);
        Stack<Point2D> newStack;
        newStack = newHull.getHull();

        boolean actual = stack.equals(newStack);
        boolean expected = false;
        assertEquals(expected, actual);
    }

    // Build a new hull with another point inside of the hull - if it does not change, then it passes
    @Test
    void testInsideHull() {
        Point2D inside;
        ArrayList<Point2D> newList = new ArrayList<Point2D>();
        newList.add(a = new Point2D(10.0, 5.0));
        newList.add(b = new Point2D(10.0, 10.0));
        newList.add(c = new Point2D(5.0, 10.0));
        newList.add(d = new Point2D(5.0, 5.0));
        newList.add(inside = new Point2D(7.5, 7.5));
        ConvexHullBuilder newHull = new ConvexHullBuilder(newList);
        Stack<Point2D> newStack;
        newStack = hull.getHull();

        boolean actual = stack.equals(newStack);
        boolean expected = true;
        assertEquals(expected, actual);
    }
}
