import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.Stopwatch;

import java.util.ArrayList;

public class PercolationStats {
    private ArrayList<Percolation> percolationList;
    private int N;
    private int trails;
    private double mean;
    private double stddev;
    private double confidenceLo;
    private double confidenceHi;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) throws IllegalArgumentException {
        // initialize the variables
        this.N = n;
        this.trails = trials;
        this.percolationList = new ArrayList<>();
        this.mean = 0;
        this.stddev = 0;
        this.confidenceLo = this.confidenceHi = 0;
        if (this.N <= 0 || this.trails <= 0) {
            throw new IllegalArgumentException();
        }

        // run trails times simulation
        for (int i = 0; i < this.trails; i++) {
            Percolation onePercolation = new Percolation(this.N);
            this.runOneTrals(onePercolation);
//            while (onePercolation.percolates()) {
//                int row = StdRandom.uniform(this.N);
//                int col = StdRandom.uniform(this.N);
//                onePercolation.open(row, col);
//            }
            this.percolationList.add(onePercolation);
        }

        // calculate the threshold mean
        double[] openSizeList = new double[this.trails];
        int count = 0;
        for (Percolation currentPercolation : this.percolationList) {
            openSizeList[count] = currentPercolation.numberOfOpenSites()/Math.pow(this.N,2);
            count++;
        }
        this.mean = StdStats.mean(openSizeList);

        // calculate the stddev
        this.stddev = StdStats.stddev(openSizeList);

        // calculate the confidenceLo
        this.confidenceLo = this.mean - ((1.96 * Math.sqrt(this.stddev)) / Math.sqrt(this.trails));

        // calculate the confidenceHi
        this.confidenceHi = this.mean + ((1.96 * Math.sqrt(this.stddev)) / Math.sqrt(this.trails));


    }

    private void runOneTrals(Percolation currentGrid) {
        int low = 1;
        int high = this.N + 1;
        while (!currentGrid.percolates()) {
            int row = StdRandom.uniform(low, high);
            int col = StdRandom.uniform(low, high);
            currentGrid.open(row, col);
        }
    }

    // sample mean of percolation threshold
    public double mean() {
        return this.mean;
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return this.stddev;

    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return this.confidenceLo;

    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return this.confidenceHi;

    }

//    public void display(int n) {
//        // display ramdom n grid
//        for (int i = 0; i < n; i++) {
//            int gridId = StdRandom.uniform(this.trails);
//            this.percolationList.get(gridId).display();
//            StdOut.println("********************************************");
//            StdOut.println("********************************************");
//        }
//    }

    // test client (see below)
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int trials = Integer.parseInt(args[1]);
//        int n = 200;
//        int trials = 100;
//        Stopwatch stopwatch = new Stopwatch();
        PercolationStats test = new PercolationStats(n,trials);
//        StdOut.printf("time cost:%f s\n",stopwatch.elapsedTime());
        StdOut.printf("mean\t\t\t\t\t = %f\n", test.mean());
        StdOut.printf("stddev\t\t\t\t\t = %f\n", test.stddev());
        StdOut.printf("95%% confidence interval\t = [-%f, %f]\n", test.confidenceLo(),test.confidenceHi());

//        test.display(3);


    }
}
