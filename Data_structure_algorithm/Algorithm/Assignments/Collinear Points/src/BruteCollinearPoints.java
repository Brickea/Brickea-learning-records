import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

/*************************************************************************
 *  Project: Collinear Points
 *  Dependencies: LineSegment, Point
 *  Author: Zixiao Wang
 *  Create: 6/24/20
 *  Description: Examines 4 points at a time and checks whether they all lie on the same line segment, returning all such line segments.
 *************************************************************************/
public class BruteCollinearPoints {
    private LineSegment[] lineSegment;
    private int lineSegmentLength = 0;

    /**
     * Calculate the factorial of a particular input number n
     *
     * @param number
     * @return the n!
     */
    private int factorial(int number) {
        if (number == 1) return 1;
        return number *= this.factorial(number - 1);
    }

    /**
     * Finds all line segments containing 4 points
     *
     * @param points a collection of point
     * @throws IllegalArgumentException if the argument to the constructor is null, if any point in the array is null,
     *                                  or if the argument to the constructor contains a repeated point.
     */
    public BruteCollinearPoints(Point[] points) throws IllegalArgumentException {

        // check the argument
        if (points == null) throw new IllegalArgumentException("points array is null!");
        for (int i = 0; i < points.length; i++) {
            if (points[i] == null) throw new IllegalArgumentException("some point in the points is null!");
        }
        for (int i = 0; i < points.length; i++) {
            for (int j = i + 1; j < points.length; j++) {
                if (points[j].compareTo(points[i]) == 0) throw new IllegalArgumentException("same point found!");
            }
        }
        // initialize the lineSegments
        int lineNumber = points.length / 4;
        this.lineSegment = new LineSegment[lineNumber];
        this.lineSegmentLength = 0;
        // find the collinear 4 points
        for (int i = 0; i < points.length; i++) {
            Point[] temp = new Point[4];
            temp[0] = points[i];
            for (int j = i + 1; j < points.length; j++) {
                temp[1] = points[j];
                for (int k = j + 1; k < points.length; k++) {
                    temp[2] = points[k];
                    for (int l = k + 1; l < points.length; l++) {
                        temp[3] = points[l];

                        double slope1 = points[i].slopeTo(points[j]);
                        double slope2 = points[j].slopeTo(points[k]);
                        double slope3 = points[k].slopeTo(points[l]);
                        if (slope1 == slope2 && slope2 == slope3) {
                            Arrays.sort(temp, Point.slopeOrder());
                            this.lineSegment[this.lineSegmentLength] = new LineSegment(temp[0], temp[3]);
                            this.lineSegmentLength++;
                        }
                    }
                }
            }
        }


    }

    /**
     * Return the number of line segments
     *
     * @return number of the segments
     */
    public int numberOfSegments() {
        return this.lineSegmentLength;

    }

    /**
     * The method segments() should include each line segment containing 4 points exactly once.
     * If 4 points appear on a line segment in the order p→q→r→s,
     * then you should include either the line segment p→s or s→p (but not both)
     * and you should not include subsegments such as p→r or q→r. For simplicity,
     * we will not supply any input to BruteCollinearPoints that has 5 or more collinear points.
     *
     * @return include each line segment containing 4 points exactly once
     */
    public LineSegment[] segments() {
        return Arrays.copyOfRange(this.lineSegment, 0, this.lineSegmentLength);
    }

    public static void main(String[] args) {
        // -------------------------------------------------------
//        StdOut.println("Test constructor's exception");
//        StdOut.println("null test");
//        Point[] testP = null;
//        BruteCollinearPoints testB = new BruteCollinearPoints(testP);
        // *******************************************************
//        StdOut.println("point null test");
//        Point[] testP = new Point[5];
//        for(int i=0;i<4;i++){
//            testP[i] = new Point(i,i);
//        }
//        testP[4] = null;
//        BruteCollinearPoints testB = new BruteCollinearPoints(testP);
        // *******************************************************
//        StdOut.println("same point test");
//        Point[] testP = new Point[5];
//        for(int i=0;i<4;i++){
//            testP[i] = new Point(i,i);
//        }
//        testP[4] = new Point(1,1);
//        BruteCollinearPoints testB = new BruteCollinearPoints(testP);
        // -------------------------------------------------------
//        StdOut.println("Collinear test");
//        Point[] testP = new Point[4];
//        for(int i=0;i<4;i++){
//            testP[i] = new Point(i,i);
//        }
//        BruteCollinearPoints testB = new BruteCollinearPoints(testP);
//        StdOut.println("Number");
//        StdOut.println(testB.numberOfSegments());
//        StdOut.println("Segments");
//        for(LineSegment i : testB.segments()){
//            StdOut.println(i);
//        }

    }
}
