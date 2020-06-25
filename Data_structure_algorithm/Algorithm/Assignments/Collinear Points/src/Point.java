/******************************************************************************
 *  Compilation:  javac Point.java
 *  Execution:    java Point
 *  Dependencies: none
 *
 *  An immutable data type for points in the plane.
 *  For use on Coursera, Algorithms Part I programming assignment.
 *
 ******************************************************************************/

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

public class Point implements Comparable<Point> {

    private final int x;     // x-coordinate of this point
    private final int y;     // y-coordinate of this point

    /**
     * Initializes a new point.
     *
     * @param x the <em>x</em>-coordinate of the point
     * @param y the <em>y</em>-coordinate of the point
     */
    public Point(int x, int y) {
        /* DO NOT MODIFY */
        this.x = x;
        this.y = y;
    }

    /**
     * Draws this point to standard draw.
     */
    public void draw() {
        /* DO NOT MODIFY */
        StdDraw.point(x, y);
    }

    /**
     * Draws the line segment between this point and the specified point
     * to standard draw.
     *
     * @param that the other point
     */
    public void drawTo(Point that) {
        /* DO NOT MODIFY */
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    /**
     * Returns the slope between this point and the specified point.
     * Formally, if the two points are (x0, y0) and (x1, y1), then the slope
     * is (y1 - y0) / (x1 - x0). For completeness, the slope is defined to be
     * +0.0 if the line segment connecting the two points is horizontal;
     * Double.POSITIVE_INFINITY if the line segment is vertical;
     * and Double.NEGATIVE_INFINITY if (x0, y0) and (x1, y1) are equal.
     *
     * @param that the other point
     * @return the slope between this point and the specified point
     */
    public double slopeTo(Point that) {
        // check if they are same point
        if ((this.x == that.x) && (this.y == that.y)) {
            return Double.NEGATIVE_INFINITY;
        }
        // check if they are horizontal
        if (this.y == that.y) {
            return +0;
        }
        // check if they are vertical
        else if (this.x == that.x) {
            return Double.POSITIVE_INFINITY;
        }
        // return the slope
        else {
            double tempy = (that.y - this.y);
            double tempx = (that.x - this.x);
            return tempy / tempx;
        }
    }

    /**
     * Compares two points by y-coordinate, breaking ties by x-coordinate.
     * Formally, the invoking point (x0, y0) is less than the argument point
     * (x1, y1) if and only if either y0 < y1 or if y0 = y1 and x0 < x1.
     *
     * @param that the other point
     * @return the value <tt>0</tt> if this point is equal to the argument
     * point (x0 = x1 and y0 = y1);
     * a negative integer if this point is less than the argument
     * point; and a positive integer if this point is greater than the
     * argument point
     */
    @Override
    public int compareTo(Point that) {
        if ((this.x == that.x) && (this.y == that.y)) {
            return 0;
        } else if ((this.y < that.y) || ((this.y == that.y) && (this.x < that.x))) {
            return -1;
        } else {
            return 1;
        }
    }

    /**
     * Compares two points by the slope they make with this point.
     * The slope is defined as in the slopeTo() method.
     *
     * @return the Comparator that defines this ordering on points
     */
    public static Comparator<Point> slopeOrder() {
        class SLOPEORDER implements Comparator<Point> {
            public int compare(Point v, Point w) {
                return v.compareTo(w);
            }
        }
        return new SLOPEORDER();
    }


    /**
     * Returns a string representation of this point.
     * This method is provide for debugging;
     * your program should not rely on the format of the string representation.
     *
     * @return a string representation of this point
     */
    public String toString() {
        /* DO NOT MODIFY */
        return "(" + x + ", " + y + ")";
    }

    /**
     * Unit tests the Point data type.
     */
    public static void main(String[] args) {
        Point test_point_1 = new Point(1,1);
        Point test_point_2 = new Point(2,2);
        Point test_point_3 = new Point(2,2); // same as 2
        Point test_point_4 = new Point(1,0); // vertical to 1
        Point test_point_5 = new Point(0,1); // horizontal to 1

        StdOut.println("slope");
        StdOut.println(test_point_1.slopeTo(test_point_2));

        StdOut.println("slope same");
        StdOut.println(test_point_2.slopeTo(test_point_3));

        StdOut.println("slope vertical");
        StdOut.println(test_point_1.slopeTo(test_point_4));

        StdOut.println("slope horizontal");
        StdOut.println(test_point_1.slopeTo(test_point_5));

        Point[] testArrays = new Point[5];
        testArrays[0] = test_point_1;
        testArrays[1] = test_point_2;
        testArrays[2] = test_point_3;
        testArrays[3] = test_point_4;
        testArrays[4] = test_point_5;

        for(Point i : testArrays){
            StdOut.println(i);
        }

        Arrays.sort(testArrays,Point.slopeOrder());

        StdOut.println("Sorted");

        for(Point i : testArrays){
            StdOut.println(i);
        }

    }
}