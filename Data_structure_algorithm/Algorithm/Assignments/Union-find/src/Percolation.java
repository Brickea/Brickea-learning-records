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
        this.sz[n * n] = 1;
        this.sz[n * n + 1] = 1;

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
        int convert_row = row - 1;
        int convert_col = col - 1;
        // check if row and col are valid
        if ((convert_row < 0 || convert_row > this.N) || (convert_col < 0 || convert_col > this.N)) {
            throw new IllegalArgumentException();
        }
        int inputSiteId = convert_row * this.N + convert_col;


        // <-- TODO -->>

        int topRow = convert_row - 1;
        int topCol = convert_col;
        int topSiteId = topRow * this.N + topCol;

        if (topRow > 0 && this.isOpen(row - 1, col)) {
            // top is a normal site
            // this.union();
        }

        int leftRow = convert_row;
        int leftCol = convert_col - 1;

        int bottomRow = convert_row + 1;
        int bottomCol = convert_col;

        int rightRow = convert_row;
        int rightCol = convert_col + 1;

    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) throws IllegalArgumentException {
        int convert_row = row - 1;
        int convert_col = col - 1;
        // check if row and col are valid
        if ((convert_row < 0 || convert_row > this.N) || (convert_col < 0 || convert_col > this.N)) {
            throw new IllegalArgumentException();
        }
        this.grid[convert_row][convert_col] = true; // open that cell

        this.openSize++;
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        int convert_row = row - 1;
        int convert_col = col - 1;
        // check if row and col are valid
        if ((convert_row < 0 || convert_row > this.N) || (convert_col < 0 || convert_col > this.N)) {
            throw new IllegalArgumentException();
        }
        return this.grid[convert_row][convert_col];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        // this is actually checking if the site is connected to the top.
        int convert_row = row - 1;
        int convert_col = col - 1;
        // check if row and col are valid
        if ((convert_row < 0 || convert_row > this.N) || (convert_col < 0 || convert_col > this.N)) {
            throw new IllegalArgumentException();
        }
        int topSiteId = this.N * this.N;
        int inputSiteId = convert_row * this.N + convert_col;
        return this.find(topSiteId, inputSiteId);
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return this.openSize;

    }

    // does the system percolate?
    public boolean percolates() {
        return false;

    }

    // test client (optional)
    public static void main(String[] args) {
        Percolation test = new Percolation(2);
    }
}
