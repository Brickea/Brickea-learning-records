import edu.princeton.cs.algs4.StdOut;
import java.util.Arrays;
import java.util.Comparator;

/*************************************************************************
 *  Project: Collinear Points
 *  Dependencies: LineSegment, Point
 *  Author: Zixiao Wang
 *  Create: 6/24/20
 *  Description: A faster, sorting-based solution. Remarkably, it is possible to solve the problem much faster than the brute-force solution
 *  * Think of p as the origin.
 *  * For each other point q, determine the slope it makes with p.
 *  * Sort the points according to the slopes they makes with p.
 *  * Check if any 3 (or more) adjacent points in the sorted order have equal slopes with respect to p. If so, these points, together with p, are collinear.
 *************************************************************************/
public class FastCollinearPoints {
    private LineSegment lineSegment[] = null;
    private int lineSegmentNumber = 0;

    /**
     * Finds all line segments containing 4 or more points
     *
     * @param points
     * @throws IllegalArgumentException if the argument to the constructor is null, if any point in the array is null,
     *                                  or if the argument to the constructor contains a repeated point.
     */
    public FastCollinearPoints(Point[] points) {
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
        // initialize linesegment
        this.lineSegment = new LineSegment[points.length / 4];

        // find collinear points.
        for (int p = 0; p < points.length; p++) {
            // Think of p as the origin.
            Point[] pointQs = new Point[points.length - p - 1];
            final int currentP = p;
            int tempIndex = 0;
            for (int q = p + 1; q < points.length; q++) {
                // For each other point q, determine the slope it makes with p.
                pointQs[tempIndex++] = points[q];
            }

            // Sort the points according to the slopes they makes with p.
            Arrays.sort(pointQs, new Comparator<Point>() {
                @Override
                public int compare(Point o1, Point o2) {
                    return Double.compare(points[currentP].slopeTo(o1), points[currentP].slopeTo(o2));
                }
            });

            // Check if any 3 (or more) adjacent points in the sorted order have equal slopes with respect to p.

            int i = 0, j = 0;
            Point[] currentSlopePoints = new Point[pointQs.length];
            while (true) {
                if (points[currentP].slopeTo(pointQs[i]) == points[currentP].slopeTo(pointQs[j])) {
                    currentSlopePoints[j] = pointQs[j];
                    j++;
                }
                else if(points[currentP].slopeTo(pointQs[i]) != points[currentP].slopeTo(pointQs[j])){
                    if(j-i+1 >= 4){
                        // If so, these points, together with p, are collinear.
                        Point[] temp = Arrays.copyOfRange(currentSlopePoints,i,j);
                        Arrays.sort(temp,Point.slopeOrder());
                        this.lineSegment[this.lineSegmentNumber++] = new LineSegment(temp[i],temp[j-1]);
                    }
                    i = j++;
                }
                if(j==pointQs.length) break;
            }
        }
    }

    /**
     * Return the number of line segments
     *
     * @return the number of line segments
     */
    public int numberOfSegments(){
        return this.lineSegmentNumber;
    }

    /**
     * Return all line segments
     *
     * @return LineSegment[]
     */
    public LineSegment[] segments(){
        return Arrays.copyOfRange(this.lineSegment,0,this.lineSegmentNumber);
    }

    public static void main(String[] args) {
        StdOut.println("test");
        Point[] testP = new Point[10];
        for(int i=0;i<10;i++){
            testP[i] = new Point(i,i);
        }

        FastCollinearPoints testF = new FastCollinearPoints(testP);
        for(LineSegment i :testF.segments()){
            StdOut.println(i);
        }
    }
}
