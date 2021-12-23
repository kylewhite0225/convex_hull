import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.Stack;
import java.util.ArrayList;
import java.util.Arrays;

/*
Author: Kyle White
Course: CS401 Algorithms
Date: 5/14/2021
 */

/*
The ConvexHullBuilder class accepts an ArrayList of Point2D objects and scans through
the list every time the GUI accepts a mouse click. The constructor builds a new hull
every time a point is added to the list, by scanning through the list, sorting the
points by Y-height and angle relative to the minimum point, and adding any point
that makes a counter-clockwise turn around the perimeter of points to the hull which
is returned as a Stack of Point2D objects.
 */

public class ConvexHullBuilder {
    private Stack<Point2D> hull = new Stack<>();

    /*
    The constructor accepts an ArrayList of Point2D objects,
    creates the ConvexHull by sorting the list of points and
    checking for counter-clockwise status, and adding them to
    the hull member of the ConvexHullBuilder class.
    @param pointList    A list of Point2D objects
     */

    public ConvexHullBuilder(ArrayList<Point2D> pointList) {
        int size = pointList.size();

        // Copy pointList into Point2D array
        Point2D[] array = new Point2D[size];
        for (int i = 0; i < size; i++) {
            array[i] = pointList.get(i);
        }

        // Sort array by y value - a[0] becomes the lower most point.
        Arrays.sort(array);

        // Create minimum point minY
        Point2D minY = array[0];

        // Sort array from index 1 to size by angle with
        // respect to minimum point and horizontal using
        // Point2D polarOrder method
        Arrays.sort(array, 1, size, minY.polarOrder());

        // minY is the lower most point, push it onto the stack.
        hull.push(minY);

        // For the first mouse click, the pointList only has 1 element in it.
        // If size < 2, the first mouse click requires no further work.
        if (size < 2) {
            return;
        }

        // Iterate through array
        for (int i = 1; i < size-1; i++) {
            // Grab the next point in the stack
            Point2D nextPoint = array[i];
            // Grab the point after that
            Point2D nextNextPoint = array[i+1];
            // Check if the point at the top of the stack, nextPoint, and nextNextPoint
            // create a clockwise angle or are co-linear.
            while (hull.size() != 0 && Point2D.ccw(hull.peek(), nextPoint, nextNextPoint) <= 0) {
                // If clockwise or co-linear, drop nextPoint and set it back to the top of the stack.
                nextPoint = hull.pop();
            }
            // Push nextPoint onto the stack
            hull.push(nextPoint);
            // Push nextNextPoint onto the stack
            hull.push(nextNextPoint);
        }
    }

    /*
    The hull method iterates through the hull member of the ConvexHullBuilder
    class, pushes every point onto a Stack of Point2D objects, then returns that stack.
    @return stack   The stack of Point2D objects within hull.
     */

    public Iterable<Point2D> hull() {
        Stack<Point2D> stack = new Stack<>();
        for(Point2D point : hull) stack.push(point);
        return stack;
    }

    /*
    Getter method for accessing the hull member of the ConvexHullBuilder object.
    @return hull    The private hull member of the ConvexHullBuilder class.
     */

    public Stack<Point2D> getHull() {
        return hull;
    }
}