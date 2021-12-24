import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;


public class PercolationStats {

    private double[] xs;
    private int T;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials){

        if (n <= 0 || trials <= 0){
            throw new IllegalArgumentException("n ≤ 0 or trials ≤ 0");
        }

        T = trials;
        xs = new double[T];

        for (int i = 0; i < T; i++){
            Percolation trail = new Percolation(n);
            while (!trail.percolates()){
                int randomRow = StdRandom.uniform(1, n + 1);
                int randomCol = StdRandom.uniform(1, n + 1);
                trail.open(randomRow, randomCol);
            }
            double openSites = trail.numberOfOpenSites();
            double xi = openSites / (n*n);
            xs[i] = xi;
        }
    }

    // sample mean of percolation threshold
    public double mean(){
        return StdStats.mean(xs);
    }

    // sample standard deviation of percolation threshold
    public double stddev(){
        return StdStats.stddev(xs);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo(){
        return mean() - 1.96 * stddev() / Math.sqrt(T);
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi(){
        return mean() + 1.96 * stddev() / Math.sqrt(T);
    }

    // test client (see below)
    public static void main(String[] args){

        int n = Integer.parseInt(args[0]);
        int trials = Integer.parseInt(args[1]);

        PercolationStats test = new PercolationStats(n, trials);

        String conf = "[" + test.confidenceLo() + ", " + test.confidenceHi() + "]";

        StdOut.println("mean                    = " + test.mean());
        StdOut.println("stddev                  = " + test.stddev());
        StdOut.println("95% confidence interval = " + conf);
    }

}