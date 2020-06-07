import edu.princeton.cs.algs4.StdOut;

public class Percolation {
    private boolean[][] grid;
    private int N;
    private int openSize;

    private int[] id; // Union-find tree-based data structure. N+2. After N id, the first extra one represent top, the second one represent bottom
    private int[] sz; // Union-find extra memory to store the size of tree

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) throws IllegalArgumentException {
        // check if n is  valid
        if (n < 0) {
            throw new IllegalArgumentException();
        }
        this.N = n;
        this.openSize = 0;
        this.grid = new boolean[n][n];

        this.id = new int[n * n + 2];
        this.sz = new int[n * n + 2];
        this.id[n * n] = n * n;
        this.id[n * n + 1] = n * n + 1;
        this.sz[n * n] = 1; // top of the grid
        this.sz[n * n + 1] = 1; // bottom of the grid

        for (int i = 0; i < this.N; i++) {
            for (int j = 0; j < this.N; j++) {
                this.grid[i][j] = false; // block all cell
                this.id[i * n + j] = i * n + j; // initialize id
                this.sz[i * n + j] = 1; // initialize size of each tree
            }
        }

    }

    // Weighted Union-find methods
    private int root(int i) {
        // return the root of i
        while (this.id[i] != i) {
            i = id[i];
        }
        return i;
    }

    // Weighted Union-find methods
    private boolean find(int p, int q) {
        // check if p and q have the same root
        return this.root(p) == this.root(q);
    }

    // Weighted Union-find methods
    private void union(int p, int q) {
        // connected p and q
        int rootP = this.root(p);
        int rootQ = this.root(q);

        if (rootP == rootQ) {
            return;
        }

        if (this.sz[rootP] > this.sz[rootQ]) {
            // add Q tree to P tree
            this.id[rootQ] = rootP;
            this.sz[rootQ] += this.sz[rootP];
        } else {
            // add P tree to Q tree
            this.id[rootP] = rootQ;
            this.sz[rootP] += this.sz[rootQ];
        }
    }

    private void connectedOpenSurroundings(int row, int col) {
        // connect surround of input site
        int convertRow = row - 1;
        int convertCol = col - 1;
        // check if row and col are valid
        if ((convertRow < 0 || convertRow > this.N) || (convertCol < 0 || convertCol > this.N)) {
            throw new IllegalArgumentException();
        }
        int inputSiteId = convertRow * this.N + convertCol;

        // connect input site surroundings
        // connect top
        int topConvertRow = convertRow - 1;
        int topConvertCol = convertCol;
        int topSiteId = topConvertRow * this.N + topConvertCol;
        if (topConvertRow > 0 && this.isOpen(row - 1, col)) {
            // top site of input site is a normal site
            this.union(inputSiteId, topSiteId);
        } else if (topConvertRow == 0) {
            // input site is the top of the grid
            this.union(inputSiteId, this.N * this.N);
        }

        // connect left
        int leftConvertRow = convertRow;
        int leftConvertCol = convertCol - 1;
        int leftSiteId = leftConvertRow * this.N + leftConvertCol;
        if (leftConvertCol >= 0 && this.isOpen(row, col - 1)) {
            // left site of input site is inside the grid
            this.union(inputSiteId, leftSiteId);
        }

        int bottomConvertRow = convertRow + 1;
        int bottomConvertCol = convertCol;
        int bottomSiteId = bottomConvertRow * this.N + bottomConvertCol;
        if (bottomConvertRow < this.N && this.isOpen(row + 1, col)) {
            // bottom site of input site is inside the grid
            this.union(inputSiteId, bottomSiteId);
        } else if (bottomConvertRow == this.N) {
            // input site is the bottom of the grid
            this.union(inputSiteId, this.N * this.N + 1);
        }

        int rightConvertRow = convertRow;
        int rightConvertCol = convertCol + 1;
        int rigthSiteId = rightConvertRow * this.N + rightConvertCol;
        if (rightConvertCol < this.N && this.isOpen(row, col + 1)) {
            // right site of input site is inside the grid
            this.union(inputSiteId, rigthSiteId);
        }
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) throws IllegalArgumentException {
        int convertRow = row - 1;
        int convertCol = col - 1;
        // check if row and col are valid
        if ((convertRow < 0 || convertRow > this.N) || (convertCol < 0 || convertCol > this.N)) {
            throw new IllegalArgumentException();
        }
        if (!this.grid[convertRow][convertCol]) {
            // if the site is not open
            this.grid[convertRow][convertCol] = true; // open that cell
            this.connectedOpenSurroundings(row, col);
            this.openSize++;
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        int convertRow = row - 1;
        int convertCol = col - 1;
        // check if row and col are valid
        if ((convertRow < 0 || convertRow > this.N) || (convertCol < 0 || convertCol > this.N)) {
            throw new IllegalArgumentException();
        }
        return this.grid[convertRow][convertCol];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        // this is actually checking if the site is connected to the top.
        int convertRow = row - 1;
        int convertCol = col - 1;
        // check if row and col are valid
        if ((convertRow < 0 || convertRow > this.N) || (convertCol < 0 || convertCol > this.N)) {
            throw new IllegalArgumentException();
        }
        int topSiteId = this.N * this.N;
        int inputSiteId = convertRow * this.N + convertCol;
        return this.find(inputSiteId, topSiteId);
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return this.openSize;

    }

    // does the system percolate?
    public boolean percolates() {
        int topSiteId = this.N * this.N;
        int bottomSiteId = this.N * this.N + 1;
        return this.find(topSiteId, bottomSiteId);

    }

    public void display() {
        for (int i = 0; i < this.N; i++) {
            for (int j = 0; j < this.N; j++) {
                if (!this.grid[i][j]) {
                    StdOut.print(" - ");
                } else {
                    StdOut.print(" | ");
                }
            }
            StdOut.println();
        }
    }

    // test client (optional)
    public static void main(String[] args) {
        Percolation test = new Percolation(10);
        StdOut.println("<--test 2-by-2 grid -->");
        StdOut.println("The open size of current grid");
        StdOut.println(test.numberOfOpenSites());

        StdOut.println("Open (1,1) and (2,1)");
        test.open(1, 1);
        test.open(2, 1);
        StdOut.println("The open size of current grid");
        StdOut.println(test.numberOfOpenSites());

        StdOut.println("If current grid percolation");
        StdOut.println(test.percolates());

        test.display();
    }
}
