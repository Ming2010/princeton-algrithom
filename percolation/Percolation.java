import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;


public class Percolation {

    private int nOpenSites = 0;
    private int n;
    private boolean[][] grid;
    private WeightedQuickUnionUF uf;
    private WeightedQuickUnionUF ufFull;
    private int virtualTop;
    private int virtualBottom;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n){

        if (n <= 0){
            throw new IllegalArgumentException("N can not be <= 0");
        }

        this.n = n;
        grid = new boolean[n][n];
        uf = new WeightedQuickUnionUF(n*n + 2);
        ufFull = new WeightedQuickUnionUF(n*n + 1);
        virtualTop = 0;
        virtualBottom = n*n + 1;

    }

    private boolean valid(int row, int col){
        return row <= this.n && col <= this.n && row > 0 && col > 0;
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col){

        // check if it is valid
        if (!valid(row, col)){
            throw new IllegalArgumentException("row and col must inside the prescribed range");
        }

        // if it is not open, open it
        if (!isOpen(row, col)){
            nOpenSites += 1;
            grid[row-1][col-1] = true;
            int cur = (row - 1) * n + col;
            int left = (row - 1) * n + col - 1;
            int right = (row - 1) * n + col + 1;
            int up = (row - 2) * n + col;
            int down = row * n + col;

            // link to top if it is in the first row
            if (row == 1){
                uf.union(cur, virtualTop);
                ufFull.union(cur, virtualTop);
            }
            // link to the bottom if it is in the last row
            if (row == n){
                uf.union(cur, virtualBottom);
            }

            // union if open
            if (valid(row-1, col) && isOpen(row-1, col)){//up
                uf.union(cur, up);
                ufFull.union(cur, up);
            }
            if (valid(row, col-1) && isOpen(row, col-1)){//left
                uf.union(cur, left);
                ufFull.union(cur, left);
            }
            if (valid(row+1, col) && isOpen(row+1, col)){//down
                uf.union(cur, down);
                ufFull.union(cur, down);
            }
            if (valid(row, col+1) && isOpen(row, col+1)){//right
                uf.union(cur, right);
                ufFull.union(cur, right);
            }

        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col){

        if (!valid(row, col)){
            throw new IllegalArgumentException("row and col must inside the prescribed range");
        }

        return grid[row-1][col-1];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col){

        if (!valid(row, col)){
            throw new IllegalArgumentException("row and col must inside the prescribed range");
        }
        int cur = (row - 1) * n + col;

        return ufFull.find(cur) == ufFull.find(virtualTop);

    }

    // returns the number of open sites
    public int numberOfOpenSites(){
        return nOpenSites;
    }

    // does the system percolate?
    public boolean percolates(){
        return uf.find(virtualBottom) == uf.find(virtualTop);
    }

    // test client (optional)
    public static void main(String[] args){
    }
}