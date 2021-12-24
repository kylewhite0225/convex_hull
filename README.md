# Convex Hull Builder

A convex hull is a series of line segments that surround a cluster of points, which is constructed 
in this program by use of an algorithm called Graham Scan.

Graham Scan selects the point with the minimum y value, then sorts all of the points in counter-clockwise order using
polar order. Then the algorithm iterates through the list of points and adds any point to the hull that creates
a counter-clockwise turn. Any points that create a clockwise turn are removed from the hull. This generates the surrounding line segments.
